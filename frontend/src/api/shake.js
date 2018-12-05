var myHeaders = new Headers();
myHeaders.append('Content-Type', 'application/json');

var myInit = { method: 'GET',
               headers: myHeaders,
               mode: 'cors',
               cache: 'default' };

export function generateSimpleShake(){
    return fetch(new Request(`/api/nl/shake/simple`), myInit).then((response) => response.json());
}