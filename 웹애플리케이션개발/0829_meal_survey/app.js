const express = require('express');
const request = require('request');
const cheerio = require('cheerio');
const path = require('path');

const app = express();



/*
tbl_table menu 클래스 아래에 tbody 아래에 tr 아래에 
1. 첫번째 td 안의 <br> 아래에 요일 정보
2. 세번째 td 안의 span 아래에 메뉴 정보(쉼표로 구분, [] 안의 텍스트는 무시)
*/



// app.get('/meal_survey', function (req, res) {
//     res.sendfile(`meal_survey.css`);
// })

// Express.js와 같은 Node.js 서버를 사용할 때, 정적 파일(CSS, JS, 이미지 등)을 올바르게 제공하기 위해 express.static 미들웨어를 사용
// 이 미들웨어를 통해 특정 디렉토리를 "정적 파일"로 서빙하도록 설정할 수 있음
app.use(express.static(path.join(__dirname, 'public')));

// console.log(__dirname);

app.get('/', (req, res) => {
    res.sendfile('./public/meal_survey.html');
});

// 메뉴 데이터 제공 API
app.get('/menu', (req, res) => {
    const url = 'https://www.kopo.ac.kr/kangseo/content.do?menu=262';

    request(url, (error, response, body) => {
        if (!error && response.statusCode == 200) {
            const $ = cheerio.load(body);
            let menuData = [];

            // 요일별 메뉴를 추출 (
            $('.tbl_table.menu tbody tr').each((i, element) => {
                // 첫 번째 <td>의 <br> 아래에 있는 요일 정보 가져오기
                const day = $(element).find('td').first().find('br').get(0).next.data.trim();
                // console.log("찍어보기");
                // console.log($(element).find('td').first().find('br').get(0));

                // 세 번째 <td>의 <span> 아래에 있는 메뉴 정보 가져오기
                let menuText = $(element).find('td').eq(2).find('span').text();
                // console.log(menuText);

                // 메뉴 정보에서 [] 안의 텍스트를 제거하고, 쉼표로 구분된 항목들을 배열로 변환
                // trim으로 공백 제거 후 filter로 빈 항목 제거
                let menu = menuText.replace(/\[.*?\]/g, '').split(',').map(item => item.trim()).filter(item => item.length > 0);

                // 객체 리터럴 문법! 변수 이름을 그대로 사용하여 객체의 프로퍼티 이름으로 지정됨
                menuData.push({ day, menu });
            });
            console.log(menuData);
            /*
            [
                {
                    day: '월요일',
                    menu: [ '백미밥', '버섯계란국', '불닭떡볶음', '타코야끼', '미역초무침', '김치' ]
                },
                {
                    day: '화요일',
                    menu: [ '미트스파게티', '스프', '모닝빵', '야채샐러드', '피클', '할라피뇨', '추가밥', '김치' ]
                },
                { day: '수요일', menu: [ '비빔밥', '미소장국', '포테이토핫도그', '천사채샐러드', '김치' ] },
                {
                    day: '목요일',
                    menu: [ '백미밥', '소고기무국', '제육김치볶음', '도톰동그랑땡전', '콩나물무침', '깍두기' ]
                },
                { day: '금요일', menu: [ '백미밥', '북어국', '매운갈비찜', '부추무침', '김치' ] },
                { day: '토요일', menu: [] },
                { day: '일요일', menu: [] }
            ]
            */

            res.json(menuData);  // JSON 형식으로 응답 전송
        } else {
            res.status(500).send('Error occurred while fetching the menu.');
        }
    });
});


app.listen(80, () => {
    console.log('Server is running on http://localhost:80');
});


