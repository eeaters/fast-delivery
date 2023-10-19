import {createApp} from 'vue'
import App from './App.vue'
import '@/style/reset.scss';
import 'element-plus/dist/index.css'
import router from './router'
import pinia from '@/store'
import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

let app = createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(router)
    .use(pinia)
    .use(ElementPlus, {
        locale: zhCn
    })
    .mount('#app')
