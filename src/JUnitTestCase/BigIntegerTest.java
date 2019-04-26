package JUnitTestCase;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ut.math.*;

// 关于assertEquals(arg0, arg1)的使用，arg0为期望值，arg1为实际值，当断言成立时，无任何输出，否则抛出异常

public class BigIntegerTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testValueOf() { // 测试BigInteger对指定数字的生成的对象是否正确
        assertEquals(new BigInteger("0"), BigInteger.valueOf(new Long(0L)));
    }

    @Test
    public void test_multiplyKaratsuba(){
        BigInteger BigInt_Kara = new BigInteger("2147483648");
        assertEquals(new BigInteger("2000000"),
                BigInt_Kara.multiply(new BigInteger("999999999999999999999999999")));
    }
}
