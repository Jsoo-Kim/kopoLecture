import { ref } from 'vue'
import { defineStore } from "pinia" // pinia 는 DB는 아니지만 비슷한 개념


// 다른 데서 쓸 거면 export 써 줘야 함
export const useCounterStore = defineStore('counter', () => { // counter 는 변수가 아니고 식별자! (식별하는 이름)

    const count = ref(1)
    
    function increment() {
      count.value++
      console.log("increment called");
    }

    return { count, increment }
  })