<template>
  <div class="about">
    <h1>This is an about page</h1>
  </div>
</template>
<script setup>
// mitt
import { getCurrentInstance, onMounted } from 'vue';
const internalInstance = getCurrentInstance();
const emitter = internalInstance.appContext.config.globalProperties.emitter;

// hooks
onMounted(() => {
  emitter.emit('testEvent', "this is a message.") // mitt
})

</script>
