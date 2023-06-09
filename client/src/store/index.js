import Vuex from 'vuex'
import Vue from 'vue'
import authStore from '@/store/modules/authStore'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    authStore
  }
})
