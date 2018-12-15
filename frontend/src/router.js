import VueRouter from 'vue-router'
import SimpleShake from '@/components/SimpleShake.vue'
import Register from '@/components/Register.vue'
import Login from '@/components/Login.vue'
import ComplexShake from '@/components/ComplexShake.vue'
import * as storage from '@/service/storage'


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
        }
    ]
})

export default router