import VueRouter from 'vue-router'
import SimpleShake from '@/components/SimpleShake.vue'
import Register from '@/components/Register.vue'
import Login from '@/components/Login.vue'
import ComplexShake from '@/components/ComplexShake.vue'
import * as storage from '@/service/storage'
import PermPrefs from '@/components/PermPrefs.vue'
import ShoppingList from '@/components/ShoppingList.vue'
import Favorites from '@/components/Favorites.vue'
import Contact from '@/components/Contact.vue'
import Menu from '@/components/Menu.vue'
import Recipes from '@/components/Recipes.vue'
import Clerk from '@/components/Clerk.vue'
import AdminHome from '@/components/AdminHome.vue'
import AdminIngredients from '@/components/AdminIngredients.vue'
import AdminPremade from '@/components/AdminPremade.vue'
import AdminUser from '@/components/AdminUser.vue'

const router = new VueRouter({
    routes: [
        {
            path: '',
            beforeEnter: (to,from,next) => {
                if (storage.isBasic()) {
                    next('/menu')
                } else if (storage.isClerk()) {
                    next('/clerk')
                } else if (storage.isAdmin()) {
                    next('/admin/home')
                }
            }
        },
        {
            path: '/simple',
            component: SimpleShake,
        },
        {
            path: '/complex',
            component: ComplexShake,
            beforeEnter: (to,from,next) => {
                if (storage.isBasic()){
                    next()
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
            beforeEnter: (to,from,next) => {
                if (storage.isBasic()){
                    next()
                }
            }
        },
        {
            path: '/shplst',
            component: ShoppingList,
            beforeEnter: (to,from,next) => {
                if (storage.isBasic()){
                    next()
                }
            }
        },
        {
            path: '/favs',
            component: Favorites,
            beforeEnter: (to,from,next) => {
                if (storage.isBasic()){
                    next()
                }
            }
        },
        {
            path: '/contact',
            component: Contact,
        },
        {
            path: '/menu',
            component: Menu,
            beforeEnter: (to,from,next) => {
                if (storage.isBasic()){
                    next()
                }
            }
        },
        {
            path: '/recipes',
            component: Recipes,
            beforeEnter: (to,from,next) => {
                if (storage.isBasic()){
                    next()
                }
            }
        },
        {
            path: '/clerk',
            component: Clerk,
            beforeEnter: (to,from,next) => {
                if (storage.isClerk()){
                    next()
                }
            }
        },
        {
            path: '/admin/home',
            component: AdminHome,
            beforeEnter: (to,from,next) => {
                if (storage.isAdmin()){
                    next()
                }
            }
        },
        {
            path: '/admin/users',
            component: AdminUser,
            beforeEnter: (to,from,next) => {
                if (storage.isAdmin()){
                    next()
                }
            }
        },
        {
            path: '/admin/premade',
            component: AdminPremade,
            beforeEnter: (to,from,next) => {
                if (storage.isAdmin()){
                    next()
                }
            }
        },
        {
            path: '/admin/ingr',
            component: AdminIngredients,
            beforeEnter: (to,from,next) => {
                if (storage.isAdmin()){
                    next()
                }
            }
        }
    ]
})

router.beforeEach((to, from, next) => {
    if (to.path === '/login' || to.path === '/register' || to.path === '/simple' || to.path === '/contact' || storage.isLoggedIn()){
        next()
    } else {
        next('/simple')
    }
})

export default router