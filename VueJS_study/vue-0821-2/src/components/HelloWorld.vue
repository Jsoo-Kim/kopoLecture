<template>
  <div class="hello">
    <h1>{{ props.msg }}</h1>
    <h1>{{ props.number }}</h1>
    <p>
      KOPO
    </p>
    <p>
      <button @click="sendMsgToParent">메시지2 보내기</button>
    </p>
  </div>
</template>

<!-- <script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'HelloWorld',
  props: {
    msg: String,
    number: {
      type: Number,
      default: 0
    }
  }
}
</script> -->

<script setup>
import { defineProps, defineEmits, onMounted, } from 'vue'

const emit = defineEmits(['send-message', 'sendMsgToParent'])
const props = defineProps({
  msg: String,
  number: Number
})

onMounted(() => {
  emit('send-message', '자식 컴포넌트에서 보낸 메시지1')
})

const sendMsgToParent = () => {
  emit('sendMsgToParent', '자식 컴포넌트에서 보낸 메시지2')
}


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
