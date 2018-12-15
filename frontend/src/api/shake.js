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
            

export function generateSimpleShake(){
    return fetch(new Request(`/api/nl/shake/simple`), myInit).then((response) => response.json());
}

export function generateComplexShake(recipe) {
    const headers = initHeaders()
    const body = JSON.stringify(recipe)
    headers.append('Authorization', getAuth())
    return fetch(new Request(`/api/nl/shake/complex`), init("POST", initHeaders(), body)).then((response) => response.json());
}