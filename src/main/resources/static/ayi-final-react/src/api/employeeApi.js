const baseUrl = `http://localhost:8080/api/employee`;

export async function getAllEmployeePages(page) {
    let rows = 8;
    let response = await fetch(`${baseUrl}/getAllEmployeePages/${page}/${rows}`, {
        "method": 'GET',
        "headers": {
            "Content-Type": 'application/json'
        }
    });

    return await response.json();
}

export async function getEmployeeById(idEmployee) {
    let response = await fetch(`${baseUrl}/getEmployeeById/${idEmployee}`, {
        "method": 'GET',
        "headers": {
            "Content-Type": 'application/json'
        }
    });

    return await response.json();
}

export async function incrementSalaries(percentage) {
    let response = await fetch(`${baseUrl}/incrementSalaries/${percentage}`, {
        "method": 'PATCH',
        "headers": {
            "Content-Type": 'application/json'
        }
    });

    return await response.json();
}

export async function assignProjectToEmployee(idEmployee, idProject) {
    let response = await fetch(`${baseUrl}/assignProjectToEmployee/${idEmployee}/${idProject}`, {
        "method": 'PATCH',
        "headers": {
            "Content-Type": 'application/json'
        }
    });

    return await response.json();
}

export async function updateEmployeeSalary(idEmployee, salary) {
    let response = await fetch(`${baseUrl}/updateEmployeeSalary/${idEmployee}/${salary}`, {
        "method": 'PATCH',
        "headers": {
            "Content-Type": 'application/json'
        }
    });

    return await response.json();
}

// export function getInflation() {
//     fetch("https://api.estadisticasbcra.com/inflacion_mensual_oficial", {
//         headers: {
//             Authorization: "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTcyOTA4OTQsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJnZXJtYW4uZnVyZm9yaUBnbWFpbC5jb20ifQ.brU43pMXElJGbAZAH_M0E32jzyTr40bhZY7Ol_Z8BGB0h7krci-voGbmqZFaPw0jy4V04Pm2eHS99bGFhZHZTg"
//         }
//     })
//     .then(response => {
//         response.json();
//     })
//     .then(data => {
//         console.log(data);
//     })
// }
