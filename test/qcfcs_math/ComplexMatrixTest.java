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

    }

    @Test
    void multiply()
    {

    }

    @Test
    void negate()
    {

    }

    @Test
    void cloneTest()
    {

    }

}