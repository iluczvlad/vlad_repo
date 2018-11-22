var myHeaders = new Headers();
myHeaders.append('Content-Type', 'application/json');
myHeaders.append('Autorization', 'Basic '+btoa('admin:admin'));

var myInit = { method: 'GET',
               headers: myHeaders,
               mode: 'cors',
               cache: 'default' };

export function generateSimpleShake(){
    return fetch(new Request(`/api/shake/simple`), myInit).then((response) => response.json());
}