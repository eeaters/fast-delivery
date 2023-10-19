interface OrderListReq{
    orderCode: string | ?,
    storeNo: string |?,
    startTime: string,
    endTime: string,
    pageQuery: PageReq
}








//request  ↑
//response ↓

interface OrderInfo {
    storeCode: string,
    orderCode: string,
    receiveUserName: string,
    receiveUserPhone: string,
    receiveUserAddress: string,
    totalPrice: number,
    orderTime: string,
    weightInfo: string | ?,
    isMock: boolean | ?;
}

interface DetailInfo{
    orderId:number,
    orderCode:string,
    channelOrderId:string,
    channelPrice:number,
    status:number,
    pushTime:string,
    riderReceiveTime:string,
    arriveStoreTime:string,
    deliveringTime:string,
    completeTime:string,
    errorTime:string,
    cancelTime:string,
    errorCode:string,
    errorMessage:string,
    cancelCode:string,
    cancelMessage:string,
    riderName:string,
    riderPhone: string
}

interface OrderInfoPage extends PageRes{
    list: OrderInfo[];
}

