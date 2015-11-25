package jUnitAndEmma;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;

public class RationalTest extends TestCase {

	protected Rational HALF;

	protected void setUp() {
		HALF = new Rational(1, 2);
	}

	// Create new test
	public RationalTest(String name) {
		super(name);
	}

	public void testEquality() {
		assertEquals(new Rational(1, 3), new Rational(1, 3));
		assertEquals(new Rational(1, 3), new Rational(2, 6));
		assertEquals(new Rational(3, 3), new Rational(1, 1));
		assertEquals(new Rational(3, 3), new Rational(-1, -1));
	}

	// Test for nonequality
	public void testNonEquality() {
		assertFalse(new Rational(2, 3).equals(new Rational(1, 3)));
	}

	public void testAccessors() {
		assertEquals(new Rational(2, 3).numerator(), 2);
		assertEquals(new Rational(2, 3).denominator(), 3);
	}

	@Ignore
	public void testRoot() {
		Rational s = new Rational(1, 4);
		Rational sRoot = null;
		try {
			sRoot = s.root();
		} catch (IllegalArgumentToSquareRootException e) {
			e.printStackTrace();
		}
		System.out.println("ROOT");
		System.out.println(sRoot.isLessThan(HALF.plus(Rational.getTolerance())));
		System.out.println(HALF.minus(Rational.getTolerance()).isLessThan(sRoot));
		assertTrue(sRoot.isLessThan(HALF.plus(Rational.getTolerance()))
				&& HALF.minus(Rational.getTolerance()).isLessThan(sRoot));
	}

	/*
	 * Start of Jo's tests
	 */

	/**
	 * Test copy constructor
	 */
	public void testConstructor01() {
		Rational s = new Rational(1, 3);
		Rational t = new Rational(s);
		assertEquals(s, t);

	}

	/**
	 * Test toString
	 */
	public void testToString() {
		Rational s = new Rational(1, 3);
		assertEquals(s.toString(), "1/3");

	}

	/**
	 * Test equality - null
	 */
	public void testEquality01() {
		assertFalse(HALF.equals(null));
	}

	/**
	 * Test equality - different class
	 */
	public void testEquality02() {
		assertFalse(HALF.equals(new String()));
	}

	/**
	 * Test set tolerance
	 */
	public void testSetTolerance01() {
		Rational.setTolerance(HALF);
		assertEquals(Rational.getTolerance(), HALF);
	}

	/**
	 * Test abs - already positive
	 */
	public void testAbs01() {
		Rational s = new Rational(4, 7);
		Rational result = s.abs();
		assertEquals(result, new Rational(4, 7));
	}

	/**
	 * Test abs - already positive
	 */
	public void testAbs02() {
		Rational s = new Rational(-4, -7);
		Rational result = s.abs();
		assertEquals(result, new Rational(4, 7));
	}

	/**
	 * Test abs - mixed
	 */
	public void testAbs03() {
		Rational s = new Rational(-4, 7);
		Rational result = s.abs();
		assertEquals(result, new Rational(4, 7));
	}

	/**
	 * Test abs - mixed
	 */
	public void testAbs04() {
		Rational s = new Rational(4, -7);
		Rational result = s.abs();
		assertEquals(result, new Rational(4, 7));
	}

	/**
	 * Test IllegalArgumentToSquareRootException Throw because too low
	 */
	public void testException01() {
		boolean flag = false;
		Rational s = new Rational(5, -6);
		try {
			s.root();
		} catch (IllegalArgumentToSquareRootException e) {
			flag = true;
		}
		assertTrue(flag);
	}

	/**
	 * Test IllegalArgumentToSquareRootException Throw because too high
	 */
	public void testException02() {
		boolean flag = false;
		Rational s = new Rational(46341, 1);
		try {
			s.root();
		} catch (IllegalArgumentToSquareRootException e) {
			flag = true;
		}
		assertTrue(flag);
	}

	/**
	 * Test plus, second rational has negative denominator
	 */
	public void testPlus01() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(1, -3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test plus, first rational has negative denominator
	 */
	public void testPlus02() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(1, 3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test plus, first rational has negative numerator
	 */
	public void testPlus03() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(1, 3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test plus, second rational has negative numerator
	 */
	public void testPlus04() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(-1, 3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test plus, both rational has negative numerator
	 */
	public void testPlus05() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(-1, 3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(-5, 6)));
	}

	/**
	 * Test plus, both rational has negative denominator
	 */
	public void testPlus06() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(1, -3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(-5, 6)));
	}

