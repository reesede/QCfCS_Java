package qcfcs_math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements unit tests for the PolarCoordinate class.
 * Created by reesede on 1/8/2017.
 * @author David E. Reese
 * @version 1.3.1
 * @since 1.3.1
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
//      20170108    D.E. Reese          Creation (Programming Drill 1.3.1).
//      20170110    D.E. Reese          Added code to stubs for setAngle_getAngle() and setRadius_getRadius().
//      20170113    D.E. Reese          Added constructors() to test constructor methods.
//

class PolarCoordinateTest
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
        PolarCoordinate theCoord = new PolarCoordinate();
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(0.0, 0.0);
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(1.0, Math.PI/4.0);
        assertEquals(Math.PI/4.0, theCoord.getAngle());
        assertEquals(1.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(1.0, 2.0*Math.PI + Math.PI/4.0);
        assertEquals(Math.PI/4.0, theCoord.getAngle());
        assertEquals(1.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(0.0, -Math.PI/4.0);
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(1.0, -Math.PI/4.0);
        assertEquals(-Math.PI/4.0, theCoord.getAngle());
        assertEquals(1.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(1.0, -2.0*Math.PI - Math.PI/4.0);
        assertEquals(-Math.PI/4.0, theCoord.getAngle());
        assertEquals(1.0, theCoord.getRadius());

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate badCoord = new PolarCoordinate(-1.0, Math.PI/2.0);
        });

        theCoord = new PolarCoordinate(new Complex(0.0,0.0));
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(new Complex(1.0,0.0));
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(1.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(new Complex(0.0, 1.0));
        assertEquals(Math.PI/2.0, theCoord.getAngle());
        assertEquals(1.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(new Complex(-1.0, 0.0));
        assertEquals(Math.PI, theCoord.getAngle());
        assertEquals(1.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(new Complex(0.0, -1.0));
        assertEquals(-Math.PI/2.0, theCoord.getAngle());
        assertEquals(1.0, theCoord.getRadius());

    }

    @Test
    void angleToStandardRange()
    {
        double newAngle = PolarCoordinate.angleToStandardRange(0.0);
        assertEquals(0.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(Math.PI/4.0);
        assertEquals(Math.PI/4.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(-Math.PI/4.0);
        assertEquals(-Math.PI/4.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(Math.PI/2.0);
        assertEquals(Math.PI/2.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(5*Math.PI/4.0);
        assertEquals(- 3.0*Math.PI/4.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(- 5*Math.PI/4.0);
        assertEquals(3.0*Math.PI/4.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(Math.PI);
        assertEquals(Math.PI, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(-Math.PI);
        assertEquals(-Math.PI, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(2*Math.PI);
        assertEquals(0.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(-2*Math.PI);
        assertEquals(0.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(3*Math.PI);
        assertEquals(Math.PI, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(-3*Math.PI);
        assertEquals(-Math.PI, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(4*Math.PI);
        assertEquals(0.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(-4*Math.PI);
        assertEquals(0.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(17.0*Math.PI/4.0);
        assertEquals(Math.PI/4.0, newAngle, 0.0000001);

        newAngle = PolarCoordinate.angleToStandardRange(-17.0*Math.PI/4.0);
        assertEquals(-Math.PI/4.0, newAngle, 0.0000001);
    }

    @Test
    void setAngle_getAngle()
    {
        // Test static methods.

        PolarCoordinate theCoord = new PolarCoordinate();
        assertEquals(0.0, PolarCoordinate.getAngle(theCoord));
        assertEquals(0.0, PolarCoordinate.getRadius(theCoord));

        PolarCoordinate.setAngle(theCoord, Math.PI/4.0);
        assertEquals(Math.PI/4.0, PolarCoordinate.getAngle(theCoord));
        assertEquals(0.0, PolarCoordinate.getRadius(theCoord));

        PolarCoordinate.setAngle(theCoord, (float)0.75);
        assertEquals(0.75, PolarCoordinate.getAngle(theCoord));
        assertEquals(0.0, PolarCoordinate.getRadius(theCoord));

        PolarCoordinate.setAngle(theCoord, (int)2);
        assertEquals(2.0, PolarCoordinate.getAngle(theCoord));
        assertEquals(0.0, PolarCoordinate.getRadius(theCoord));

        // Test instance methods.

        theCoord = new PolarCoordinate();
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        PolarCoordinate.setAngle(theCoord, Math.PI/4.0);
        assertEquals(Math.PI/4.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        PolarCoordinate.setAngle(theCoord, (float)0.75);
        assertEquals(0.75, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        PolarCoordinate.setAngle(theCoord, (int)2);
        assertEquals(2.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());
    }

    @Test
    void setRadius_getRadius()
    {
        // Test static methods.

        PolarCoordinate theCoord = new PolarCoordinate();
        assertEquals(0.0, PolarCoordinate.getAngle(theCoord));
        assertEquals(0.0, PolarCoordinate.getRadius(theCoord));

        PolarCoordinate.setRadius(theCoord, 2.5);
        assertEquals(0.0, PolarCoordinate.getAngle(theCoord));
        assertEquals(2.5, PolarCoordinate.getRadius(theCoord));

        PolarCoordinate.setRadius(theCoord, (float)0.75);
        assertEquals(0.0, PolarCoordinate.getAngle(theCoord));
        assertEquals(0.75, PolarCoordinate.getRadius(theCoord));

        PolarCoordinate.setRadius(theCoord, (int)2);
        assertEquals(0.0, PolarCoordinate.getAngle(theCoord));
        assertEquals(2.0, PolarCoordinate.getRadius(theCoord));

        // Test instance methods.

        theCoord = new PolarCoordinate();
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(0.0, theCoord.getRadius());

        PolarCoordinate.setRadius(theCoord, 2.5);
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(2.5, theCoord.getRadius());

        PolarCoordinate.setRadius(theCoord, (float)0.75);
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(0.75, theCoord.getRadius());

        PolarCoordinate.setRadius(theCoord, (int)2);
        assertEquals(0.0, theCoord.getAngle());
        assertEquals(2.0, theCoord.getRadius());
    }

    @Test
    void polarToComplex()
    {
        PolarCoordinate theCoord = new PolarCoordinate();
        Complex theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(0.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theCoord = new PolarCoordinate(new Complex(1.0, 0.0));
        theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(1.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theCoord = new PolarCoordinate(new Complex(0.0, 1.0));
        theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(0.0, theComplex.getReal(), 0.00000001);
        assertEquals(1.0, theComplex.getImag(), 0.00000001);

        theCoord = new PolarCoordinate(new Complex(-1.0, 0.0));
        theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(-1.0, theComplex.getReal(), 0.00000001);
        assertEquals(0.0, theComplex.getImag(), 0.00000001);

        theCoord = new PolarCoordinate(new Complex(0.0, -1.0));
        theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(0.0, theComplex.getReal(), 0.00000001);
        assertEquals(-1.0, theComplex.getImag(), 0.00000001);

        theCoord = new PolarCoordinate(new Complex(1.0, 1.0));
        theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(1.0, theComplex.getReal(), 0.00000001);
        assertEquals(1.0, theComplex.getImag(), 0.00000001);

        theCoord = new PolarCoordinate(new Complex(-3.0, -1.0));
        theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(-3.0, theComplex.getReal(), 0.00000001);
        assertEquals(-1.0, theComplex.getImag(), 0.00000001);

        theCoord = new PolarCoordinate();
        theComplex = theCoord.toComplex();
        assertEquals(0.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theCoord = new PolarCoordinate(new Complex(1.0, 0.0));
        theComplex = theCoord.toComplex();
        assertEquals(1.0, theComplex.getReal());
        assertEquals(0.0, theComplex.getImag());

        theCoord = new PolarCoordinate(new Complex(0.0, 1.0));
        theComplex = theCoord.toComplex();
        assertEquals(0.0, theComplex.getReal(), 0.00000001);
        assertEquals(1.0, theComplex.getImag(), 0.00000001);

        theCoord = new PolarCoordinate(new Complex(-1.0, 0.0));
        theComplex = theCoord.toComplex();
        assertEquals(-1.0, theComplex.getReal(), 0.00000001);
        assertEquals(0.0, theComplex.getImag(), 0.00000001);

        theCoord = new PolarCoordinate(new Complex(0.0, -1.0));
        theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(0.0, theComplex.getReal(), 0.00000001);
        assertEquals(-1.0, theComplex.getImag(), 0.00000001);

        theCoord = new PolarCoordinate(new Complex(1.0, 1.0));
        theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(1.0, theComplex.getReal(), 0.00000001);
        assertEquals(1.0, theComplex.getImag(), 0.00000001);

        theCoord = new PolarCoordinate(new Complex(-3.0, -1.0));
        theComplex = PolarCoordinate.polarToComplex(theCoord);
        assertEquals(-3.0, theComplex.getReal(), 0.00000001);
        assertEquals(-1.0, theComplex.getImag(), 0.00000001);


    }

}