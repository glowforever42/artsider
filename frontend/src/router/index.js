import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from './home'
import SignUp from './account/signUp'
import EmailConfirmation from './account/emailConfirmation'

Vue.use(VueRouter)

const routes = [
  ...Home,
  ...SignUp,
  ...EmailConfirmation
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
