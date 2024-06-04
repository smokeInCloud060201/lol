package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vn.com.lol.utils.IDGeneratorUtil;

class IDGeneratorUtilTest {

    @Test
    void shouldThrowExceptionWhenInvalidNodeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            IDGeneratorUtil idGeneratorUtil = new IDGeneratorUtil(9999L);
        });
    }
}
