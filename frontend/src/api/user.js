

const initHeaders = function() {
    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');
    return myHeaders
}

const init = function(method, headers, body) {
    return { 
        method: method,
        headers: headers,
        mode: 'cors',
        cache: 'default',
        body,
    }
};

export function loginUser(email, password) {
    const headers = initHeaders()
    headers.append('Authorization', 'Basic ' + btoa(email + ':' + password))
    return fetch(new Request('/api/li/user/login'), init('GET', headers)).then((response) => response.ok)
}

export function registerUser(user, withJson) {
    return fetch(new Request(`/api/nl/user/save`), init('POST', initHeaders(), user)).then((response) => {
        if (withJson) {
            return response.json()
        }
        return
    });
}