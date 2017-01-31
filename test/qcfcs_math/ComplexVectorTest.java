package qcfcs_math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements unit tests for the ComplexVector class.
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
//      20170128    D.E. Reese          Added code for setAndGet().
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
            ComplexVector.multiply(new ComplexVector(2), null);
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
            new ComplexVector(2).multiply(null);
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
    void cloneTest()
    {

    }
}