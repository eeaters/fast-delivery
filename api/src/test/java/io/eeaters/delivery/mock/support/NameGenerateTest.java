package io.eeaters.delivery.mock.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NameGenerateTest {

    @Test
    public void test() {
        String enName = NameGenerate.getEnName();
        Assertions.assertNotNull(enName);

        String cnName = NameGenerate.getCnName();
        Assertions.assertNotNull(cnName);
    }
}
