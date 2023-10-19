import {defineStore} from "pinia";
import {login} from "@/api/user";
import {GET_USERINFO, REMOVE_USERINFO, SET_USERINFO} from "@/util/localStorage.ts";

const userStore = defineStore('user', {
    state: (): UserInfo => {
        return {
            userInfo: JSON.parse(GET_USERINFO() as string) || {},
        }
    },
    actions: {
        async login(loginData: UserLoginData) {
            this.userInfo = await login(loginData);
            SET_USERINFO(this.userInfo)
        },

        logout() {
            //notify backend serve
            this.userInfo = {};
            REMOVE_USERINFO();
        }
    }
})

export default userStore;
