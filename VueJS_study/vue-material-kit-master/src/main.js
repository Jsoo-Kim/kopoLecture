import { createApp } from "vue";
import { createPinia } from "pinia";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import Mock from "@/mock.js";  // @ 이건 src (설정 파일 어딘가에 정의되어 있음!)

// Nucleo Icons
import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";

import materialKit from "./material-kit";

const app = createApp(App);

//mock
const axiosClient = axios.create({});
Mock.mocked(axiosClient); // mock.js에 정의되어 있는 함수! (mocked) => 목 데이터로 테스트 끝나면 이 줄을 주석처리 하면 됨
app.provide("$axios", axiosClient);

app.use(createPinia());
app.use(router);
app.use(materialKit);
app.mount("#app");
