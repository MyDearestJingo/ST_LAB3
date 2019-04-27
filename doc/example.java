import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ut.math.*;

public class BigIntegerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValueOf() { // 测试BigInteger对指定数字的生成的对象是否正确
		
		//测试用例1
		assertEquals( new BigInteger(new int[0], 0), BigInteger.valueOf(new Long(0L)));
		
		//测试用例2
 		assertEquals(new BigInteger(16L),BigInteger.valueOf(new Long(16L)));
		
		//测试用例3
		assertEquals(new BigInteger(-15L),BigInteger.valueOf(new Long(-15L)));
		
		//测试用例4
		assertEquals(new BigInteger(12506L),BigInteger.valueOf(new Long(12506L)));
		
	}

	@Test
	public void testCompareMagnitudeBigInteger() {
		String val = "12345";

		BigInteger big = new BigInteger(val, 10);

		//测试用例1
		assertEquals(1,big.compareMagnitude(new BigInteger(123)));
		
		//测试用例2
		assertEquals(-1,big.compareMagnitude(new BigInteger(12345)));
	
		//测试用例3
		assertEquals(0,big.compareMagnitude(new BigInteger(1234)));
		
		//测试用例4
		assertEquals(1,big.compareMagnitude(new BigInteger(1224)));
		
		//测试用例5
		assertEquals(-1,big.compareMagnitude(new BigInteger(1284)));
		
		
	}

	@Test
	public void testEqualsObject() {
		
		BigInteger big = new BigInteger(1234);
		
		//测试用例1：是否与自己相等
		assertEquals(true , big.equals(big));
		
		//测试用例2
		Long l = new Long(1234L);
		assertEquals(false , big.equals(l));
		
		//测试用例3
		assertEquals(false , big.equals(new BigInteger(-1234)));
		
		//测试用例4
		assertEquals(false , big.equals(new BigInteger(12345)));
		
		//测试用例5
		assertEquals(false , big.equals(new BigInteger(1233)));
		
		//测试用例6
		assertEquals(true , big.equals(new BigInteger(1234)));
	}

	@Rule
	public ExpectedException thrown= ExpectedException.none();
	@Test
	public void testByteValueExact() {
		
		//测试用例1
		BigInteger c1 = new BigInteger(48);
		assertEquals(48 , c1.byteValueExact());
		
		//测试用例2
		BigInteger c2 = new BigInteger(129);
		thrown.expect(ArithmeticException.class);
		c2.byteValueExact();
		
		//测试用例3
		BigInteger c3 = new BigInteger(-129);
		thrown.expect(ArithmeticException.class);
		c3.byteValueExact();
		
		//测试用例4
		BigInteger c4 = new BigInteger(1234567891);
		thrown.expect(ArithmeticException.class);
		c4.byteValueExact();
		
		//测试用例5
		BigInteger c5 = new BigInteger(12345678912L);
		thrown.expect(ArithmeticException.class);
		c5.byteValueExact();
		
	}

}
