package qcfcs_math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements unit tests for the ComplexVector class.
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
//      20170128    D.E. Reese          Added code for setAndGet().
//      20170201    D.E. Reese          Fixed ambiguous null pointers in multiply().
//      20170204    D.E. Reese          Added multiplyMatrix().
//      20170205    D.E. Reese          Added transpose(), transposeConjugate(), innerProduct(), size().
//      20170206    D.E. Reese          Added code to transpose(), transposeConjugate(), and innerProduct() stubs.
//

class ComplexVectorTest
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

        ComplexVector theVec = new ComplexVector(2);
        assertEquals(0.0, ComplexVector.get(theVec, 0).getReal());
        assertEquals(0.0, ComplexVector.get(theVec, 0).getImag());
        assertEquals(0.0, ComplexVector.get(theVec, 1).getReal());
        assertEquals(0.0, ComplexVector.get(theVec, 1).getImag());

        ComplexVector.set(theVec, 0, new Complex(1.0, 1.0));
        ComplexVector.set(theVec, 1, new Complex(2.0, 2.0));
        assertEquals(1.0, ComplexVector.get(theVec, 0).getReal());
        assertEquals(1.0, ComplexVector.get(theVec, 0).getImag());
        assertEquals(2.0, ComplexVector.get(theVec, 1).getReal());
        assertEquals(2.0, ComplexVector.get(theVec, 1).getImag());

        ComplexVector.set(theVec, 0, 3.0);
        ComplexVector.set(theVec,1, (float)4.0);
        assertEquals(3.0, ComplexVector.get(theVec, 0).getReal());
        assertEquals(0.0, ComplexVector.get(theVec, 0).getImag());
        assertEquals(4.0, ComplexVector.get(theVec, 1).getReal());
        assertEquals(0.0, ComplexVector.get(theVec, 1).getImag());

        ComplexVector.set(theVec, 0, 5);
        ComplexVector.set(theVec, 1, new PolarCoordinate(4.0, Math.PI/4.0));
        assertEquals(5.0, ComplexVector.get(theVec, 0).getReal());
        assertEquals(0.0, ComplexVector.get(theVec, 0).getImag());
        assertEquals(2.0*Math.sqrt(2.0), ComplexVector.get(theVec, 1).getReal(), 0.00000001);
        assertEquals(2.0*Math.sqrt(2.0), ComplexVector.get(theVec, 1).getImag(), 0.00000001);

        // Test instance methods.

        theVec = new ComplexVector(2);
        assertEquals(0.0, theVec.get(0).getReal());
        assertEquals(0.0, theVec.get(0).getImag());
        assertEquals(0.0, theVec.get(1).getReal());
        assertEquals(0.0, theVec.get(1).getImag());

        theVec.set(0, new Complex(1.0, 1.0));
        theVec.set(1, new Complex(2.0, 2.0));
        assertEquals(1.0, theVec.get(0).getReal());
        assertEquals(1.0, theVec.get(0).getImag());
        assertEquals(2.0, theVec.get(1).getReal());
        assertEquals(2.0, theVec.get(1).getImag());

        ComplexVector.set(theVec, 0, 3.0);
        ComplexVector.set(theVec,1, (float)4.0);
        assertEquals(3.0, theVec.get(0).getReal());
        assertEquals(0.0, theVec.get(0).getImag());
        assertEquals(4.0, theVec.get(1).getReal());
        assertEquals(0.0, theVec.get(1).getImag());

        theVec.set(0, 5);
        theVec.set(1, new PolarCoordinate(4.0, Math.PI/4.0));
        assertEquals(5.0, theVec.get(0).getReal());
        assertEquals(0.0, theVec.get(0).getImag());
        assertEquals(2.0*Math.sqrt(2.0), theVec.get(1).getReal(), 0.00000001);
        assertEquals(2.0*Math.sqrt(2.0), theVec.get(1).getImag(), 0.00000001);
    }

    @Test
    void add()
    {
        // Test class method.

        ComplexVector vec1 = new ComplexVector(2);
        ComplexVector vec2 = new ComplexVector(2);
        ComplexVector sum;

        vec1.set(0, new Complex(1.0, 1.0));
        vec1.set(1, new Complex(2.0, 2.0));
        vec2.set(0, new Complex(3.0, 3.0));
        vec2.set(1, new Complex(4.0, 4.0));

        sum = ComplexVector.add(vec1, vec2);
        assertEquals(4.0, sum.get(0).getReal());
        assertEquals(4.0, sum.get(0).getImag());
        assertEquals(6.0, sum.get(1).getReal());
        assertEquals(6.0, sum.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.add(new ComplexVector(2), null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.add(null, new ComplexVector(2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.add(null, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.add(new ComplexVector(2), new ComplexVector(3));
        });

        // Test instance method.

        sum = vec1.add(vec2);
        assertEquals(4.0, sum.get(0).getReal());
        assertEquals(4.0, sum.get(0).getImag());
        assertEquals(6.0, sum.get(1).getReal());
        assertEquals(6.0, sum.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexVector(2).add(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexVector(2).add(new ComplexVector(3));
        });
    }

    @Test
    void subtract()
    {
        // Test class method.

        ComplexVector vec1 = new ComplexVector(2);
        ComplexVector vec2 = new ComplexVector(2);
        ComplexVector difference;

        vec1.set(0, new Complex(1.0, 1.0));
        vec1.set(1, new Complex(2.0, 2.0));
        vec2.set(0, new Complex(3.0, 3.0));
        vec2.set(1, new Complex(4.0, 4.0));

        difference = ComplexVector.subtract(vec1, vec2);
        assertEquals(-2.0, difference.get(0).getReal());
        assertEquals(-2.0, difference.get(0).getImag());
        assertEquals(-2.0, difference.get(1).getReal());
        assertEquals(-2.0, difference.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.subtract(new ComplexVector(2), null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.subtract(null, new ComplexVector(2));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.subtract(null, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.subtract(new ComplexVector(2), new ComplexVector(3));
        });

        // Test instance method.

        difference = vec1.subtract(vec2);
        assertEquals(-2.0, difference.get(0).getReal());
        assertEquals(-2.0, difference.get(0).getImag());
        assertEquals(-2.0, difference.get(1).getReal());
        assertEquals(-2.0, difference.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexVector(2).subtract(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexVector(2).subtract(new ComplexVector(3));
        });

    }

    @Test
    void multiply()
    {
        // Test class methods for scalar multiplication.

        ComplexVector vec1 = new ComplexVector(2);
        ComplexVector product;

        vec1.set(0, new Complex(1.0, 1.0));
        vec1.set(1, new Complex(2.0, 2.0));

        product = ComplexVector.multiply(vec1, new Complex(1.0,1.0));
        assertEquals(0.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(0.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = ComplexVector.multiply(vec1, 2.0);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = ComplexVector.multiply(vec1, (float)2.0);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = ComplexVector.multiply(vec1, 2);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.multiply(new ComplexVector(2), (Complex)null);
        });

        // Test instance methods for scalar multiplication.

        vec1.set(0, new Complex(1.0, 1.0));
        vec1.set(1, new Complex(2.0, 2.0));

        product = vec1.multiply(new Complex(1.0,1.0));
        assertEquals(0.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(0.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = vec1.multiply(2.0);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = vec1.multiply((float)2.0);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        product = vec1.multiply(2);
        assertEquals(2.0, product.get(0).getReal());
        assertEquals(2.0, product.get(0).getImag());
        assertEquals(4.0, product.get(1).getReal());
        assertEquals(4.0, product.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            new ComplexVector(2).multiply((Complex)null);
        });
    }

    @Test
    void multiplyMatrix()
    {
        ComplexVector theVector = new ComplexVector(3);
        ComplexMatrix theMatrix = new ComplexMatrix(3,3);
        ComplexMatrix theProduct;

        theVector.set(0, new Complex(1.0,1.0));
        theVector.set(1, new Complex(1.0));
        theVector.set(2, new Complex(2.0));

        theMatrix.set(0,0, new Complex(1.0));
        theMatrix.set(0,2, new Complex(1.0));
        theMatrix.set(1,1, new Complex(1.0));
        theMatrix.set(2,0, new Complex(1.0));
        theMatrix.set(2,2, new Complex(1.0));

        theProduct = theMatrix.multiply(theVector);
        assertEquals(3, theProduct.getNumRows());
        assertEquals(1, theProduct.getNumColumns());
        assertEquals(3, theProduct.get(0,0).getReal());
        assertEquals(1, theProduct.get(0,0).getImag());
        assertEquals(1, theProduct.get(1,0).getReal());
        assertEquals(0, theProduct.get(1,0).getImag());
        assertEquals(3, theProduct.get(2,0).getReal());
        assertEquals(1, theProduct.get(2,0).getImag());


        final ComplexVector vector1 = theVector;
        final ComplexMatrix matrix1 = theMatrix;

        assertThrows(IllegalArgumentException.class, () -> {
            vector1.multiply(matrix1);
        });

    }

    @Test
    void negate()
    {
        // Test class method.

        ComplexVector vec1 = new ComplexVector(2);
        ComplexVector result;

        result = ComplexVector.negate(vec1);
        assertEquals(0.0, result.get(0).getReal());
        assertEquals(0.0, result.get(0).getImag());
        assertEquals(0.0, result.get(1).getReal());
        assertEquals(0.0, result.get(1).getImag());

        vec1.set(0, new Complex(1.0, 1.0));
        vec1.set(1, new Complex(2.0, 2.0));

        result = ComplexVector.negate(vec1);
        assertEquals(-1.0, result.get(0).getReal());
        assertEquals(-1.0, result.get(0).getImag());
        assertEquals(-2.0, result.get(1).getReal());
        assertEquals(-2.0, result.get(1).getImag());

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.negate(null);
        });

        // Test instance method.

        vec1 = new ComplexVector(2);

        result = vec1.negate();
        assertEquals(0.0, result.get(0).getReal());
        assertEquals(0.0, result.get(0).getImag());
        assertEquals(0.0, result.get(1).getReal());
        assertEquals(0.0, result.get(1).getImag());

        vec1.set(0, new Complex(1.0, 1.0));
        vec1.set(1, new Complex(2.0, 2.0));

        result = vec1.negate();
        assertEquals(-1.0, result.get(0).getReal());
        assertEquals(-1.0, result.get(0).getImag());
        assertEquals(-2.0, result.get(1).getReal());
        assertEquals(-2.0, result.get(1).getImag());
    }

    @Test
    void transpose()
    {
        ComplexVector theVector;
        ComplexCovector result;

        // Test class instance.

        theVector = new ComplexVector(1);
        theVector.set(0, new Complex (1.0, 1.0));
        result = ComplexVector.transpose(theVector);
        assertEquals(1, theVector.getNumRows());
        assertEquals(1, theVector.getNumColumns());
        assertTrue(theVector.get(0).equals(new Complex(1.0, 1.0)));
        assertEquals(1, result.getNumRows());
        assertEquals(1, result.getNumColumns());
        assertTrue(result.get(0).equals(new Complex(1.0, 1.0)));

        theVector = new ComplexVector(3);
        theVector.set(0, new Complex(1.0, 1.0));
        theVector.set(1, new Complex(2.0, 2.0));
        theVector.set(2, new Complex(3.0, 3.0));
        result = ComplexVector.transpose(theVector);
        assertEquals(3, theVector.getNumRows());
        assertEquals(1, theVector.getNumColumns());
        assertTrue(theVector.get(0).equals(new Complex(1.0, 1.0)));
        assertTrue(theVector.get(1).equals(new Complex(2.0, 2.0)));
        assertTrue(theVector.get(2).equals(new Complex(3.0, 3.0)));
        assertEquals(1, result.getNumRows());
        assertEquals(3, result.getNumColumns());
        assertTrue(result.get(0).equals(new Complex(1.0, 1.0)));
        assertTrue(result.get(1).equals(new Complex(2.0, 2.0)));
        assertTrue(result.get(2).equals(new Complex(3.0, 3.0)));

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.transpose(null);
        });

        // Test instance instance.

        theVector = new ComplexVector(1);
        theVector.set(0, new Complex (1.0, 1.0));
        result = theVector.transpose();
        assertEquals(1, theVector.getNumRows());
        assertEquals(1, theVector.getNumColumns());
        assertTrue(theVector.get(0).equals(new Complex(1.0, 1.0)));
        assertEquals(1, result.getNumRows());
        assertEquals(1, result.getNumColumns());
        assertTrue(result.get(0).equals(new Complex(1.0, 1.0)));

        theVector = new ComplexVector(3);
        theVector.set(0, new Complex(1.0, 1.0));
        theVector.set(1, new Complex(2.0, 2.0));
        theVector.set(2, new Complex(3.0, 3.0));
        result = theVector.transpose();
        assertEquals(3, theVector.getNumRows());
        assertEquals(1, theVector.getNumColumns());
        assertTrue(theVector.get(0).equals(new Complex(1.0, 1.0)));
        assertTrue(theVector.get(1).equals(new Complex(2.0, 2.0)));
        assertTrue(theVector.get(2).equals(new Complex(3.0, 3.0)));
        assertEquals(1, result.getNumRows());
        assertEquals(3, result.getNumColumns());
        assertTrue(result.get(0).equals(new Complex(1.0, 1.0)));
        assertTrue(result.get(1).equals(new Complex(2.0, 2.0)));
        assertTrue(result.get(2).equals(new Complex(3.0, 3.0)));
    }

    @Test
    void transposeConjugate()
    {
        ComplexVector theVector;
        ComplexCovector result;

        // Test class instance.

        theVector = new ComplexVector(1);
        theVector.set(0, new Complex (1.0, 1.0));
        result = ComplexVector.transposeConjugate(theVector);
        assertEquals(1, theVector.getNumRows());
        assertEquals(1, theVector.getNumColumns());
        assertTrue(theVector.get(0).equals(new Complex(1.0, 1.0)));
        assertEquals(1, result.getNumRows());
        assertEquals(1, result.getNumColumns());
        assertTrue(result.get(0).equals(new Complex(1.0, -1.0)));

        theVector = new ComplexVector(3);
        theVector.set(0, new Complex(1.0, 1.0));
        theVector.set(1, new Complex(2.0, 2.0));
        theVector.set(2, new Complex(3.0, 3.0));
        result = ComplexVector.transposeConjugate(theVector);
        assertEquals(3, theVector.getNumRows());
        assertEquals(1, theVector.getNumColumns());
        assertTrue(theVector.get(0).equals(new Complex(1.0, 1.0)));
        assertTrue(theVector.get(1).equals(new Complex(2.0, 2.0)));
        assertTrue(theVector.get(2).equals(new Complex(3.0, 3.0)));
        assertEquals(1, result.getNumRows());
        assertEquals(3, result.getNumColumns());
        assertTrue(result.get(0).equals(new Complex(1.0, -1.0)));
        assertTrue(result.get(1).equals(new Complex(2.0, -2.0)));
        assertTrue(result.get(2).equals(new Complex(3.0, -3.0)));

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.transpose(null);
        });

        // Test instance instance.

        theVector = new ComplexVector(1);
        theVector.set(0, new Complex (1.0, 1.0));
        result = theVector.transposeConjugate();
        assertEquals(1, theVector.getNumRows());
        assertEquals(1, theVector.getNumColumns());
        assertTrue(theVector.get(0).equals(new Complex(1.0, 1.0)));
        assertEquals(1, result.getNumRows());
        assertEquals(1, result.getNumColumns());
        assertTrue(result.get(0).equals(new Complex(1.0, -1.0)));

        theVector = new ComplexVector(3);
        theVector.set(0, new Complex(1.0, 1.0));
        theVector.set(1, new Complex(2.0, 2.0));
        theVector.set(2, new Complex(3.0, 3.0));
        result = theVector.transposeConjugate();
        assertEquals(3, theVector.getNumRows());
        assertEquals(1, theVector.getNumColumns());
        assertTrue(theVector.get(0).equals(new Complex(1.0, 1.0)));
        assertTrue(theVector.get(1).equals(new Complex(2.0, 2.0)));
        assertTrue(theVector.get(2).equals(new Complex(3.0, 3.0)));
        assertEquals(1, result.getNumRows());
        assertEquals(3, result.getNumColumns());
        assertTrue(result.get(0).equals(new Complex(1.0, -1.0)));
        assertTrue(result.get(1).equals(new Complex(2.0, -2.0)));
        assertTrue(result.get(2).equals(new Complex(3.0, -3.0)));
    }

    @Test
    void innerProduct()
    {
        ComplexVector vector1;
        ComplexVector vector2;
        Complex result;

        // Test class method.

        vector1 = new ComplexVector(1);
        vector1.set(0, new Complex(1.0, 1.0));
        vector2 = new ComplexVector(1);
        vector2.set(0, new Complex(2.0,1.0));
        result = ComplexVector.innerProduct(vector1, vector2);
        assertTrue(result.equals(new Complex(3.0, -1.0)));

        vector1 = new ComplexVector(3);
        vector1.set(0, new Complex(1.0, 1.0));
        vector1.set(1, new Complex(2.0,2.0));
        vector1.set(2, new Complex(3.0,3.0));
        result = ComplexVector.innerProduct(vector1, vector1);
        assertTrue(result.equals(new Complex(28.0, 0.0)));

        final ComplexVector testVector1 = new ComplexVector(3);
        final ComplexVector testVector2 = new ComplexVector(4);
        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.innerProduct(testVector1, testVector2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.innerProduct(testVector1,null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.innerProduct(null, testVector1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.innerProduct(null,null);
        });

        // Test instance method.

        vector1 = new ComplexVector(1);
        vector1.set(0, new Complex(1.0, 1.0));
        vector2 = new ComplexVector(1);
        vector2.set(0, new Complex(2.0,1.0));
        result = vector1.innerProduct(vector2);
        assertTrue(result.equals(new Complex(3.0, -1.0)));

        vector1 = new ComplexVector(3);
        vector1.set(0, new Complex(1.0, 1.0));
        vector1.set(1, new Complex(2.0,2.0));
        vector1.set(2, new Complex(3.0,3.0));
        result = vector1.innerProduct(vector1);
        assertTrue(result.equals(new Complex(28.0, 0.0)));

        assertThrows(IllegalArgumentException.class, () -> {
            testVector1.innerProduct(testVector2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            testVector1.innerProduct(null);
        });

    }

    @Test
    void size()
    {
        ComplexVector theVector;

        // Test class method.

        theVector = new ComplexVector(1);
        theVector.set(0, new Complex(1.0, 1.0));
        assertEquals(1, ComplexVector.size(theVector));

        theVector = new ComplexVector(3);
        assertEquals(3, ComplexVector.size(theVector));

        assertThrows(IllegalArgumentException.class, () -> {
            ComplexVector.size(null);
        });

        // Test instance method.

        theVector = new ComplexVector(1);
        theVector.set(0, new Complex(1.0, 1.0));
        assertEquals(1, theVector.size());

        theVector = new ComplexVector(3);
        assertEquals(3, theVector.size());
    }

    @Test
    void cloneTest()
    {

    }
}