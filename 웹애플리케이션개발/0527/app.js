const express = require('express');
const http = require('http');
const app = express();

const bodyParser = require('body-parser');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

app.listen(80);

const mysql = require('mysql');


var connection = mysql.createConnection({
  host: 'localhost'
  , port: 3306
  , user: 'root'
  , password: '0000'
  , database: 'webui'
});

app.get('/newWrite', function (req, res) {
  res.sendfile("newWrite.html");
});

app.post('/Write', function (req, res) {
  let title = (req.body.title);
  let content = (req.body.content);
  console.log(title, content);
  //데이터베이스에 새로운 글 쓰기
  connection.query(`INSERT INTO news (title, content) VALUES ('${title}','${content}');`,
  function (err, rows, fields) {
    if(err) throw err;
    res.send(rows)
  }
)
});

app.get('/newsList', function (req, res) {
  res.sendfile("newsList.html");
});

app.get('/List', function (req, res) {
  //데이터베이스에 있는 글 목록 가져오기
  connection.query(`select * from news`,
  function (err, rows, fields) {
    if(err) throw err;
    res.send(rows)
  }
)
});

//app.delete로 받아야
app.delete('/deleteNews', function (req, res) {
  let no = req.body.deleteNo;
  let deleteQuery = `DELETE FROM news WHERE no=${no};`;
  console.log(deleteQuery);
  connection.query(deleteQuery,
    function (err, rows, fields){
    if(err) throw err;
    res.send(rows)
  }
)
});

app.put('/Update', function (req, res) {
  let no = req.body.no;
  let title = req.body.title;
  let content = req.body.content;
  console.log(no, title, content);
  let updateQuery = "UPDATE `webui`.`news` SET `title` = ?, `content` = ? WHERE  `no` = ?;"
  console.log(updateQuery);
  connection.query(updateQuery,
    [title, content, index],
    function (err, rows, fields) {
    if(err) throw err;
    res.send(rows)
  }
)
});

app.get('/newsUpdate', function (req, res) {
  res.sendfile("newsUpdate.html");
});

app.get('/newsSelect', function (req, res) {
  let no = req.query.no;
  connection.query(`SELECT * FROM news WHERE no = ${no};`,
  function (err, rows, fields) {
    if (err) throw err;
    res.send(rows);
  })
})
