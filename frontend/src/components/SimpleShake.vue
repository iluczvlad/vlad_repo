<template>
    <div class="simple-shake">
        <!-- <md-button class="md-raised" @click="makeSimpleShake">Make a Shake</md-button>
        <IngredientTable v-if="simpleShake" :shake-arr="simpleShakeArr"/> -->
        <div class="central-logo">
            <carousel :autoplay="true" :perPage="1" :loop="true" :paginationEnabled="false">
                <slide>
                    <img class="carousel-img" alt="1" src="../assets/sl1.jpg">
                </slide>
                <slide>
                    <img class="carousel-img" alt="2" src="../assets/sl2.jpg">
                </slide>
                <slide>
                    <img class="carousel-img" alt="3" src="../assets/sl3.jpg">
                </slide>
            </carousel>
        </div>
        <div class="buttons-home">
            <md-button class="md-raised md-primary" @click="$router.push('login')" v-if="!userLoggedIn">
                Login
            </md-button>
            <md-button class="md-raised md-accent" @click="$router.push('register')"  v-if="!userLoggedIn">
                Sign Up
            </md-button>
        </div>
        <router-link to="/contact" class="contact-link">
            Contact
        </router-link>
    </div>
</template>

<script>
import {generateSimpleShake} from '@/api/shake.js'
import IngredientTable from './IngredientTable.vue'
import * as storage from '@/service/storage'

export default {
    name: 'SimpleShake',
    components: {
        IngredientTable,
    },
    data() {
        return {
            simpleShake: null,
            userLoggedIn: storage.isLoggedIn(),
        }
    },
    methods: {
        makeSimpleShake() {
            generateSimpleShake().then((response) => {
                this.simpleShake=response
            })
        }
    },
    computed: {
        simpleShakeArr() {
            return Object.entries(this.simpleShake).map(arr => arr[1])
        },
    }
}
</script>

<style scoped>
.simple-shake {
    display: flex;  
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    height: 100%;
}
.central-logo {
    width: 85%;
    height: 275px;
    margin-top: 30px;
    margin-bottom: 50px;
}
.contact-link {
    color: black !important;
}
.carousel-img {
    width: inherit;
}
</style>
