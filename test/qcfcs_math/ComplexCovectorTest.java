package qcfcs_math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements unit tests for the ComplexCovector class.
 * Created by reesede on 1/22/2017.
 * @author David E. Reese
 * @version 2.4.1
 * @since 2.1.1
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
//      20170122    D.E. Reese          Creation (Programming Drill 1.2.1)
//      20170131    D.E. Reese          Added code to stubs, copying from ComplexVectorTest.
//      20170201    D.E. Reese          Fixed ambiguous null pointers in multiply().
//      20170204    D.E. Reese          Added multiplyMatrix().
//      20170205    D.E. Reese          Added transpose(), transposeConjugate(), innerProduct(), size()
//

class ComplexCovectorTest
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
    void setAndGet()
    {
        // Test class methods.

        ComplexCovector theCovec = new ComplexCovector(2);
        assertEquals(0.0, ComplexCovector.get(theCovec, 0).getReal());
        assertEquals(0.0, ComplexCovector.get(theCovec, 0).getImag());
        assertEquals(0.0, ComplexCovector.get(theCovec, 1).getReal());
        assertEquals(0.0, ComplexCovector.get(theCovec, 1).getImag());

        ComplexCovector.set(theCovec, 0, new Complex(1.0, 1.0));
        ComplexCovector.set(theCovec, 1, new Complex(2.0, 2.0));
        assertEquals(1.0, ComplexCovector.get(theCovec, 0).getReal());
        assertEquals(1.0, ComplexCovector.get(theCovec, 0).getImag());
        assertEquals(2.0, ComplexCovector.get(theCovec, 1).getReal());
        assertEquals(2.0, ComplexCovector.get(theCovec, 1).getImag());

        ComplexCovector.set(theCovec, 0, 3.0);
        ComplexCovector.set(theCovec,1, (float)4.0);
        assertEquals(3.0, ComplexCovector.get(theCovec, 0).getReal());
        assertEquals(0.0, ComplexCovector.get(theCovec, 0).getImag());
        assertEquals(4.0, ComplexCovector.get(theCovec, 1).getReal());
        assertEquals(0.0, ComplexCovector.get(theCovec, 1).getImag());

        ComplexCovector.set(theCovec, 0, 5);
        ComplexCovector.set(theCovec, 1, new PolarCoordinate(4.0, Math.PI/4.0));
        assertEquals(5.0, ComplexCovector.get(theCovec, 0).getReal());
        assertEquals(0.0, ComplexCovector.get(theCovec, 0).getImag());
        assertEquals(2.0*Math.sqrt(2.0), ComplexCovector.get(theCovec, 1).getReal(), 0.00000001);
        assertEquals(2.0*Math.sqrt(2.0), ComplexCovector.get(theCovec, 1).getImag(), 0.00000001);

        // Test instance methods.

        theCovec = new ComplexCovector(2);
        assertEquals(0.0, theCovec.get(0).getReal());
        assertEquals(0.0, theCovec.get(0).getImag());
        assertEquals(0.0, theCovec.get(1).getReal());
        assertEquals(0.0, theCovec.get(1).getImag());

        theCovec.set(0, new Complex(1.0, 1.0));
        theCovec.set(1, new Complex(2.0, 2.0));
        assertEquals(1.0, theCovec.get(0).getReal());
        assertEquals(1.0, theCovec.get(0).getImag());
        assertEquals(2.0, theCovec.get(1).getReal());
        assertEquals(2.0, theCovec.get(1).getImag());

        ComplexCovector.set(theCovec, 0, 3.0);
        ComplexCovector.set(theCovec,1, (float)4.0);
        assertEquals(3.0, theCovec.get(0).getReal());
        assertEquals(0.0, theCovec.get(0).getImag());
        assertEquals(4.0, theCovec.get(1).getReal());
        assertEquals(0.0, theCovec.get(1).getImag());

        theCovec.set(0, 5);
        theCovec.set(1, new PolarCoordinate(4.0, Math.PI/4.0));
        assertEquals(5.0, theCovec.get(0).getReal());
        assertEquals(0.0, theCovec.get(0).getImag());
        assertEquals(2.0*Math.sqrt(2.0), theCovec.get(1).getReal(), 0.00000001);
        assertEquals(2.0*Math.sqrt(2.0), theCovec.get(1).getImag(), 0.00000001);
    }

    @Test
    void add()
    {
        // Test class method.

        ComplexCovector covec1 = new ComplexCovector(2);
        ComplexCovector covec2 = new ComplexCovector(2);
        ComplexCovector sum;

        covec1.set(0, new Complex(1.0, 1.0));
        covec1.set(1, new Complex(2.0, 2.0));
        covec2.set(0, new Complex(3.0, 3.0));
        covec2.set(1, new Complex(4.0, 4.0));

        sum = ComplexCovector.add(covec1, covec2);
        assertEquals(4.0, sum.get(0).getReal());
        assertEquals(4.0, sum.get(0).getImag());
        assertEquals(6.0, sum.get(1).getReal());
        assertEquals(6.0, sum.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.add(new ComplexCovector(2), null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.add(null, new ComplexCovector(2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.add(null, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.add(new ComplexCovector(2), new ComplexCovector(3));
        });

        // Test instance method.

        sum = covec1.add(covec2);
        assertEquals(4.0, sum.get(0).getReal());
        assertEquals(4.0, sum.get(0).getImag());
        assertEquals(6.0, sum.get(1).getReal());
        assertEquals(6.0, sum.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexCovector(2).add(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexCovector(2).add(new ComplexCovector(3));
        });
    }

    @Test
    void subtract()
    {
        // Test class method.

        ComplexCovector covec1 = new ComplexCovector(2);
        ComplexCovector covec2 = new ComplexCovector(2);
        ComplexCovector difference;

        covec1.set(0, new Complex(1.0, 1.0));
        covec1.set(1, new Complex(2.0, 2.0));
        covec2.set(0, new Complex(3.0, 3.0));
        covec2.set(1, new Complex(4.0, 4.0));

        difference = ComplexCovector.subtract(covec1, covec2);
        assertEquals(-2.0, difference.get(0).getReal());
        assertEquals(-2.0, difference.get(0).getImag());
        assertEquals(-2.0, difference.get(1).getReal());
        assertEquals(-2.0, difference.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.subtract(new ComplexCovector(2), null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.subtract(null, new ComplexCovector(2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.subtract(null, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.subtract(new ComplexCovector(2), new ComplexCovector(3));
        });

        // Test instance method.

        difference = covec1.subtract(covec2);
        assertEquals(-2.0, difference.get(0).getReal());
        assertEquals(-2.0, difference.get(0).getImag());
        assertEquals(-2.0, difference.get(1).getReal());
        assertEquals(-2.0, difference.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexCovector(2).subtract(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexCovector(2).subtract(new ComplexCovector(3));
        });

    }

    @Test
    void multiplyScalar()
    {
        // Test class methods for scalar multiplication.

        ComplexCovector covec1 = new ComplexCovector(2);
        ComplexCovector product;

        covec1.set(0, new Complex(1.0, 1.0));
        covec1.set(1, new Complex(2.0, 2.0));

        product = ComplexCovector.multiply(covec1, new Complex(1.0,1.0));
        assertEquals(0.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(0.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = ComplexCovector.multiply(covec1, 2.0);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = ComplexCovector.multiply(covec1, (float)2.0);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = ComplexCovector.multiply(covec1, 2);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.multiply(new ComplexCovector(2), (Complex)null);
        });

        // Test instance methods for scalar multiplication.

        covec1.set(0, new Complex(1.0, 1.0));
        covec1.set(1, new Complex(2.0, 2.0));

        product = covec1.multiply(new Complex(1.0,1.0));
        assertEquals(0.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(0.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = covec1.multiply(2.0);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = covec1.multiply((float)2.0);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = covec1.multiply(2);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexCovector(2).multiply((Complex)null);
        });

    }

    @Test
    void multiplyMatrix()
    {
        ComplexCovector theCovector = new ComplexCovector(3);
        ComplexMatrix theMatrix = new ComplexMatrix(3,3);
        ComplexMatrix theProduct;

        theCovector.set(0, new Complex(1.0,1.0));
        theCovector.set(1, new Complex(1.0));
        theCovector.set(2, new Complex(2.0));

        theMatrix.set(0,0, new Complex(1.0));
        theMatrix.set(0,2, new Complex(1.0));
        theMatrix.set(1,1, new Complex(1.0));
        theMatrix.set(2,0, new Complex(1.0));
        theMatrix.set(2,2, new Complex(1.0));

        theProduct = theCovector.multiply(theMatrix);
        assertEquals(1, theProduct.getNumRows());
        assertEquals(3, theProduct.getNumColumns());
        assertEquals(3, theProduct.get(0,0).getReal());
        assertEquals(1, theProduct.get(0,0).getImag());
        assertEquals(1, theProduct.get(0,1).getReal());
        assertEquals(0, theProduct.get(0,1).getImag());
        assertEquals(3, theProduct.get(0,2).getReal());
        assertEquals(1, theProduct.get(0,2).getImag());


        final ComplexCovector covec1 = theCovector;
        final ComplexMatrix matrix1 = theMatrix;

        assertThrows(IllegalArgumentException.class, () -> {
            matrix1.multiply(covec1);
        });

    }

    @Test
    void negate()
    {
        // Test class method.

        ComplexCovector covec1 = new ComplexCovector(2);
        ComplexCovector result;

        result = ComplexCovector.negate(covec1);
        assertEquals(0.0, result.get(0).getReal());
        assertEquals(0.0, result.get(0).getImag());
        assertEquals(0.0, result.get(1).getReal());
        assertEquals(0.0, result.get(1).getImag());

        covec1.set(0, new Complex(1.0, 1.0));
        covec1.set(1, new Complex(2.0, 2.0));

        result = ComplexCovector.negate(covec1);
        assertEquals(-1.0, result.get(0).getReal());
        assertEquals(-1.0, result.get(0).getImag());
        assertEquals(-2.0, result.get(1).getReal());
        assertEquals(-2.0, result.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexCovector.negate(null);
        });

        // Test instance method.

        covec1 = new ComplexCovector(2);

        result = covec1.negate();
        assertEquals(0.0, result.get(0).getReal());
        assertEquals(0.0, result.get(0).getImag());
        assertEquals(0.0, result.get(1).getReal());
        assertEquals(0.0, result.get(1).getImag());

        covec1.set(0, new Complex(1.0, 1.0));
        covec1.set(1, new Complex(2.0, 2.0));

        result = covec1.negate();
        assertEquals(-1.0, result.get(0).getReal());
        assertEquals(-1.0, result.get(0).getImag());
        assertEquals(-2.0, result.get(1).getReal());
        assertEquals(-2.0, result.get(1).getImag());
    }

    @Test
    void transpose()
    {

    }

    @Test
    void transposeConjugate()
    {

    }

    @Test
    void innerProduct()
    {

    }

    @Test
    void size()
    {

    }
}