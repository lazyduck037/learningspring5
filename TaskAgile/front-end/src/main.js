import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Vuelidate from 'vuelidate'
import { i18n } from './i18n'
import eventBus from './event-bus'
import realTimeClient from '@/real-time-client'

Vue.use(Vuelidate)
Vue.config.productionTip = false
Vue.prototype.$bus = eventBus
Vue.prototype.$rt = realTimeClient

Vue.config.productionTip = false

axios.defaults.baseURL = '/api'
axios.defaults.headers.common.Accept = 'application/json'
axios.interceptors.response.use(
  response => response,
  (error) => {
    return Promise.reject(error)
  }
)

new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')
