import request from "@/util/request.ts";

enum API{
    PAGE = "delivery/order/page",
    DETAIL = "delivery/detail"
}

export const order_page = (req: OrderListReq) => request.post<any, OrderInfoPage>(API.PAGE, req);

export const delivery_detail = (orderCode: string) => request.get<any, DetailInfo>(API.DETAIL + "?orderCode=" + orderCode);