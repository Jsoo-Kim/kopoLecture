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
            input, select {
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
            <h1>SignUp</h1>
            <form action="signup" method="post">
                <input type="text" placeholder="이름" name="name" />
                <input type="text" placeholder="ID" name="userId" />
                <input type="password" placeholder="PASSWORD" name="userPw" />
                <select id="gender" name="gender" required>
            		<option value="남">남</option>
            		<option value="여">여</option>
        		</select><br>
                <input type="text" placeholder="주소" name="address" />
                <input type="submit" value="가입 완료" />
            </form>
            <p>
               <a href="/DiceGame">홈</a>
            </p>
            <div>${message}</div>
        </div>
    </body>
</html>