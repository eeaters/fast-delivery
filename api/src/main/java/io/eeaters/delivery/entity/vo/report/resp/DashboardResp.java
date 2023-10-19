package io.eeaters.delivery.entity.vo.report.resp;

import lombok.Data;

import java.util.List;

@Data
public class DashboardResp {

    private ChannelInfo all;

    private List<ChannelInfo> subChannels;

    @Data
    public static class ChannelInfo{

        private String channel;

        private Integer completeNum;

        private Integer cancelNum;

        private Integer ongoingNum;
    }
}
