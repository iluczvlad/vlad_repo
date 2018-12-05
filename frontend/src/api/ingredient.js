var myHeaders = new Headers();
myHeaders.append('Content-Type', 'application/json');

var myInit = {
    method: 'GET',
    headers: myHeaders,
    mode: 'cors',
    cache: 'default'
};

export function getIngredient(id) {
    return fetch(new Request(`/api/li/ingredient/${id}`), myInit).then((response) => response.json());
}

export function getAllIngredients(){
    return fetch(new Request('/api/li/ingredient/list'), myInit).then((response) => response.json());
}