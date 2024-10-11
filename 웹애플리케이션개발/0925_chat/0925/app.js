const express = require('express');
const { createServer } = require('node:http');
const { join } = require('node:path');
const { Server } = require('socket.io');

const app = express();
var bodyParser = require("body-parser");
app.use(bodyParser.urlencoded({limit: '50mb', extended: false}));
app.use(bodyParser.json({limit: '50mb'}));
const server = createServer(app);
const io = new Server(server);


const mysql = require('mysql');

var connection = mysql.createConnection({
	host: 'localhost'
	, port: 3306
	, user: 'root'
	, password: '0000'
	, database: 'webui'
});

server.listen(80, () => {
});

users = [];

io.on('connection', function(socket){
	socket.on('joinRoom', function(loginId){
		socket.join("chatRoom");
			for(let i=0;i<users.length;i++) {
				let user = users[i];
				if(user.loginId == loginId) {
					user.socketId = socket.id;
					socket.broadcast.to("chatRoom").emit("newUserConnected", loginId);
					return;
				}
			}
	});

	socket.on('sendMsg', function(message){
		for(let i=0;i<users.length;i++) {
			let user = users[i];
			if(user.socketId==socket.id) {
					socket.broadcast.to("chatRoom").emit("getMsg", {loginId: user.loginId, message: message});

					let insertQuery = `INSERT INTO message (loginId, message) VALUES ('${user.loginId}', '${message}');`
					connection.query(insertQuery,
						function (err, rows, fields) {
							if (err) {
								throw err;
							}
						}
					);
			}
		}
	});

	socket.on('disconnect', function(){
		for(let i=0;i<users.length;i++) {
			let user = users[i]
			if(user.socketId == socket.id) {
				socket.broadcast.to("chatRoom").emit("userDisconnected", user.loginId);
				users.splice(i, 1);
				break;
			}
		}

	});
});

app.get('/chat', function (req, res) {
	res.sendfile('chat.html');
});

app.get('/login', function (req, res) {
	let loginId = req.query.loginId;
	for(let i=0;i<users.length;i++) {
		let user = users[i]
		if(user.loginId == loginId) {
			res.send({msg: "fail"});
			return;
		}
	}
	res.send({msg: "success", users:users});
	users.push({loginId: loginId, socketId:"temp socket id"});
});

app.get('/getChatHistoryRecentTen', function (req, res) {

	let selectQuery = `SELECT * FROM message order by no desc LIMIT 10;`
	connection.query(selectQuery,
		function (err, rows, fields) {
			if (err) {
				throw err;
			}
			else {
				res.send(rows);
			}
		}
	);
});
