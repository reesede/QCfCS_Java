package qcfcs_math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.IllegalArgumentException;

/**
 * This class implements complex numbers.
 * Created by reesede on 1/7/17.
 * @author David E. Reese
 * @version 1.2.1
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
// along with AsianFlashCard.  If not, see <http://www.gnu.org/licenses/>.
//
// History:
//      20170107    D.E. Reese          Creation (Programming Drill 1.2.1)
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
    void conjugate()
    {
        Complex theComplex;
        Complex conjugatedComplex;

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

        // Test adding doubles, floats, and ints.

        num1 = new Complex(2.0,2.0);
        theSum = num1.add(2.0);
        assertEquals(4.0, theSum.realPart);
        assertEquals(2.0, theSum.imagPart);

        theSum = num1.add((float)2.0);
        assertEquals(4.0, theSum.realPart);
        assertEquals(2.0, theSum.imagPart);

        theSum = num1.add(2);
        assertEquals(4.0, theSum.realPart);
        assertEquals(2.0, theSum.imagPart);

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
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(1.0, theDifference.realPart);
        assertEquals(0.0, theDifference.imagPart);

        theDifference = num1.subtract(num2);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(1.0, theDifference.realPart);
        assertEquals(0.0, theDifference.imagPart);

        // Verify that real num1 and imaginary num2 subtract correctly.

        num1 = new Complex(3.0);
        num2 = new Complex(0.0, 1.0);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(1.0, num2.imagPart);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(1.0, num2.imagPart);
        assertEquals(3.0, theDifference.realPart);
        assertEquals(-1.0, theDifference.imagPart);

        theDifference = num1.subtract(num2);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(1.0, num2.imagPart);
        assertEquals(3.0, theDifference.realPart);
        assertEquals(-1.0, theDifference.imagPart);

        // Verify that imaginary num1 and real num2 subtract correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(2.0, 0.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(-2.0, theDifference.realPart);
        assertEquals(1.0, theDifference.imagPart);

        theDifference = num1.subtract(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(-2.0, theDifference.realPart);
        assertEquals(1.0, theDifference.imagPart);

        // Verify that imaginary num1 and imaginary num2 subtract correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(0.0, 2.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theDifference.realPart);
        assertEquals(-1.0, theDifference.imagPart);

        theDifference = num1.subtract(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theDifference.realPart);
        assertEquals(-1.0, theDifference.imagPart);

        // Verify that complex num1 and complex num2 subtract correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex(2.0, 2.0);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(-1.0, theDifference.realPart);
        assertEquals(-1.0, theDifference.imagPart);

        theDifference = num1.subtract(num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(-1.0, theDifference.realPart);
        assertEquals(-1.0, theDifference.imagPart);

        // Verify that complex num1 and 0 num2 subtract correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex();
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(1.0, theDifference.realPart);
        assertEquals(1.0, theDifference.imagPart);

        theDifference = num1.subtract(num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(1.0, theDifference.realPart);
        assertEquals(1.0, theDifference.imagPart);

        // Verify that 0 num1 and complex num2 subtract correctly.

        num1 = new Complex();
        num2 = new Complex(2.0, 2.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theDifference = Complex.subtract(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(-2.0, theDifference.realPart);
        assertEquals(-2.0, theDifference.imagPart);

        theDifference = num1.subtract(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(-2.0, theDifference.realPart);
        assertEquals(-2.0, theDifference.imagPart);

        // Test subtracting doubles, floats, and ints.

        num1 = new Complex(4.0,2.0);
        theDifference = num1.subtract(2.0);
        assertEquals(2.0, theDifference.realPart);
        assertEquals(2.0, theDifference.imagPart);

        theDifference = num1.subtract((float)2.0);
        assertEquals(2.0, theDifference.realPart);
        assertEquals(2.0, theDifference.imagPart);

        theDifference = num1.subtract(2);
        assertEquals(2.0, theDifference.realPart);
        assertEquals(2.0, theDifference.imagPart);

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
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(6.0, theProduct.realPart);
        assertEquals(0.0, theProduct.imagPart);

        theProduct = num1.multiply(num2);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(6.0, theProduct.realPart);
        assertEquals(0.0, theProduct.imagPart);

        // Verify that real num1 and imaginary num2 multiply correctly.

        num1 = new Complex(3.0);
        num2 = new Complex(0.0, 1.0);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(1.0, num2.imagPart);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(1.0, num2.imagPart);
        assertEquals(0.0, theProduct.realPart);
        assertEquals(3.0, theProduct.imagPart);

        theProduct = num1.multiply(num2);
        assertEquals(3.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(1.0, num2.imagPart);
        assertEquals(0.0, theProduct.realPart);
        assertEquals(3.0, theProduct.imagPart);

        // Verify that imaginary num1 and real num2 multiply correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(2.0, 0.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(0.0, theProduct.realPart);
        assertEquals(2.0, theProduct.imagPart);

        theProduct = num1.multiply(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(0.0, theProduct.realPart);
        assertEquals(2.0, theProduct.imagPart);

        // Verify that imaginary num1 and imaginary num2 multiply correctly.

        num1 = new Complex(0.0, 1.0);
        num2 = new Complex(0.0, 2.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(-2.0, theProduct.realPart);
        assertEquals(0.0, theProduct.imagPart);

        theProduct = num1.multiply(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(-2.0, theProduct.realPart);
        assertEquals(0.0, theProduct.imagPart);

        // Verify that complex num1 and complex num2 multiply correctly.

        num1 = new Complex(1.0, 2.0);
        num2 = new Complex(2.0, 2.0);
        assertEquals(1.0, num1.realPart);
        assertEquals(2.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(2.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(-2.0, theProduct.realPart);
        assertEquals(6.0, theProduct.imagPart);

        theProduct = num1.multiply(num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(2.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(-2.0, theProduct.realPart);
        assertEquals(6.0, theProduct.imagPart);

        // Verify that complex num1 and 0 num2 multiply correctly.

        num1 = new Complex(1.0, 1.0);
        num2 = new Complex();
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(0.0, theProduct.realPart);
        assertEquals(0.0, theProduct.imagPart);

        theProduct = num1.multiply(num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(1.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(0.0, theProduct.realPart);
        assertEquals(0.0, theProduct.imagPart);

        // Verify that 0 num1 and complex num2 multiply correctly.

        num1 = new Complex();
        num2 = new Complex(2.0, 2.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theProduct = Complex.multiply(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theProduct.realPart);
        assertEquals(0.0, theProduct.imagPart);

        theProduct = num1.multiply(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theProduct.realPart);
        assertEquals(0.0, theProduct.imagPart);

        // Test multiplying by doubles, floats, and ints.

        num1 = new Complex(4.0,1.0);
        theProduct = num1.multiply(2.0);
        assertEquals(8.0, theProduct.realPart);
        assertEquals(2.0, theProduct.imagPart);

        theProduct = num1.multiply((float)2.0);
        assertEquals(8.0, theProduct.realPart);
        assertEquals(2.0, theProduct.imagPart);

        theProduct = num1.multiply(2);
        assertEquals(8.0, theProduct.realPart);
        assertEquals(2.0, theProduct.imagPart);

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
        assertEquals(6.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(6.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(3.0, theQuotient.realPart);
        assertEquals(0.0, theQuotient.imagPart);

        theQuotient = num1.divide(num2);
        assertEquals(6.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(3.0, theQuotient.realPart);
        assertEquals(0.0, theQuotient.imagPart);

        // Verify that real num1 and imaginary num2 divide correctly.

        num1 = new Complex(6.0);
        num2 = new Complex(0.0, 2.0);
        assertEquals(6.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(6.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theQuotient.realPart);
        assertEquals(-3.0, theQuotient.imagPart);

        theQuotient = num1.divide(num2);
        assertEquals(6.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theQuotient.realPart);
        assertEquals(-3.0, theQuotient.imagPart);

        // Verify that imaginary num1 and real num2 divide correctly.

        num1 = new Complex(0.0, 6.0);
        num2 = new Complex(2.0, 0.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(6.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(6.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(0.0, theQuotient.realPart);
        assertEquals(3.0, theQuotient.imagPart);

        theQuotient = num1.divide(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(6.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(0.0, num2.imagPart);
        assertEquals(0.0, theQuotient.realPart);
        assertEquals(3.0, theQuotient.imagPart);

        // Verify that imaginary num1 and imaginary num2 divide correctly.

        num1 = new Complex(0.0, 6.0);
        num2 = new Complex(0.0, 3.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(6.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(3.0, num2.imagPart);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(6.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(3.0, num2.imagPart);
        assertEquals(2.0, theQuotient.realPart);
        assertEquals(0.0, theQuotient.imagPart);

        theQuotient = num1.divide(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(6.0, num1.imagPart);
        assertEquals(0.0, num2.realPart);
        assertEquals(3.0, num2.imagPart);
        assertEquals(2.0, theQuotient.realPart);
        assertEquals(0.0, theQuotient.imagPart);

        // Verify that complex num1 and complex num2 divide correctly.

        num1 = new Complex(1.0, 2.0);
        num2 = new Complex(2.0, 2.0);
        assertEquals(1.0, num1.realPart);
        assertEquals(2.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(2.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.75, theQuotient.realPart);
        assertEquals(0.25, theQuotient.imagPart);

        theQuotient = num1.divide(num2);
        assertEquals(1.0, num1.realPart);
        assertEquals(2.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.75, theQuotient.realPart);
        assertEquals(0.25, theQuotient.imagPart);

        // Verify that 0 num1 and complex num2 divide correctly.

        num1 = new Complex();
        num2 = new Complex(2.0, 2.0);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);

        theQuotient = Complex.divide(num1, num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theQuotient.realPart);
        assertEquals(0.0, theQuotient.imagPart);

        theQuotient = num1.divide(num2);
        assertEquals(0.0, num1.realPart);
        assertEquals(0.0, num1.imagPart);
        assertEquals(2.0, num2.realPart);
        assertEquals(2.0, num2.imagPart);
        assertEquals(0.0, theQuotient.realPart);
        assertEquals(0.0, theQuotient.imagPart);

        // Test dividing by doubles, floats, and ints.

        num1 = new Complex(5.0,2.0);
        theQuotient = num1.divide(2.0);
        assertEquals(2.5, theQuotient.realPart);
        assertEquals(1.0, theQuotient.imagPart);

        theQuotient = num1.divide((float)2.0);
        assertEquals(2.5, theQuotient.realPart);
        assertEquals(1.0, theQuotient.imagPart);

        theQuotient = num1.divide(2);
        assertEquals(2.5, theQuotient.realPart);
        assertEquals(1.0, theQuotient.imagPart);

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
}