<script setup> 
// App.vue 에 자식 컴포넌트가 2개! (HelloWorld 뷰와 TheWelcome 뷰가 페이지 안에서 좌, 우 2개로 나뉘어짐)
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
// ref를 사용해야 '기본타입 자료형, object, array' 의 변경사항이 template에서도 적용됨
// reactive를 사용하면 .value 없이 사용 가능! 단, '기본타입 자료형' 에는 사용 못 하며 많이 쓰면 느려짐
import { ref, reactive } from 'vue' 

////// 변수 선언 영역 //////
// ref
const count = ref(0) // ref(객체)를 씌워야 template와 script의 변수가 서로 반응함(reactive) => MVVM 구조

// reactive (꼭 필요하지 않으면 안 씀! 많이 쓰면 느려지기 때문!!)
const state = reactive({
  count: 0
})

// 템플릿 문법: Boolean 속성
const isButtonDisabled = ref(true)

// 템플릿 문법: 여러 속성 바인딩
const objectOfAttrs = {
  id: 'container',
  class: 'wrapper'
}

////// 함수 선언 영역 //////
// function increment() {
// } 요즘에 함수 선언 이렇게 안 함! 애로우 문법을 주로 씀
const increment = () => {
  count.value += 1
  // alert(count.value)
  console.log("count: ", count.value);
  
}
const buttonClicked = () => {
  isButtonDisabled.value != isButtonDisabled.value
}

</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />
      <!-- ref -->
      <button @click="increment">{{ count }}</button><br>

      <!-- reactive -->
      <button @click="state.count++">{{ state.count }}</button>

      <!-- 템플릿 문법: v-bind -->
      <div id="count"></div>
      <div v-bind:id="count"></div> <!-- 여기에서 count는 변수임! -->
      <div :id="count"></div> <!-- 'v-bind'의 축약 형태! 여기에서 count는 변수임! -->

      <!-- 템플릿 문법: Boolean 속성 -->
      <button :disabled="isButtonDisabled">{{ count }}</button><br>
      <button :disabled="count >= 20">하하하</button><br>

      <!-- 템플릿 문법: JavaScript 표현식 사용 -->
      {{ count + 1 }}<br>
      {{ count > 20 ? '20초과' : '20이하' }}<br>
      {{ objectOfAttrs.id.split('').reverse().join('') }}<br>
    </div>
  </header>

  <main>
    <TheWelcome />
  </main>

</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
