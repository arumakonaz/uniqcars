import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/HomeView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginView.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('@/views/RegisterView.vue')
  },
  {
    path: '/forgot-password',
    name: 'forgot-password',
    component: () => import('@/views/ForgotPasswordView.vue')
  },
  {
    path: '/renew-password',
    name: 'renew-password',
    component: () => import('@/views/RenewPasswordView.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('@/views/ProfileView.vue')
  },
  {
    path: '/cars',
    name: 'cars',
    component: () => import('@/views/CarsView.vue')
  },
  {
    path: '/cars/:id',
    name: 'car',
    component: () => import('@/views/SingleCarView.vue')
  },
  {
    path: '/partners',
    name: 'partners',
    component: () => import('@/views/PartnersView.vue')
  },
  {
    path: '/partners/:id',
    name: 'partner',
    component: () => import('@/views/SinglePartnerView.vue')
  },
  { path: '/privacy-policy',
    name: 'privacy-policy',
    component: () => import('@/views/PrivacyPolicyView.vue')
  },
  {
    path: '/user-agreement',
    name: 'user-agreement',
    component: () => import('@/views/UserAgreementView.vue')
  },
  {
    path: '/invoice/:carId',
    name: 'invoice',
    component: () => import('@/views/InvoiceView.vue')
  },
  {
    path: '/approved',
    name: 'approved',
    component: () => import('@/views/ApprovedView.vue')
  },
  {
    path: '/canceled',
    name: 'canceled',
    component: () => import('@/views/CanceledView.vue')
  },
  {
    path: '/contacts',
    name: 'contacts',
    component: () => import('@/views/ContactsView.vue')
  },
  // for routes that don't exist
  {
    path: '*',
    name: 'not-found',
    component: () => import('@/views/NotFoundView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
