<!-- <template>
  <img alt="Vue logo" src="./assets/logo.png">
  <p><button @click="addCount">{{ count }}</button></p>
  <HelloWorld 
    msg="하하하하" 
    v-bind:number="count"
    @send-message="receiveMessage"
    @sendMsgToParent="receiveMsgFromChild">
    <h1>엘리먼트</h1>
    </HelloWorld>
    <h2>부모 영역</h2>

</template> -->

<!-- <script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script> -->

<!-- <script setup> 
import HelloWorld from './components/HelloWorld.vue'
import { ref } from 'vue'

const count = ref(0)

const addCount = () => {
  count.value += 1
}
const receiveMessage = (par) => {
  console.log("자식 메시지1: ", par);
}
const receiveMsgFromChild = (param) => {
  console.log("자식 메시지2: ", param);
}

</script>

<style>

div {
  background-color: #86e286;
  border: 1px solid rgb(0, 66, 128);
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style> -->


<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/kopo">Kopo</router-link>
  </nav>
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

