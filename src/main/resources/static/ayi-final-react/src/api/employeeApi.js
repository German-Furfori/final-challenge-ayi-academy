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
