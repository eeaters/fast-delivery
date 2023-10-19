package io.eeaters.delivery.mock.support;

import java.util.concurrent.ThreadLocalRandom;

public class PhoneGenerate {

    // 生成随机手机号码
    public static String getPhoneNumber() {
        // 中国手机号码前三位
        String[] firstThreeDigits = {"130", "131", "132", "133", "134", "135", "136", "137", "138", "139",
                "150", "151", "152", "153", "155", "156", "157", "158", "159", "170", "176", "177", "178",
                "180", "181", "182", "183", "184", "185", "186", "187", "188", "189"};

        // 随机选择前三位
        String randomFirstThreeDigits = firstThreeDigits[ThreadLocalRandom.current().nextInt(firstThreeDigits.length)];

        // 随机生成后8位数字
        StringBuilder phoneNumber = new StringBuilder(randomFirstThreeDigits);
        for (int i = 0; i < 8; i++) {
            phoneNumber.append(ThreadLocalRandom.current().nextInt(10));
        }

        return phoneNumber.toString();
    }
}
