/**
     * Returns a BigInteger whose value is {@code (this - val)}.
     *
     * @param  val value to be subtracted from this BigInteger.
     * @return {@code this - val}
*/
public BigInteger subtract(BigInteger val) { // 减法操作， val为被减数
    if (val.signum == 0) // signum为符号标志位，-1为负数，0为0，1为正数
        return this;
    if (signum == 0)
        return val.negate();
    if (val.signum != signum) // 两数异号
        return new BigInteger(add(mag, val.mag), signum);

    int cmp = compareMagnitude(val); // 比较两数大小，cmp>1则this > val，cmp=0则二者相等，cmp<0则this < val
    if (cmp == 0)
        return ZERO;
    int[] resultMag = (cmp > 0 ? subtract(mag, val.mag)
                        : subtract(val.mag, mag));
    resultMag = trustedStripLeadingZeroInts(resultMag);
    return new BigInteger(resultMag, cmp == signum ? 1 : -1);
}

private static BigInteger multiplyByInt(int[] x, int y, int sign) {
    if (Integer.bitCount(y) == 1) {
        return new BigInteger(shiftLeft(x,Integer.numberOfTrailingZeros(y)), sign);
    }
    int xlen = x.length;
    int[] rmag =  new int[xlen + 1];
    long carry = 0;
    long yl = y & LONG_MASK;
    int rstart = rmag.length - 1;
    for (int i = xlen - 1; i >= 0; i--) {
        long product = (x[i] & LONG_MASK) * yl + carry;
        rmag[rstart--] = (int)product;
        carry = product >>> 32;
    }
    if (carry == 0L) {
        rmag = java.util.Arrays.copyOfRange(rmag, 1, rmag.length);
    } else {
        rmag[rstart] = (int)carry;
    }
    return new BigInteger(rmag, sign);
}

/**
 * Returns a BigInteger whose value is {@code (this<sup>2</sup>)}.
 *
 * @return {@code this<sup>2</sup>}
 */
private BigInteger square() {
    if (signum == 0) {
        return ZERO;
    }
    int len = mag.length;

    if (len < KARATSUBA_SQUARE_THRESHOLD) {
        int[] z = squareToLen(mag, len, null);
        return new BigInteger(trustedStripLeadingZeroInts(z), 1);
    } else {
        if (len < TOOM_COOK_SQUARE_THRESHOLD) {
            return squareKaratsuba();
        } else {
            return squareToomCook3();
        }
    }
}

private static BigInteger multiplyKaratsuba(BigInteger x, BigInteger y) {
    int xlen = x.mag.length;
    int ylen = y.mag.length;

    // The number of ints in each half of the number.
    int half = (Math.max(xlen, ylen)+1) / 2;

    // xl and yl are the lower halves of x and y respectively,
    // xh and yh are the upper halves.
    BigInteger xl = x.getLower(half);
    BigInteger xh = x.getUpper(half);
    BigInteger yl = y.getLower(half);
    BigInteger yh = y.getUpper(half);

    BigInteger p1 = xh.multiply(yh);  // p1 = xh*yh
    BigInteger p2 = xl.multiply(yl);  // p2 = xl*yl

    // p3=(xh+xl)*(yh+yl)
    BigInteger p3 = xh.add(xl).multiply(yh.add(yl));

    // result = p1 * 2^(32*2*half) + (p3 - p1 - p2) * 2^(32*half) + p2
    BigInteger result = p1.shiftLeft(32*half).add(p3.subtract(p1).subtract(p2)).shiftLeft(32*half).add(p2);

    if (x.signum != y.signum) {
        return result.negate();
    } else {
        return result;
    }
}