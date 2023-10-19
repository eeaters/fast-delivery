package io.eeaters.delivery.mock.support;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 中国的经纬度范围:
 * 经度范围：73.55°E 到 135.05°E
 * 纬度范围：3.86°N 到 53.56°N
 */
public class GeoGenerate {

    static DecimalFormat decimalFormat = new DecimalFormat("#.######");

    public static String getCnLongitude() {
        double longitude = 61.5 + ( ThreadLocalRandom.current().nextDouble(73.55));
        return decimalFormat.format(longitude);
    }

    /**
     * 生成纬度（latitude），范围：-90 到 90
     * @return 随机纬度
     */
    public static String getCnLatitude() {
        double latitude = 3.86 + (  ThreadLocalRandom.current().nextDouble(49.7));
        return decimalFormat.format(latitude);
    }
}
