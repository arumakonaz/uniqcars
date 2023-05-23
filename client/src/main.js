import Vue from 'vue'
import App from '@/App.vue'
import '@/registerServiceWorker'
import router from '@/router'

import store from '@/store/index.js'
 
import VueI18n from 'vue-i18n'
import kz from '@/locales/kz.json'
import ru from '@/locales/ru.json'
import en from '@/locales/en.json'

import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { getAxiosInstance } from '@/utils/api'

import plugin, { setGlobalConfig } from '@serializedowen/vue-img-watermark'
import VueMask from 'v-mask'
import GeneralMixin from '@/mixins/GeneralMixin'

import { BootstrapVue, IconsPlugin, ToastPlugin } from 'bootstrap-vue'
import 'flag-icons/css/flag-icons.min.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@/style/app.scss'

Vue.config.productionTip = false

Vue.use(VueMask)
Vue.directive('mask', VueMask.VueMaskDirective);
Vue.use(BootstrapVue)
Vue.use(ToastPlugin)
Vue.use(IconsPlugin)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.mixin(GeneralMixin)

Vue.use(plugin)

setGlobalConfig({
  mode: 'topleft',
  content: 'UNIQCARS',
  fillStyle: 'white',
  textBaseline: 'middle',
  font: '1.3rem Arial'
})

Vue.use(VueI18n)

Vue.prototype.$http = getAxiosInstance()

const messages = {
  kz: kz,
  ru: ru,
  en: en
}

// Create VueI18n instance with options
const i18n = new VueI18n({
  locale: 'ru',
  messages
})

new Vue({
  i18n,
  router,
  store,
  ...App
})
