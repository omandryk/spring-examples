<html>
<head>
    <title>Home</title>
    <script src="/resources/jquery.min.js"></script>
</head>
<body>
<button id="stop">Stop</button>

<script type="text/javascript">
    var url = 'ws://' + window.location.host + '/marco';
    var sock = new WebSocket(url);

    sock.onopen = function() {
        console.log('Opening');
        $('#output').append('Opening <br/>');
        sayMarco();
    };

    sock.onmessage = function(e) {
        $('#output').append('Received message: "' + e.data + '"<br/>');
        setTimeout(function(){sayMarco()}, 2000);
    };

    sock.onclose = function() {
        $('#output').append('Closing <br/>');
    };

    function sayMarco() {
        $('#output').append('Sending Marco! <br/>');
        sock.send("Marco!");
    }

    $('#stop').click(function () {
        sock.close()
    });
</script>

<div id="output"></div>
</body>
</html>
