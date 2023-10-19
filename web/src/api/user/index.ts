import request from "@/util/request.ts";

enum API{
    LOGIN = "user/login",
}

export const login = (loginData: UserLoginData) => request.post<any, UserInfo>(API.LOGIN, loginData);