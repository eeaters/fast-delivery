import request from "@/util/request.ts";


enum API{
    REGION = "common/region/all",
    CHANNEL = "common/channel/all"
}

export const region = () => request.get<any, Cascade[]>(API.REGION);

export const channel = () => request.get<any, Channel[]>(API.CHANNEL);