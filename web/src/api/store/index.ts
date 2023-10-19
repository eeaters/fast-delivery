import request from "@/util/request.ts";

enum API{
    STORE_PAGE = "store/page",
    STORE_ADD = "store/add",

    STORE_ALL = "store/listAll",
}

export const store_page = (req: StorePageReq) => request.post<any, StorePageResp>(API.STORE_PAGE, req);

export const store_page_add = (req: StoreAddReq) => request.post<any, any>(API.STORE_ADD, req);

export const store_list_all = () => request.get<any, StoreBaseInfo[]>(API.STORE_ALL);
