// 查询入参
interface PlanPageReq {
    planName: string | ?,
    pageQuery: PageReq
}

interface PlanBaseInfo{
    id: number | ?,
    planName: string,
}

interface PlanInfo extends PlanBaseInfo{
    desc: string,
    type: number,
    timePeriod: number;
    mappingList: PlanChannelMapping[]
}

interface PlanChannelMapping {
    id: number | ?,
    channel: string,
    channelName: string,
    sort: number;
}

interface PlanAdd extends PlanInfo{
    operateUser: {userName: string},
}


// 响应结果
interface PlanPageResp extends PageRes{
    list: PlanInfo[];
}

