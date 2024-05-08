var express = require('express'); // Express(웹 애플리케이션을 쉽게 개발할 수 있도록 도와주는 Node.js용 웹 프레임워크) 모듈을 가져와 변수 express에 할당
var http = require('http'); // HTTP 모듈(Node.js에서 HTTP 서버를 생성하는 기능을 제공)을 가져와 변수 http에 할당
var app = express(); // express 함수를 호출하여 애플리케이션 객체를 생성하고, 이를 변수 app에 할당 => 이 애플리케이션 객체에 다양한 설정과 라우팅 규칙을 추가할 수 있음
var server = http.createServer(app).listen(80); // app을 기반으로 하는 HTTP 서버를 생성하고, 서버를 80번 포트에서 실행! 웹 서버를 시작하는 부분으로, 클라이언트의 요청을 받아들이고 응답하는 역할

// 라우터
app.get('/mid1', function(req, res) {
  res.sendfile("mid1.html")
})

app.get('/mid2', function(req, res) {
  res.sendfile("mid2.html")
})

app.get('/mid3', function(req, res) {
  res.sendfile("mid3.html")
})

app.get('/mid4', function(req, res) {
  res.sendfile("mid4.html")
})


app.get('/bmi', function(req, res) {
  let height = parseFloat(req.query.height);
  let weight = parseFloat(req.query.weight);
  calBmi = ( weight / Math.pow(height * 0.01, 2) ).toFixed(1);
  result = "";

  if (calBmi < 20) {
    result = "저체중";
  } else if (calBmi < 25) {
    result = "정상";
  } else if (calBmi < 30) {
    result = "과체중";
  } else {
    result = "비만";
  }

  let response = `bmi : ${calBmi} / 결과 : ${result}`
  res.send(response);
})


let totalScores = [];
app.get('/rank', function(req, res) {
  let korean = parseFloat(req.query.korean) * 1;
  let english = parseFloat(req.query.english) * 2;
  let math = parseFloat(req.query.math) * 3;
  let totalScore  = korean + english + math;
  totalScores.push(totalScore);

  // console.log(totalScores);

  totalScores.sort(function(a, b) {
    return b - a;
  });

  let yourRank = 0;
  for (let i = 1; i <= totalScores.length; i++) {
    if (totalScores[i-1] == totalScore) {
      yourRank = i;
      break;
    }
  }

  let response = `석차 : ${yourRank} <br>`
  res.send(response)
})
