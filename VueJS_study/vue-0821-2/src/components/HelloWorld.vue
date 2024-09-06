<template>
  <div class="hello">
    <h1>{{ props.msg }}</h1>
    <h1>{{ props.number }}</h1>
    <!-- slot은 부모에서 전달 받은 부분 할당하는 영역-->
    <slot></slot>
    <slot></slot>
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
  emit('send-message', '마운트 되는 순간 자식 컴포넌트에서 보냄(메시지1)')
})

const sendMsgToParent = () => {
  emit('sendMsgToParent', '버튼 클릭 시 자식 컴포넌트에서 보냄(메시지2)')
}


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!-- "scoped"를 적용하면 해당 컴포넌트에 한정되어 적용 -->
<style scoped> 

div {
  background-color: #f5d682;
  border: 1px solid red;
}

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
