import MockAdapter from "axios-mock-adapter";

const testData1 = [

    {
        Idx: 0,
        Info: {
            name: 'hong gil dong',
            age: 40
        }
    },
    {
        Idx: 1,
        Info: {
            name: 'kim gil dong',
            age: 50
        }
    }
]

function parseQueryString(url) {
    const queryString = url.replace(/.*\?/, "");
    const result = {};

    if (queryString === url || !queryString) {
        return result;
    }

    const urlParams = new URLSearchParams(queryString);

    urlParams.forEach((val, key) => {
        if (Object.prototype.hasOwnProperty.call(result, key)) {
            result[key] = [result[key], val];
        } else {
            result[key] = val;
        }
    });

    return result;
}

// 여기 있는 url로 들어오면 mock으로 인터셉트 거는 것
export default { // export 했다는 건 어디선가 import 한다는 것
    mocked(axiosClient) {
        const mock = new MockAdapter(axiosClient, { onNoMatch: "passthrough" });

        // mock.onGet("/api/hello").reply(200, { hello: "world" }); 
        mock.onGet("/api/hello").reply(200, { hello: testData1 });

        mock.onGet(/\/api\/hello2?.*/).reply((config) => {  // '/api/hello2?~~' 
            const params = parseQueryString(config.url);  // 위에 정의된 함수 잘 뜯어보기
            return [200, { config, params }];
        });

        mock.onPost("/api/formPost").reply((config) => { // config는 axios의 요청 객체
            //const formData = config.data;​  // 요청의 body 부분
            const name = config.data.get("name");  // FormData의 내용은 FormData 자체의 메서드를 통해 접근할 수 있으며, config.data는 이 데이터를 단순한 객체 형태로 제공하지 않기 때문에 빈 객체로 표시될 수 있음
            return [200, { config: config, name: name }]; // 요청 객체도 응답 body(data)에 넣어서 보냄
        });

        mock.onPost("/api/postJson").reply((config) => {
            const data = JSON.parse(config.data);
            return [200, { data }];
        });

        mock.onGet("/api/delayHello").reply((config) => {
            const delay = 5000;
            return new Promise((resolve, reject) => {
                setTimeout(() => {
                    resolve([200, { hello: "world" }]);
                }, delay);
            });
        });

        // // 위와 같음
        // mock.onGet("/api/delayHello").reply(async (config) => {
        //     const delay = 5000;

        //     // Promise를 반환하는 delay 함수를 생성​
        //     const delayFunction = (ms) => new Promise(resolve => setTimeout(resolve, ms));

        //     // delay가 끝날 때까지 대기​
        //     await delayFunction(delay);

        //     // 응답 반환​
        //     return [200, { hello: "world" }];
        // });
    }
}