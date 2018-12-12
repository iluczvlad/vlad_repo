import VueRouter from 'vue-router'
import SimpleShake from '@/components/SimpleShake.vue'
import Register from '@/components/Register.vue'
import Login from '@/components/Login.vue'
import ComplexShake from '@/components/ComplexShake.vue'


const router = new VueRouter({
    routes: [
        {
            path: '',
            component: ComplexShake,
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