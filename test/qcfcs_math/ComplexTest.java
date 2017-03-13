package qcfcs_math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.IllegalArgumentException;

/**
 * This class implements unit tests for the Complex class.
 * Created by reesede on 1/7/2017.
 * @author David E. Reese
 * @version 3.1.1
 * @since 1.2.1
 */

// Copyright 2017 David E. Reese
//
// This file is part of QCfCS_java.
//
// QCfCS_java is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// QCfCS_java is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with QCfCS_java.  If not, see <http://www.gnu.org/licenses/>.
//
// History:
//      20170107    D.E. Reese          Creation (Programming Drill 1.2.1)
//                                      Added setReal_getReal() and setImag_getImag(). Added calls to setReal (),
//                                      getReal (), setImag (), and getImag () after realPart and imagPart made
//                                      private.
//      20170113    D.E. Reese          Added constructors() to test constructor methods.
//      20170115    D.E. Reese          Added tests for pow().
//      20170121    D.E. Reese          Added stubs for tests for abs() and equals().
//      20170122    D.E. Reese          Added stub for test for negate().
//      20170123    D.E. Reese          Added code to abs() stub.
//      20170124    D.E. Reese          Added code to equals() and negate() stubs.
//      20170125    D.E. Reese          Added code to test equals() for double, float, and int.
//      20170205    D.E. Reese          Added transpose() and transposeConjugate().
//      20170210    D.E. Reese          Added testToString() and set(). Deleted transpose() and transposeConjugate().
//      20170305    D.E. Reese          Added parseComplex().
//      20170313    D.E. Reese          Added modulus().
//
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
    void constructors()
    {
        Complex theNum = new Complex ();
        assertEquals(0.0, Complex.getReal(theNum));
        assertEquals(0.0, Complex.getImag(theNum));

        theNum = new Complex (1.0);
        assertEquals(1.0, theNum.getReal());
        assertEquals(0.0, theNum.getImag());

        theNum = new Complex(1.0,1.0);
        assertEquals(1.0, theNum.getReal());
        assertEquals(1.0, theNum.getImag());

        theNum = new Complex(0.0, 2.0);
        assertEquals(0.0, theNum.getReal());
        assertEquals(2.0, theNum.getImag());

        theNum = new Complex(new PolarCoordinate(0.0,0.0));
        assertEquals(0.0, theNum.getReal());
        assertEquals(0.0, theNum.getImag());

        theNum = new Complex(new PolarCoordinate(1.0, Math.PI/4.0));
        assertEquals(Math.sqrt(2.0)/2.0, theNum.getReal(), 0.00000001);
        assertEquals(Math.sqrt(2.0)/2.0, theNum.getImag(), 0.00000001);

        theNum = new Complex(new PolarCoordinate(1.0, -Math.PI/4.0));
        assertEquals(Math.sqrt(2.0)/2.0, theNum.getReal(), 0.00000001);
        assertEquals(-Math.sqrt(2.0)/2.0, theNum.getImag(), 0.00000001);

        theNum = new Complex(new PolarCoordinate(1.0, 4.0*Math.PI+Math.PI/4.0));
        assertEquals(Math.sqrt(2.0)/2.0, theNum.getReal(), 0.00000001);
        assertEquals(Math.sqrt(2.0)/2.0, theNum.getImag(), 0.00000001);

        assertThrows(IllegalArgumentException.class, () -> {
            Complex tComplex = new Complex((PolarCoordinate)null);
        });

        theNum = new Complex(1.0, 2.0);
        Complex newNum = new Complex(theNum);
        assertEquals(1.0, newNum.getReal());
        assertEquals(2.0, theNum.getImag());
    }

    @Test
    void set()
    {
        Complex theComplex;
        Complex test1;

        // Test class method.

        test1 = new Complex(1.0,1.0);
        theComplex = new Complex();
        Complex.set(theComplex, test1);
        assertEquals(1.0, theComplex.getReal());
        assertEquals(1.0, theComplex.getImag());
        assertEquals(1.0, test1.getReal());
        assertEquals(1.0, test1.getImag());

        Complex.set(theComplex, 2.0, 2.0);
        assertEquals(2.0, theComplex.getReal());
        assertEquals(2.0, theComplex.getImag());
        assertEquals(1.0, test1.getReal());
        assertEquals(1.0, test1.getImag());

        Complex.set(test1, 3.0,3.0);
        assertEquals(2.0, theComplex.getReal());
        assertEquals(2.0, theComplex.getImag());
        assertEquals(3.0, test1.getReal());
        assertEquals(3.0, test1.getImag());

        final Complex test2 = new Complex(4.0, 4.0);
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.set(test2, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.set(null, test2);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.set(null, null);
        });

        // Test instance method.

        test1 = new Complex(1.0,1.0);
        theComplex = new Complex();
        theComplex.set(test1);
        assertEquals(1.0, theComplex.getReal());
        assertEquals(1.0, theComplex.getImag());
        assertEquals(1.0, test1.getReal());
        assertEquals(1.0, test1.getImag());

        theComplex.set(2.0, 2.0);
        assertEquals(2.0, theComplex.getReal());
        assertEquals(2.0, theComplex.getImag());
        assertEquals(1.0, test1.getReal());
        assertEquals(1.0, test1.getImag());

        test1.set(3.0,3.0);
        assertEquals(2.0, theComplex.getReal());
        assertEquals(2.0, theComplex.getImag());
        assertEquals(3.0, test1.getReal());
        assertEquals(3.0, test1.getImag());
    }

    @Test
    void setReal_getReal()
    {
        // Test class methods.

        Complex theNum = new Complex ();

        Complex.setReal(theNum, 1.0);
        assertEquals(1.0, Complex.getReal(theNum));
        assertEquals(0.0, Complex.getImag(theNum));

        Complex.setReal(theNum, (float)2.0);
        assertEquals(2.0, Complex.getReal(theNum));
        assertEquals(0.0, Complex.getImag(theNum));

        Complex.setReal(theNum, 3);
        assertEquals(3.0, Complex.getReal(theNum));
        assertEquals(0.0, Complex.getImag(theNum));

        theNum.setReal(4.0);
        assertEquals(4.0, Complex.getReal(theNum));
        assertEquals(0.0, Complex.getImag(theNum));

        // Test instance methods.

        theNum.setReal((float)5.0);
        assertEquals(5.0, Complex.getReal(theNum));
        assertEquals(0.0, Complex.getImag(theNum));

        theNum.setReal(6);
        assertEquals(6.0, Complex.getReal(theNum));
        assertEquals(0.0, Complex.getImag(theNum));
    }

    @Test
    void setImag_getImag()
    {
        // Test class methods.

        Complex theNum = new Complex ();

        Complex.setImag(theNum, 1.0);
        assertEquals(0.0, Complex.getReal(theNum));
        assertEquals(1.0, Complex.getImag(theNum));

        Complex.setImag(theNum, (float)2.0);
        assertEquals(0.0, Complex.getReal(theNum));
        assertEquals(2.0, Complex.getImag(theNum));

        Complex.setImag(theNum, 3);
        assertEquals(0.0, Complex.getReal(theNum));
        assertEquals(3.0, Complex.getImag(theNum));

        theNum.setImag(4.0);
        assertEquals(0.0, Complex.getReal(theNum));
        assertEquals(4.0, Complex.getImag(theNum));

        // Test instance methods.

        theNum.setImag((float)5.0);
        assertEquals(0.0, Complex.getReal(theNum));
        assertEquals(5.0, Complex.getImag(theNum));

        theNum.setImag(6);
        assertEquals(0.0, Complex.getReal(theNum));
        assertEquals(6.0, Complex.getImag(theNum));
    }

    @Test
    void conjugate()
    {
        Complex theComplex;
        Complex conjugatedComplex;

        // Test conjugate for 0 + 0I, which should be 0.

        theComplex = new Complex ();

        conjugatedComplex = Complex.conjugate(theComplex);
        assertEquals(0.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());
        assertEquals(0.0, conjugatedComplex.getReal());
        assertEquals(0.0, conjugatedComplex.getImag());

        conjugatedComplex = theComplex.conjugate();
        assertEquals(0.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());
        assertEquals(0.0, conjugatedComplex.getReal());
        assertEquals(0.0, conjugatedComplex.getImag());

        // Test conjugate for 1.0 + 0I, which should be 1.0 + 0I.

        theComplex = new Complex (1.0);

        conjugatedComplex = Complex.conjugate(theComplex);
        assertEquals(1.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());
        assertEquals(1.0, conjugatedComplex.getReal());
        assertEquals(0.0, conjugatedComplex.getImag());

        conjugatedComplex = theComplex.conjugate();
        assertEquals(1.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());
        assertEquals(1.0, conjugatedComplex.getReal());
        assertEquals(0.0, conjugatedComplex.getImag());

        // Test conjugate for 1.0 + 1.0I, which should be 1.0 - 1.0I.

        theComplex = new Complex(1.0,1.0);

        conjugatedComplex = Complex.conjugate(theComplex);
        assertEquals(1.0, theComplex.getReal());
        assertEquals(1.0, theComplex.getImag());
        assertEquals(1.0, conjugatedComplex.getReal());
        assertEquals(-1.0, conjugatedComplex.getImag());

        conjugatedComplex = theComplex.conjugate();
        assertEquals(1.0, theComplex.getReal());
        assertEquals(1.0, theComplex.getImag());
        assertEquals(1.0, conjugatedComplex.getReal());
        assertEquals(-1.0, conjugatedComplex.getImag());

        // Verify that the exception is generated as expected.

        assertThrows(IllegalArgumentException.class, () -> {
            Complex.conjugate(null);
        });
    }

    @Test
    void add()
    {
        Complex num1;
        Complex num2;
        Complex theSum;

        // Verify that complex numbers that are only real are added correctly.

        num1 = new Complex(1.0);
        num2 = new Complex(2.0);

        theSum = Complex.add(num1, num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(3.0, theSum.getReal());
        assertEquals(0.0, theSum.getImag());

        theSum = num1.add(num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(3.0, theSum.getReal());
        assertEquals(0.0, theSum.getImag());

        // Verify that real num1 and imaginary num2 add correctly.

        num1 = new Complex(1.0);
        num2 = new Complex(0.0, 2.0);

        theSum = Complex.add(num1, num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(1.0, theSum.getReal());
        assertEquals(2.0, theSum.getImag());

        theSum = num1.add(num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(1.0, theSum.getReal());
        assertEquals(2.0, theSum.getImag());

        // Verify that imaginary num1 and real num2 add correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(2.0, 0.0);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());

        theSum = Complex.add(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(2.0, theSum.getReal());
        assertEquals(1.0, theSum.getImag());

        theSum = num1.add(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(2.0, theSum.getReal());
        assertEquals(1.0, theSum.getImag());

        // Verify that imaginary num1 and imaginary num2 add correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(0.0, 2.0);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());

        theSum = Complex.add(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theSum.getReal());
        assertEquals(3.0, theSum.getImag());

        theSum = num1.add(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theSum.getReal());
        assertEquals(3.0, theSum.getImag());

        // Verify that complex num1 and complex num2 add correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex(2.0, 2.0);

        theSum = Complex.add(num1, num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(3.0, theSum.getReal());
        assertEquals(3.0, theSum.getImag());

        theSum = num1.add(num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(3.0, theSum.getReal());
        assertEquals(3.0, theSum.getImag());

        // Verify that complex num1 and 0 num2 add correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex();

        theSum = Complex.add(num1, num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(1.0, theSum.getReal());
        assertEquals(1.0, theSum.getImag());

        theSum = num1.add(num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(1.0, theSum.getReal());
        assertEquals(1.0, theSum.getImag());

        // Verify that 0 num1 and complex num2 add correctly.

        num1 = new Complex();
        num2 = new Complex(2.0, 2.0);

        theSum = Complex.add(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(2.0, theSum.getReal());
        assertEquals(2.0, theSum.getImag());

        theSum = num1.add(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(2.0, theSum.getReal());
        assertEquals(2.0, theSum.getImag());

        // Test adding doubles, floats, and ints.

        num1 = new Complex(2.0,2.0);
        theSum = num1.add(2.0);
        assertEquals(4.0, theSum.getReal());
        assertEquals(2.0, theSum.getImag());

        theSum = num1.add((float)2.0);
        assertEquals(4.0, theSum.getReal());
        assertEquals(2.0, theSum.getImag());

        theSum = num1.add(2);
        assertEquals(4.0, theSum.getReal());
        assertEquals(2.0, theSum.getImag());

        // Verify that the exceptions are produced as expected.

        final Complex theNum = new Complex (2.0, 2.0);
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.add(null, theNum);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.add(theNum, null);
        });
    }

    @Test
    void subtract()
    {
        Complex num1;
        Complex num2;
        Complex theDifference;

        // Verify that complex numbers that are only real subtract correctly.

        num1 = new Complex(3.0);
        num2 = new Complex(2.0);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(3.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(1.0, theDifference.getReal());
        assertEquals(0.0, theDifference.getImag());

        theDifference = num1.subtract(num2);
        assertEquals(3.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(1.0, theDifference.getReal());
        assertEquals(0.0, theDifference.getImag());

        // Verify that real num1 and imaginary num2 subtract correctly.

        num1 = new Complex(3.0);
        num2 = new Complex(0.0, 1.0);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(3.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(1.0, num2.getImag());
        assertEquals(3.0, theDifference.getReal());
        assertEquals(-1.0, theDifference.getImag());

        theDifference = num1.subtract(num2);
        assertEquals(3.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(1.0, num2.getImag());
        assertEquals(3.0, theDifference.getReal());
        assertEquals(-1.0, theDifference.getImag());

        // Verify that imaginary num1 and real num2 subtract correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(2.0, 0.0);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(-2.0, theDifference.getReal());
        assertEquals(1.0, theDifference.getImag());

        theDifference = num1.subtract(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(-2.0, theDifference.getReal());
        assertEquals(1.0, theDifference.getImag());

        // Verify that imaginary num1 and imaginary num2 subtract correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(0.0, 2.0);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theDifference.getReal());
        assertEquals(-1.0, theDifference.getImag());

        theDifference = num1.subtract(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theDifference.getReal());
        assertEquals(-1.0, theDifference.getImag());

        // Verify that complex num1 and complex num2 subtract correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex(2.0, 2.0);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(-1.0, theDifference.getReal());
        assertEquals(-1.0, theDifference.getImag());

        theDifference = num1.subtract(num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(-1.0, theDifference.getReal());
        assertEquals(-1.0, theDifference.getImag());

        // Verify that complex num1 and 0 num2 subtract correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex();

        theDifference = Complex.subtract(num1, num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(1.0, theDifference.getReal());
        assertEquals(1.0, theDifference.getImag());

        theDifference = num1.subtract(num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(1.0, theDifference.getReal());
        assertEquals(1.0, theDifference.getImag());

        // Verify that 0 num1 and complex num2 subtract correctly.

        num1 = new Complex();
        num2 = new Complex(2.0, 2.0);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(-2.0, theDifference.getReal());
        assertEquals(-2.0, theDifference.getImag());

        theDifference = num1.subtract(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(-2.0, theDifference.getReal());
        assertEquals(-2.0, theDifference.getImag());

        // Test subtracting doubles, floats, and ints.

        num1 = new Complex(4.0,2.0);
        theDifference = num1.subtract(2.0);
        assertEquals(2.0, theDifference.getReal());
        assertEquals(2.0, theDifference.getImag());

        theDifference = num1.subtract((float)2.0);
        assertEquals(2.0, theDifference.getReal());
        assertEquals(2.0, theDifference.getImag());

        theDifference = num1.subtract(2);
        assertEquals(2.0, theDifference.getReal());
        assertEquals(2.0, theDifference.getImag());

        // Verify that the exceptions are produced as expected.

        final Complex theNum = new Complex (2.0, 2.0);
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.subtract(null, theNum);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.subtract(theNum, null);
        });

    }

    @Test
    void multiply()
    {
        Complex num1;
        Complex num2;
        Complex theProduct;

        // Verify that complex numbers that are only real multiply correctly.

        num1 = new Complex(3.0);
        num2 = new Complex(2.0);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(3.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(6.0, theProduct.getReal());
        assertEquals(0.0, theProduct.getImag());

        theProduct = num1.multiply(num2);
        assertEquals(3.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(6.0, theProduct.getReal());
        assertEquals(0.0, theProduct.getImag());

        // Verify that real num1 and imaginary num2 multiply correctly.

        num1 = new Complex(3.0);
        num2 = new Complex(0.0, 1.0);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(3.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(1.0, num2.getImag());
        assertEquals(0.0, theProduct.getReal());
        assertEquals(3.0, theProduct.getImag());

        theProduct = num1.multiply(num2);
        assertEquals(3.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(1.0, num2.getImag());
        assertEquals(0.0, theProduct.getReal());
        assertEquals(3.0, theProduct.getImag());

        // Verify that imaginary num1 and real num2 multiply correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(2.0, 0.0);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(0.0, theProduct.getReal());
        assertEquals(2.0, theProduct.getImag());

        theProduct = num1.multiply(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(0.0, theProduct.getReal());
        assertEquals(2.0, theProduct.getImag());

        // Verify that imaginary num1 and imaginary num2 multiply correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(0.0, 2.0);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(-2.0, theProduct.getReal());
        assertEquals(0.0, theProduct.getImag());

        theProduct = num1.multiply(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(-2.0, theProduct.getReal());
        assertEquals(0.0, theProduct.getImag());

        // Verify that complex num1 and complex num2 multiply correctly.

        num1 = new Complex(1.0, 2.0);
        num2 = new Complex(2.0, 2.0);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(2.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(-2.0, theProduct.getReal());
        assertEquals(6.0, theProduct.getImag());

        theProduct = num1.multiply(num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(2.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(-2.0, theProduct.getReal());
        assertEquals(6.0, theProduct.getImag());

        // Verify that complex num1 and 0 num2 multiply correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex();

        theProduct = Complex.multiply(num1, num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(0.0, theProduct.getReal());
        assertEquals(0.0, theProduct.getImag());

        theProduct = num1.multiply(num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(1.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(0.0, theProduct.getReal());
        assertEquals(0.0, theProduct.getImag());

        // Verify that 0 num1 and complex num2 multiply correctly.

        num1 = new Complex();
        num2 = new Complex(2.0, 2.0);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theProduct.getReal());
        assertEquals(0.0, theProduct.getImag());

        theProduct = num1.multiply(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theProduct.getReal());
        assertEquals(0.0, theProduct.getImag());

        // Test multiplying by doubles, floats, and ints.

        num1 = new Complex(4.0,1.0);
        theProduct = num1.multiply(2.0);
        assertEquals(8.0, theProduct.getReal());
        assertEquals(2.0, theProduct.getImag());

        theProduct = num1.multiply((float)2.0);
        assertEquals(8.0, theProduct.getReal());
        assertEquals(2.0, theProduct.getImag());

        theProduct = num1.multiply(2);
        assertEquals(8.0, theProduct.getReal());
        assertEquals(2.0, theProduct.getImag());

        // Verify that the exceptions are produced as expected.

        final Complex theNum = new Complex (2.0, 2.0);
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.multiply(null, theNum);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.multiply(theNum, null);
        });
    }

    @Test
    void divide()
    {
        Complex num1;
        Complex num2;
        Complex theQuotient;

        // Verify that complex numbers that are only real divide correctly.

        num1 = new Complex(6.0);
        num2 = new Complex(2.0);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(6.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(3.0, theQuotient.getReal());
        assertEquals(0.0, theQuotient.getImag());

        theQuotient = num1.divide(num2);
        assertEquals(6.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(3.0, theQuotient.getReal());
        assertEquals(0.0, theQuotient.getImag());

        // Verify that real num1 and imaginary num2 divide correctly.

        num1 = new Complex(6.0);
        num2 = new Complex(0.0, 2.0);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(6.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theQuotient.getReal());
        assertEquals(-3.0, theQuotient.getImag());

        theQuotient = num1.divide(num2);
        assertEquals(6.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theQuotient.getReal());
        assertEquals(-3.0, theQuotient.getImag());

        // Verify that imaginary num1 and real num2 divide correctly.

        num1 = new Complex(0.0, 6.0);
        num2 = new Complex(2.0, 0.0);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(6.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(0.0, theQuotient.getReal());
        assertEquals(3.0, theQuotient.getImag());

        theQuotient = num1.divide(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(6.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(0.0, num2.getImag());
        assertEquals(0.0, theQuotient.getReal());
        assertEquals(3.0, theQuotient.getImag());

        // Verify that imaginary num1 and imaginary num2 divide correctly.

        num1 = new Complex(0.0, 6.0);
        num2 = new Complex(0.0, 3.0);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(6.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(3.0, num2.getImag());
        assertEquals(2.0, theQuotient.getReal());
        assertEquals(0.0, theQuotient.getImag());

        theQuotient = num1.divide(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(6.0, num1.getImag());
        assertEquals(0.0, num2.getReal());
        assertEquals(3.0, num2.getImag());
        assertEquals(2.0, theQuotient.getReal());
        assertEquals(0.0, theQuotient.getImag());

        // Verify that complex num1 and complex num2 divide correctly.

        num1 = new Complex(1.0, 2.0);
        num2 = new Complex(2.0, 2.0);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(2.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.75, theQuotient.getReal());
        assertEquals(0.25, theQuotient.getImag());

        theQuotient = num1.divide(num2);
        assertEquals(1.0, num1.getReal());
        assertEquals(2.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.75, theQuotient.getReal());
        assertEquals(0.25, theQuotient.getImag());

        // Verify that 0 num1 and complex num2 divide correctly.

        num1 = new Complex();
        num2 = new Complex(2.0, 2.0);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theQuotient.getReal());
        assertEquals(0.0, theQuotient.getImag());

        theQuotient = num1.divide(num2);
        assertEquals(0.0, num1.getReal());
        assertEquals(0.0, num1.getImag());
        assertEquals(2.0, num2.getReal());
        assertEquals(2.0, num2.getImag());
        assertEquals(0.0, theQuotient.getReal());
        assertEquals(0.0, theQuotient.getImag());

        // Test dividing by doubles, floats, and ints.

        num1 = new Complex(5.0,2.0);
        theQuotient = num1.divide(2.0);
        assertEquals(2.5, theQuotient.getReal());
        assertEquals(1.0, theQuotient.getImag());

        theQuotient = num1.divide((float)2.0);
        assertEquals(2.5, theQuotient.getReal());
        assertEquals(1.0, theQuotient.getImag());

        theQuotient = num1.divide(2);
        assertEquals(2.5, theQuotient.getReal());
        assertEquals(1.0, theQuotient.getImag());

        // Verify that the exceptions are produced as expected.

        final Complex theNum = new Complex (2.0, 2.0);
        final Complex zeroNum = new Complex ();
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.divide(null, theNum);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.divide(theNum, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Complex.divide(theNum, zeroNum);
        });
    }

    @Test
    void pow()
    {
        Complex theNum = new Complex ();
        Complex result = Complex.pow(theNum, 0.0);
        assertEquals(1.0, result.getReal());
        assertEquals(0.0, result.getImag());

        result = Complex.pow(theNum, 1.0);
        assertEquals(0.0, result.getReal());
        assertEquals(0.0, result.getImag());

        theNum = new Complex(0.0, 1.0);
        result = Complex.pow(theNum, 1.0);
        assertEquals(0.0, result.getReal(), 0.00000001);
        assertEquals(1.0, result.getImag(), 0.00000001);

        theNum = new Complex(0.0, 1.0);
        result = Complex.pow(theNum, 2.0);
        assertEquals(-1.0, result.getReal(), 0.00000001);
        assertEquals(0.0, result.getImag(), 0.00000001);

        theNum = new Complex(0.0, 1.0);
        result = Complex.pow(theNum, 3.0);
        assertEquals(0.0, result.getReal(), 0.00000001);
        assertEquals(-1.0, result.getImag(), 0.00000001);

        theNum = new Complex(0.0, 1.0);
        result = Complex.pow(theNum, 4.0);
        assertEquals(1.0, result.getReal(), 0.00000001);
        assertEquals(0.0, result.getImag(), 0.00000001);

        theNum = new Complex(4.0, 0.0);
        result = Complex.pow(theNum, 1.0/2.0);
        assertEquals(2.0, result.getReal(), 0.00000001);
        assertEquals(0.0, result.getImag(), 0.00000001);

        theNum = new Complex(4.0, 0.0);
        result = Complex.pow(theNum, -1.0/2.0);
        assertEquals(1.0/2.0, result.getReal(), 0.00000001);
        assertEquals(0.0, result.getImag(), 0.00000001);

        theNum = new Complex(0.0, 4.0);
        result = Complex.pow(theNum, 2.0);
        assertEquals(-16.0, result.getReal(), 0.00000001);
        assertEquals(0.0, result.getImag(), 0.00000001);

        theNum = new Complex(0.0, 4.0);
        result = Complex.pow(theNum, 1.0/2.0);
        assertEquals(Math.sqrt(2.0), result.getReal(), 0.00000001);
        assertEquals(Math.sqrt(2.0), result.getImag(), 0.00000001);

        theNum = new Complex(0.0, 4.0);
        result = Complex.pow(theNum, -1.0/2.0);
        assertEquals(1.0/Math.sqrt(8.0), result.getReal(), 0.00000001);
        assertEquals(-1.0/Math.sqrt(8.0), result.getImag(), 0.00000001);

        theNum = new Complex(1.0, 1.0);
        result = Complex.pow(theNum, 2.0);
        assertEquals(0.0, result.getReal(), 0.00000001);
        assertEquals(2.0, result.getImag(), 0.00000001);

        result = Complex.pow(theNum, 3.0);
        assertEquals(-2.0, result.getReal(), 0.00000001);
        assertEquals(2.0, result.getImag(), 0.00000001);

        result = Complex.pow(theNum, 4.0);
        assertEquals(-4.0, result.getReal(), 0.00000001);
        assertEquals(0.0, result.getImag(), 0.00000001);

        result = Complex.pow(theNum, 5.0);
        assertEquals(-4.0, result.getReal(), 0.00000001);
        assertEquals(-4.0, result.getImag(), 0.00000001);

        result = Complex.pow(theNum, 6.0);
        assertEquals(0.0, result.getReal(), 0.00000001);
        assertEquals(-8.0, result.getImag(), 0.00000001);

        result = Complex.pow(theNum, 7.0);
        assertEquals(8.0, result.getReal(), 0.00000001);
        assertEquals(-8.0, result.getImag(), 0.00000001);

        result = Complex.pow(theNum, 8.0);
        assertEquals(16.0, result.getReal(), 0.00000001);
        assertEquals(0.0, result.getImag(), 0.00000001);

        assertThrows(IllegalArgumentException.class, () -> {
            Complex.pow(null, 3.0);
        });

        result = theNum.pow(4.0);
        assertEquals(-4.0, result.getReal(), 0.00000001);
        assertEquals(0.0, result.getImag(), 0.00000001);
    }

    @Test
    void isZero()
    {
        assertTrue(Complex.isZero(new Complex()));
        assertTrue(Complex.isZero(new Complex(0.0, 0.0)));
        assertFalse(Complex.isZero(new Complex(1.0)));
        assertFalse(Complex.isZero(new Complex(0.0,1.0)));
        assertFalse(Complex.isZero(new Complex(1.0,1.0)));

        Complex theNum = new Complex();
        assertTrue(Complex.isZero(theNum));
        assertTrue(theNum.isZero());

        theNum = new Complex(0.0);
        assertTrue(Complex.isZero(theNum));
        assertTrue(theNum.isZero());

        theNum = new Complex(1.0);
        assertFalse(Complex.isZero(theNum));
        assertFalse(theNum.isZero());

        theNum = new Complex(0.0, 1.0);
        assertFalse(Complex.isZero(theNum));
        assertFalse(theNum.isZero());

        theNum = new Complex(1.0, 1.0);
        assertFalse(Complex.isZero(theNum));
        assertFalse(theNum.isZero());

        assertThrows(IllegalArgumentException.class, () -> {
            Complex.isZero(null);
        });
    }

    @Test
    void complexToPolarCoordinate ()
    {
        Complex theComplex = new Complex(new PolarCoordinate(0.0,0.0));
        PolarCoordinate theCoord = Complex.complexToPolarCoordinate(theComplex);
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        theComplex = new Complex (new PolarCoordinate(1.0, Math.PI/4.0));
        theCoord = Complex.complexToPolarCoordinate(theComplex);
        assertEquals(1.0, theCoord.getRadius());
        assertEquals(Math.PI/4.0, theCoord.getAngle());

        theComplex = new Complex (new PolarCoordinate(1.0, 4.0*Math.PI + Math.PI/4.0));
        theCoord = Complex.complexToPolarCoordinate(theComplex);
        assertEquals(1.0, theCoord.getRadius());
        assertEquals(Math.PI/4.0, theCoord.getAngle(), 0.00000001);

        theComplex = new Complex(new PolarCoordinate(0.0,0.0));
        theCoord = theComplex.toPolarCoordinate();
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        theComplex = new Complex (new PolarCoordinate(1.0, Math.PI/4.0));
        theCoord = theComplex.toPolarCoordinate();
        assertEquals(1.0, theCoord.getRadius());
        assertEquals(Math.PI/4.0, theCoord.getAngle());

        theComplex = new Complex (new PolarCoordinate(1.0, 4.0*Math.PI + Math.PI/4.0));
        theCoord = theComplex.toPolarCoordinate();
        assertEquals(1.0, theCoord.getRadius());
        assertEquals(Math.PI/4.0, theCoord.getAngle(), 0.00000001);
    }

    @Test
    void abs()
    {
        //TODO: add tests for instance operation.

        Complex theComplex = new Complex();
        double theAbs = Complex.abs(theComplex);
        assertEquals(0.0, theAbs);

        theComplex = new Complex(1.0,0.0);
        theAbs = Complex.abs(theComplex);
        assertEquals(1.0, theAbs);

        theComplex = new Complex(1.0, 1.0);
        theAbs = Complex.abs(theComplex);
        assertEquals(Math.sqrt(2.0), theAbs);

        theComplex = new Complex(-1.0, 1.0);
        theAbs = Complex.abs(theComplex);
        assertEquals(Math.sqrt(2.0), theAbs);

        theComplex = new Complex(0, 1.0);
        theAbs = Complex.abs(theComplex);
        assertEquals(1.0, theAbs);

        assertThrows(IllegalArgumentException.class, () -> {
            Complex.abs(null);
        });

        theComplex = new Complex(1.0,1.0);
        theAbs = theComplex.abs();
        assertEquals(Math.sqrt(2.0), theAbs);
    }

    @Test
    void modulus()
    {
        // TODO: add tests.
    }

    @Test
    void equals()
    {
        Complex num1 = new Complex();
        Complex num2 = new Complex();
        boolean result = Complex.equals(num1, num2);
        assertEquals(true, result);

        num1 = new Complex(1.0, 1.0);
        result = Complex.equals(num1, num2);
        assertEquals(false, result);

        num2 = new Complex(1.0,1.0);
        result = Complex.equals(num1, num2);
        assertEquals(true, result);

        num1 = new Complex(1.0);
        result = Complex.equals(num1, num2);
        assertEquals(false, result);

        result = Complex.equals(num2, num1);
        assertEquals(false, result);

        num2 = new Complex(1.0);
        result = Complex.equals(num1, num2);
        assertEquals(true, result);

        result = Complex.equals(num2, num1);
        assertEquals(true, result);

        result = Complex.equals(num1, (double)1.0);
        assertEquals(true, result);

        result = Complex.equals(num1, (double)2.0);
        assertEquals(false, result);

        num1 = new Complex(1.0, 1.0);
        result = Complex.equals(num1, (double)1.0);
        assertEquals(false, result);

        num1 = new Complex(1.0);
        result = Complex.equals(num1, (float)1.0);
        assertEquals(true, result);

        result = Complex.equals(num1, (float)2.0);
        assertEquals(false, result);

        num1 = new Complex(1.0, 1.0);
        result = Complex.equals(num1, (float)1.0);
        assertEquals(false, result);

        num1 = new Complex(1.0);
        result = Complex.equals(num1, (int)1.0);
        assertEquals(true, result);

        result = Complex.equals(num1, (int)2.0);
        assertEquals(false, result);

        num1 = new Complex(1.0, 1.0);
        result = Complex.equals(num1, (int)1.0);
        assertEquals(false, result);

        assertThrows(IllegalArgumentException.class, () -> {
            Complex.equals(null,null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Complex.equals(new Complex(1.0,1.0),null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Complex.equals(null,new Complex(1.0,1.0));
        });

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex(1.0);
        result = num1.equals(num2);
        assertEquals(false, result);

        num2 = new Complex(1.0,1.0);
        result = num1.equals(num2);
        assertEquals(true, result);

        num1 = new Complex(1.0);
        assertEquals(true, num1.equals((double)1.0));
        assertEquals(false, num1.equals((double)2.0));
        assertEquals(true, num1.equals((float)1.0));
        assertEquals(false, num1.equals((float)2.0));
        assertEquals(true, num1.equals(1));
        assertEquals(false, num1.equals(2));

        num1 = new Complex(1.0,1.0);
        assertEquals(false, num1.equals((double)1.0));
        assertEquals(false, num1.equals((float)1.0));
        assertEquals(false, num1.equals((int)1.0));

        assertThrows(IllegalArgumentException.class, () -> {
            new Complex(1.0, 1.0).equals(null);
        });
    }

    @Test
    void negate()
    {
        Complex theNum;
        Complex result;

        // Test class method.

        theNum = new Complex();
        result = Complex.negate(theNum);
        assertEquals(0.0, result.getReal());
        assertEquals(0.0, result.getImag());

        theNum = new Complex(1.0);
        result = Complex.negate(theNum);
        assertEquals(-1.0, result.getReal());
        assertEquals(0.0, result.getImag());

        theNum = new Complex(-1.0);
        result = Complex.negate(theNum);
        assertEquals(1.0, result.getReal());
        assertEquals(0.0, result.getImag());

        theNum = new Complex(0.0, 1.0);
        result = Complex.negate(theNum);
        assertEquals(0.0, result.getReal());
        assertEquals(-1.0, result.getImag());

        theNum = new Complex(0.0, -1.0);
        result = Complex.negate(theNum);
        assertEquals(0.0, result.getReal());
        assertEquals(1.0, result.getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            Complex.negate(null);
        });

        // Test instance method.

        theNum = new Complex();
        result = theNum.negate();
        assertEquals(0.0, result.getReal());
        assertEquals(0.0, result.getImag());

        theNum = new Complex(1.0);
        result = theNum.negate();
        assertEquals(-1.0, result.getReal());
        assertEquals(0.0, result.getImag());

        theNum = new Complex(-1.0);
        result = theNum.negate();
        assertEquals(1.0, result.getReal());
        assertEquals(0.0, result.getImag());

        theNum = new Complex(0.0, 1.0);
        result = theNum.negate();
        assertEquals(0.0, result.getReal());
        assertEquals(-1.0, result.getImag());

        theNum = new Complex(0.0, -1.0);
        result = theNum.negate();
        assertEquals(0.0, result.getReal());
        assertEquals(1.0, result.getImag());
    }

    @Test
    void testToString()
    {
        Complex theComplex;

        // Test class method.

        theComplex = new Complex (1.0, 0.0);
        assertTrue(Complex.toString(theComplex).compareTo("1.0") == 0);
        theComplex = new Complex (-1.0, 0.0);
        assertTrue(Complex.toString(theComplex).compareTo("-1.0") == 0);
        theComplex = new Complex(1.0,1.0);
        assertTrue(Complex.toString(theComplex).compareTo("1.0 + 1.0*I") == 0);
        theComplex = new Complex(1.0,-1.0);
        assertTrue(Complex.toString(theComplex).compareTo("1.0 - 1.0*I") == 0);
        theComplex = new Complex(-1.0,-1.0);
        assertTrue(Complex.toString(theComplex).compareTo("-1.0 - 1.0*I") == 0);

        // Test instance method.

        theComplex = new Complex (1.0, 0.0);
        assertTrue(theComplex.toString().compareTo("1.0") == 0);
        theComplex = new Complex (-1.0, 0.0);
        assertTrue(theComplex.toString().compareTo("-1.0") == 0);
        theComplex = new Complex(1.0,1.0);
        assertTrue(theComplex.toString().compareTo("1.0 + 1.0*I") == 0);
        theComplex = new Complex(1.0,-1.0);
        assertTrue(theComplex.toString().compareTo("1.0 - 1.0*I") == 0);
        theComplex = new Complex(-1.0,-1.0);
        assertTrue(theComplex.toString().compareTo("-1.0 - 1.0*I") == 0);
    }

    @Test
    void parseComplex()
    {
        assertTrue(Complex.parseComplex("0").equals(new Complex(0.0, 0.0)));
        assertTrue(Complex.parseComplex("1").equals(new Complex(1.0, 0.0)));
        assertTrue(Complex.parseComplex("1.1").equals(new Complex(1.1, 0.0)));
        assertTrue(Complex.parseComplex("-1").equals(new Complex(-1.0, 0.0)));
        assertTrue(Complex.parseComplex("-1.1").equals(new Complex(-1.1, 0.0)));
        assertTrue(Complex.parseComplex("+1").equals(new Complex(1.0, 0.0)));
        assertTrue(Complex.parseComplex("+1.1").equals(new Complex(1.1, 0.0)));
        assertTrue(Complex.parseComplex("0.111").equals(new Complex(0.111, 0.0)));
        assertTrue(Complex.parseComplex("+0.111").equals(new Complex(0.111, 0.0)));
        assertTrue(Complex.parseComplex("-0.111").equals(new Complex(-0.111, 0.0)));
        assertTrue(Complex.parseComplex(".111").equals(new Complex(0.111, 0.0)));
        assertTrue(Complex.parseComplex("+.111").equals(new Complex(0.111, 0.0)));
        assertTrue(Complex.parseComplex("-.111").equals(new Complex(-0.111, 0.0)));

        assertTrue(Complex.parseComplex("I").equals(new Complex(0.0, 1.0)));
        assertTrue(Complex.parseComplex("+I").equals(new Complex(0.0, 1.0)));
        assertTrue(Complex.parseComplex("-I").equals(new Complex(0.0, -1.0)));
        assertTrue(Complex.parseComplex("1*I").equals(new Complex(0.0, 1.0)));
        assertTrue(Complex.parseComplex("+1*I").equals(new Complex(0.0, 1.0)));
        assertTrue(Complex.parseComplex("-1*I").equals(new Complex(0.0, -1.0)));
        assertTrue(Complex.parseComplex("1.0*I").equals(new Complex(0.0, 1.0)));
        assertTrue(Complex.parseComplex("+1.0*I").equals(new Complex(0.0, 1.0)));
        assertTrue(Complex.parseComplex("-1.0*I").equals(new Complex(0.0, -1.0)));
        assertTrue(Complex.parseComplex("0.111*I").equals(new Complex(0.0, 0.111)));
        assertTrue(Complex.parseComplex("+0.111*I").equals(new Complex(0.0, 0.111)));
        assertTrue(Complex.parseComplex("-0.111*I").equals(new Complex(0.0, -0.111)));
        assertTrue(Complex.parseComplex(".111*I").equals(new Complex(0.0, 0.111)));
        assertTrue(Complex.parseComplex("+.111*I").equals(new Complex(0.0, 0.111)));
        assertTrue(Complex.parseComplex("-.111*I").equals(new Complex(0.0, -0.111)));

        assertTrue(Complex.parseComplex("1+I").equals(new Complex(1.0, 1.0)));
        assertTrue(Complex.parseComplex("1-I").equals(new Complex(1.0, -1.0)));
        assertTrue(Complex.parseComplex("-1+I").equals(new Complex(-1.0, 1.0)));
        assertTrue(Complex.parseComplex("-1-I").equals(new Complex(-1.0, -1.0)));
        assertTrue(Complex.parseComplex("-1.1+I").equals(new Complex(-1.1, 1.0)));
        assertTrue(Complex.parseComplex("-1.1-I").equals(new Complex(-1.1, -1.0)));

        assertTrue(Complex.parseComplex("1+1*I").equals(new Complex(1.0, 1.0)));
        assertTrue(Complex.parseComplex("1-1*I").equals(new Complex(1.0, -1.0)));
        assertTrue(Complex.parseComplex("1+1.1*I").equals(new Complex(1.0, 1.1)));
        assertTrue(Complex.parseComplex("1-1.1*I").equals(new Complex(1.0, -1.1)));
        assertTrue(Complex.parseComplex("+1+1*I").equals(new Complex(1.0, 1.0)));
        assertTrue(Complex.parseComplex("+1-1*I").equals(new Complex(1.0, -1.0)));
        assertTrue(Complex.parseComplex("+1+1.1*I").equals(new Complex(1.0, 1.1)));
        assertTrue(Complex.parseComplex("+1-1.1*I").equals(new Complex(1.0, -1.1)));
        assertTrue(Complex.parseComplex("-1+1*I").equals(new Complex(-1.0, 1.0)));
        assertTrue(Complex.parseComplex("-1-1*I").equals(new Complex(-1.0, -1.0)));
        assertTrue(Complex.parseComplex("-1+1.1*I").equals(new Complex(-1.0, 1.1)));
        assertTrue(Complex.parseComplex("-1-1.1*I").equals(new Complex(-1.0, -1.1)));
        assertTrue(Complex.parseComplex("1.1+1*I").equals(new Complex(1.1, 1.0)));
        assertTrue(Complex.parseComplex("1.1-1*I").equals(new Complex(1.1, -1.0)));
        assertTrue(Complex.parseComplex("1.1+1.1*I").equals(new Complex(1.1, 1.1)));
        assertTrue(Complex.parseComplex("1.1-1.1*I").equals(new Complex(1.1, -1.1)));
        assertTrue(Complex.parseComplex("+1.1+1*I").equals(new Complex(1.1, 1.0)));
        assertTrue(Complex.parseComplex("+1.1-1*I").equals(new Complex(1.1, -1.0)));
        assertTrue(Complex.parseComplex("+1.1+1.1*I").equals(new Complex(1.1, 1.1)));
        assertTrue(Complex.parseComplex("+1.1-1.1*I").equals(new Complex(1.1, -1.1)));
        assertTrue(Complex.parseComplex("-1.1+1*I").equals(new Complex(-1.1, 1.0)));
        assertTrue(Complex.parseComplex("-1.1-1*I").equals(new Complex(-1.1, -1.0)));
        assertTrue(Complex.parseComplex("-1.1+1.1*I").equals(new Complex(-1.1, 1.1)));
        assertTrue(Complex.parseComplex("-1.1-1.1*I").equals(new Complex(-1.1, -1.1)));
    }
}