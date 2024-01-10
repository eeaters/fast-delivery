package io.eeaters.delivery.entity.dto.third;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateDeliveryDTO {

    // 销售订单号
    private String orderCode;

    // 配送订单号
    private String deliveryCode;

    // 下单时间
    private LocalDateTime orderTime;

    // 发货门店号
    private String storeCode;

    // 订单价格
    private Integer totalPrice;

    // 订单重量
    private Integer weightGram;

    // 配送商品列表
    private List<ProductInfo> productInfoList;

    //收货人联系信息
    private ContractInformation userInformation;

    //发货门店联系信息
    private ContractInformation storeInformation;


    /**
     * 联系信息
     */
    @Data
    public static class ContractInformation{

        //联系人
        private String contractUser;

        //联系电话
        private String contractPhone;

        //联系地址
        private String address;

        //经度
        private Double longitude;

        //纬度
        private Double latitude;
    }

    @Data
    public static class ProductInfo{

        // 商品名称
        private String productName;

        // 商品数量
        private Integer productNum;
    }
}
