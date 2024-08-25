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
                border: 1px solid #999;
                margin: 20px auto;
                border-radius: 10px;
                background: #f1f1f1;
            }
            h1 {
                margin-bottom: 40px;
                font-size: 1.2em;
            }
            form {
                background: #fff;
                padding: 10px;
                border-radius: 10px;
                box-sizing: border-box;
            }
            input {
                width: 100%;
                line-height: 2em;
                height: 3em;
                box-sizing: border-box;
                padding-left: 10px;
                margin: 10px 5px;
            }
            p:hover {
                background: 8e93c0;
            }
            p:hover a {
                color: gray;
            }
            p {
                background: #fff;
                padding: 10px;
                border-radius: 10px;
               	text-align: center;
            }
            a {
                text-decoration: none;
                color: #333;
            }
            div {
            	text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="wrap">
            <h1>주사위 게임</h1>
            <form action="play_action">
                <input type="submit" value="게임 실행" />
            </form>
        </div>
    </body>
</html>