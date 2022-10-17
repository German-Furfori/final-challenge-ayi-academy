export async function getAllProjects() {
    let response = await fetch(`http://localhost:8080/api/project/getAllProjects`, {
        "method": 'GET',
        "headers": {
            "Content-Type": 'application/json'
        }
    });

    return await response.json();
}