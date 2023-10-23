package io.eeaters.delivery.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static String random() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // 生成一个18位的随机数
        StringBuilder sb = new StringBuilder(18);
        //以2开头
        sb.append("2");
        for (int i = 1; i < 18; i++) {
            // 生成一个随机的数字，范围在0到9之间
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
