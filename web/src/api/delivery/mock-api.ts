import request from "@/util/request.ts";

enum API{
    MOCK_INFO = "mock/delivery",

    CREATE_DELIVERY = "delivery/create",

}

export const mock_delivery_create = (req:DeliveryCreateReq) => request.post<any,any>(API.CREATE_DELIVERY, req);

export const mock_delivery = () => request.get<any, DeliveryCreateReq>(API.MOCK_INFO);
