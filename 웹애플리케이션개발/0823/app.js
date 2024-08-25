const express = require('express');
const app = express();

// const bodyParser = require('body-parser');
// app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({ extended: false }));

// (Express 4.16.0 이후) 내장 미들웨어 사용 
app.use(express.json()); // JSON 데이터 파싱
app.use(express.urlencoded({ extended: false })); // URL 인코딩된 데이터 파싱

app.listen(80);

const mysql = require('mysql');
const { log } = require('console');


var connection = mysql.createConnection({
    host: 'localhost'
    , port: 3306
    , user: 'root'
    , password: '0000'
    , database: 'webui'
});

app.get('/studentRegistrationForm', function (req, res) {
    res.sendfile("studentRegistrationForm.html");
});

app.post('/registration', function (req, res) {
    let studentName = (req.body.studentName);
    let studentNumber = (req.body.studentNumber);
    let nationalID = (req.body.nationalID);
    let studentAddress = (req.body.studentAddress);
    console.log(studentName, studentNumber, nationalID, studentAddress);
    connection.query(`INSERT INTO students (name, student_number, national_id, address) 
                      VALUES ('${studentName}','${studentNumber}','${nationalID}','${studentAddress}');`,
        function (err, rows, fields) {
            if (err) throw err;
            res.send(rows)
        }
    )
});

app.get('/studentsList', function (req, res) {
    res.sendfile("studentsList.html");
});

app.get('/studentsListUp', function (req, res) {
    connection.query(`select * from students`,
        function (err, rows, fields) {
            if (err) throw err;
            res.send(rows)
        }
    )
});

app.delete('/deleteStudent', function (req, res) {
    let id = req.body.deleteID;
    let deleteQuery = `DELETE FROM students WHERE student_id=${id};`;
    console.log(deleteQuery);
    connection.query(deleteQuery,
        function (err, rows, fields) {
            if (err) throw err;
            console.log(rows);
            res.send(rows)
        }
    )
});

app.get('/studentUpdate', function (req, res) {
    res.sendfile("studentUpdate.html");
});

app.put('/updateStudent', function (req, res) {
    let id = req.body.updateID;
    let studentName = (req.body.studentName);
    let studentNumber = (req.body.studentNumber);
    let nationalID = (req.body.nationalID);
    let studentAddress = (req.body.studentAddress);
    // console.log(id, studentName, studentNumber, nationalID, studentAddress);
    let updateQuery = "UPDATE `webui`.`students` SET `name` = ?, `student_number` = ?, `national_id` = ?, `address` = ? WHERE  `student_id` = ?;"
    // console.log(updateQuery);
    connection.query(updateQuery,
        [studentName, studentNumber, nationalID, studentAddress, id],
        function (err, results, fields) {
            if (err) {
                console.error('Update error:', err);
                res.status(500).send('Update failed');
                return;
            }
            res.send(results);
        }
    )
});

app.get('/selectStudent', function (req, res) {
    let id = req.query.id;
    connection.query(`SELECT * FROM students WHERE student_id = ${id};`,
        function (err, rows, fields) {
            if (err) throw err;
            console.log(rows);
            res.send(rows);
        })
})
