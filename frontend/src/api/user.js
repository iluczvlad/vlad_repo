var myHeaders = new Headers();
myHeaders.append('Content-Type', 'application/json');

const init = function(method, body) {
    return { 
        method: method,
        headers: myHeaders,
        mode: 'cors',
        cache: 'default',
        body,
    }
};

export function registerUser(user, withJson) {
    return fetch(new Request(`/api/user/save`), init('POST', user)).then((response) => {
        if (withJson) {
            return response.json()
        }
        return
    });
}