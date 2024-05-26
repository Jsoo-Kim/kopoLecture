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
        </style>
    </head>
    <body>
        <div class="wrap">
            <h1>Message</h1>
            <p>${message }</p>
        </div>
    </body>
</html>