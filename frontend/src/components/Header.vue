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
    </div>
</template>

<script>
import * as storage from '@/service/storage'

export default {
    data() {
        return {
            userLoggedIn: storage.isLoggedIn(),
            showNavigation: false,
        }
    },
    methods: {
        logout() {
            storage.logout()
            this.userLoggedIn = false
            this.$router.push('/simple')
        },
        toggleNavigation() {
            this.showNavigation=!this.showNavigation
        }
    },
    watch: {
        $route() {
            this.userLoggedIn = storage.isLoggedIn()
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
