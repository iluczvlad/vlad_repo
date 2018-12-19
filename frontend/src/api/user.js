import {getAuth} from '@/service/storage'

var myHeaders = new Headers();
myHeaders.append('Content-Type', 'application/json');

var myInit = {
    method: 'GET',
    headers: myHeaders,
    mode: 'cors',
    cache: 'default' 
};

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
        body: body,
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

export function getUserByEmail(email){
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/user`), init("POST", headers, email)).then((response) => response.json());
}

export function saveUserPreferences(dto){
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/user/prefs`), init("POST", headers, JSON.stringify(dto))).then((response) => response.ok);
}

export function addToShoppingList(userId, lst){
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/user/${userId}/shplst`), init("POST", headers, JSON.stringify(lst))).then((response) => response.ok);
}

export function deleteFromShoppingList(userId, id){
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/user/${userId}/shplst/${id}`), init("DELETE", headers)).then((response) => response.ok);
}