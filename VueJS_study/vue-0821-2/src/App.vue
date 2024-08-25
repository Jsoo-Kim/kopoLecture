<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <p><button @click="addCount">{{ count }}</button></p>
  <HelloWorld 
    msg="하하하하" 
    v-bind:number="count"
    @send-message="receiveMessage"
    @sendMsgToParent="receiveMsgFromChild" />
</template>

<!-- <script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script> -->

<script setup> 
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
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
