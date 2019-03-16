const authorizationKey='authorizationKey'
const emailKey='emailKey'
const notifiedKey='notified'
const roleKey="roleKey"

export function login(email, password, role) {
    window.localStorage.setItem(authorizationKey, 'Basic ' + btoa(email + ':' + password))
    window.localStorage.setItem(emailKey, email)
    window.localStorage.setItem(roleKey, role)
}

export function getAuth(){
    return window.localStorage.getItem(authorizationKey)
}

export function logout(){
    window.localStorage.removeItem(authorizationKey)
    window.localStorage.removeItem(emailKey)
    window.localStorage.removeItem(roleKey)
}

export function isLoggedIn() {
    return window.localStorage.getItem(authorizationKey) !== null
}

export function getEmail(){
    return window.localStorage.getItem(emailKey)
}

export function getRole(){
    return window.localStorage.getItem(roleKey)
}

export function isAdmin(){
    return window.localStorage.getItem(roleKey) === "ADMIN"
}

export function isBasic(){
    return window.localStorage.getItem(roleKey) === "BASIC"
}

export function isClerk(){
    return window.localStorage.getItem(roleKey) === "CLERK"
}
