export function login(email, password){
    window.localStorage.setItem('loggedInUser', JSON.stringify({email, password}))
}

export function logout(){
    window.localStorage.removeItem('loggedInUser')
}

export function isLoggedIn(){
    return window.localStorage.getItem('loggedInUser') !== null
}

export function getUser(){
    return JSON.parse(window.localStorage.getItem('loggedInUser'))
}