	/**
	 * Test plus, both rational are positive
	 */
	public void testPlus07() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(1, 3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(5, 6)));
	}

	/**
	 * Test plus, second rational has negative denominator and first rational
	 * has negative numerator
	 */
	public void testPlus08() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(1, -3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(-5, 6)));
	}

	/**
	 * Test plus, first rational has negative denominator and second rational
	 * has negative numerator
	 */
	public void testPlus09() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(-1, 3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(-5, 6)));
	}

	/**
	 * Test plus, first rational has negative denominator & numerator
	 */
	public void testPlus10() {
		Rational s = new Rational(-1, -2);
		Rational t = new Rational(1, 3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(5, 6)));
	}

	/**
	 * Test plus, second rational has negative denominator & numerator
	 */
	public void testPlus11() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(-1, -3);
		Rational result = s.plus(t);
		assertTrue(result.equals(new Rational(5, 6)));
	}

	/**
	 * Test minus, second rational has negative denominator
	 */
	public void testMinus01() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(1, -3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(5, 6)));
	}

	/**
	 * Test minus, first rational has negative denominator
	 */
	public void testMinus02() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(1, 3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(-5, 6)));
	}

	/**
	 * Test minus, first rational has negative numerator
	 */
	public void testMinus03() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(1, 3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(-5, 6)));
	}

	/**
	 * Test minus, second rational has negative numerator
	 */
	public void testMinus04() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(-1, 3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(5, 6)));
	}

	/**
	 * Test minus, both rational has negative numerator
	 */
	public void testMinus05() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(-1, 3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test minus, both rational has negative denominator
	 */
	public void testMinus06() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(1, -3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test minus, both rational are positive
	 */
	public void testMinus07() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(1, 3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test minus, second rational has negative denominator and first rational
	 * has negative numerator
	 */
	public void testMinus08() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(1, -3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test minus, first rational has negative denominator and second rational
	 * has negative numerator
	 */
	public void testMinus09() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(-1, 3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test minus, first rational has negative denominator & numerator
	 */
	public void testMinus10() {
		Rational s = new Rational(-1, -2);
		Rational t = new Rational(1, 3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test minus, second rational has negative denominator & numerator
	 */
	public void testMinus11() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(-1, -3);
		Rational result = s.minus(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test times, second rational has negative denominator
	 */
	public void testTimes01() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(1, -3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test times, first rational has negative denominator
	 */
	public void testTimes02() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(1, 3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test times, first rational has negative numerator
	 */
	public void testTimes03() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(1, 3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test times, second rational has negative numerator
	 */
	public void testTimes04() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(-1, 3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(-1, 6)));
	}

	/**
	 * Test times, both rational has negative numerator
	 */
	public void testTimes05() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(-1, 3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test times, both rational has negative denominator
	 */
	public void testTimes06() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(1, -3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test times, both rational are positive
	 */
	public void testTimes07() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(1, 3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test times, second rational has negative denominator and first rational
	 * has negative numerator
	 */
	public void testTimes08() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(1, -3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test times, first rational has negative denominator and second rational
	 * has negative numerator
	 */
	public void testTimes09() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(-1, 3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test times, first rational has negative denominator & numerator
	 */
	public void testTimes10() {
		Rational s = new Rational(-1, -2);
		Rational t = new Rational(1, 3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test times, second rational has negative denominator & numerator
	 */
	public void testTimes11() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(-1, -3);
		Rational result = s.times(t);
		assertTrue(result.equals(new Rational(1, 6)));
	}

	/**
	 * Test divides, second rational has negative denominator
	 */
	public void testDivides01() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(1, -3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(-3, 2)));
	}

	/**
	 * Test divides, first rational has negative denominator
	 */
	public void testDivides02() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(1, 3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(-3, 2)));
	}

	/**
	 * Test divides, first rational has negative numerator
	 */
	public void testDivides03() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(1, 3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(-3, 2)));
	}

	/**
	 * Test divides, second rational has negative numerator
	 */
	public void testDivides04() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(-1, 3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(-3, 2)));
	}

	/**
	 * Test divides, both rational has negative numerator
	 */
	public void testDivides05() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(-1, 3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(3, 2)));
	}

	/**
	 * Test divides, both rational has negative denominator
	 */
	public void testDivides06() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(1, -3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(3, 2)));
	}

	/**
	 * Test divides, both rational are positive
	 */
	public void testDivides07() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(1, 3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(3, 2)));
	}

	/**
	 * Test divides, second rational has negative denominator and first rational
	 * has negative numerator
	 */
	public void testDivides08() {
		Rational s = new Rational(-1, 2);
		Rational t = new Rational(1, -3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(3, 2)));
	}

	/**
	 * Test divides, first rational has negative denominator and second rational
	 * has negative numerator
	 */
	public void testDivides09() {
		Rational s = new Rational(1, -2);
		Rational t = new Rational(-1, 3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(3, 2)));
	}

	/**
	 * Test divides, first rational has negative denominator & numerator
	 */
	public void testDivides10() {
		Rational s = new Rational(-1, -2);
		Rational t = new Rational(1, 3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(3, 2)));
	}

	/**
	 * Test divides, second rational has negative denominator & numerator
	 */
	public void testDivides11() {
		Rational s = new Rational(1, 2);
		Rational t = new Rational(-1, -3);
		Rational result = s.divides(t);
		assertTrue(result.equals(new Rational(3, 2)));
	}
	
	/**
	 * Test isLessThan, second rational has negative denominator
	 */
	public void testIsLessThan01()
	{
		Rational s = new Rational(1,2);
		Rational t = new Rational(1,-3);
		boolean result = s.isLessThan(t);
		assertFalse(result);
	}
	
	/**
	 * Test isLessThan, first rational has negative denominator
	 */
	public void testIsLessThan02()
	{
		Rational s = new Rational(1,-2);
		Rational t = new Rational(1,3);
		boolean result = s.isLessThan(t);
		assertTrue(result);
	}
	
	/**
	 * Test isLessThan, first rational has negative numerator
	 */
	public void testIsLessThan03()
	{
		Rational s = new Rational(-1,2);
		Rational t = new Rational(1,3);
		boolean result = s.isLessThan(t);
		assertTrue(result);
	}
	
	/**
	 * Test isLessThan, second rational has negative numerator
	 */
	public void testIsLessThan04()
	{
		Rational s = new Rational(1,2);
		Rational t = new Rational(-1,3);
		boolean result = s.isLessThan(t);
		assertFalse(result);
	}
	
	/**
	 * Test isLessThan, both rational has negative numerator
	 */
	public void testIsLessThan05()
	{
		Rational s = new Rational(-1,2);
		Rational t = new Rational(-1,3);
		boolean result = s.isLessThan(t);
		assertTrue(result);
	}
	
	/**
	 * Test isLessThan, both rational has negative denominator
	 */
	public void testIsLessThan06()
	{
		Rational s = new Rational(1,-2);
		Rational t = new Rational(1,-3);
		boolean result = s.isLessThan(t);
		assertTrue(result);
	}
	
	/**
	 * Test isLessThan, both rational are positive
	 */
	public void testIsLessThan07()
	{
		Rational s = new Rational(1,2);
		Rational t = new Rational(1,3);
		boolean result = s.isLessThan(t);
		assertFalse(result);
	}
	
	/**
	 * Test isLessThan, second rational has negative denominator
	 *  and first rational has negative numerator
	 */
	public void testIsLessThan08()
	{
		Rational s = new Rational(-1,2);
		Rational t = new Rational(1,-3);
		boolean result = s.isLessThan(t);
		assertTrue(result);
	}
	
	/**
	 * Test isLessThan, first rational has negative denominator
	 *  and second rational has negative numerator
	 */
	public void testIsLessThan09()
	{
		Rational s = new Rational(1,-2);
		Rational t = new Rational(-1,3);
		boolean result = s.isLessThan(t);
		assertTrue(result);
	}
	
	/**
	 * Test isLessThan, first rational has negative denominator & numerator
	 */
	public void testIsLessThan10()
	{
		Rational s = new Rational(-1,-2);
		Rational t = new Rational(1,3);
		boolean result = s.isLessThan(t);
		assertFalse(result);
	}
	
	/**
	 * Test isLessThan, second rational has negative denominator & numerator
	 */
	public void testIsLessThan11()
	{
		Rational s = new Rational(1,2);
		Rational t = new Rational(-1,-3);
		boolean result = s.isLessThan(t);
		assertFalse(result);
	}

	/**
	 * Test root
	 */
	public void testRoot01() {
		Rational s = new Rational(4, 1);
		Rational result = null;
		Rational.setTolerance(new Rational(1, 2));
		try {
			result = s.root();
		} catch (IllegalArgumentToSquareRootException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ROOT");
		System.out.println("\tResult: " + result);
		//Check that root is 2 +- the tolerance
		Rational answer = new Rational(2, 1);
		Rational low = answer.minus(Rational.getTolerance());
		Rational high = answer.plus(Rational.getTolerance());
		System.out.println("\tTolerance: " + Rational.getTolerance());
		System.out.println("\tAnswer: " + answer);
		System.out.println("\tLow: " + low);
		System.out.println("\tHigh: " + high);
		assertTrue(result.isLessThan(high) && low.isLessThan(result));
	}
	
	/**
	 * Test main, just for more code coverage
	 */
	public void testMain()
	{
		Rational.main(null);
	}
}
