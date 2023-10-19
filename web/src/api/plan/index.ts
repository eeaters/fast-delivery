import request from "@/util/request.ts";

enum API{
    PLAN_PAGE = "/plan/page",
    PLAN_LIST_ALL = "/plan/listAll",
    PLAN_ADD = "/plan/add",
    PLAN_UPDATE = "/plan/update"
}

export const plan_page = (req: PlanPageReq) => request.post<any, PlanPageResp>(API.PLAN_PAGE, req);

export const plan_all = () => request.get<any, PlanBaseInfo[]>(API.PLAN_LIST_ALL);

export const plan_add = (req: PlanAdd) => request.post<any, any>(API.PLAN_ADD, req);

export const plan_update = (req: PlanAdd) => request.post<any, any>(API.PLAN_UPDATE, req);