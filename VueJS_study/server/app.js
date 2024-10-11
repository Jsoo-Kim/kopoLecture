const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');


const cors = require('cors');


const app = express();
var request = require('request');


app.use(bodyParser.json()); // json을 parse하도록 함​
app.use(cors());
app.use(express.static("."));



// configuration =========================​

app.set('port', process.env.PORT || 8030);
app.use( '/', express.static( path.join(__dirname, './dist') ));  
app.get('/', function (req,res) {
    res.sendFile(path.join(__dirname, './dist', 'index.html'));
});


app.listen(app.get('port'), () => {
  console.log('Express server listening on port ' + app.get('port'));
});