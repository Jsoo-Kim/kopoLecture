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
            <h1>Insert</h1>
            <form action="insert_action">
                <input type="text" placeholder="이름" name="name" />
                <input type="number" placeholder="중간 성적" name="middle_score" />
                <input type="number" placeholder="기말 성적" name="final_score" />
                <input type="submit" value="입력 완료" />
            </form>
            <p><a href="/t5/">홈</a></p>
            <div>${message}</div>
        </div>
    </body>
</html>