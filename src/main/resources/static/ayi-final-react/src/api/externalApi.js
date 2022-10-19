export function getInflation() {
    fetch("https://api.estadisticasbcra.com/inflacion_mensual_oficial", {
        headers: {
            Authorization: "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTcyOTA4OTQsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJnZXJtYW4uZnVyZm9yaUBnbWFpbC5jb20ifQ.brU43pMXElJGbAZAH_M0E32jzyTr40bhZY7Ol_Z8BGB0h7krci-voGbmqZFaPw0jy4V04Pm2eHS99bGFhZHZTg"
        }
    })
    .then(response => {
        response.json();
    })
    .then(data => {
        console.log(data);
    })
}