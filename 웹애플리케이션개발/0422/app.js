var express = require('express'); // Express(웹 애플리케이션을 쉽게 개발할 수 있도록 도와주는 Node.js용 웹 프레임워크) 모듈을 가져와 변수 express에 할당
var http = require('http'); // HTTP 모듈(Node.js에서 HTTP 서버를 생성하는 기능을 제공)을 가져와 변수 http에 할당
var app = express(); // express 함수를 호출하여 애플리케이션 객체를 생성하고, 이를 변수 app에 할당 => 이 애플리케이션 객체에 다양한 설정과 라우팅 규칙을 추가할 수 있음
var server = http.createServer(app).listen(80); // app을 기반으로 하는 HTTP 서버를 생성하고, 서버를 80번 포트에서 실행! 웹 서버를 시작하는 부분으로, 클라이언트의 요청을 받아들이고 응답하는 역할

// 라우터
app.get('/main', function(req, res) {
  res.sendfile("main.html")
})

app.get('/main2', function(req, res) {
  res.sendfile("main2.html")
})

app.get('/main3', function(req, res) {
  res.sendfile("main3.html")
})

app.get('/main4', function(req, res) {
  res.sendfile("main4.html")
})

app.get('/main5', function(req, res) {
  res.sendfile("main5.html")
})

app.get('/qs', function(req, res) {
  console.log(req.query);
  res.send(`your querystring is a : ${req.query.a}, b : ${req.query.b}, c : ${req.query.c}`);
})

// -- 방법 1
app.get('/qs2', function(req, res) {
  console.log(req.query);
  res.send(`${parseInt(req.query.inputNum1) + parseInt(req.query.inputNum2) + parseInt(req.query.inputNum3)}`);
})

// -- 방법 2
app.get('/qsplus', function(req, res) {
  console.log(req.query); // cmd 창에서 확인해야 함
  let num1 = parseInt(req.query.num1);
  let num2 = parseInt(req.query.num2);
  let num3 = parseInt(req.query.num3);
  let result = num1 + num2 + num3
  res.send(String(result));
})

app.get('/qspurchase', function(req, res) {
  console.log(req.query);
  let inputPrice= parseInt(req.query.inputPrice);
  let whatYouCanBuy = "";

  let items = [
    {name: "item1", price: 1000},
    {name: "item2", price: 5000},
    {name: "item3", price: 10000},
    {name: "item4", price: 30000},
    {name: "item5", price: 50000},
    {name: "item6", price: 100000},
    {name: "item7", price: 500000},
  ]
  // console.log(items);

  for (let item of items) {
    if (item.price <= inputPrice) {
      whatYouCanBuy = `${item.name} - ${item.price}`;
      // console.log(whatYouCanBuy);
    }
  }

  if (inputPrice < 1000) {
    whatYouCanBuy = "구입불가";
  }

  res.send(whatYouCanBuy);
})
