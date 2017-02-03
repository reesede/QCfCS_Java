package qcfcs_math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements unit tests for the ComplexMatrix class.
 * Created by reesede on 1/22/2017.
 * @author David E. Reese
 * @version 2.1.1
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
//      20170126    D.E. Reese          Merged get() and set() into setAndGet() and added tests to stub.
//      20170127    D.E. Reese          Added additional tests to setAndGet() and added tests to add() stub.
//      20170128    D.E. Reese          Added code to stubs for negate(), multiply() (scalar only), and cloneTest().
//      20170201    D.E. Reese          Renamed multiply() to multiplyScalar() and added multiplyMatrix() test.
//      20170203    D.E. Reese          Added code to testAndConvert1x1Matrix().
//

class ComplexMatrixTest
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
        ComplexMatrix theMatrix = new ComplexMatrix(2,2);
        Complex theComplex;

        theComplex = ComplexMatrix.get(theMatrix, 0, 0);
        assertEquals(0.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theComplex = ComplexMatrix.get(theMatrix, 0, 1);
        assertEquals(0.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theComplex = ComplexMatrix.get(theMatrix, 1, 0);
        assertEquals(0.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theComplex = ComplexMatrix.get(theMatrix, 1, 1);
        assertEquals(0.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        ComplexMatrix.set(theMatrix,0,0, new Complex(1.0, 1.0));
        theComplex = ComplexMatrix.get(theMatrix,0,0);
        assertEquals(1.0, theComplex.getReal());
        assertEquals(1.0, theComplex.getImag());

        theMatrix.set(0,1, (double)2.0);
        theComplex = ComplexMatrix.get(theMatrix,0,1);
        assertEquals(2.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theMatrix.set(1,0,(float)3.0);
        theComplex = ComplexMatrix.get(theMatrix,1,0);
        assertEquals(3.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theMatrix.set(1,1,4);
        theComplex = ComplexMatrix.get(theMatrix,1,1);
        assertEquals(4.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theMatrix.set(0,0, new PolarCoordinate(4.0, Math.PI/4.0));
        theComplex = ComplexMatrix.get(theMatrix,0,0);
        assertEquals(2.0*Math.sqrt(2.0), theComplex.getReal(), 0.00000001);
        assertEquals(2.0*Math.sqrt(2.0), theComplex.getImag(), 0.00000001);

        theMatrix = new ComplexMatrix(2,2);
        assertEquals(0.0, theMatrix.get(0,0).getReal());
        assertEquals(0.0, theMatrix.get(0,0).getImag());
        assertEquals(0.0, theMatrix.get(0,1).getReal());
        assertEquals(0.0, theMatrix.get(0,1).getImag());
        assertEquals(0.0, theMatrix.get(1,0).getReal());
        assertEquals(0.0, theMatrix.get(1,0).getImag());
        assertEquals(0.0, theMatrix.get(1,1).getReal());
        assertEquals(0.0, theMatrix.get(1,1).getImag());

        theMatrix.set(0,0, new Complex(1.0, 1.0));
        assertEquals(1.0, theMatrix.get(0,0).getReal());
        assertEquals(1.0, theMatrix.get(0,0).getImag());

        theMatrix.set(0,1, (double)2.0);
        assertEquals(2.0, theMatrix.get(0,1).getReal());
        assertEquals(0.0, theMatrix.get(0,1).getImag());

        theMatrix.set(1,0,(float)3.0);
        assertEquals(3.0, theMatrix.get(1,0).getReal());
        assertEquals(0.0, theMatrix.get(1,0).getImag());

        theMatrix.set(1,1,4);
        assertEquals(4.0, theMatrix.get(1,1).getReal());
        assertEquals(0.0, theMatrix.get(1,1).getImag());

        theMatrix.set(0,0, new PolarCoordinate(4.0, Math.PI/4.0));
        assertEquals(2.0*Math.sqrt(2.0), theMatrix.get(0,0).getReal(), 0.00000001);
        assertEquals(2.0*Math.sqrt(2.0), theMatrix.get(0,0).getImag(), 0.00000001);

    }

    @Test
    void add()
    {
        // Test static operations.

        ComplexMatrix matrix1 = new ComplexMatrix(2,2);
        ComplexMatrix matrix2 = new ComplexMatrix(2,2);

        matrix1.set(0,0, new Complex(1.0, 1.0));
        matrix1.set(0,1, new Complex (2.0, 2.0));
        matrix1.set(1,0, new Complex (3.0, -2.0));
        matrix1.set(1,1, 5.0);

        ComplexMatrix sum = ComplexMatrix.add(matrix1, matrix2);
        assertEquals(1.0, sum.get(0,0).getReal());
        assertEquals(1.0, sum.get(0,0).getImag());
        assertEquals(2.0, sum.get(0,1).getReal());
        assertEquals(2.0, sum.get(0,1).getImag());
        assertEquals(3.0, sum.get(1,0).getReal());
        assertEquals(-2.0, sum.get(1,0).getImag());
        assertEquals(5.0, sum.get(1,1).getReal());
        assertEquals(0.0, sum.get(1,1).getImag());

        matrix2.set(0,0, new Complex(-1.0, -1.0));
        matrix2.set(0,1, new Complex (-1.0, -1.0));
        matrix2.set(1,0, new Complex (-1.0, -1.0));
        matrix2.set(1,1, new Complex (-1.0, -1.0));

        sum = ComplexMatrix.add(matrix1, matrix2);
        assertEquals(0.0, sum.get(0,0).getReal());
        assertEquals(0.0, sum.get(0,0).getImag());
        assertEquals(1.0, sum.get(0,1).getReal());
        assertEquals(1.0, sum.get(0,1).getImag());
        assertEquals(2.0, sum.get(1,0).getReal());
        assertEquals(-3.0, sum.get(1,0).getImag());
        assertEquals(4.0, sum.get(1,1).getReal());
        assertEquals(-1.0, sum.get(1,1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.add(new ComplexMatrix(2,2), new ComplexMatrix(2,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.add(new ComplexMatrix(2,2), new ComplexMatrix(1,2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.add(new ComplexMatrix(3,2), new ComplexMatrix(2,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.add(new ComplexMatrix(2,2), null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.add(null, new ComplexMatrix(2,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.add(null, null);
        });

        // Test instance operation.

        matrix1 = new ComplexMatrix(2,2);
        matrix2 = new ComplexMatrix(2,2);

        matrix1.set(0,0, new Complex(1.0, 1.0));
        matrix1.set(0,1, new Complex (2.0, 2.0));
        matrix1.set(1,0, new Complex (3.0, -2.0));
        matrix1.set(1,1, 5.0);

        sum = matrix1.add(matrix2);
        assertEquals(1.0, sum.get(0,0).getReal());
        assertEquals(1.0, sum.get(0,0).getImag());
        assertEquals(2.0, sum.get(0,1).getReal());
        assertEquals(2.0, sum.get(0,1).getImag());
        assertEquals(3.0, sum.get(1,0).getReal());
        assertEquals(-2.0, sum.get(1,0).getImag());
        assertEquals(5.0, sum.get(1,1).getReal());
        assertEquals(0.0, sum.get(1,1).getImag());

        matrix2.set(0,0, new Complex(-1.0, -1.0));
        matrix2.set(0,1, new Complex (-1.0, -1.0));
        matrix2.set(1,0, new Complex (-1.0, -1.0));
        matrix2.set(1,1, new Complex (-1.0, -1.0));

        sum = matrix1.add(matrix2);
        assertEquals(0.0, sum.get(0,0).getReal());
        assertEquals(0.0, sum.get(0,0).getImag());
        assertEquals(1.0, sum.get(0,1).getReal());
        assertEquals(1.0, sum.get(0,1).getImag());
        assertEquals(2.0, sum.get(1,0).getReal());
        assertEquals(-3.0, sum.get(1,0).getImag());
        assertEquals(4.0, sum.get(1,1).getReal());
        assertEquals(-1.0, sum.get(1,1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexMatrix(2,2).add(new ComplexMatrix(2,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexMatrix(2,2).add(new ComplexMatrix(1,2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexMatrix(2,2).add(new ComplexMatrix(3,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexMatrix(2,2).add(null);
        });

    }

    @Test
    void subtract()
    {
        // Test static operations.

        ComplexMatrix matrix1 = new ComplexMatrix(2,2);
        ComplexMatrix matrix2 = new ComplexMatrix(2,2);

        matrix1.set(0,0, new Complex(1.0, 1.0));
        matrix1.set(0,1, new Complex (2.0, 2.0));
        matrix1.set(1,0, new Complex (3.0, -2.0));
        matrix1.set(1,1, 5.0);

        ComplexMatrix difference = ComplexMatrix.subtract(matrix1, matrix2);
        assertEquals(1.0, difference.get(0,0).getReal());
        assertEquals(1.0, difference.get(0,0).getImag());
        assertEquals(2.0, difference.get(0,1).getReal());
        assertEquals(2.0, difference.get(0,1).getImag());
        assertEquals(3.0, difference.get(1,0).getReal());
        assertEquals(-2.0, difference.get(1,0).getImag());
        assertEquals(5.0, difference.get(1,1).getReal());
        assertEquals(0.0, difference.get(1,1).getImag());

        matrix2.set(0,0, new Complex(-1.0, -1.0));
        matrix2.set(0,1, new Complex (-1.0, -1.0));
        matrix2.set(1,0, new Complex (-1.0, -1.0));
        matrix2.set(1,1, new Complex (-1.0, -1.0));

        difference = ComplexMatrix.subtract(matrix1, matrix2);
        assertEquals(2.0, difference.get(0,0).getReal());
        assertEquals(2.0, difference.get(0,0).getImag());
        assertEquals(3.0, difference.get(0,1).getReal());
        assertEquals(3.0, difference.get(0,1).getImag());
        assertEquals(4.0, difference.get(1,0).getReal());
        assertEquals(-1.0, difference.get(1,0).getImag());
        assertEquals(6.0, difference.get(1,1).getReal());
        assertEquals(1.0, difference.get(1,1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.subtract(new ComplexMatrix(2,2), new ComplexMatrix(2,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.subtract(new ComplexMatrix(2,2), new ComplexMatrix(1,2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.subtract(new ComplexMatrix(3,2), new ComplexMatrix(2,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.subtract(new ComplexMatrix(2,2), null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.subtract(null, new ComplexMatrix(2,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.subtract(null, null);
        });

        // Test instance operation.

        matrix1 = new ComplexMatrix(2,2);
        matrix2 = new ComplexMatrix(2,2);

        matrix1.set(0,0, new Complex(1.0, 1.0));
        matrix1.set(0,1, new Complex (2.0, 2.0));
        matrix1.set(1,0, new Complex (3.0, -2.0));
        matrix1.set(1,1, 5.0);

        difference = matrix1.subtract(matrix2);
        assertEquals(1.0, difference.get(0,0).getReal());
        assertEquals(1.0, difference.get(0,0).getImag());
        assertEquals(2.0, difference.get(0,1).getReal());
        assertEquals(2.0, difference.get(0,1).getImag());
        assertEquals(3.0, difference.get(1,0).getReal());
        assertEquals(-2.0, difference.get(1,0).getImag());
        assertEquals(5.0, difference.get(1,1).getReal());
        assertEquals(0.0, difference.get(1,1).getImag());

        matrix2.set(0,0, new Complex(-1.0, -1.0));
        matrix2.set(0,1, new Complex (-1.0, -1.0));
        matrix2.set(1,0, new Complex (-1.0, -1.0));
        matrix2.set(1,1, new Complex (-1.0, -1.0));

        difference = matrix1.subtract(matrix2);
        assertEquals(2.0, difference.get(0,0).getReal());
        assertEquals(2.0, difference.get(0,0).getImag());
        assertEquals(3.0, difference.get(0,1).getReal());
        assertEquals(3.0, difference.get(0,1).getImag());
        assertEquals(4.0, difference.get(1,0).getReal());
        assertEquals(-1.0, difference.get(1,0).getImag());
        assertEquals(6.0, difference.get(1,1).getReal());
        assertEquals(1.0, difference.get(1,1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexMatrix(2,2).subtract(new ComplexMatrix(2,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexMatrix(2, 2).subtract(new ComplexMatrix(1,2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexMatrix(3, 2).subtract(new ComplexMatrix(2,1));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexMatrix(2, 2).subtract(null);
        });

    }

    @Test
    void multiplyScalar()
    {
        ComplexMatrix theMatrix;
        ComplexMatrix product;

        // Verify scalar multiplication.

        theMatrix = new ComplexMatrix(2,2);
        product = ComplexMatrix.multiply(theMatrix, (double)2.0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = ComplexMatrix.multiply(theMatrix, (float)2.0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = ComplexMatrix.multiply(theMatrix, 2);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        theMatrix.set(0,0, new Complex(1.0, 1.0));
        theMatrix.set(0, 1, new Complex(2.0,0.0));
        theMatrix.set(1,0, new Complex(0.0, 3.0));
        theMatrix.set(1, 1, new Complex());
        product = ComplexMatrix.multiply(theMatrix, 0.0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = ComplexMatrix.multiply(theMatrix, (float)0.0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = ComplexMatrix.multiply(theMatrix, 0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = ComplexMatrix.multiply(theMatrix, (double)(-5.0));
        assertEquals(-5.0, product.get(0,0).getReal());
        assertEquals(-5.0, product.get(0,0).getImag());
        assertEquals(-10.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(-15.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = ComplexMatrix.multiply(theMatrix, (float)(-5.0));
        assertEquals(-5.0, product.get(0,0).getReal());
        assertEquals(-5.0, product.get(0,0).getImag());
        assertEquals(-10.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(-15.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = ComplexMatrix.multiply(theMatrix, -5);
        assertEquals(-5.0, product.get(0,0).getReal());
        assertEquals(-5.0, product.get(0,0).getImag());
        assertEquals(-10.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(-15.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.multiply(null, 5.0);
        });

        // Check scalar multiplication with instance methods.

        theMatrix = new ComplexMatrix(2,2);
        product = theMatrix.multiply((double)2.0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = theMatrix.multiply((float)2.0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = theMatrix.multiply(2);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        theMatrix.set(0,0, new Complex(1.0, 1.0));
        theMatrix.set(0, 1, new Complex(2.0,0.0));
        theMatrix.set(1,0, new Complex(0.0, 3.0));
        theMatrix.set(1, 1, new Complex());
        product = theMatrix.multiply(0.0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = theMatrix.multiply((float)0.0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = theMatrix.multiply(0);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = theMatrix.multiply((double)(-5.0));
        assertEquals(-5.0, product.get(0,0).getReal());
        assertEquals(-5.0, product.get(0,0).getImag());
        assertEquals(-10.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(-15.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = theMatrix.multiply((float)(-5.0));
        assertEquals(-5.0, product.get(0,0).getReal());
        assertEquals(-5.0, product.get(0,0).getImag());
        assertEquals(-10.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(-15.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());

        product = theMatrix.multiply(-5);
        assertEquals(-5.0, product.get(0,0).getReal());
        assertEquals(-5.0, product.get(0,0).getImag());
        assertEquals(-10.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(-15.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
    }

    @Test
    void multiplyMatrix()
    {
        ComplexMatrix matrix1 = new ComplexMatrix(3,3);
        ComplexMatrix matrix2 = new ComplexMatrix(3,3);
        ComplexMatrix product;

        // Test class method.

        product = ComplexMatrix.multiply(matrix1, matrix2);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());
        assertEquals(0.0, product.get(2,0).getReal());
        assertEquals(0.0, product.get(2,0).getImag());
        assertEquals(0.0, product.get(2,1).getReal());
        assertEquals(0.0, product.get(2,1).getImag());
        assertEquals(0.0, product.get(2,2).getReal());
        assertEquals(0.0, product.get(2,2).getImag());

        matrix1.set(0,0, new Complex(1.0));
        matrix1.set(0,2, new Complex(1.0));
        matrix1.set(1,1, new Complex(1.0));
        matrix1.set(2,0, new Complex(1.0));
        matrix1.set(2,2, new Complex(1.0));
        product = ComplexMatrix.multiply(matrix1, matrix2);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());
        assertEquals(0.0, product.get(2,0).getReal());
        assertEquals(0.0, product.get(2,0).getImag());
        assertEquals(0.0, product.get(2,1).getReal());
        assertEquals(0.0, product.get(2,1).getImag());
        assertEquals(0.0, product.get(2,2).getReal());
        assertEquals(0.0, product.get(2,2).getImag());

        matrix2.set(0,0, new Complex(1.0));
        matrix2.set(1,1, new Complex(1.0));
        matrix2.set(2,2, new Complex(1.0));
        product = ComplexMatrix.multiply(matrix1, matrix2);
        assertEquals(1.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(1.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(1.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());
        assertEquals(1.0, product.get(2,0).getReal());
        assertEquals(0.0, product.get(2,0).getImag());
        assertEquals(0.0, product.get(2,1).getReal());
        assertEquals(0.0, product.get(2,1).getImag());
        assertEquals(1.0, product.get(2,2).getReal());
        assertEquals(0.0, product.get(2,2).getImag());

        matrix2.set(0,2, new Complex(2.0, 1.0));
        product = ComplexMatrix.multiply(matrix1, matrix2);
        assertEquals(1.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(3.0, product.get(0,2).getReal());
        assertEquals(1.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(1.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());
        assertEquals(1.0, product.get(2,0).getReal());
        assertEquals(0.0, product.get(2,0).getImag());
        assertEquals(0.0, product.get(2,1).getReal());
        assertEquals(0.0, product.get(2,1).getImag());
        assertEquals(3.0, product.get(2,2).getReal());
        assertEquals(1.0, product.get(2,2).getImag());

        matrix1 = new ComplexMatrix(2,2);
        matrix2 = new ComplexMatrix(2, 3);
        product = ComplexMatrix.multiply(matrix1, matrix2);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());

        matrix1 = new ComplexMatrix(2,2);
        matrix2 = new ComplexMatrix(2, 3);
        matrix1.set(0,0, new Complex(1.0));
        matrix1.set(0,1, new Complex(1.0));
        matrix1.set(1,0, new Complex(1.0));
        matrix1.set(1,1, new Complex(1.0));
        matrix2.set(0,0, new Complex(1.0));
        matrix2.set(0,1, new Complex(1.0));
        matrix2.set(0,2, new Complex(1.0));
        matrix2.set(1,0, new Complex(1.0));
        matrix2.set(1,1, new Complex(1.0));
        matrix2.set(1,2, new Complex(1.0));
        product = ComplexMatrix.multiply(matrix1, matrix2);
        assertEquals(2.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(2.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(2.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(2.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(2.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(2.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.multiply((ComplexMatrix)null, new ComplexMatrix(2,2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.multiply(new ComplexMatrix(2,2), (ComplexMatrix)null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.multiply((ComplexMatrix)null, (ComplexMatrix)null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.multiply(new ComplexMatrix(2,3), new ComplexMatrix(2,3));
        });

        // Test instance method.

        matrix1 = new ComplexMatrix(3,3);
        matrix2 = new ComplexMatrix(3,3);

        // Test multiplying two zero matrices of the same dimension.

        product = matrix1.multiply(matrix2);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());
        assertEquals(0.0, product.get(2,0).getReal());
        assertEquals(0.0, product.get(2,0).getImag());
        assertEquals(0.0, product.get(2,1).getReal());
        assertEquals(0.0, product.get(2,1).getImag());
        assertEquals(0.0, product.get(2,2).getReal());
        assertEquals(0.0, product.get(2,2).getImag());

        matrix1.set(0,0, new Complex(1.0));
        matrix1.set(0,2, new Complex(1.0));
        matrix1.set(1,1, new Complex(1.0));
        matrix1.set(2,0, new Complex(1.0));
        matrix1.set(2,2, new Complex(1.0));
        product = matrix1.multiply(matrix2);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());
        assertEquals(0.0, product.get(2,0).getReal());
        assertEquals(0.0, product.get(2,0).getImag());
        assertEquals(0.0, product.get(2,1).getReal());
        assertEquals(0.0, product.get(2,1).getImag());
        assertEquals(0.0, product.get(2,2).getReal());
        assertEquals(0.0, product.get(2,2).getImag());

        matrix2.set(0,0, new Complex(1.0));
        matrix2.set(1,1, new Complex(1.0));
        matrix2.set(2,2, new Complex(1.0));
        product = matrix1.multiply(matrix2);
        assertEquals(1.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(1.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(1.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());
        assertEquals(1.0, product.get(2,0).getReal());
        assertEquals(0.0, product.get(2,0).getImag());
        assertEquals(0.0, product.get(2,1).getReal());
        assertEquals(0.0, product.get(2,1).getImag());
        assertEquals(1.0, product.get(2,2).getReal());
        assertEquals(0.0, product.get(2,2).getImag());

        matrix2.set(0,2, new Complex(2.0, 1.0));
        product = matrix1.multiply(matrix2);
        assertEquals(1.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(3.0, product.get(0,2).getReal());
        assertEquals(1.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(1.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());
        assertEquals(1.0, product.get(2,0).getReal());
        assertEquals(0.0, product.get(2,0).getImag());
        assertEquals(0.0, product.get(2,1).getReal());
        assertEquals(0.0, product.get(2,1).getImag());
        assertEquals(3.0, product.get(2,2).getReal());
        assertEquals(1.0, product.get(2,2).getImag());

        matrix1 = new ComplexMatrix(2,2);
        matrix2 = new ComplexMatrix(2, 3);
        product = matrix1.multiply(matrix2);
        assertEquals(0.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(0.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(0.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(0.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(0.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(0.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());

        matrix1 = new ComplexMatrix(2,2);
        matrix2 = new ComplexMatrix(2, 3);
        matrix1.set(0,0, new Complex(1.0));
        matrix1.set(0,1, new Complex(1.0));
        matrix1.set(1,0, new Complex(1.0));
        matrix1.set(1,1, new Complex(1.0));
        matrix2.set(0,0, new Complex(1.0));
        matrix2.set(0,1, new Complex(1.0));
        matrix2.set(0,2, new Complex(1.0));
        matrix2.set(1,0, new Complex(1.0));
        matrix2.set(1,1, new Complex(1.0));
        matrix2.set(1,2, new Complex(1.0));
        product = matrix1.multiply(matrix2);
        assertEquals(2.0, product.get(0,0).getReal());
        assertEquals(0.0, product.get(0,0).getImag());
        assertEquals(2.0, product.get(0,1).getReal());
        assertEquals(0.0, product.get(0,1).getImag());
        assertEquals(2.0, product.get(0,2).getReal());
        assertEquals(0.0, product.get(0,2).getImag());
        assertEquals(2.0, product.get(1,0).getReal());
        assertEquals(0.0, product.get(1,0).getImag());
        assertEquals(2.0, product.get(1,1).getReal());
        assertEquals(0.0, product.get(1,1).getImag());
        assertEquals(2.0, product.get(1,2).getReal());
        assertEquals(0.0, product.get(1,2).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.multiply((ComplexMatrix)null, new ComplexMatrix(2,2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.multiply(new ComplexMatrix(2,2), (ComplexMatrix)null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.multiply((ComplexMatrix)null, (ComplexMatrix)null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.multiply(new ComplexMatrix(2,3), new ComplexMatrix(2,3));
        });

    }

    @Test
    void negate()
    {
        // Test class method.

        ComplexMatrix theMatrix = new ComplexMatrix(2,2);
        ComplexMatrix theResult = ComplexMatrix.negate(theMatrix);
        assertEquals(0.0, theResult.get(0,0).getReal());
        assertEquals(0.0, theResult.get(0,0).getImag());
        assertEquals(0.0, theResult.get(0,1).getReal());
        assertEquals(0.0, theResult.get(0,1).getImag());
        assertEquals(0.0, theResult.get(1,0).getReal());
        assertEquals(0.0, theResult.get(1,0).getImag());
        assertEquals(0.0, theResult.get(1,1).getReal());
        assertEquals(0.0, theResult.get(1,1).getImag());

        theMatrix.set(0,0, new Complex());
        theMatrix.set(0,1, new Complex(1.0));
        theMatrix.set(1,0, new Complex(0.0, 2.0));
        theMatrix.set(1,1, new Complex(3.0, 3.0));
        theResult = ComplexMatrix.negate(theMatrix);
        assertEquals(0.0, theResult.get(0,0).getReal());
        assertEquals(0.0, theResult.get(0,0).getImag());
        assertEquals(-1.0, theResult.get(0,1).getReal());
        assertEquals(0.0, theResult.get(0,1).getImag());
        assertEquals(0.0, theResult.get(1,0).getReal());
        assertEquals(-2.0, theResult.get(1,0).getImag());
        assertEquals(-3.0, theResult.get(1,1).getReal());
        assertEquals(-3.0, theResult.get(1,1).getImag());

        theResult = ComplexMatrix.negate(theResult);
        assertEquals(0.0, theResult.get(0,0).getReal());
        assertEquals(0.0, theResult.get(0,0).getImag());
        assertEquals(1.0, theResult.get(0,1).getReal());
        assertEquals(0.0, theResult.get(0,1).getImag());
        assertEquals(0.0, theResult.get(1,0).getReal());
        assertEquals(2.0, theResult.get(1,0).getImag());
        assertEquals(3.0, theResult.get(1,1).getReal());
        assertEquals(3.0, theResult.get(1,1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.negate(null);
        });


        // Test instance method.

        theMatrix = new ComplexMatrix(2,2);
        theResult = theMatrix.negate();
        assertEquals(0.0, theResult.get(0,0).getReal());
        assertEquals(0.0, theResult.get(0,0).getImag());
        assertEquals(0.0, theResult.get(0,1).getReal());
        assertEquals(0.0, theResult.get(0,1).getImag());
        assertEquals(0.0, theResult.get(1,0).getReal());
        assertEquals(0.0, theResult.get(1,0).getImag());
        assertEquals(0.0, theResult.get(1,1).getReal());
        assertEquals(0.0, theResult.get(1,1).getImag());

        theMatrix.set(0,0, new Complex());
        theMatrix.set(0,1, new Complex(1.0));
        theMatrix.set(1,0, new Complex(0.0, 2.0));
        theMatrix.set(1,1, new Complex(3.0, 3.0));
        theResult = theMatrix.negate();
        assertEquals(0.0, theResult.get(0,0).getReal());
        assertEquals(0.0, theResult.get(0,0).getImag());
        assertEquals(-1.0, theResult.get(0,1).getReal());
        assertEquals(0.0, theResult.get(0,1).getImag());
        assertEquals(0.0, theResult.get(1,0).getReal());
        assertEquals(-2.0, theResult.get(1,0).getImag());
        assertEquals(-3.0, theResult.get(1,1).getReal());
        assertEquals(-3.0, theResult.get(1,1).getImag());

        theResult = theResult.negate();
        assertEquals(0.0, theResult.get(0,0).getReal());
        assertEquals(0.0, theResult.get(0,0).getImag());
        assertEquals(1.0, theResult.get(0,1).getReal());
        assertEquals(0.0, theResult.get(0,1).getImag());
        assertEquals(0.0, theResult.get(1,0).getReal());
        assertEquals(2.0, theResult.get(1,0).getImag());
        assertEquals(3.0, theResult.get(1,1).getReal());
        assertEquals(3.0, theResult.get(1,1).getImag());
    }

    @Test
    void cloneTest()
    {
        // Test class method.

        ComplexMatrix theMatrix = new ComplexMatrix(2,2);
        ComplexMatrix theResult = theMatrix.clone();
        assertEquals(0.0, theMatrix.get(0,0).getReal());
        assertEquals(0.0, theMatrix.get(0,0).getImag());
        assertEquals(0.0, theMatrix.get(0,1).getReal());
        assertEquals(0.0, theMatrix.get(0,1).getImag());
        assertEquals(0.0, theMatrix.get(1,0).getReal());
        assertEquals(0.0, theMatrix.get(1,0).getImag());
        assertEquals(0.0, theMatrix.get(1,1).getReal());
        assertEquals(0.0, theMatrix.get(1,1).getImag());
        assertEquals(0.0, theResult.get(0,0).getReal());
        assertEquals(0.0, theResult.get(0,0).getImag());
        assertEquals(0.0, theResult.get(0,1).getReal());
        assertEquals(0.0, theResult.get(0,1).getImag());
        assertEquals(0.0, theResult.get(1,0).getReal());
        assertEquals(0.0, theResult.get(1,0).getImag());
        assertEquals(0.0, theResult.get(1,1).getReal());
        assertEquals(0.0, theResult.get(1,1).getImag());

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                theMatrix.set(i, j, new Complex(1.0, 1.0));
        assertEquals(1.0, theMatrix.get(0,0).getReal());
        assertEquals(1.0, theMatrix.get(0,0).getImag());
        assertEquals(1.0, theMatrix.get(0,1).getReal());
        assertEquals(1.0, theMatrix.get(0,1).getImag());
        assertEquals(1.0, theMatrix.get(1,0).getReal());
        assertEquals(1.0, theMatrix.get(1,0).getImag());
        assertEquals(1.0, theMatrix.get(1,1).getReal());
        assertEquals(1.0, theMatrix.get(1,1).getImag());
        assertEquals(0.0, theResult.get(0,0).getReal());
        assertEquals(0.0, theResult.get(0,0).getImag());
        assertEquals(0.0, theResult.get(0,1).getReal());
        assertEquals(0.0, theResult.get(0,1).getImag());
        assertEquals(0.0, theResult.get(1,0).getReal());
        assertEquals(0.0, theResult.get(1,0).getImag());
        assertEquals(0.0, theResult.get(1,1).getReal());
        assertEquals(0.0, theResult.get(1,1).getImag());

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                theResult.set(i, j, new Complex(2.0, 2.0));
        assertEquals(1.0, theMatrix.get(0,0).getReal());
        assertEquals(1.0, theMatrix.get(0,0).getImag());
        assertEquals(1.0, theMatrix.get(0,1).getReal());
        assertEquals(1.0, theMatrix.get(0,1).getImag());
        assertEquals(1.0, theMatrix.get(1,0).getReal());
        assertEquals(1.0, theMatrix.get(1,0).getImag());
        assertEquals(1.0, theMatrix.get(1,1).getReal());
        assertEquals(1.0, theMatrix.get(1,1).getImag());
        assertEquals(2.0, theResult.get(0,0).getReal());
        assertEquals(2.0, theResult.get(0,0).getImag());
        assertEquals(2.0, theResult.get(0,1).getReal());
        assertEquals(2.0, theResult.get(0,1).getImag());
        assertEquals(2.0, theResult.get(1,0).getReal());
        assertEquals(2.0, theResult.get(1,0).getImag());
        assertEquals(2.0, theResult.get(1,1).getReal());
        assertEquals(2.0, theResult.get(1,1).getImag());

        theMatrix.set(0,0, new Complex());
        theMatrix.set(0,1, new Complex(1.0));
        theMatrix.set(1,0, new Complex(0.0, 2.0));
        theMatrix.set(1,1, new Complex(3.0, 3.0));
        theResult = theMatrix.clone();
        assertEquals(0.0, theMatrix.get(0,0).getReal());
        assertEquals(0.0, theMatrix.get(0,0).getImag());
        assertEquals(1.0, theMatrix.get(0,1).getReal());
        assertEquals(0.0, theMatrix.get(0,1).getImag());
        assertEquals(0.0, theMatrix.get(1,0).getReal());
        assertEquals(2.0, theMatrix.get(1,0).getImag());
        assertEquals(3.0, theMatrix.get(1,1).getReal());
        assertEquals(3.0, theMatrix.get(1,1).getImag());
        assertEquals(0.0, theResult.get(0,0).getReal());
        assertEquals(0.0, theResult.get(0,0).getImag());
        assertEquals(1.0, theResult.get(0,1).getReal());
        assertEquals(0.0, theResult.get(0,1).getImag());
        assertEquals(0.0, theResult.get(1,0).getReal());
        assertEquals(2.0, theResult.get(1,0).getImag());
        assertEquals(3.0, theResult.get(1,1).getReal());
        assertEquals(3.0, theResult.get(1,1).getImag());

    }

    @Test
    void testAndConvert1x1Matrix()
    {
        ComplexMatrix theMatrix = new ComplexMatrix(1,1);
        Complex theScalar;

        theMatrix.set(0,0, new Complex(1.0, 1.0));

        assertTrue(ComplexMatrix.is1By1(theMatrix));

        theScalar = ComplexMatrix.convert1By1ToScalar(theMatrix);
        assertEquals(1.0, theScalar.getReal());
        assertEquals(1.0, theScalar.getImag());

        theMatrix = new ComplexMatrix(2,1);
        theMatrix.set(0,0, new Complex(1.0, 1.0));
        theMatrix.set(1,0, new Complex(1.0, 1.0));

        assertFalse(ComplexMatrix.is1By1(theMatrix));

        final ComplexMatrix testMatrix = theMatrix;

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.convert1By1ToScalar(testMatrix);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.is1By1(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexMatrix.convert1By1ToScalar(null);
        });

        // Test instance method.

        theMatrix = new ComplexMatrix(1,1);
        theMatrix.set(0,0, new Complex(1.0, 1.0));

        assertTrue(theMatrix.is1By1());

        theScalar = theMatrix.convert1By1ToScalar();
        assertEquals(1.0, theScalar.getReal());
        assertEquals(1.0, theScalar.getImag());

        theMatrix = new ComplexMatrix(2,1);
        theMatrix.set(0,0, new Complex(1.0, 1.0));
        theMatrix.set(1,0, new Complex(1.0, 1.0));

        assertFalse(theMatrix.is1By1());

        assertThrows(IllegalArgumentException.class, () -> {
            testMatrix.convert1By1ToScalar();
        });
    }
}