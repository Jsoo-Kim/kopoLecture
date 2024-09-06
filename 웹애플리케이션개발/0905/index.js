const path = require('path');
const http = require("http");

const express = require("express");
const app = express();
const server = http.createServer(app);
const { Server } = require("socket.io");  // Socket.IO 라이브러리에서 서버 객체를 불러옴
const io = new Server(server);  // HTTP 서버를 기반으로 Socket.IO 서버를 생성하고 이를 'io' 변수에 저장


// Express.js와 같은 Node.js 서버를 사용할 때, 정적 파일(CSS, JS, 이미지 등)을 올바르게 제공하기 위해 express.static 미들웨어를 사용
// 이 미들웨어를 통해 특정 디렉토리를 "정적 파일"로 서빙하도록 설정할 수 있음
app.use(express.static(path.join(__dirname, 'public')));


app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "/public/index.html"));
});



/*
- io.on() : 서버의 전역 이벤트 처리
  ex) 클라이언트가 서버에 처음 연결되었을 때 발생하는 connection 이벤트 처리

- socket.on() : 특정 클라이언트의 소켓 연결에 대한 이벤트 처리
  ex) 채팅 메시지, 특정 방으로의 참여, 개별 클라이언트가 보내는 데이터 처리 등
*/

io.on("connection", (socket) => {
  socket.on("new join room", (preJoinRoom, newJoinRoom, userName) => {
    // 소켓에 사용자 이름을 저장
    socket.name = userName;

    // 사용자가 새로운 방에 참여하도록 함 (특정 방에 소켓 추가)
    socket.join(newJoinRoom);

    // 사용자의 현재 방 이름을 소켓에 저장
    socket.room = newJoinRoom;

    // 새로 참여한 방에 있는 모든 클라이언트의 소켓 정보를 가져옴
    let clients = io.sockets.adapter.rooms.get(newJoinRoom);  

    /*
    - io.sockets: 서버에 연결된 모든 소켓 객체를 관리하는 Socket.IO의 소켓 컬렉션
    - adapter: Socket.IO에서 방(Rooms)과 네임스페이스를 관리하는 내부 객체 => 소켓 출입 정보 관리
    - rooms: 모든 방에 대한 정보를 담고 있는 맵(Map) => 각 방에 어떤 소켓들이 연결되어 있는지를 기록
    - get(preJoinRoom): preJoinRoom이라는 이름의 방에 연결된 모든 클라이언트의 소켓 ID를 Set 객체로 반환
      - `preJoinRoom` 은 방의 이름 또는 ID
    */

    // 방의 현재 사용자 목록과 사용자 수를 얻어옴
    const { currentChatRoomUserList, roomClientsNum } = getRoomInfo(clients);

    // 새로운 방의 모든 클라이언트에게 이벤트 전송
    io.to(newJoinRoom).emit(
      "notice",
      currentChatRoomUserList,
      roomClientsNum,
      socket.name,
      " 님이 들어오셨습니다"
    );

    // 사용자가 이전에 참여한 방이 있으면 
    if (preJoinRoom !== "") {
      // 이전 방을 떠남 (특정 방에서 소켓을 나가게 함)
      socket.leave(preJoinRoom);

      // 떠난 방에 남아 있는 클라이언트 정보를 가져옴
      let clients = io.sockets.adapter.rooms.get(preJoinRoom);

      // 이전 방의 현재 사용자 목록과 사용자 수를 얻어옴
      const { currentChatRoomUserList, roomClientsNum } = getRoomInfo(clients);

      // 이전 방의 모든 사용자에게 이벤트 전송
      io.to(preJoinRoom).emit(
        "notice",
        currentChatRoomUserList,
        roomClientsNum,
        socket.name,
        " 님이 나가셨습니다"
      );
    }
  });


  // 클라이언트가 'chat message' 이벤트를 발생시키면 실행되는 리스너로, 채팅 메시지를 보냄
  socket.on("chat message", (msg) => {
    socket.to(socket.room).emit("chat message", socket.name, msg);
  });


  // 사용자가 연결을 끊었을 때 실행되는 이벤트
  socket.on("disconnect", () => {
    // 사용자가 속해 있던 방의 클라이언트 정보를 가져옴
    let clients = io.sockets.adapter.rooms.get(socket.room);

    // 방의 현재 사용자 목록과 사용자 수를 얻어옴
    const { currentChatRoomUserList, roomClientsNum } = getRoomInfo(clients);

    // 모든 클라이언트에게 공지 메시지를 전송
    io.emit(
      "notice",
      currentChatRoomUserList,
      roomClientsNum,
      socket.name,
      "님이 나가셨습니다"
    );
  });
});


server.listen(3000, () => {
  console.log("listening on *:3000");
});


/* 함수 목록 */

// 방에 있는 클라이언트들의 정보를 수집하는 함수
function getRoomInfo(clients) {
  const roomClientsNum = clients ? clients.size : 0;

  const currentChatRoomUserList = [];

  if (clients) {
    clients.forEach((element) => {
      currentChatRoomUserList.push(io.sockets.sockets.get(element).name);
    });
  }

  /*
  - io.sockets: 서버에 연결된 모든 소켓 객체를 관리하는 Socket.IO의 소켓 컬렉션
  - sockets: 서버에 연결된 개별 소켓들(socket 객체)을 담고 있는 맵(Map) => 각 소켓은 고유한 ID로 구분
  - get(element): 특정 클라이언트(소켓)의 ID(element)로 소켓 객체를 가져옴
  - .name: 소켓에 연결된 클라이언트의 사용자 이름을 저장해 둔 속성에 접근
  */

  // {} 중괄호를 사용하여 객체 리터럴 문법으로 반환 (변수명이 key가 됨)
  return { roomClientsNum, currentChatRoomUserList };
}
