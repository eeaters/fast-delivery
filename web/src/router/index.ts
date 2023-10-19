import {createRouter, createWebHashHistory} from "vue-router";

const route = [
    {
        path: '/login',
        component: () => import( '@/views/login/index.vue'),
        name: 'login'
    },
    {
        path: "/",
        component: () => import( '@/views/home/index.vue'),
        name: 'home',
        children:[
            {
                path: "home",
                component: () => import("@/views/home/dashboard/index.vue"),
            },
            {
                path: "plan",
                component: () => import("@/views/home/plan/index.vue"),
            },
            {
                path: "store",
                component: () => import("@/views/home/store/index.vue"),
            },
            {
                path: "delivery",
                component: () => import("@/views/home/delivery/index.vue"),
            },
        ]
    },
    {
        path: "/404",
        component: () => import( '@/views/404/index.vue'),
        name: '404'
    },
    {
        path: "/:pathMatch(.*)*",
        redirect: '/404',
        name: 'any'
    }
]

export default createRouter({
    history: createWebHashHistory(),
    routes: route,
    //切换路由时, 滚动条立马调到最上方
    scrollBehavior(){
        return {
            left: 0,
            top: 0,
        }
    }
})