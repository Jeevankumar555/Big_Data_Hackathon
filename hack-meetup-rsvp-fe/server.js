var express = require('express');
var app = express();
app.use(express.static(__dirname + '/public')); //__dir and not _dir
app.use('/bootstrap', express.static(__dirname + '/node_modules/bootstrap/dist/'));
app.use('/jquery', express.static(__dirname + '/node_modules/jquery/dist/'));
app.use('/sockjs-client', express.static(__dirname + '/node_modules/sockjs-client/dist/'));
app.use('/stomp-websocket', express.static(__dirname + '/node_modules/stompjs/lib/'));
var port = 5555; // you can use any port
app.listen(port);
console.log('server running on ' + port);