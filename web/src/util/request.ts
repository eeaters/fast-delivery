import axios from 'axios';
import {ElMessage} from "element-plus";
import userStore from "@/store/modules/user.ts";

const request = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    timeout: 5000,
})

request.interceptors.request.use(config => {
    let useUserStore = userStore()
    if (useUserStore.userInfo.token) {
        config.headers.token = useUserStore.userInfo.token
    }
    //todo
    if (config.url.endsWith("delivery/create")) {
        config.headers.mock = true;
    }
    return config;
});

request.interceptors.response.use(response => {
    return response.data;
}, error => {
    let status = error.response.status;
    switch (status) {
        case 404:
            ElMessage({
                type: 'error',
                message: "请求路径不存在!"
            })
            break;
        case 500 | 501 | 502 | 503 | 504 | 505:
            ElMessage({
                type: 'error',
                message: '服务器已宕机!'
            })
            break;
        case 401:
            ElMessage({
                type: 'error',
                message: '请求类型不正确!'
            })
            break;


    }
    return Promise.reject(new Error(error.message));
})
export default request;