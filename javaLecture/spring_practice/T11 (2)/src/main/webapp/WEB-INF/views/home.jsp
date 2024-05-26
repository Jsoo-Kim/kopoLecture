<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <style>
            .wrap {
                width: 800px;
                padding: 10px;
                margin: 20px auto;
                border-radius: 10px;
                background: #f1f1f1;
            }
            ul, li {
                list-style-type: none;
                margin: 0px;
                padding: 0px;
            }
            ul {
                display: flex;
                gap: 10px;
                justify-content: left;
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
                background: #8e93c0;
            }
            li:hover a {
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
                        <a href="insert">성적 입력</a>
                    </li>
                    <li>
                        <a href="#">다른 메뉴</a>
                    </li>
                </ul>
            </nav>
            <table>
                <thead>
                    <tr>
                        <th>id</th><th>이름</th><th>중간고사</th><th>기말고사</th><th>생성일</th>
                    </tr>
                </thead>
                <tbody>
                ${list }
                </tbody>
            </table>
        </div>
    </body>
</html>