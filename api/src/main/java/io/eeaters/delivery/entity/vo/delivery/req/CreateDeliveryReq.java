package io.eeaters.delivery.entity.vo.delivery.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateDeliveryReq {

    @Schema(name = "状态变更回调地址", example = "http://xxx.com/delivery/callback")
    private String callbackUrl;

    @Schema(name = "订单号", example = "1234567890")
    private String orderCode;

    @Schema(name = "用户下单时间", example = "2023-10-03 08:13:56")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    @Schema(name = "收货人地址", example = "3/4站台")
    private String receiveUserAddress;

    @Schema(name = "收货人经度", example = "12.544456")
    private String receiveUserLongitude;

    @Schema(name = "收货人经度", example = "42.544456")
    private String receiveUserLatitude;

    @Schema(name = "收货人姓名", example = "汤姆里德尔")
    private String receiveUserName;

    @Schema(name = "收货人电话", example = "13666666666")
    private String receiveUserPhone;

    @Schema(name = "门店编号", example = "99999")
    private String storeCode;


    @Schema(name = "订单总金额", example = "10000")
    private Integer totalPrice;

    @Schema(name = "物品重量（单位：克）", example = "10000")
    private Integer weightGram;

    @Schema(name = "商品详情")
    private List<ProductDetailInfo> detailInfos;

}
