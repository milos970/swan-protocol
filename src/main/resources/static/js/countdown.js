function connect() {
    const socket = new SockJS('/ws');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {


        console.log("Connected: " + frame);
        stompClient.subscribe('/topic/countdown', function (message) {
            const data = JSON.parse(message.body);
            let minutes = parseInt(data[0]);
            let seconds = parseInt(data[1]);
            document.getElementById('countdown').innerText =
                minutes + ":" + (seconds < 10 ? "0" + seconds : seconds);


                changeBackground(minutes, seconds);

        });
    });

    return stompClient;
}

function changeBackground(minutes, seconds) {
    const element = document.querySelector(".work-body");

    if (!element) return;

    if (minutes <= 0 && (!element.classList.contains("work-body-error")) ) {
        element.classList.add("work-body-error");
    }

    if (minutes > 0 && element.classList.contains("work-body-error") ) {
            element.classList.remove("work-body-error");
    }
}


document.getElementById("numbers-input").addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        sendMessage();
        document.getElementById("numbers-input").value = "";
    }
});

function sendMessage() {
    const text = document.getElementById("numbers-input").value;

    if (client && client.connected) {
        client.send(
            "/app/numbers/sendText",   // cieľová adresa na serveri (prefix /app býva bežný)
            {},
            text
        );
    } else {
        console.log("Not connected");
    }
}


let client = connect();


