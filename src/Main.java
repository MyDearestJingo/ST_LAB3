import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ut.math.*;
import JUnitTestCase.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        BigIntegerTest testObj = new BigIntegerTest();
//        testObj.testValueOf();
        testObj.test_multiplyKaratsuba();
        System.out.println(("Test Complete"));
    }


}
