<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <style>
            .wrap {
                width: 500px;
                padding: 10px;
                border: 1px solid #999;
                margin: 20px auto;
                border-radius: 10px;
                background: #f1f1f1;
            }
            h1 {
                margin-bottom: 40px;
                font-size: 1.2em;
            }
            p {
                background: #fff;
                padding: 10px;
                border-radius: 10px;
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
        </style>
    </head>
    <body>
        <div class="wrap">
            <nav>
                <ul>
                    <li>
                        <a href="/t11">홈 화면으로</a>
                    </li>
                </ul>
            </nav>
            <h1>Message</h1>
            <p>${message }</p>
        </div>
    </body>
</html>