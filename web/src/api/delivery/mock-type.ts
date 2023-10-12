interface DeliveryCreateReq {
    callbackUrl: string,
    orderCode: string,
    orderTime: string,
    receiveUserAddress:string,
    receiveUserLongitude:string,
    receiveUserLatitude:string,
    receiveUserName:string,
    receiveUserPhone:string,
    storeCode:string,
    totalPrice:string,
    weightGram:string,
    detailInfos: ProductInfo[];
}

