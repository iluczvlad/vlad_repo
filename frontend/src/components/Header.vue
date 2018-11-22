<template>
    <div class="header">
        
        <router-link to="/"><md-icon>menu</md-icon></router-link>
            
        <div class="log-buttons">
            <md-button class="md-raised md-primary" @click="$router.push('login')" v-if="!userLoggedIn">
                Login
            </md-button>
            <md-button class="md-raised md-accent" @click="$router.push('register')"  v-if="!userLoggedIn">
                Sign Up
            </md-button>
            <md-button class="md-raised md-accent" @click="logout" v-if="userLoggedIn">
                Sign Out
            </md-button>
        </div>
    </div>
</template>

<script>
import * as storage from '@/service/storage'

export default {
    methods: {
        logout(){
            storage.logout()
        }
    },
    computed: {
        userLoggedIn(){
            return storage.isLoggedIn()
        }
    }
}
</script>

<style scoped>
.header{
    width: 100%;
    height: 50px;
    background-color: green;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.logo{
    width: 100px;
    height: 100%;
    background-color: aqua;
    cursor: pointer;
}
</style>
