<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        /* CSS 스타일 지정 */
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
           li {
           display: inline-block;
           padding: 10px 30px;
           border: 1px solid #333;
           border-radius: 5px;
      		}
           li:hover {
           background: 8e93c0;
        }
        li:hover a {
            color: #ffffff;
        }
           a {
               text-decoration: none;
               color: #333;
           }
            a:hover {
           color: #ffffff;
           }
    </style>
</head>
<body>
    <div class="wrap">
    		<ul>
                <!-- 홈 링크 -->
                <li>
                    <a href="/Customer/">홈</a>
                </li>
            </ul>
        <!-- 제목 -->
        <h1>Message</h1>
        <!-- 메시지 내용 -->
        <p>${message}</p>
    </div>
</body>
</html>
