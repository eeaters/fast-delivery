import request from "@/util/request.ts";

enum API{
    ORDER_PAGE = "delivery/order/page",
    ORDER_INFO = "delivery/order/info",
    DETAIL = "delivery/latest"
}

export const order_page = (req: OrderListReq) => request.post<any, OrderInfoPage>(API.ORDER_PAGE, req);

export const order_info = (orderCode: string) => request.get<any, OrderDetailInfo>(API.ORDER_INFO + "?orderCode=" + orderCode);

export const delivery_detail = (orderCode: string) => request.get<any, DeliveryDetailInfo>(API.DETAIL + "?orderCode=" + orderCode);