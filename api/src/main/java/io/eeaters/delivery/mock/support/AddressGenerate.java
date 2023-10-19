package io.eeaters.delivery.mock.support;

import java.util.concurrent.ThreadLocalRandom;

public class AddressGenerate {

    public static String getAddress() {

        String province = PROVINCES[ThreadLocalRandom.current().nextInt(PROVINCES.length)];
        String city = CITIES[ThreadLocalRandom.current().nextInt(CITIES.length)];
        String district = DISTRICTS[ThreadLocalRandom.current().nextInt(DISTRICTS.length)];
        String street = STREETS[ThreadLocalRandom.current().nextInt(STREETS.length)];
        int streetNumber = ThreadLocalRandom.current().nextInt(1000) + 1; // Random street number between 1 and 1000

        return String.format("%s%s%s%s%d号", province, city, district, street, streetNumber);
    }

    private static final String[] PROVINCES = {
            "北京市", "天津市", "河北省", "山西省", "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省", "上海市", "江苏省",
            "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区",
            "海南省", "重庆市", "四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省", "青海省", "宁夏回族自治区",
            "新疆维吾尔自治区", "台湾省", "香港特别行政区", "澳门特别行政区"
    };

    private static final String[] CITIES = {
            "北京市", "天津市", "上海市", "重庆市", "苏州市", "杭州市", "南京市", "成都市", "广州市", "深圳市",
            "武汉市", "西安市", "长沙市", "厦门市", "青岛市", "郑州市", "哈尔滨市", "沈阳市", "大连市", "济南市",
            "石家庄市", "长春市", "南昌市", "贵阳市", "昆明市", "兰州市", "西宁市", "太原市", "呼和浩特市", "银川市",
            "拉萨市", "台北市", "香港特别行政区", "澳门特别行政区"
    };

    private static final String[] DISTRICTS = {
            "东城区", "西城区", "朝阳区", "海淀区", "丰台区", "石景山区", "通州区", "顺义区", "房山区", "大兴区",
            "昌平区", "平谷区", "怀柔区", "密云区", "门头沟区", "延庆区", "和平区", "河东区", "河西区", "南开区",
            "河北区", "红桥区", "塘沽区", "汉沽区", "大港区", "东丽区", "西青区", "津南区", "北辰区", "武清区",
            "宝坻区", "宁河区", "静海区", "蓟州区", "黄浦区", "卢湾区", "徐汇区", "长宁区", "静安区", "普陀区",
            "虹口区", "杨浦区", "闵行区", "宝山区", "嘉定区", "浦东新区", "金山区", "松江区", "青浦区", "奉贤区",
            "崇明区"
    };

    private static final String[] STREETS = {
            "人民路", "中山路", "和平路", "建设路", "长江路", "解放路", "新华路", "朝阳路", "文化路", "永安路",
            "南京路", "北京路", "湖北路", "西藏路", "浙江路", "广东路", "河南路", "山东路", "福建路", "安徽路"
    };

}
