import request from "@/util/request.ts";


enum API{
    REGION = "common/region/all",
}

export const region = () => request.get<any, Cascade[]>(API.REGION);
