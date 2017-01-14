package qcfcs_math;

import java.lang.Math;

/**
 * This class implements polar coordinates.
 * Created by reesede on 1/7/2017.
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
//      20170107    D.E. Reese          Creation (Programming Drill 1.3.1)

public class PolarCoordinate
{
    /**
     * Angle of coordinate.
     */
    private double angle;

    /**
     * Radius of coordinate.
     */
    private double radius;

    /**
     * Default constructor, which sets both the radius and the angle to 0.0.
     */
    public PolarCoordinate ()
    {
        angle = 0.0;
        radius = 0.0;
    }

    /**
     * Constructor to create a polar coordinate with both a radius and an angle.
     * @param theRadius Radius of coordinate (must be >= 0.0).
     * @param theAngle  Angle of coordinate.
     * @throws IllegalArgumentException Thrown if theRadius < 0.0.
     */
    public PolarCoordinate (double theRadius, double theAngle) throws IllegalArgumentException
    {
        if (theRadius < 0.0) throw new IllegalArgumentException("theRadius must be >= 0.0.");
        if (theRadius == 0.0) theAngle = 0.0;
        angle = PolarCoordinate.angleToStandardRange(theAngle);
        radius = theRadius;
    }

    /**
     * Constructor to create a polar coordinate out of a complex number.
     * @param theComplex    Complex number to be used to create the polar coordinate.
     * @throws IllegalArgumentException Thrown if theComplex is null.
     */
    public PolarCoordinate (Complex theComplex) throws IllegalArgumentException
    {
        if (theComplex == null) throw new IllegalArgumentException("theComplex is null");

        double theRadius = 0.0;
        double theAngle = 0.0;

        if ((theComplex.getReal() == 0.0) && (theComplex.getImag() == 0.0))
        {
            theRadius = 0.0;
            theAngle = 0.0;
        }
        else
        {
            theRadius = Math.sqrt(Math.pow(theComplex.getReal(), 2.0) + Math.pow(theComplex.getImag(), 2.0));

            if ((theComplex.getImag() > 0.0) && (theComplex.getReal() > 0.0))
                theAngle = Math.atan(theComplex.getImag() / theComplex.getReal());

            if ((theComplex.getImag() < 0.0) && (theComplex.getReal() > 0.0))
                theAngle = Math.atan(theComplex.getImag() / theComplex.getReal()) + Math.PI;

            if ((theComplex.getImag() < 0.0) && (theComplex.getReal() < 0.0))
                theAngle = Math.atan(theComplex.getImag() / theComplex.getReal()) - Math.PI;

            if ((theComplex.getImag() == 0.0) && (theComplex.getReal() > 0.0))
                theAngle = 0.0;

            if ((theComplex.getImag() == 0.0) && (theComplex.getReal() < 0.0))
                theAngle = Math.PI;

            if ((theComplex.getImag() > 0.0) && (theComplex.getReal() == 0.0))
                theAngle = Math.PI/2.0;

            if ((theComplex.getImag() < 0.0) && (theComplex.getReal() == 0.0))
                theAngle = -Math.PI/2.0;
        }

        radius = theRadius;
        angle  = PolarCoordinate.angleToStandardRange(theAngle);
        ;
    }

    /**
     * This method converts any real angle value to the range -PI...PI.
     * @param theAngle  Angle to be converted.
     * @return          Angle normalized to within the range -PI...PI.
     */
    public static double angleToStandardRange (double theAngle)
    {
        double theResult = theAngle;

        // Convert the angle to be within the range -2*Pi...2*Pi.

        theResult -= (double)((int)(theAngle / (2.0 * Math.PI))) * 2.0 * Math.PI;

        // If the converted angle is greater than Pi, it is in the third or fourth quadrant, so it
        // should be negative. If the converted angle is less than Pi, it is in the first or second
        // quadrant, so should be positive.

        if (theResult > Math.PI)
        {
            theResult -= 2.0 * Math.PI;
        }
        else
        {
            if (theResult < -Math.PI)
                theResult += 2.0 * Math.PI;
        }
        return theResult;
    }

    /**
     * This method returns the angle of the polar coordinate.
     * @param theCoord  Polar coordinate whose angle is to be returned.
     * @return          Angle of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theCoord is null.
     */
    public static double getAngle (PolarCoordinate theCoord) throws IllegalArgumentException
    {
        if (theCoord == null) throw new IllegalArgumentException("theCoord is null");
        return theCoord.angle;
    }

    /**
     * This method returns the radius of the polar coordinate.
     * @param theCoord  Polar coordinate whose radius is to be returned.
     * @return          radius of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theCoord is null.
     */
    public static double getRadius (PolarCoordinate theCoord) throws IllegalArgumentException
    {
        if (theCoord == null) throw new IllegalArgumentException("theCoord is null");
        return theCoord.radius;
    }

    /**
     * This method sets the angle of a given polar coordinate to the specified double value.
     * @param theCoord  Polar coordinate whose radius is to be set.
     * @param theAngle  New value of the angle of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theCoord is null.
     */
    public static void setAngle (PolarCoordinate theCoord, double theAngle) throws IllegalArgumentException
    {
        if (theCoord == null) throw new IllegalArgumentException("theCoord is null");
        theCoord.angle = PolarCoordinate.angleToStandardRange(theAngle);
    }

    /**
     * This method sets the angle of a given polar coordinate to the specified float value.
     * @param theCoord  Polar coordinate whose radius is to be set.
     * @param theAngle  New value of the angle of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theCoord is null.
     */
    public static void setAngle (PolarCoordinate theCoord, float theAngle) throws IllegalArgumentException
    {
        if (theCoord == null) throw new IllegalArgumentException("theCoord is null");
        theCoord.angle = PolarCoordinate.angleToStandardRange((double)theAngle);
    }

    /**
     * This method sets the angle of a given polar coordinate to the specified int value.
     * @param theCoord  Polar coordinate whose radius is to be set.
     * @param theAngle  New value of the angle of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theCoord is null.
     */
    public static void setAngle (PolarCoordinate theCoord, int theAngle) throws IllegalArgumentException
    {
        if (theCoord == null) throw new IllegalArgumentException("theCoord is null");
        theCoord.angle = PolarCoordinate.angleToStandardRange((double)theAngle);
    }

    /**
     * This method sets the radius of a given polar coordinate to the specified double value. Note that if the
     * new radius is 0.0, the angle will be set to 0.0 (angle is undefined for a 0-length radius).
     * @param theCoord  Polar coordinate whose radius is to be set.
     * @param theRadius New value of the radius of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theCoord is null or theRadius < 0.0.
     */
    public static void setRadius (PolarCoordinate theCoord, double theRadius) throws IllegalArgumentException
    {
        if (theCoord == null) throw new IllegalArgumentException("theCoord is null");
        if (theRadius < 0.0) throw new IllegalArgumentException("theRadius is < 0.0");
        theCoord.radius = theRadius;
        if (theRadius == 0.0)
            theCoord.angle = 0.0;
    }

    /**
     * This method sets the radius of a given polar coordinate to the specified float value. Note that if the
     * new radius is 0.0, the angle will be set to 0.0 (angle is undefined for a 0-length radius).
     * @param theCoord  Polar coordinate whose radius is to be set.
     * @param theRadius New value of the radius of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theCoord is null or theRadius < 0.0.
     */
    public static void setRadius (PolarCoordinate theCoord, float theRadius) throws IllegalArgumentException
    {
        if (theCoord == null) throw new IllegalArgumentException("theCoord is null");
        if (theRadius < 0.0) throw new IllegalArgumentException("theRadius is < 0.0");
        theCoord.radius = (double)theRadius;
        if (theRadius == 0.0)
            theCoord.angle = 0.0;
    }

    /**
     * This method sets the radius of a given polar coordinate to the specified int value. Note that if the
     * new radius is 0.0, the angle will be set to 0.0 (angle is undefined for a 0-length radius).
     * @param theCoord  Polar coordinate whose radius is to be set.
     * @param theRadius New value of the radius of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theCoord is null or theRadius < 0.0.
     */
    public static void setRadius (PolarCoordinate theCoord, int theRadius) throws IllegalArgumentException
    {
        if (theCoord == null) throw new IllegalArgumentException("theCoord is null");
        if (theRadius < 0) throw new IllegalArgumentException("theRadius is < 0.0");
        theCoord.radius = (double)theRadius;
        if (theRadius == 0)
            theCoord.angle = 0.0;
    }

    /**
     * This method converts a given polar coordinate into a complex number.
     * @param theCoord  Coordinate to convert to a complex number.
     * @return          Complex number corresponding to the polar coordinate.
     * @throws IllegalArgumentException Thrown if theCoord is null.
     */
    public static Complex polarToComplex (PolarCoordinate theCoord) throws IllegalArgumentException
    {
        if (theCoord == null) throw new IllegalArgumentException("theCoord is null");

        double realPart = theCoord.radius * Math.cos(theCoord.angle);
        double imagPart = theCoord.radius * Math.sin(theCoord.angle);

        return new Complex(realPart, imagPart);
    }

    /**
     * This method returns the angle of the polar coordinate.
     * @return          Angle of the polar coordinate.
     */
    public double getAngle ()
    {
        return angle;
    }

    /**
     * This method returns the radius of the polar coordinate.
     * @return          radius of the polar coordinate.
     */
    public double getRadius ()
    {
        return radius;
    }

    /**
     * This method sets the angle of a given polar coordinate to the specified double value.
     * @param theAngle  New value of the angle of the polar coordinate.
     */
    public void setAngle (double theAngle)
    {
        angle = PolarCoordinate.angleToStandardRange(theAngle);
    }

    /**
     * This method sets the angle of a given polar coordinate to the specified float value.
     * @param theAngle  New value of the angle of the polar coordinate.
     */
    public void setAngle (float theAngle)
    {
        angle = PolarCoordinate.angleToStandardRange((double)theAngle);
    }

    /**
     * This method sets the angle of a given polar coordinate to the specified int value.
     * @param theAngle  New value of the angle of the polar coordinate.
     */
    public void setAngle (int theAngle)
    {
        angle = PolarCoordinate.angleToStandardRange((double)theAngle);
    }

    /**
     * This method sets the radius of a given polar coordinate to the specified double value. Note that if the
     * new radius is 0.0, the angle will be set to 0.0 (angle is undefined for a 0-length radius).
     * @param theRadius New value of the radius of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theRadius < 0.0.
     */
    public void setRadius (double theRadius) throws IllegalArgumentException
    {
        if (theRadius < 0.0) throw new IllegalArgumentException("theRadius < 0.0");
        PolarCoordinate.setRadius(this, theRadius);
    }

    /**
     * This method sets the radius of a given polar coordinate to the specified float value. Note that if the
     * new radius is 0.0, the angle will be set to 0.0 (angle is undefined for a 0-length radius).
     * @param theRadius New value of the radius of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theRadius < 0.0.
     */
    public void setRadius (float theRadius) throws IllegalArgumentException
    {
        if (theRadius < 0.0) throw new IllegalArgumentException("theRadius < 0.0");
        PolarCoordinate.setRadius(this, theRadius);
    }

    /**
     * This method sets the radius of a given polar coordinate to the specified int value. Note that if the
     * new radius is 0.0, the angle will be set to 0.0 (angle is undefined for a 0-length radius).
     * @param theRadius New value of the radius of the polar coordinate.
     * @throws IllegalArgumentException Thrown if theRadius < 0.
     */
    public void setRadius (int theRadius) throws IllegalArgumentException
    {
        if (theRadius < 0.0) throw new IllegalArgumentException("theRadius < 0");
        PolarCoordinate.setRadius(this, theRadius);
    }

    /**
     * This method converts a given polar coordinate into a complex number.
     */
    public Complex toComplex () throws IllegalArgumentException
    {
        return PolarCoordinate.polarToComplex(this);
    }
}
