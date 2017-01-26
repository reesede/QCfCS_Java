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