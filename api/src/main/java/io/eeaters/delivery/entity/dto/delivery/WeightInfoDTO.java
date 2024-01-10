package io.eeaters.delivery.entity.dto.delivery;

import lombok.Data;

import java.util.List;

/**
 * 计算配送渠道权重的信息
 */
@Data
public class WeightInfoDTO {

    //方案权重规则
    private Integer type;

    private List<ChannelWeightInfo> infoList;

    @Data
    public static class ChannelWeightInfo{

        //三方渠道
        private String channel;

        //排序
        private Integer sort;

        //距离
        private Integer distance;

        //配送价格
        private String price;

        //配送耗时
        private Integer duration;
    }
}
