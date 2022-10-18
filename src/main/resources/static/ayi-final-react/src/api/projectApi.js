const baseUrl = `http://localhost:8080/api/project`;

export async function getAllProjects() {
    let response = await fetch(`${baseUrl}/getAllProjects`, {
        "method": 'GET',
        "headers": {
            "Content-Type": 'application/json'
        }
    });

    return await response.json();
}