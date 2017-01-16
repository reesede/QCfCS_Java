package qcfcs_math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements unit tests for the PolarCoordinate class.
 * Created by reesede on 1/8/2017.
 * @author David E. Reese
 * @version 1.3.3
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
//      20170115    D.E. Reese          Converted standard range of angles from -PI...PI to 0...2*PI.
//                                      Added tests for multiply, divide, pow (Programming Drill 1.3.3).
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
        assertEquals(7.0*Math.PI/4.0, theCoord.getAngle());
        assertEquals(1.0, theCoord.getRadius());

        theCoord = new PolarCoordinate(1.0, -2.0*Math.PI - Math.PI/4.0);
        assertEquals(7.0*Math.PI/4.0, theCoord.getAngle());
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
        assertEquals(3.0*Math.PI/2.0, theCoord.getAngle());
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
        assertEquals(7.0*Math.PI/4.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(Math.PI/2.0);
        assertEquals(Math.PI/2.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(5*Math.PI/4.0);
        assertEquals(5.0*Math.PI/4.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(- 5*Math.PI/4.0);
        assertEquals(3.0*Math.PI/4.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(Math.PI);
        assertEquals(Math.PI, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(-Math.PI);
        assertEquals(Math.PI, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(2*Math.PI);
        assertEquals(0.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(-2*Math.PI);
        assertEquals(0.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(3*Math.PI);
        assertEquals(Math.PI, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(-3*Math.PI);
        assertEquals(Math.PI, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(4*Math.PI);
        assertEquals(0.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(-4*Math.PI);
        assertEquals(0.0, newAngle);

        newAngle = PolarCoordinate.angleToStandardRange(17.0*Math.PI/4.0);
        assertEquals(Math.PI/4.0, newAngle, 0.0000001);

        newAngle = PolarCoordinate.angleToStandardRange(-17.0*Math.PI/4.0);
        assertEquals(7.0*Math.PI/4.0, newAngle, 0.0000001);
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

    @Test
    void multiply ()
    {
        PolarCoordinate num1 = new PolarCoordinate();
        PolarCoordinate num2 = new PolarCoordinate();
        PolarCoordinate theProduct = PolarCoordinate.multiply(num1, num2);
        assertEquals(0.0, theProduct.getAngle());
        assertEquals(0.0, theProduct.getRadius());

        num1 = new PolarCoordinate(1.0, Math.PI/4.0);
        num2 = new PolarCoordinate();
        theProduct = PolarCoordinate.multiply(num1, num2);
        assertEquals(0.0, theProduct.getAngle());
        assertEquals(0.0, theProduct.getRadius());

        theProduct = PolarCoordinate.multiply(num2, num1);
        assertEquals(0.0, theProduct.getAngle());
        assertEquals(0.0, theProduct.getRadius());

        num1 = new PolarCoordinate(2.0, Math.PI/4.0);
        num2 = new PolarCoordinate(3.0, Math.PI/2.0);
        theProduct = PolarCoordinate.multiply(num1, num2);
        assertEquals(3.0*Math.PI/4.0, theProduct.getAngle());
        assertEquals(6.0, theProduct.getRadius());

        num1 = new PolarCoordinate(2.0, Math.PI/2.0);
        num2 = new PolarCoordinate(3.0, Math.PI/2.0);
        theProduct = PolarCoordinate.multiply(num1, num2);
        assertEquals(Math.PI, theProduct.getAngle());
        assertEquals(6.0, theProduct.getRadius());

        num1 = new PolarCoordinate(2.0, Math.PI);
        num2 = new PolarCoordinate(3.0, Math.PI);
        theProduct = PolarCoordinate.multiply(num1, num2);
        assertEquals(0.0, theProduct.getAngle());
        assertEquals(6.0, theProduct.getRadius());

        num1 = new PolarCoordinate(2.0, 3.0*Math.PI/2.0);
        num2 = new PolarCoordinate(3.0, 3.0*Math.PI/2.0);
        theProduct = PolarCoordinate.multiply(num1, num2);
        assertEquals(Math.PI, theProduct.getAngle());
        assertEquals(6.0, theProduct.getRadius());

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate theNum = new PolarCoordinate(2.0, 3.0*Math.PI/2.0);
            PolarCoordinate badCoord = PolarCoordinate.multiply(theNum, null);
        });

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate theNum = new PolarCoordinate(2.0, 3.0*Math.PI/2.0);
            PolarCoordinate badCoord = PolarCoordinate.multiply(null, theNum);
        });

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate badCoord = PolarCoordinate.multiply(null, null);
        });

        num1 = new PolarCoordinate(2.0, Math.PI/2.0);
        num2 = new PolarCoordinate(3.0, Math.PI/2.0);
        theProduct = num1.multiply(num2);
        assertEquals(Math.PI, theProduct.getAngle());
        assertEquals(6.0, theProduct.getRadius());

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate theNum = new PolarCoordinate(2.0, 3.0*Math.PI/2.0);
            PolarCoordinate badCoord = theNum.multiply(null);
        });

    }

    @Test
    void divide ()
    {
        PolarCoordinate num1 = new PolarCoordinate();
        PolarCoordinate num2 = new PolarCoordinate(1.0,0.0);
        PolarCoordinate quotient = PolarCoordinate.divide(num1, num2);
        assertEquals(0.0, quotient.getRadius());
        assertEquals(0.0, quotient.getAngle());

        num1 = new PolarCoordinate(2.0, 0.0);
        num2 = new PolarCoordinate(2.0, 0.0);
        quotient = PolarCoordinate.divide(num1, num2);
        assertEquals(1.0, quotient.getRadius());
        assertEquals(0.0, quotient.getAngle());

        num1 = new PolarCoordinate(2.0, Math.PI/4.0);
        num2 = new PolarCoordinate(2.0, Math.PI/4.0);
        quotient = PolarCoordinate.divide(num1, num2);
        assertEquals(1.0, quotient.getRadius());
        assertEquals(0.0, quotient.getAngle());

        num1 = new PolarCoordinate(3.0, Math.PI/4.0);
        num2 = new PolarCoordinate(2.0, Math.PI/2.0);
        quotient = PolarCoordinate.divide(num1, num2);
        assertEquals(1.5, quotient.getRadius());
        assertEquals(7.0*Math.PI/4.0, quotient.getAngle());

        num1 = new PolarCoordinate(3.0, Math.PI/4.0);
        num2 = new PolarCoordinate(2.0, 2.0*Math.PI);
        quotient = PolarCoordinate.divide(num1, num2);
        assertEquals(1.5, quotient.getRadius());
        assertEquals(Math.PI/4.0, quotient.getAngle());

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate theNum = new PolarCoordinate(2.0, 3.0*Math.PI/2.0);
            PolarCoordinate badCoord = PolarCoordinate.divide(theNum, null);
        });

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate theNum = new PolarCoordinate(2.0, 3.0*Math.PI/2.0);
            PolarCoordinate badCoord = PolarCoordinate.divide(null, theNum);
        });

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate badCoord = PolarCoordinate.divide(null, null);
        });

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate theNum1 = new PolarCoordinate(2.0, 3.0*Math.PI/2.0);
            PolarCoordinate theNum2 = new PolarCoordinate(0.0, 0.0);
            PolarCoordinate badCoord = PolarCoordinate.divide(theNum1, theNum2);
        });

        num1 = new PolarCoordinate(2.0, 3.0*Math.PI/2.0);
        num2 = new PolarCoordinate(4.0, Math.PI/2.0);
        quotient = num1.divide(num2);
        assertEquals(0.5, quotient.getRadius());
        assertEquals(Math.PI, quotient.getAngle());

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate theNum = new PolarCoordinate(2.0, 3.0*Math.PI/2.0);
            PolarCoordinate badCoord = theNum.divide(null);
        });

    }

    @Test
    void pow ()
    {
        PolarCoordinate num1 = new PolarCoordinate();
        PolarCoordinate result = PolarCoordinate.pow(num1, 0.0);
        assertEquals(1.0, result.getRadius());
        assertEquals(0.0, result.getAngle());

        result = PolarCoordinate.pow(num1, 1.0);
        assertEquals(0.0, result.getRadius());
        assertEquals(0.0, result.getAngle());

        num1 = new PolarCoordinate(2.0, 0.0);
        result = PolarCoordinate.pow(num1, 1.0);
        assertEquals(2.0, result.getRadius());
        assertEquals(0.0, result.getAngle());

        num1 = new PolarCoordinate(2.0, 0.0);
        result = PolarCoordinate.pow(num1, 2.0);
        assertEquals(4.0, result.getRadius());
        assertEquals(0.0, result.getAngle());

        num1 = new PolarCoordinate(4.0, 0.0);
        result = PolarCoordinate.pow(num1, 1.0/2.0);
        assertEquals(2.0, result.getRadius());
        assertEquals(0.0, result.getAngle());

        num1 = new PolarCoordinate(4.0, 0.0);
        result = PolarCoordinate.pow(num1, -1.0);
        assertEquals(1/4.0, result.getRadius());
        assertEquals(0.0, result.getAngle());

        num1 = new PolarCoordinate(4.0, Math.PI/4.0);
        result = PolarCoordinate.pow(num1, 0.0);
        assertEquals(1.0, result.getRadius());
        assertEquals(0.0, result.getAngle());

        num1 = new PolarCoordinate(4.0, Math.PI/4.0);
        result = PolarCoordinate.pow(num1, 1.0);
        assertEquals(4.0, result.getRadius());
        assertEquals(Math.PI/4.0, result.getAngle());

        num1 = new PolarCoordinate(4.0, Math.PI/4.0);
        result = PolarCoordinate.pow(num1, -1.0);
        assertEquals(0.25, result.getRadius());
        assertEquals(7.0*Math.PI/4.0, result.getAngle());

        num1 = new PolarCoordinate(4.0, Math.PI/4.0);
        result = PolarCoordinate.pow(num1, 1.0/2.0);
        assertEquals(2.0, result.getRadius());
        assertEquals(Math.PI/8.0, result.getAngle());

        assertThrows(IllegalArgumentException.class , () -> {
            PolarCoordinate badCoord = PolarCoordinate.pow(null, 1.0);
        });

        num1 = new PolarCoordinate(4.0, Math.PI/4.0);
        result = num1.pow(1.0/2.0);
        assertEquals(2.0, result.getRadius());
        assertEquals(Math.PI/8.0, result.getAngle());

    }

}