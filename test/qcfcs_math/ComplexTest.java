package qcfcs_math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by reesede on 1/7/17.
 */
class ComplexTest
{
    @BeforeEach
    void setUp()
    {

    }

    @AfterEach
    void tearDown()
    {

    }

    @Test
    void conjugate()
    {
        Complex theComplex = null;
        Complex conjugatedComplex = null;

        // Test conjugate for 0 + 0I, which should be 0.

        theComplex = new Complex ();
        assertEquals(0.0, theComplex.realPart);
        assertEquals(0.0, theComplex.imagPart);

        conjugatedComplex = Complex.conjugate(theComplex);
        assertEquals(0.0, theComplex.realPart);
        assertEquals(0.0, theComplex.imagPart);
        assertEquals(0.0, conjugatedComplex.realPart);
        assertEquals(0.0, conjugatedComplex.imagPart);

        conjugatedComplex = theComplex.conjugate();
        assertEquals(0.0, theComplex.realPart);
        assertEquals(0.0, theComplex.imagPart);
        assertEquals(0.0, conjugatedComplex.realPart);
        assertEquals(0.0, conjugatedComplex.imagPart);

        // Test conjugate for 1.0 + 0I, which should be 1.0 + 0I.

        theComplex = new Complex (1.0);
        assertEquals(1.0, theComplex.realPart);
        assertEquals(0.0, theComplex.imagPart);

        conjugatedComplex = Complex.conjugate(theComplex);
        assertEquals(1.0, theComplex.realPart);
        assertEquals(0.0, theComplex.imagPart);
        assertEquals(1.0, conjugatedComplex.realPart);
        assertEquals(0.0, conjugatedComplex.imagPart);

        conjugatedComplex = theComplex.conjugate();
        assertEquals(1.0, theComplex.realPart);
        assertEquals(0.0, theComplex.imagPart);
        assertEquals(1.0, conjugatedComplex.realPart);
        assertEquals(0.0, conjugatedComplex.imagPart);

        // Test conjugate for 1.0 + 1.0I, which should be 1.0 - 1.0I.

        theComplex = new Complex(1.0,1.0);
        assertEquals(1.0, theComplex.realPart);
        assertEquals(1.0, theComplex.imagPart);

        conjugatedComplex = Complex.conjugate(theComplex);
        assertEquals(1.0, theComplex.realPart);
        assertEquals(1.0, theComplex.imagPart);
        assertEquals(1.0, conjugatedComplex.realPart);
        assertEquals(-1.0, conjugatedComplex.imagPart);

        conjugatedComplex = theComplex.conjugate();
        assertEquals(1.0, theComplex.realPart);
        assertEquals(1.0, theComplex.imagPart);
        assertEquals(1.0, conjugatedComplex.realPart);
        assertEquals(-1.0, conjugatedComplex.imagPart);

        // Verify that the exception is generated as expected.

        try
        {
            Complex.conjugate(null);
            fail("Expected IllegalArgumentsException not thrown.");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    @Test
    void add()
    {
        Complex num1 = null;
        Complex num2 = null;
        Complex theSum = null;

        // Verify that complex numbers that are only real are added correctly.

        num1 = new Complex(1.0);
        num2 = new Complex(2.0);
        assertEquals(1.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theSum = Complex.add(num1, num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(3.0, theSum.realPart);
        assertEquals(0.0, theSum.imagPart);

        theSum = num1.add(num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(3.0, theSum.realPart);
        assertEquals(0.0, theSum.imagPart);

        // Verify that real num1 and imaginary num2 add correctly.

        num1 = new Complex(1.0);
        num2 = new Complex(0.0, 2.0);
        assertEquals(1.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theSum = Complex.add(num1, num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(1.0, theSum.realPart);
        assertEquals(2.0, theSum.imagPart);

        theSum = num1.add(num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(1.0, theSum.realPart);
        assertEquals(2.0, theSum.imagPart);

        // Verify that imaginary num1 and real num2 add correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(2.0, 0.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theSum = Complex.add(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(2.0, theSum.realPart);
        assertEquals(1.0, theSum.imagPart);

        theSum = num1.add(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(2.0, theSum.realPart);
        assertEquals(1.0, theSum.imagPart);

        // Verify that imaginary num1 and imaginary num2 add correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(0.0, 2.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theSum = Complex.add(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theSum.realPart);
        assertEquals(3.0, theSum.imagPart);

        theSum = num1.add(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theSum.realPart);
        assertEquals(3.0, theSum.imagPart);

        // Verify that complex num1 and complex num2 add correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex(2.0, 2.0);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theSum = Complex.add(num1, num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(3.0, theSum.realPart);
        assertEquals(3.0, theSum.imagPart);

        theSum = num1.add(num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(3.0, theSum.realPart);
        assertEquals(3.0, theSum.imagPart);

        // Verify that complex num1 and 0 num2 add correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex();
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theSum = Complex.add(num1, num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(1.0, theSum.realPart);
        assertEquals(1.0, theSum.imagPart);

        theSum = num1.add(num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(1.0, theSum.realPart);
        assertEquals(1.0, theSum.imagPart);

        // Verify that 0 num1 and complex num2 add correctly.

        num1 = new Complex();
        num2 = new Complex(2.0, 2.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theSum = Complex.add(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(2.0, theSum.realPart);
        assertEquals(2.0, theSum.imagPart);

        theSum = num1.add(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(2.0, theSum.realPart);
        assertEquals(2.0, theSum.imagPart);

        // Verify that the exceptions are produced as expected.

        num1 = null;
        num2 = new Complex (2.0, 2.0);
        try {
            theSum = Complex.add(num1, num2);
            fail("IllegalArgumentException for num1 not thrown.");
        } catch (IllegalArgumentException e) {}

        num1 = new Complex (1.0, 1.0);
        num2 = null;
        try {
            theSum = Complex.add(num1, num2);
            fail("IllegalArgumentException for num2 not thrown.");
        } catch (IllegalArgumentException e) {}

    }

    @Test
    void subtract()
    {

    }

    @Test
    void multiply()
    {

    }

    @Test
    void divide()
    {

    }

    @Test
    void isZero()
    {

    }
}