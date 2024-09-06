<template>
    <div class="kopo">
        <h1>This is an kopo page</h1>
        <button @click="sendRequest">요청</button>
        <button @click="sendAsyncAwait">비동기요청</button>
        <button @click="sendAsyncOldStyle">비동기요청(old style)</button>
    </div>
    <p></p>
    {{ mycount.count }}
    <p></p>
    <button @click="increment">전역변수 count++</button>
    <div class="bitcoin">
        <h2>Bitcoin</h2>
        <p>{{ test2 }}</p>
        <p v-if="loading" class="loading-text">로딩중<span class="dots"></span></p>
        <!-- 데이터가 있을 때 표시 -->
        <div v-if="!loading && bitcoinData">
            <div v-for="(value, key) in bitcoinData.currencies" :key="key">
                <p>{{ key.toUpperCase() }}: {{ value[0] }} ({{ value[1] }})</p>
            </div>
            <p>Updated at: {{ bitcoinData.time }}</p>
        </div>
    </div>
</template>

<script setup>
import { ref, inject, onMounted, getCurrentInstance } from 'vue'; // 복습: ref, reactive 를 통해 정의된 변수는 template에서 직접 접근 가능! 값이 변경될 때마다 template이 자동으로 업데이트 됨
import { useCounterStore  } from '@/stores/counter'; // @ 는 src 


//// mitt (AboutView.vue 참고)
const internalInstance = getCurrentInstance();
const emitter = internalInstance.appContext.config.globalProperties.emitter;

const receiveEvt = (par) => {
    console.log(par);
}


//// Pinia 상태 관리 (counter.js 참고)
const mycount = useCounterStore()

onMounted(() => {
    console.log("마운트 되었음");
    mycount.count++
    emitter.on('test-event', receiveEvt) // 'test-event'가 발생하면 receiveEvt 함수 실행 (mitt 라이브러리를 사용한 이벤트 리스너) => KopoView 들어가서 마운팅이 되어야 리스닝이 되고 이렇게 리스닝 하고 있어야 이벤트가 실행됨! 
})
const increment = () => {
    mycount.increment()
}


//// axios
const axios = inject('$axios')
// 로딩 상태, 데이터 및 업데이트 시간을 저장하는 변수들
const loading = ref(false);
const bitcoinData = ref(null);

// axios + CORS
const sendRequest = () => {
    loading.value = true; // 요청 시작 시 로딩 중으로 설정
    axios.get('/v1/bpi/currentprice.json')  // 앞의 'https://api.coindesk.com' 은 vue.config.js 에 있음!
        .then(res => {
            console.log(res)
            bitcoinData.value = {
                currencies: {
                    usd: [res.data.bpi.USD.rate, res.data.bpi.USD.description],
                    gbp: [res.data.bpi.GBP.rate, res.data.bpi.GBP.description],
                    eur: [res.data.bpi.EUR.rate, res.data.bpi.EUR.description],
                },
                time: res.data.time.updated,
            };
        })
        .catch(error => {
            console.error("Error fetching data:", error);
        })
        .finally(() => {
            loading.value = false; // 요청 완료 후 로딩 상태 해제
        });
}

// const sendRequest = () => {
//     loading.value = true; // 요청 시작 시 로딩 중으로 설정
//     setTimeout(() => {
//         axios.get('https://api.coindesk.com/v1/bpi/currentprice.json')
//             .then(res => {
//                 console.log(res)
//                 bitcoinData.value = {
//                     currencies: {
//                         usd: [res.data.bpi.USD.rate, res.data.bpi.USD.description],
//                         gbp: [res.data.bpi.GBP.rate, res.data.bpi.GBP.description],
//                         eur: [res.data.bpi.EUR.rate, res.data.bpi.EUR.description],
//                     },
//                     time: res.data.time.updated,
//                 };
//             })
//             .catch(error => {
//                 console.error("Error fetching data:", error);
//             })
//             .finally(() => {
//                 loading.value = false; // 요청 완료 후 로딩 상태 해제
//             });
//     }, 3000); // 3초 지연 (그냥 로딩중 애니메이션 보고 싶어서 추가함)

// }

//// Async, Await
const requestPost = () => {
    const params = {
        name: "morpheus",
        job: "leader",
    };
    console.log("[START] axios Post");
    return axios
        .post(`/api/users`, params, {})
        .then((res) => {
            console.log("[END] axios Post : ", res);
            return "success"
        })
        .catch((res) => {
            console.error(res);
            return "Failure";
        })
}

const sendAsyncAwait = async () => {
    try {
        const resultValue = await requestPost();
        requestGet(resultValue);
    } catch (error) {
        console.error('Error occurred: ', error);
    }
}

const requestGet = (arg) => {
    console.log(`[START] axios Get,
            Call Back From Previous Axios Post = ${arg} `);
    axios
        .get(`/api/users?/page=1`, {})
        .then((res) => {
            console.log("[END] axios Get : ", res);
        })
        .catch((res) => {
            console.error("request get res : ", res);
        })
}

//// async, await old style
function sendAsyncOldStyle() {
    requestPost()
        .then((postRes) => {
            console.log("POST 응답 : ", postRes);
            return requestGet(postRes);  // POST 결과를 인자로 전달
        })
        .then((getRes) => {
            console.log("GET 응답 : ", getRes);
        })
        .catch((err) => {
            console.log("에러 발생 : ", err);  // POST 또는 GET 에러 처리
        });
}

</script>

<style scoped>
.loading-text {
    font-size: 1.5em;
    font-weight: bold;
    color: #3498db;
    /* 예쁜 파란색 */
    text-align: center;
    margin-top: 20px;
}

.dots::after {
    content: '';
    animation: dots 1.5s steps(5, end) infinite;
    /* 애니메이션 설정 */
}

@keyframes dots {

    0%,
    20% {
        content: '';
    }

    40% {
        content: '.';
    }

    60% {
        content: '..';
    }

    80% {
        content: '...';
    }

    100% {
        content: '';
    }
}
</style>