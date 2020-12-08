import Vue from 'vue'
import VueRouter from 'vue-router'
const login =()=>import("@/views/login/login.vue")
const home =()=>import("@/views/layout/home.vue")
const user =()=>import("@/views/user/user.vue")
Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    redirect:"/login"
  },
  {
    path:"/login",
    component:login,
  },
  {
    path:"/home",
    component:home,
    redirect:'/user',
    children:[
      { path:'/user', component:user}
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
