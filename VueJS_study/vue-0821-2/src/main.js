import { createApp } from 'vue'
import App from './App.vue'
import router from './router'  // vue add router 하니까 추가됨
import axios from 'axios'
import { createPinia } from 'pinia'
import mitt from 'mitt'

// const app = createApp(App)
// const pinia = createPinia()
const emitter = mitt() 

// app.provide('$axios', axios); // axios 주입
// app.use(pinia)
// app.use(router).mount('#app')
// app.config.globalProperties.emmiter = emitter

const app = createApp(App)
  .provide('$axios', axios)  // 전역적으로 axios를 제공
  .use(createPinia())         // Pinia 상태 관리 사용
  .use(router)                // Vue Router 사용

// 전역 속성에 mitt 인스턴스를 추가
app.config.globalProperties.emitter = emitter;

app.mount('#app');