<template>
    <div class="header">
        
        <div class="menu-icon-size">
            <md-button class="md-icon-button" @click="toggleNavigation" v-if="userLoggedIn && !isClerk">
                <md-icon>menu</md-icon>
            </md-button>
        </div>
        
        <router-link class="home-logo" to="/">
            <div><img alt="Vue logo" src="../assets/logo.png"></div>
        </router-link>

        <div class="log-buttons">
            <md-button class="md-raised md-accent" @click="logout" v-if="userLoggedIn">
                Sign Out
            </md-button>
        </div>
        <md-drawer v-if="userLoggedIn" :md-active.sync="showNavigation">
            <md-toolbar class="md-transparent" md-elevation="0">
                <span class="md-title">Menu</span>
            </md-toolbar>

            <md-list>
                
                <router-link to="/menu" v-if="isBasic">
                    <md-list-item>
                        <md-icon>list</md-icon>
                        <span class="md-list-item-text">Menu</span>
                    </md-list-item>
                </router-link>

                <router-link to="/recipes" v-if="isBasic">
                    <md-list-item>
                        <md-icon>local_drink</md-icon>
                        <span class="md-list-item-text">Our Recipes</span>
                    </md-list-item>
                </router-link>

                <!-- <router-link to="/simple">
                    <md-list-item>
                        <md-icon>free_breakfast</md-icon>
                        <span class="md-list-item-text">Simple Shake</span>
                    </md-list-item>
                </router-link> -->

                <router-link to="/complex" v-if="isBasic">
                    <md-list-item>
                        <md-icon>rotate_right</md-icon>
                        <span class="md-list-item-text">Randomiser</span>
                    </md-list-item>
                </router-link>

                <router-link to="/favs" v-if="isBasic">
                    <md-list-item>
                        <md-icon>favorite</md-icon>
                        <span class="md-list-item-text">Favorites</span>
                    </md-list-item>
                </router-link>

                <router-link to="/prefs" v-if="isBasic">
                    <md-list-item>
                        <md-icon>person</md-icon>
                        <span class="md-list-item-text">Preferences</span>
                    </md-list-item>
                </router-link>

                <router-link to="/shplst" v-if="isBasic">
                    <md-list-item>
                        <md-icon>local_grocery_store</md-icon>
                        <span class="md-list-item-text">Cart</span>
                    </md-list-item>
                </router-link>

                <router-link to="/admin/home" v-if="isAdmin">
                    <md-list-item>
                        <span class="md-list-item-text">Home</span>
                    </md-list-item>
                </router-link>

                <router-link to="/admin/users/create" v-if="isAdmin">
                    <md-list-item>
                        <span class="md-list-item-text">Users Creation</span>
                    </md-list-item>
                </router-link>

                <router-link to="/admin/users" v-if="isAdmin">
                    <md-list-item>
                        <span class="md-list-item-text">Users Management</span>
                    </md-list-item>
                </router-link>

                <router-link to="/admin/premade" v-if="isAdmin">
                    <md-list-item>
                        <span class="md-list-item-text">Recipes Management</span>
                    </md-list-item>
                </router-link>

                <router-link to="/admin/ingr" v-if="isAdmin">
                    <md-list-item>
                        <span class="md-list-item-text">Ingredients Management</span>
                    </md-list-item>
                </router-link>
            </md-list>
        </md-drawer>

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
            role: null,
        }
    },
    mounted(){
        this.checkNotification()
    },
    methods: {
        logout() {
            storage.logout()
            this.userLoggedIn = false
            this.role = null
            this.$router.push('/simple')
        },
        toggleNavigation() {
            this.showNavigation=!this.showNavigation
        },
        checkNotification() {
            if (this.userLoggedIn) {
                getUserByEmail(storage.getEmail()).then(user => {
                    this.userId = user.id
                    if (user.role === 'BASIC'){
                        this.notificationActive = !user.noticed
                    }
                    this.role = user.role
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
    computed: {
        isAdmin() {
            return this.role && this.role === "ADMIN"
        },
        isBasic() {
            return this.role && this.role === "BASIC"
        },
        isClerk() {
            return this.role && this.role === "CLERK"
        }
    },
    watch: {
        $route(route, oldRoute) {
            this.showNavigation = false
            this.role = storage.getRole()
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
.menu-icon-size {
    width: 92px;
}
.md-drawer {
    width: 230px;
    max-width: calc(100vw - 125px);
  }
.home-logo{
    width: 200px;
    height: 90%;
    cursor: pointer;
}
</style>
