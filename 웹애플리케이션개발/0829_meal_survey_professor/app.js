const express = require('express'); // Express(웹 애플리케이션을 쉽게 개발할 수 있도록 도와주는 Node.js용 웹 프레임워크) 모듈을 가져와 변수 express에 할당
const bodyParser = require("body-parser");
const request = require("request");
const cheerio = require("cheerio");
const app = express(); // express 함수를 호출하여 애플리케이션 객체를 생성하고, 이를 변수 app에 할당 => 이 애플리케이션 객체에 다양한 설정과 라우팅 규칙을 추가할 수 있음

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

app.listen(80);

// 0509에서 설치하지 말고 그 위(웹애플리케이션개발) 폴더에 설치 (전역설치도 해볼까?)
const mysql = require('mysql'); 

var connection = mysql.createConnection({
    host: 'localhost'
    , port: 3306
    , user: 'root'
    , password: '0000'
    , database: 'webui'
})

app.get('/menu', function (req, res) {
    res.sendfile(`menu.html`);
})

app.get('/getMenu', function (req, res) {

    request('https://www.kopo.ac.kr/kangseo/content.do?menu=262', function (error, response, body) {
        console.error('error: ', error);
        console.log('statusCode: ', response && response.statusCode);
        // console.log('body: ', body);
        let $ = cheerio.load(body);
        let tds = $("td");
        // console.log("#####################");
        // console.log(tds[2].children[1].children[0]);
        
        let menus = [];

        for (let i = 0; i < 5; i++) {
            let dailyMenu = tds[2 + (i * 4)].children[1].children[0].data.replace(/\n/ig, '').split("[")[0].split(",");
            // console.log(dailyMenu);
            dailyMenu.pop();
            menus.push(dailyMenu);
        }
        res.send(menus);
        // console.log(menus);
        /*
        [
            [ '백미밥', ' 콩나물국', ' 닭갈비', ' 생선까스', ' 도라지오이무침', ' 김치' ],
            [ '오므라이스', ' 양송이스프', ' 빌소시지', ' 시져샐러드', ' 김치' ],
            [ '백미밥', ' 샤브샤브국', ' 메밀전병', ' 감자채볶음', ' 마늘쫑지무침', ' 김치' ],
            [ '백미밥', ' 미역국', ' 깻잎제육볶음', ' 새우까스', ' 연근조림', ' 김치' ],
            [ '참치김치덮밥', ' 파계란국', ' 군만두', ' 오복지무침', ' 열무김치', ' 망고주스' ]
        ]
        */
    })
})



// app.get('/meal_survey', function (req, res) {
//     res.sendfile(`meal_survey.css`);
// })
