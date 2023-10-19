import request from "@/util/request.ts";


enum API{
    DASHBOARD = "/report/dashboard",
}

export const dashboard = (tab: number) => request.get<any, DashboardInfo>(API.DASHBOARD + "?period=" + tab);