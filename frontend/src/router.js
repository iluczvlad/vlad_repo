import VueRouter from 'vue-router'
import SimpleShake from '@/components/SimpleShake.vue'
import Register from '@/components/Register.vue'
import Login from '@/components/Login.vue'
import ComplexShake from '@/components/ComplexShake.vue'
import * as storage from '@/service/storage'
import PermPrefs from '@/components/PermPrefs.vue'
import ShoppingList from '@/components/ShoppingList.vue'
import Favorites from '@/components/Favorites.vue'


const router = new VueRouter({
    routes: [
        {
            path: '',
            redirect: '/complex'
        },
        {
            path: '/simple',
            component: SimpleShake,
        },
        {
            path: '/complex',
            component: ComplexShake,
            beforeEnter: (to,from,next) => {
                if (storage.isLoggedIn()){
                    next()
                } else {
                    next('/simple')
                }
            }
        },
        {
            path: '/register',
            component: Register,
        },
        {
            path: '/login',
            component: Login,
        },
        {
            path: '/prefs',
            component: PermPrefs,
        },
        {
            path: '/shplst',
            component: ShoppingList,
        },
        {
            path: '/favs',
            component: Favorites,
        }
    ]
})

export default router