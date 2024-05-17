const express = require('express'); // Express(웹 애플리케이션을 쉽게 개발할 수 있도록 도와주는 Node.js용 웹 프레임워크) 모듈을 가져와 변수 express에 할당
const bodyParser = require("body-parser")
// const http = require('http'); // HTTP 모듈(Node.js에서 HTTP 서버를 생성하는 기능을 제공)을 가져와 변수 http에 할당
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


// 라우터
app.get('/', function (req, res) {
    res.send("hello")
})


app.get('/newsWrite', function (req, res) {
    res.sendfile("newsWrite.html")
})


app.post('/write', (req, res) => {
    let title = req.body.title;
    let content = req.body.content;

    // 데이터베이스에 새로운 글 쓰기
    try {
        connection.query(`INSERT INTO news (title, content) VALUES ('${title}', '${content}');`, function (err, rows, result) {
            if (err) {
                console.error('MySQL 쿼리 오류:', err);
                throw err;
            }
            console.log('DB에 저장 완료:', result);
            res.send('DB에 입력 성공');
        });
    } catch (err) {
        console.error('DB에 입력 실패:', err);
        res.status(500).send('DB에 입력 실패');
    }
})


app.get('/newsList', function (req, res) {
    res.sendfile("newsList.html")
})

app.get('/showList', function (req, res) {

    // 데이터베이스에 있는 글 목록 가져오기
    connection.query(`SELECT * FROM news;`, function (err, rows, result) {
        if (err) {
            console.error('MySQL 쿼리 오류:', err);
            res.status(500).send('출력 실패');
            return;
        }
        console.log('출력 완료:', rows);
        res.json(rows);
    });

})


app.delete('/deleteNews', function(req, res) {
    let no = req.body.no;
    console.log('no: ' + no);

    connection.query(`DELETE FROM news WHERE no=${no}`, function(err, rows, result) {
        if (err) {
            console.error('MySQL 쿼리 오류:', err);
            res.status(500).json({ success: false, message: '삭제 실패' });
        } else {
            console.log('삭제 완료:', rows);
            res.json({ success: true });
        }
    });
});

  
