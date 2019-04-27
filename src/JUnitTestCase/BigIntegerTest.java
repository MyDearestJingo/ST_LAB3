/*
    Project Name: Software Test Lab3
    Coder: Jingo Lan
    Last Update: Apr 27nd 2019 22:22
 */

package JUnitTestCase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
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
    public void test_subtract(){
        System.out.println("TEST: public BigInteger subtract(BigInteger val)");
        StringBuffer val1 = new StringBuffer("2");
        StringBuffer val2 = new StringBuffer("4");
        StringBuffer val3 = new StringBuffer("-2");

        for(int i=0;i<800;i++){
            val1.append('0');
            val2.append('0');
            val3.append('0');
        }

        BigInteger BigInt_2E8 = new BigInteger(val1.substring(0));
        BigInteger BigInt_4E8 = new BigInteger(val2.substring(0));
                BigInteger BigInt_0 = new BigInteger("0");

        // U0
        assertEquals(new BigInteger(val1.substring(0)), BigInt_2E8.subtract(new BigInteger("0")));
        System.out.println("U0 Complete");

        // U1
        assertEquals(new BigInteger(val3.substring(0)), BigInt_0.subtract(new BigInteger(val1.substring(0))));
        System.out.println("U1 Complete");

        // U2
        assertEquals(new BigInteger(val2.substring(0)), BigInt_2E8.subtract(new BigInteger(val3.substring(0))));
        System.out.println("U2 Complete");

        // U3
        assertEquals(new BigInteger("0"), BigInt_2E8.subtract(new BigInteger(val1.substring(0))));
        System.out.println("U3 Complete");

        // U4
        assertEquals(new BigInteger(val1.substring(0)), BigInt_4E8.subtract(new BigInteger(val1.substring(0))));
        System.out.println("U4 Complete");

        // U5
        assertEquals(new BigInteger(val3.substring(0)), BigInt_2E8.subtract(BigInt_4E8));
        System.out.println("U5 Complete");

        System.out.println("COMPLETE: public BigInteger subtract(BigInteger val)");

    }

    @Test
    public void test_multiplyByInt(){
        System.out.println("TEST: private static BigInteger multiplyByInt(int[] x, int y, int sign)");
        StringBuffer val0 = new StringBuffer("4");
        StringBuffer val1 = new StringBuffer("4");
        StringBuffer val2 = new StringBuffer("4");
        StringBuffer val3 = new StringBuffer("16");
        for(int i=0;i<200;i++){
            val1.append("0");
            val3.append("0");
        }

        BigInteger bi0 = new BigInteger("400");
        BigInteger bi1 = new BigInteger("4");

        // U0
        assertEquals(new BigInteger(val3.substring(0)),bi1.multiply(new BigInteger(val1.substring(0))));
        System.out.println("U0 Complete");

        // U1
        assertEquals(new BigInteger("160000"), bi0.multiply(new BigInteger("400")));
        System.out.println("U1 Complete");

        // U2
        assertEquals(new BigInteger("1600"), bi0.multiply(new BigInteger("4")));
        System.out.println("U2 Complete");

        System.out.println("COMPLETE: private static BigInteger multiplyByInt(int[] x, int y, int sign)");
    }

    @Test
    public void test_square(){
        System.out.println("TEST: private BigInteger square()");
        StringBuffer val0 = new StringBuffer("2");
        StringBuffer val1 = new StringBuffer("4");
        for(int i=0;i<500;i++){
            val0.append("0");
            val1.append("00");
        }
        BigInteger bi0 = new BigInteger(val0.substring(0));

        // U0
        assertEquals(new BigInteger(val1.substring(0)), bi0.multiply(bi0));
        System.out.println("U0 Complete");

        // U1
        for(int i=0;i<780;i++){
            val0.append("0");
            val1.append("00");
        }
        bi0 = new BigInteger(val0.substring(0));
        assertEquals(new BigInteger(val1.substring(0)), bi0.multiply(bi0));
        System.out.println("U1 Complete");

        // U2
        for(int i=0;i<880;i++){
            val0.append("0");
            val1.append("00");
        }
        bi0 = new BigInteger(val0.substring(0));
        assertEquals(new BigInteger(val1.substring(0)), bi0.multiply(bi0));
        System.out.println("U2 Complete");

        System.out.println("COMPLETE: private BigInteger square()");
    }


    @Test
    public void test_multiplyKaratsuba(){
        System.out.println("TEST: private static BigInteger multiplyKaratsuba(BigInteger x, BigInteger y)");
        StringBuffer val1 = new StringBuffer("2");
        StringBuffer val2 = new StringBuffer("2");
        StringBuffer val3 = new StringBuffer("4");
        for(int i=0;i<800;i++) {
            val1.append("0");
            val2.append("0");
        }
        for(int i=0;i<1600;i++){
            val3.append("0");
        }
        BigInteger BigInt_Kara = new BigInteger(val1.substring(0));

        // U0
        assertEquals(new BigInteger(val3.substring(0)),
                BigInt_Kara.multiply(new BigInteger(val2.substring(0))));
        System.out.println("U0 Complete");

        // U1
        val2.setCharAt(0,'-');
        val2.setCharAt(1,'2');
        val2.append('0');
        val3.setCharAt(0,'-');
        val3.setCharAt(1,'4');
        val3.append('0');
        assertEquals(new BigInteger(val3.substring(0)),
                BigInt_Kara.multiply(new BigInteger(val2.substring(0))));
        System.out.println("U1 Complete");

        System.out.println("COMPLETE: private static BigInteger multiplyKaratsuba(BigInteger x, BigInteger y)");
    }
}
