<html>
<head>
    <title>Home</title>
    <script src="/resources/jquery.min.js"></script>
    <script src="/resources/sockjs.min.js"></script>
</head>
<body>
<button id="stop">Stop</button>

<script type="text/javascript">
    var sock = new SockJS('/marco');

    sock.onopen = function () {
        console.log('Opening');
        sayMarco();
    }

    sock.onmessage = function (e) {
        console.log('Received message: ', e.data);
        $('#output').append('Received "' + e.data + '"<br/>');
        setTimeout(function () {
            sayMarco()
        }, 2000);
    }

    sock.onclose = function () {
        console.log('Closing');
    }

    function sayMarco() {
        console.log('Sending Marco!');
        $('#output').append('Sending "Marco!"<br/>');
        sock.send("Marco!");
    }

    $('#stop').click(function () {
        sock.close()
    });
</script>

<div id="output"></div>
</body>
</html>
