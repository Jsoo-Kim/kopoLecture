<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <style>
            .wrap {
                width: 800px;
                padding: 10px;
                margin: 20px auto;
                border-radius: 10px;
                background: #f1f1f1;
                text-align: center; <!-- 추가 -->
            }
            ul, li {
                list-style-type: none;
                margin: 0px;
                padding: 0px;
            }
            ul {
                display: flex;
                gap: 10px;
                justify-content: center; <!-- 수정 -->
            }
            li {
                display: inline-block;
                padding: 10px 30px;
                border: 1px solid #333;
                border-radius: 5px;
            }
            a {
                text-decoration: none;
                color: #333;
            }
            li:hover {
                background: 8e93c0;
            }
            li:hover a {
                color: #ffffff;
            }
            a:hover {
                color: #ffffff;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 50px;
            }
            th {
                background: #555;
                color: #fff;
                padding: 5px 0px;
                text-align: center;
            }
            td {
                padding: 5px 0px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="wrap">
            <nav>
                <ul>
                    <li>
                        <a href="/DiceGame">홈</a>
                    </li>
                </ul>
            </nav>
            <table>
                <thead>
                    <tr>
                        <th>이름</th>
                        <th>아이디</th>
                        <th>패스워드</th>
                        <th>성별</th>
                        <th>주소</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
      				${list }
                </tbody>
            </table>
        </div>
    </body>
</html>
