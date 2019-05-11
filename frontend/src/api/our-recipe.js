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
        body,
    }
};
        

export function saveOurRecipe(dto) {
    const headers = initHeaders()
    const body = JSON.stringify(dto)
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/or`), init("POST", headers, body)).then((response) => response.ok);
}

export function listOurRecipe() {
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/or/list`), init("GET", headers)).then((response) => response.json());
}

export function deleteOurRecipe(id) {
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/or/${id}`), init("DELETE", headers)).then((response) => response.ok);
}
