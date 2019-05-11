import {getAuth, getEmail} from '@/service/storage'

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
        body,
    }
};

export function getIngredient(id) {
    return fetch(new Request(`/api/li/ingredient/${id}`), myInit).then((response) => response.json());
}

export function deleteIngredient(id) {
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/ingredient/${id}`), init('DELETE', headers)).then((response) => response.ok);
}

export function saveIngredient(dto) {
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/ingredient`), init('POST', headers, JSON.stringify(dto))).then((response) => response.ok);
}

export function getIngredientsByType(type) {
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/ingredient/type/${type}/${getEmail()}`), init('GET', headers)).then((response) => response.json());
}

export function getAllIngredients(){
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request('/api/li/ingredient/list'), init('GET', headers)).then((response) => response.json());
}