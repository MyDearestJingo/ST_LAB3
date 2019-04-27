import JUnitTestCase.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Software Test Lab3 | Coder: Jingo Lan");
        BigIntegerTest testObj = new BigIntegerTest();

        testObj.test_subtract();
        testObj.test_multiplyByInt();
        testObj.test_square();
        testObj.test_multiplyKaratsuba();

        System.out.println(("Test Complete"));
    }


}
