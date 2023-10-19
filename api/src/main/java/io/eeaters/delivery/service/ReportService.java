package io.eeaters.delivery.service;

import io.eeaters.delivery.entity.vo.report.resp.DashboardResp;
import io.eeaters.delivery.enums.ChannelEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ReportService {


    public DashboardResp mockDashboard() {
        DashboardResp resp = new DashboardResp();

        ThreadLocalRandom random = ThreadLocalRandom.current();

        List<DashboardResp.ChannelInfo> channelInfos = new ArrayList<>();
        Integer completeNum=0,onGoing=0,cancelNum = 0;
        for (ChannelEnum value : ChannelEnum.values()) {
            DashboardResp.ChannelInfo channelInfo = new DashboardResp.ChannelInfo();
            channelInfo.setChannel(value.getCode());
            channelInfo.setCompleteNum(random.nextInt(100));
            channelInfo.setOngoingNum(random.nextInt(10));
            channelInfo.setCancelNum(random.nextInt(20));

            completeNum += channelInfo.getCompleteNum();
            onGoing += channelInfo.getOngoingNum();
            cancelNum += channelInfo.getCancelNum();

            channelInfos.add(channelInfo);
        }
        DashboardResp.ChannelInfo all = new DashboardResp.ChannelInfo();
        all.setCompleteNum(completeNum);
        all.setOngoingNum(onGoing);
        all.setCancelNum(cancelNum);

        resp.setSubChannels(channelInfos);
        resp.setAll(all);
        return resp;
    }

}
