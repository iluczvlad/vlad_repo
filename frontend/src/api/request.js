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

export function placeOrder(dto){
    const headers = initHeaders()
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/li/request/place`), init("POST", headers, JSON.stringify(dto))).then((response) => response.ok);
}