interface StoreBaseInfo{
    storeName:string,
    storeCode:string,
}



interface StoreAddReq extends StoreBaseInfo{
    id: number | ?;
    provinceId:number | ?,
    cityId:number | ?,
    districtId: number | ?,
    address:string
    latitude:string,
    longitude:string,
    storeContract: string,
    storeContractPhone: string,
    planId:number | ?,
    operateUser:{
        userName: string;
    }
}

interface StorePageReq extends StoreBaseInfo{
    pageQuery: PageReq
}



// req  ↑
// resp ↓

interface StoreInfo extends StoreBaseInfo{
    id: number;
    provinceName: string,
    provinceId:number,
    cityName:string,
    cityId:number,
    districtName: string | ?,
    districtId: number | ?,
    address:string
    latitude:string,
    longitude:string,
    storeContract: string,
    storeContractPhone: string,
    planId:number,
    planName: string | ?;
}

interface StorePageResp extends PageRes{
    list: StoreInfo[];
}

