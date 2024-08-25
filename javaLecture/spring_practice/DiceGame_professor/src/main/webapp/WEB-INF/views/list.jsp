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
                text-align: center;
            }
            ul, li {
                list-style-type: none;
                margin: 0px;
                padding: 0px;
            }
            ul {
                display: flex;
                gap: 10px;
                justify-content: center;
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
        <script>
	const apiTest = async function() {
		const response = await fetch('http://localhost:8080/dicegame/list_api');
		const data = await response.json();
		console.log(data);
		
		let html_text = '';
		for (let i = 0; i < data.length; i++) {
			html_text = html_text + "<tr>";
			html_text = html_text + "<td>";
			html_text = html_text + data[i].idx;
			html_text = html_text + "</td>";
			html_text = html_text + "<td>";
			html_text = html_text + data[i].user;
			html_text = html_text + "</td>";
			html_text = html_text + "<td>";
			html_text = html_text + data[i].com;
			html_text = html_text + "</td>";
			html_text = html_text + "<td>";
			html_text = html_text + data[i].result;
			html_text = html_text + "</td>";
			html_text = html_text + "<td>";
			html_text = html_text + data[i].created;
			html_text = html_text + "</td>";
			html_text = html_text + "</tr>";
		}
		let list_element = document.getElementById('list');
		list_element.innerHTML = html_text;
	}        
        
        </script>
    </head>
    <body>
        <div class="wrap">
            <nav>
                <ul>
                    <li>
                        <a href="play">주사위 게임 실행</a>
                    </li>
                    <li>
                        <a href="/dicegame">홈</a>
                    </li>
                </ul>
            </nav>
            <table>
                <thead>
                    <tr>
                        <th>idx</th>
                        <th>사용자</th>
                        <th>컴퓨터</th>
                        <th>결과</th>
                        <th>생성일</th>
                    </tr>
                </thead>
                <tbody id="list">
                    
                </tbody>
            </table>
        </div>
<script type="text/javascript">
apiTest();
</script>
    </body>
</html>
