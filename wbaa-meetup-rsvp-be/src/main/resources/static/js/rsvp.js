var stompClient;
var canvasEl = document.getElementById("world-map-canvas");
var ctx = canvasEl.getContext("2d");

canvasEl.height = window.innerHeight;
canvasEl.width = window.innerWidth;

var background = new Image();
background.src = "img/world-map.png";

// Make sure the image is loaded first otherwise nothing will draw.
background.onload = function(){
  ctx.drawImage(background,0,0,canvasEl.width, canvasEl.height);
}

var redDot = new Image();
redDot.src = "img/pointer.gif";

var equator = canvasEl.height/2;
var primeMeridian = canvasEl.width/2;

function calculateY (lat) {
  floatLat = parseFloat(lat);
  // South of equator is considered negative -__-
  y = equator - floatLat/90 * equator;
  return y;
}

function calculateX (lon) {
  floatLon = parseFloat(lon);
  x = primeMeridian + floatLon/180 * primeMeridian;
  return x;
}

		/* Configuring WebSocket on Client Side */
		var socket = new SockJS('/meetup');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			stompClient.subscribe('/famous/locations', function(message) {
              var obj = JSON.parse(message.body);
              var x = calculateX(obj.lon);
              var y = calculateY(obj.lat);
              ctx.drawImage(redDot, x, y);
			});
		});
