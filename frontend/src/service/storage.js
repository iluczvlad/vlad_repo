const authorizationKey='authorizationKey'
const emailKey='emailKey'

export function login(email, password) {
    window.localStorage.setItem(authorizationKey, 'Basic ' + btoa(email + ':' + password))
    window.localStorage.setItem(emailKey, email)
}

export function getAuth(){
    return window.localStorage.getItem(authorizationKey)
}

export function logout(){
    window.localStorage.removeItem(authorizationKey)
    window.localStorage.removeItem(emailKey)
}

export function isLoggedIn() {
    return window.localStorage.getItem(authorizationKey) !== null
}

export function getEmail(){
    return JSON.parse(window.localStorage.getItem(emailKey))
}