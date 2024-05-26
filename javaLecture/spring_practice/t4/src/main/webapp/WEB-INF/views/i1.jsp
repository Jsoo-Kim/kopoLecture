<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <style>
            .wrap {
                width: 500px;
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
                background: 8e93c0;
            }
            li:hover a {
                color: #ffffff;
            }
        </style>
    </head>
    <body>
        <div class="wrap">
            <nav>
                <ul>
                    <li>
                        <a href="student_form">성적 입력</a>
                    </li>
                    <li>
                        <a href="#">다른 메뉴</a>
                    </li>
                </ul>
            </nav>
        </div>
    </body>
</html>