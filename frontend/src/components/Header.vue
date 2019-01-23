<template>
    <div class="header">
        
        <md-button class="md-icon-button" @click="toggleNavigation">
            <md-icon>menu</md-icon>
        </md-button>

        <md-drawer v-if="userLoggedIn" :md-active.sync="showNavigation">
            <md-toolbar class="md-transparent" md-elevation="0">
                <span class="md-title">Menu</span>
            </md-toolbar>

            <md-list>
                <router-link to="/prefs">
                    <md-list-item>
                        <md-icon>person</md-icon>
                        <span class="md-list-item-text">Preferences</span>
                    </md-list-item>
                </router-link>

                <router-link to="/simple">
                    <md-list-item>
                        <md-icon>free_breakfast</md-icon>
                        <span class="md-list-item-text">Simple Shake</span>
                    </md-list-item>
                </router-link>

                <router-link to="/complex">
                    <md-list-item>
                        <md-icon>local_drink</md-icon>
                        <span class="md-list-item-text">Complex Shake</span>
                    </md-list-item>
                </router-link>

                <router-link to="/shplst">
                    <md-list-item>
                        <md-icon>local_grocery_store</md-icon>
                        <span class="md-list-item-text">Shopping List</span>
                    </md-list-item>
                </router-link>

                <router-link to="/favs">
                    <md-list-item>
                        <md-icon>favorite</md-icon>
                        <span class="md-list-item-text">Favorites</span>
                    </md-list-item>
                </router-link>
            </md-list>
        </md-drawer>  

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
        <md-snackbar md-position="center" :md-duration="20000" :md-active.sync="notificationActive">
            <span>You can configure some personal preferences.</span>
            <md-button class="md-raised md-accent" @click="notificationActive = false">
                Dismiss
            </md-button>
            <router-link to="/prefs" tag="div">
                <md-button class="md-raised md-primary" @click="setNotified">
                    Preferences
                </md-button>
            </router-link>
        </md-snackbar>
    </div>
</template>

<script>
import * as storage from '@/service/storage'
import {getUserByEmail,  setNotified} from '@/api/user'

export default {
    data() {
        return {
            userLoggedIn: storage.isLoggedIn(),
            showNavigation: false,
            notificationActive: false,
            userId: null,
        }
    },
    mounted(){
        this.checkNotification()
    },
    methods: {
        logout() {
            storage.logout()
            this.userLoggedIn = false
            this.$router.push('/simple')
        },
        toggleNavigation() {
            this.showNavigation=!this.showNavigation
        },
        checkNotification() {
            if (this.userLoggedIn) {
                getUserByEmail(storage.getEmail()).then(user => {
                    this.userId = user.id
                    this.notificationActive = !user.noticed
                })
            }
        },
        setNotified() {
            setNotified({
                id: this.userId,
                noticed: true,
            }).then(() => this.notificationActive = false)
        },
    },
    watch: {
        $route(route, oldRoute) {
            this.userLoggedIn = storage.isLoggedIn()
            if (route.path === '/complex' && oldRoute.path === '/login')
                this.checkNotification()
        }
    }
}
</script>

<style scoped>
.header{
    width: 100%;
    background-color: rgba(255, 255, 255, .3);
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.md-drawer {
    width: 230px;
    max-width: calc(100vw - 125px);
  }
.logo{
    width: 100px;
    height: 100%;
    background-color: aqua;
    cursor: pointer;
}
</style>
