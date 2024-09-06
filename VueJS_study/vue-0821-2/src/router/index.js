import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import KopoView from '../views/KopoView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView // 위에 import 되어 있어서 그냥 이렇게 쓸 수 있음!
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  // {
  //   path: '/kopo',
  //   name: 'kopo',
  //   component: () => import( '../views/KopoView.vue')
  // },
  {
    path: '/kopo',
    name: 'kopo',
    component: KopoView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
