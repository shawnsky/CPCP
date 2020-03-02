import Vue from 'vue'
import router from './router'
import Antd from 'ant-design-vue'
import App from './App.vue'
import axios from 'axios'


import 'ant-design-vue/dist/antd.css'

Vue.config.productionTip = false

Vue.use(Antd)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

axios.interceptors.request.use(
  function(config) {
    if (localStorage.token){
        config.headers.Authorization = localStorage.token
    }
    return config;
  },
  function(error) {
    return Promise.reject(error);
  }
);