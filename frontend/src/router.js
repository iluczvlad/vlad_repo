import VueRouter from 'vue-router'
import SimpleShake from '@/components/SimpleShake.vue'
import Register from '@/components/Register.vue'

const router = new VueRouter({
    routes: [
        {
            path: '',
            component: SimpleShake,
        },
        {
            path: '/register',
            component: Register,
        }
    ]
})

export default router