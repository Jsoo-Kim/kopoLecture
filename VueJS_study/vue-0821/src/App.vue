<script setup> 
// App.vue 에 자식 컴포넌트가 2개! (HelloWorld 뷰와 TheWelcome 뷰가 페이지 안에서 좌, 우 2개로 나뉘어짐)
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
// ref를 사용해야 '기본타입 자료형, object, array' 의 변경사항이 template에서도 적용됨
// reactive를 사용하면 .value 없이 사용 가능! 단, '기본타입 자료형' 에는 사용 못 하며 많이 쓰면 느려짐
import { ref, reactive, computed, onMounted } from 'vue' 

////// 변수 선언 영역 //////
const author = reactive({
  name: 'John Doe',
  books: [
    'Book111',
    'Book222',
    'Book333',
  ]
})
const count = ref(0) 
const isShow = ref(true)
const items = ref([{ message: 'Foo' }, { message: 'Bar' }])
const userInputMessage = ref("");

////// 함수 선언 영역 //////
// 책이 1권 이상 있는지 확인
const hasBooks = computed(() => {
  return author.books.length > 0
})
// 책 한 권씩 삭제
const bookRemove = () => {
  author.books.pop()
}
// 새로운 책 추가
const bookAdd = () => {
  const timestamp = new Date().getTime(); // 고유한 타임스탬프 생성
  author.books.push(`Book${timestamp}`); // 책 제목에 타임스탬프 추가
}
const increment = (evt) => {
  count.value += 1
  isShow.value = !isShow.value

  alert(evt.target.tagName)
}

onMounted(() => {
  console.log('마운티드 함수가 호출됨');
})

oonUpdated(() => {
  console.log('업데이티드 함수가 호출됨');
})

createHydrationRenderer(() => {
  console.log('CREATED CALLED');
  
})

</script>

<template>
  <header>
    <img
      alt="Vue logo"
      v-bind:class="{logo: count > 3}"
      src="./assets/logo.svg"
      :width="count > 3 ? 12 : 125"
      height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <div>
        <h1>{{ hasBooks ? '책을 집필했습니다.' : '책을 집필하지 않았습니다.' }}</h1>
        <p><button v-on:click="bookRemove">책 한 권 삭제</button></p>
        <p><button v-on:click="bookAdd">책 한 권 추가</button></p>
        <button @click="increment($event)">{{ count }}</button><br>
        <ul>
          <li v-for="item in items" :key="item.message">
            {{ item.message }}
          </li>
          <li v-for="(item, index) in items" :key="item.message">
            {{ index }} - {{ item.message }}
          </li>
        </ul> 
  
        <h1 v-show="isShow">대한민국</h1>
        <h2 v-if="isShow">독립</h2>
        <h2 v-else>만세</h2>

        <input
        v-model="userInputMessage"
        placeholder="여기에 입력하세요" />
        <h1>{{ userInputMessage }}</h1>
      </div>

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
