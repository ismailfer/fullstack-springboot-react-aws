import fetch from 'unfetch';

// defines a function to get students
// const getAllStudents = () => fetch('localhost:8080/students');

// globalize the main url (http://localhost:8080), see package.json proxy setting
export const getAllStudents = () => 
    fetch('/api/students').then(checkStatus);

export const addNewStudent = student => 
    fetch('/api/students', {
        headers: {'Content-type':'application/json'},
        method: 'POST',
        body: JSON.stringify(student)
    }).then(checkStatus);


const checkStatus = response => {
    if (response.ok)
    {
        return response;
    }
    else
    {
        var error = new Error(response.statusText);

        error.response = response;

        response.json().then(e => {
            error.error = e;                
        });

        return Promise.reject(error);

    }
}
