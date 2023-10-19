export const GET_USERINFO = ()=>{
    return localStorage.getItem("USER_INFO");
}

export const SET_USERINFO = (userInfo: UserInfo) => {
    localStorage.setItem("USER_INFO", JSON.stringify(userInfo));
};

export const REMOVE_USERINFO = () => {
    localStorage.removeItem("USER_INFO");
};