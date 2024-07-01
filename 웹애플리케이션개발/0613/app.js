const express = require('express'); // Express(웹 애플리케이션을 쉽게 개발할 수 있도록 도와주는 Node.js용 웹 프레임워크) 모듈을 가져와 변수 express에 할당
const bodyParser = require("body-parser")
const http = require('http'); // HTTP 모듈(Node.js에서 HTTP 서버를 생성하는 기능을 제공)을 가져와 변수 http에 할당
const app = express(); // express 함수를 호출하여 애플리케이션 객체를 생성하고, 이를 변수 app에 할당 => 이 애플리케이션 객체에 다양한 설정과 라우팅 규칙을 추가할 수 있음
// const server = http.createServer(app).listen(80); // app을 기반으로 하는 HTTP 서버를 생성하고, 서버를 80번 포트에서 실행! 웹 서버를 시작하는 부분으로, 클라이언트의 요청을 받아들이고 응답하는 역할
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.listen(80);

const mysql = require('mysql'); // npm install mysql 로 설치해야 함! 0509에서 설치하지 말고 그 위(웹애플리케이션개발) 폴더에 설치 (전역설치도 해볼까?)

var connection = mysql.createConnection({
    host: 'localhost'
    , port: 3306
    , user: 'root'
    , password: '0000'
    , database: 'webui'
})

// let selectQuery = `select * from news;`
// connection.query(selectQuery,
//     function (err, rows, fields) {
//         console.log(1);
//     }
// )

// console.log(2);

app.get('/time', function(req, res) {
    res.sendfile("time.html");
})

app.get('/graph', function(req, res) {
    res.sendfile("graph.html");
})
  
