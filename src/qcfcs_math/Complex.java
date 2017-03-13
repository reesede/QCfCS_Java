package qcfcs_math;

/**
 * This class implements complex numbers. Note that the real part and imaginary part of the complex number always
 * convert -0.0 to 0.0.
 * Created by reesede on 1/4/2017.
 * @author David E. Reese
 * @version 3.3.1
 * @since 1.1.1
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
// along with QCfCS_java.  If not, see <http://www.gnu.org/licenses/>.
//
// History:
//      20170104    D.E. Reese          Creation (Programming Drill 1.1.1)
//      20170105    D.E. Reese          Added GPL.
//                                      Made realPart and imagPart public.
//                                      Added subtract (), conjugate ().
//      20170106    D.E. Reese          Added isZero (), divide ().
//      20170107    D.E. Reese          Added static isZero () and explicit throw syntax to static procedure calls.
//                                      Added additional operations when adding, subtracting, multiplying and
//                                      dividing by doubles, floats, and ints.
//                                      Made realPart and imagPart private and added getReal () and getImag ()
//                                      methods.
//      20170113    D.E. Reese          Added constructor to create a complex number from a polar coordinate.
//      20170114    D.E. Reese          Added constructor to create a complex number from an existing complex number.
//      20170117    D.E. Reese          Fixed bug in complexToPolarCoordinate() for points on real and imaginary axes.
//      20170121    D.E. Reese          Added abs().
//      20170124    D.E. Reese          Fixed -0.0 bug in negate().
//      20170125    D.E. Reese          Added equals() methods to compare a complex to double, float, and int.
//      20170128    D.E. Reese          Added fixZero() method to convert -0.0 to 0.0 and modified all methods that
//                                      set the real or imaginary parts to call it on it.
//      20170210    D.E. Reese          Added toString() and set(). Finalized parameters to methods.
//      20170216    D.E. Reese          Added deltaPrecision, setPrecision(), and getPrecision(). Added code to
//                                      equals to determine equality within a given precision.
//      20170305    D.E. Reese          Added parseComplex().
//      20170313    D.E. Reese          Added modulus().
//

public class Complex
{

    /**
     * Used as a delta from an exact value in comparisons for equality. A value will be considered equal if it is
     * in the range (value-deltaValue)...value...(value+deltaValue).
     */
    private static double deltaPrecision = 0.0000001;

    /**
     * Real part of complex number.
     */
    private double realPart;

    /**
     * Imaginary part of complex number.
     */
    private double imagPart;

    /**
     * Default constructor. Sets the complex number to 0 + 0I.
     */
    public Complex ()
    {
        realPart = 0.0;
        imagPart = 0.0;
    }

    /**
     * Constructor which sets the complex number to a real number.
     * @param theReal   Real part of the new complex number.
     */
    public Complex(final double theReal)
    {
        realPart = Complex.fixZero(theReal);
        imagPart = 0.0;
    }

    /**
     * Constructor which sets the complex number to given real and imaginary parts.
     * @param theReal   Real part of the new complex number.
     * @param theImag   Imaginary part of the new complex number.
     */

    public Complex (final double theReal, final double theImag)
    {
        realPart = Complex.fixZero(theReal);
        imagPart = Complex.fixZero(theImag);
    }

    /**
     * Constructor which sets the complex number to the values derived from a polar coordinate.
     * @param theCoord  Polar coordinate whose values are used to form a new complex number.
     * @throws IllegalArgumentException Thrown if theCoord is null.
     */
    public Complex(final PolarCoordinate theCoord) throws IllegalArgumentException
    {
        if(theCoord == null) throw new IllegalArgumentException("theCoord is null");
        Complex newNum = theCoord.toComplex();
        realPart = Complex.fixZero(newNum.realPart);
        imagPart = Complex.fixZero(newNum.imagPart);
    }

    /**
     * Constructor to create a new complex number that has the same real and imaginary parts as an existing
     * complex number. Note that this is a deep copy.
     * @param theNum    Complex number whose real and imaginary parts will be set to those of the new complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public Complex (final Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        this.realPart = Complex.fixZero(theNum.realPart);
        this.imagPart = Complex.fixZero(theNum.imagPart);
    }

    /**
     * This method takes a double and returns it back unchanged, unless that number is -0.0. In this case, it
     * converts the -0.0 to 0.0.
     * @param theNum    Double to be checked and converted to 0.0 if -0.0.
     * @return  theNum unless theNum == -0.0, in which case it returns 0.0.
     */
    private static double fixZero(final double theNum)
    {
        if (theNum == -0.0)
            return 0.0;
        return theNum;
    }

    /**
     * This method returns the real part of a complex number.
     * @param theNum    Complex number whose real part is to be returned.
     * @return          Real part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static double getReal (final Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        return Complex.fixZero(theNum.realPart);
    }

    /**
     * This method returns the imaginary part of a complex number.
     * @param theNum    Complex number whose imaginary part is to be returned.
     * @return          Imaginary part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static double getImag (final Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        return Complex.fixZero(theNum.imagPart);
    }

    /**
     * This method sets the real and imaginary parts of a complex number to the corresponding values of another complex.
     * @param theComplex    Complex number whose values are to be set.
     * @param newValue      Complex number whose values are to be used to set to theComplex.
     * @throws IllegalArgumentException Thrown if theComplex is null or newValue is null.
     */
    public static void set(final Complex theComplex, final Complex newValue) throws IllegalArgumentException
    {
        if (theComplex == null) throw new IllegalArgumentException("theComplex is null.");
        if (newValue == null) throw new IllegalArgumentException("newValue is null.");
        theComplex.realPart = newValue.realPart;
        theComplex.imagPart = newValue.imagPart;
    }

    /**
     * This method sets the real and imaginary parts of a complex number to given values.
     * @param theComplex    Complex number whose values are to be set.
     * @param newRealPart   New value of the realPart of theComplex.
     * @param newImagPart   New value of the imagPart of theComplex.
     * @throws IllegalArgumentException Thrown if theComplex is null.
     */
    public static void set(final Complex theComplex, final double newRealPart, final double newImagPart) throws IllegalArgumentException
    {
        if (theComplex == null) throw new IllegalArgumentException("theComplex is null.");
        theComplex.realPart = newRealPart;
        theComplex.imagPart = newImagPart;
    }

    /**
     * This method sets the real part of a given complex number to a given double value.
     * @param theNum        Complex number whose real part is to be changed.
     * @param newRealPart   New value of the real part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setReal (final Complex theNum, final double newRealPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.realPart = Complex.fixZero(newRealPart);
    }

    /**
     * This method sets the real part of a given complex number to a given float value.
     * @param theNum        Complex number whose real part is to be changed.
     * @param newRealPart   New value of the real part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setReal (final Complex theNum, final float newRealPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.realPart = Complex.fixZero((double)newRealPart);
    }

    /**
     * This method sets the real part of a given complex number to a given int value.
     * @param theNum        Complex number whose real part is to be changed.
     * @param newRealPart   New value of the real part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setReal (final Complex theNum, final int newRealPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.realPart = Complex.fixZero((double)newRealPart);
    }

    /**
     * This method sets the imaginary part of a given complex number to a given double value.
     * @param theNum        Complex number whose imaginary part is to be changed.
     * @param newImagPart   New value of the imaginary part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setImag (final Complex theNum, final double newImagPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.imagPart = Complex.fixZero(newImagPart);
    }

    /**
     * This method sets the imaginary part of a given complex number to a given float value.
     * @param theNum        Complex number whose imaginary part is to be changed.
     * @param newImagPart   New value of the imaginary part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setImag (final Complex theNum, final float newImagPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.imagPart = Complex.fixZero((double)newImagPart);
    }

    /**
     * This method sets the imaginary part of a given complex number to a given int value.
     * @param theNum        Complex number whose imaginary part is to be changed.
     * @param newImagPart   New value of the imaginary part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setImag (final Complex theNum, final int newImagPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.imagPart = Complex.fixZero((double)newImagPart);
    }

    /**
     * This method checks if a complex number is 0.
     * @param theNum    Complex number to check.
     * @return          True if theNum is 0; returns false otherwise.
     * @throws IllegalArgumentException
     */
    public static boolean isZero(final Complex theNum) throws IllegalArgumentException {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        if ((theNum.realPart == 0.0) && (theNum.imagPart == 0.0))
            return true;
        return false;
    }

    /**
     * This method checks if two complex numbers are arithmetically equal (i.e., their real parts are the same and
     * their imaginary parts are the same).
     * @param num1  First number to compare.
     * @param num2  Second number to compare.
     * @return  true if the real parts of the two complex numbers and the imaginary parts of the two complex numbers
     * have the same value.
     * @throws IllegalArgumentException Thrown if num1 is null or num2 is null.
     */
    public static boolean equals(final Complex num1, final Complex num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");

        double minPrecision = num2.realPart - Complex.deltaPrecision;
        double maxPrecision = num2.realPart + Complex.deltaPrecision;
        if ((num1.realPart < minPrecision) || (num1.realPart > maxPrecision))
            return false;

        minPrecision = num2.imagPart - Complex.deltaPrecision;
        maxPrecision = num2.imagPart + Complex.deltaPrecision;
        if ((num1.imagPart < minPrecision) || (num1.imagPart > maxPrecision))
            return false;
        return true;
    }

    /**
     * This method checks if a complex number is equal to a double (i.e., the real part of the complex equals the
     * double and the imaginary part of the complex is 0.0).
     * @param num1  Complex number to compare to num2.
     * @param num2  Double to compare to num1.
     * @return  true if the real part of num1 equals num2 and the imaginary part of num1 is 0.0; false otherwise.
     * @throws IllegalArgumentException Thrown if num1 is null.
     */
    public static boolean equals(final Complex num1, final double num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");

        double minPrecision = num2 - Complex.deltaPrecision;
        double maxPrecision = num2 + Complex.deltaPrecision;
        if ((num1.realPart < minPrecision) || (num1.realPart > maxPrecision))
            return false;

        minPrecision = 0.0 - Complex.deltaPrecision;
        maxPrecision = 0.0 + Complex.deltaPrecision;
        if ((num1.imagPart < minPrecision) || (num1.imagPart > maxPrecision))
            return false;
        return true;
    }

    /**
     * This method checks if a complex number is equal to a float (i.e., the real part of the complex equals the
     * double and the imaginary part of the complex is 0.0).
     * @param num1  Complex number to compare to num2.
     * @param num2  Float to compare to num1.
     * @return  true if the real part of num1 equals num2 and the imaginary part of num1 is 0.0; false otherwise.
     * @throws IllegalArgumentException Thrown if num1 is null.
     */
    public static boolean equals(final Complex num1, final float num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");

        double minPrecision = (double)num2 - Complex.deltaPrecision;
        double maxPrecision = (double)num2 + Complex.deltaPrecision;
        if ((num1.realPart < minPrecision) || (num1.realPart > maxPrecision))
            return false;

        minPrecision = 0.0 - Complex.deltaPrecision;
        maxPrecision = 0.0 + Complex.deltaPrecision;
        if ((num1.imagPart < minPrecision) || (num1.imagPart > maxPrecision))
            return false;
        return true;
    }

    /**
     * This method checks if a complex number is equal to a int (i.e., the real part of the complex equals the
     * double and the imaginary part of the complex is 0.0).
     * @param num1  Complex number to compare to num2.
     * @param num2  int to compare to num1.
     * @return  true if the real part of num1 equals num2 and the imaginary part of num1 is 0.0; false otherwise.
     * @throws IllegalArgumentException Thrown if num1 is null.
     */
    public static boolean equals(final Complex num1, final int num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");

        double minPrecision = (double)num2 - Complex.deltaPrecision;
        double maxPrecision = (double)num2 + Complex.deltaPrecision;
        if ((num1.realPart < minPrecision) || (num1.realPart > maxPrecision))
            return false;

        minPrecision = 0.0 - Complex.deltaPrecision;
        maxPrecision = 0.0 + Complex.deltaPrecision;
        if ((num1.imagPart < minPrecision) || (num1.imagPart > maxPrecision))
            return false;
        return true;
    }

    /**
     * This method takes the complex conjugate of a complex number.
     * @param theNum    Complex number whose conjugate is to be taken.
     * @return          Complex conjugate of theNum.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static Complex conjugate (final Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        Complex newComplex = new Complex (theNum.realPart, theNum.imagPart);
        newComplex.imagPart = Complex.fixZero(- newComplex.imagPart);
        return newComplex;
    }

    /**
     * Method to add two complex numbers.
     * @param num1  First number to add.
     * @param num2  Second number to add.
     * @return      Sum of the two complex numbers.
     * @throws IllegalArgumentException Thrown if num1 or num2 is null.
     */
    public static Complex add (final Complex num1, final Complex num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");
        return new Complex(num1.realPart + num2.realPart, num1.imagPart + num2.imagPart);
    }

    /**
     * Method to subtract two complex numbers.
     * @param num1  First number from which num2 will be subtracted.
     * @param num2  Second number, which will be subtracted from num1.
     * @return      Difference between num1 and num2.
     * @throws IllegalArgumentException Thrown if num1 or num2 is null.
     */
    public static Complex subtract (final Complex num1, final Complex num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");
        return new Complex(num1.realPart - num2.realPart, num1.imagPart - num2.imagPart);
    }

    /**
     * Method to multiply two complex numbers.
     * @param num1  First number to multiply.
     * @param num2  Second number to multiply.
     * @return
     * @throws IllegalArgumentException Thrown if num1 or num2 is null.
     */
    public static Complex multiply (final Complex num1, final Complex num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");

        Complex newComplex = new Complex ();
        newComplex.realPart = Complex.fixZero(num1.realPart * num2.realPart - num1.imagPart * num2.imagPart);
        newComplex.imagPart = Complex.fixZero(num1.realPart * num2.imagPart + num1.imagPart * num2.realPart);
        return newComplex;
    }

    /**
     * This method multiplies a complex number by a double. This scales both the real and imaginary parts
     * of the complex number.
     * @param num1  Complex number to be multiplied.
     * @param num2  Number by which num1 is to be scaled.
     * @return  Product of num1 * num2, with both real and imaginary parts of num1 multiplied by num2.
     * @throws IllegalArgumentException Thrown if num1 is null.
     */
    public static Complex multiply (final Complex num1, final double num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");

        return new Complex(num1.realPart * num2, num1.imagPart * num2);
    }

    /**
     * This method multiplies a complex number by a float. This scales both the real and imaginary parts
     * of the complex number.
     * @param num1  Complex number to be multiplied.
     * @param num2  Number by which num1 is to be scaled.
     * @return  Product of num1 * num2, with both real and imaginary parts of num1 multiplied by num2.
     * @throws IllegalArgumentException Thrown if num1 is null.
     */
    public static Complex multiply (final Complex num1, final float num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");

        return new Complex(num1.realPart * num2, num1.imagPart * num2);
    }

    /**
     * This method multiplies a complex number by an integer. This scales both the real and imaginary parts
     * of the complex number.
     * @param num1  Complex number to be multiplied.
     * @param num2  Number by which num1 is to be scaled.
     * @return  Product of num1 * num2, with both real and imaginary parts of num1 multiplied by num2.
     * @throws IllegalArgumentException Thrown if num1 is null.
     */
    public static Complex multiply (final Complex num1, final int num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");

        return new Complex(num1.realPart * num2, num1.imagPart * num2);
    }

    /**
     * This method divides a complex number by another complex number.
     * @param num1  Complex number to be divided.
     * @param num2  Number by which num1 s to be divided.
     * @return  Quotient of num1 / num2.
     * @throws IllegalArgumentException Thrown if num1 is null or num2 is 0.
     */
    public static Complex divide (final Complex num1, final Complex num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");
        if (num2.isZero()) throw new IllegalArgumentException("num2 == 0");

        double divisor = multiply(num2, num2.conjugate()).realPart;
        Complex result = multiply(num1, num2.conjugate());
        result.realPart = Complex.fixZero(result.realPart / divisor);
        result.imagPart = Complex.fixZero(result.imagPart / divisor);
        return result;
    }

    /**
     * This method divides a complex number by a double. This scales both the real and imaginary parts
     * of the complex number.
     * @param num1  Complex number to be divided.
     * @param num2  Number by which num1 is to be scaled.
     * @return  Product of num1 / num2, with both real and imaginary parts of num1 divided by num2.
     * @throws IllegalArgumentException Thrown if num1 is null or num2 is 0.
     */
    public static Complex divide (final Complex num1, final double num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == 0.0) throw new IllegalArgumentException("num2 == 0");

        return new Complex(num1.realPart / num2, num1.imagPart / num2);
    }

    /**
     * This method divides a complex number by a float. This scales both the real and imaginary parts
     * of the complex number.
     * @param num1  Complex number to be divided.
     * @param num2  Number by which num1 is to be scaled.
     * @return  Product of num1 / num2, with both real and imaginary parts of num1 divided by num2.
     * @throws IllegalArgumentException Thrown if num1 is null or num2 is 0.
     */
    public static Complex divide (final Complex num1, final float num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == 0.0) throw new IllegalArgumentException("num2 == 0");

        return new Complex(num1.realPart / (double)num2, num1.imagPart / (double)num2);
    }

    /**
     * This method divides a complex number by an integer. This scales both the real and imaginary parts
     * of the complex number.
     * @param num1  Complex number to be divided.
     * @param num2  Number by which num1 is to be scaled.
     * @return  Product of num1 / num2, with both real and imaginary parts of num1 divided by num2.
     * @throws IllegalArgumentException Thrown if num1 is null or num2 is 0.
     */
    public static Complex divide (final Complex num1, final int num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == 0) throw new IllegalArgumentException("num2 == 0");

        return new Complex(num1.realPart / (double)num2, num1.imagPart / (double)num2);
    }

    /**
     * This method raises a complex number to a power.
     * @param theNum    Complex number to raise to a power.
     * @param thePow    Power to which to raise theNum.
     * @return  Result of raising theNum to thePow (i.e., theNum^thePow).
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static Complex pow (final Complex theNum, final double thePow) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null.");

        return theNum.toPolarCoordinate().pow(thePow).toComplex();
    }

    /**
     * This method converts a complex number into its polar coordinate.
     * @param theComplex    Complex number to convert to polar coordinates.
     * @return              Polar coordinate representation of the complex number.
     * @throws IllegalArgumentException Thrown if theComplex is null.
     */
    public static PolarCoordinate complexToPolarCoordinate (final Complex theComplex) throws IllegalArgumentException
    {
        if (theComplex == null) throw new IllegalArgumentException("theComplex is null");

        double theAngle = 0.0;
        double theRadius = Math.sqrt(Math.pow(theComplex.realPart, 2.0) + Math.pow(theComplex.imagPart, 2.0));

        // If the radius is 0, just return a 0 polar coordinate (radius of 0 and angle of 0).

        if (theRadius == 0.0)
            return new PolarCoordinate(0.0,0.0);

        if (theComplex.getImag() > 0.0)
            theAngle = Math.atan(theComplex.getImag() / theComplex.getReal());

        if ((theComplex.getImag() < 0.0) && (theComplex.getReal() > 0.0))
            theAngle = Math.atan(theComplex.getImag() / theComplex.getReal()) + Math.PI;

        if ((theComplex.getImag() < 0.0) && (theComplex.getReal() < 0.0))
            theAngle = Math.atan(theComplex.getImag() / theComplex.getReal()) - Math.PI;

        if ((theComplex.getReal() > 0.0) && (theComplex.getImag() == 0.0))
            theAngle = 0.0;

        if ((theComplex.getReal() < 0.0) && (theComplex.getImag() == 0.0))
            theAngle = Math.PI;

        if ((theComplex.getReal() == 0.0) && (theComplex.getImag() > 0.0))
            theAngle = Math.PI/2.0;

        if ((theComplex.getReal() == 0.0) && (theComplex.getImag() < 0.0))
            theAngle = 3.0*Math.PI/2.0;

        return new PolarCoordinate (theRadius, theAngle);
    }

    /**
     * This method calculates the absolute value of the complex number (i.e., its radius).
     * @param theComplex    Number whose absolute value is to be calculated.
     * @return  Absolute value of theComplex.
     * @throws IllegalArgumentException Thrown if theComplex is null.
     */
    public static double abs(final Complex theComplex) throws IllegalArgumentException
    {
        if (theComplex == null) throw new IllegalArgumentException("theComplex is null.");

        return Math.sqrt(Math.pow(theComplex.realPart, 2.0) + Math.pow(theComplex.imagPart, 2.0));
    }

    /**
     * This method returns the modulus of the complex number (i.e., the sum of the square of the real part
     * and the square of the imaginary part). It is the square of the absolute value.
     * @param theComplex    Number whose modulus is to be calculated.
     * @return  Modulus of theComplex.
     * @throws IllegalArgumentException Thrown if theComplex is null.
     */
    public static double modulus(final Complex theComplex) throws IllegalArgumentException
    {
        if (theComplex == null) throw new IllegalArgumentException("theComplex is null.");

        return Math.pow(theComplex.realPart, 2.0) + Math.pow(theComplex.imagPart, 2.0);
    }

    /**
     * This method returns the negative of a complex number, i.e., the number multiplied by -1.
     * @param theComplex    Complex number to be negated.
     * @return  Negative of theComplex.
     * @throws IllegalArgumentException Thrown if theComplex is null.
     */
    public static Complex negate(final Complex theComplex) throws IllegalArgumentException
    {
        if (theComplex == null) throw new IllegalArgumentException("theComplex is null.");

        double resultReal = 0.0;
        double resultImag = 0.0;

        if (theComplex.realPart != 0.0)
            resultReal = -(theComplex.realPart);
        if (theComplex.imagPart != 0.0)
            resultImag = -(theComplex.imagPart);
        return new Complex(-(theComplex.realPart), -(theComplex.imagPart));
    }

    /**
     * This method converts a complex number into a string of the form: "a + b*I".
     * @param theComplex    Complex number to be converted.
     * @return  A string representation of the complex number of null if theComplex is null.
     */
    public static String toString(final Complex theComplex)
    {
        if (theComplex == null) return null;

        String theString = null;
        if (theComplex.imagPart == 0.0)
            theString = new String(theComplex.realPart + "");
        if (theComplex.imagPart > 0.0)
            theString = new String(theComplex.realPart + " + " + theComplex.imagPart + "*I");
        if (theComplex.imagPart < 0.0)
            theString = new String(theComplex.realPart + " - " + Math.abs(theComplex.imagPart) + "*I");
        return theString;
    }

    /**
     * This method decodes a string into a complex number. The string must be of one of the following formats:
     *      complex := real
     *              := real + real * I
     *              := real - real * I
     *              := real * I
     *              := real * I
     *              := real * I
     *              := I
     *              := + I
     *              := - I
     *      real    := digits
     *              := + digits
     *              := - digits
     *              := digits.digits
     *              := + digits.digits
     *              := - digits.digits
     *              := .digits
     *              := + .digits
     *              := - .digits
     *              := .digits.digits
     *              := + .digits.digits
     *              := - .digits.digits
     * Where digits are 0...9. Note that spaces normally are ignored, except that there can not be any spaces between
     * digits and decimal points.
     * @param theString String to be converted to a complex number.
     * @return  Complex number extracted from the string.
     * @throws NumberFormatException    Thrown if the string is not in one of the specified formats.
     */
    public static Complex parseComplex (final String theString) throws NumberFormatException
    {
        if (theString == null) throw new NumberFormatException("theString is null.");
        if (theString.length() == 0) throw new NumberFormatException("Attempt to parse empty string.");

        // Move through the string character-by-character, copying each valid character into a temporary working string.
        // The valid characters are: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, +, -, *, I. Spaces will be eliminated. If an invalid
        // character is detected, throw a NumberFormatException.

        String tempString = new String();
        for(int i = 0; i < theString.length(); i++)
        {
            // Get the next character in the string.

            char theChar = theString.charAt(i);

            // Check if the character is valid. At this point, a space also will be considered valid.

            boolean goodChar = false;
            if(theChar == '+') goodChar = true;
            if(theChar == '-') goodChar = true;
            if(theChar == '.') goodChar = true;
            if(theChar == '*') goodChar = true;
            if(theChar == 'I') goodChar = true;
            if((theChar >= '0') && (theChar <= '9')) goodChar = true;
            if(theChar == ' ') goodChar = true;
            if(!goodChar) throw new NumberFormatException("Illegal character in string to be parsed.");

            // Append the character to the temporary work string.

            if(theChar != ' ')
                tempString += theChar;
        }

        // Start parsing the work string for a valid complex number.

        final int IN_REAL_SIGN = 0;
        final int IN_REAL_PART_LEFT = 1;
        final int IN_REAL_PART_RIGHT = 2;
        final int IN_IMAG_PART_LEFT = 3;
        final int IN_IMAG_PART_RIGHT = 4;
        final int IN_I_PART = 5;
        final int DONE = 6;

        int    state = IN_REAL_SIGN;
        double realPart = 0.0;
        double imagPart = 0.0;
        int    realSign = 1;
        int    imagSign = 1;
        boolean realPartDigitFound = false;
        boolean imagPartDigitFound = false;
        double  realPartDecimals = 1.0;
        double  imagPartDecimals = 1.0;

        for(int i = 0; i < tempString.length(); i++)
        {
            char theChar = tempString.charAt(i);
            switch(state)
            {
                case IN_REAL_SIGN:
                    switch(theChar)
                    {
                        case '+':
                            realSign = 1;
                            state = IN_REAL_PART_LEFT;
                            break;
                        case '-':
                            realSign = -1;
                            state = IN_REAL_PART_LEFT;
                            break;
                        case '.':
                            state = IN_REAL_PART_RIGHT;
                            break;
                        case '*':
                            throw new NumberFormatException("Cannot start Complex with *.");
                        case 'I':
                            realPart = 0.0;
                            imagPart = 1.0;
                            state = DONE;
                            break;
                        default:
                            realPart = Character.getNumericValue(theChar);
                            realPartDigitFound = true;
                            state = IN_REAL_PART_LEFT;
                            break;
                    }
                    break;
                case IN_REAL_PART_LEFT:
                    switch(theChar)
                    {
                        case '+':
                            if (!realPartDigitFound) throw new NumberFormatException("No real digit found.");
                            imagSign = 1;
                            state = IN_IMAG_PART_LEFT;
                            break;
                        case '-':
                            if (!realPartDigitFound) throw new NumberFormatException("No real digit found.");
                            imagSign = -1;
                            state = IN_IMAG_PART_LEFT;
                            break;
                        case '.':
                            state = IN_REAL_PART_RIGHT;
                            break;
                        case '*':
                            if (!realPartDigitFound) throw new NumberFormatException("No imaginary digit found.");
                            imagPart = realPart;
                            imagSign = realSign;
                            realPart = 0.0;
                            state = IN_I_PART;
                            break;
                        case 'I':
                            if (realPartDigitFound)
                                throw new NumberFormatException("No * found after imaginary part before I.");
                            realPart=0.0;
                            imagSign=realSign;
                            imagPart=1.0;
                            state=DONE;
                            break;
                        default:
                            realPart = realPart * 10.0 + Character.getNumericValue(theChar);
                            realPartDigitFound = true;
                            break;
                    }
                    break;
                case IN_REAL_PART_RIGHT:
                    switch(theChar)
                    {
                        case '+':
                            if (!realPartDigitFound) throw new NumberFormatException("No real digit found.");
                            imagSign = 1;
                            state = IN_IMAG_PART_LEFT;
                            break;
                        case '-':
                            if (!realPartDigitFound) throw new NumberFormatException("No real digit found.");
                            imagSign = -1;
                            state = IN_IMAG_PART_LEFT;
                            break;
                        case '.':
                            throw new NumberFormatException("Two decimal points found in real part.");
                        case '*':
                            if (!realPartDigitFound) throw new NumberFormatException("No imaginary digit found.");
                            imagPart = realPart;
                            imagSign = realSign;
                            realPart = 0.0;
                            state = IN_I_PART;
                            break;
                        case 'I':
                            throw new NumberFormatException("No * found after imaginary part before I.");
                        default:
                            realPartDecimals /= 10.0;
                            realPart = realPart + realPartDecimals * (double)Character.getNumericValue(theChar);
                            realPartDigitFound = true;
                            break;
                    }
                    break;
                case IN_IMAG_PART_LEFT:
                    switch(theChar)
                    {
                        case '+':
                            throw new NumberFormatException("Additional plus sign found in imaginary part.");
                        case '-':
                            throw new NumberFormatException("Additional minus sign found in imaginary part.");
                        case '.':
                            state = IN_IMAG_PART_RIGHT;
                            break;
                        case '*':
                            if (!imagPartDigitFound) throw new NumberFormatException("No imaginary digit found.");
                            state = IN_I_PART;
                            break;
                        case 'I':
                            if (imagPartDigitFound)
                                throw new NumberFormatException("Additional I found in imaginary part.");
                            imagPart = 1.0;
                            imagPartDigitFound = true;
                            state = DONE;
                            break;
                        default:
                            imagPart = imagPart * 10.0 + Character.getNumericValue(theChar);
                            imagPartDigitFound = true;
                            break;
                    }
                    break;
                case IN_IMAG_PART_RIGHT:
                    switch(theChar)
                    {
                        case '+':
                            throw new NumberFormatException("Additional plus sign found in imaginary part.");
                        case '-':
                            throw new NumberFormatException("Additional minus sign found in imaginary part.");
                        case '.':
                            throw new NumberFormatException("Two decimal points found in imaginary part.");
                        case '*':
                            state = IN_I_PART;
                            break;
                        case 'I':
                            throw new NumberFormatException("No * found after imaginary part before I.");
                        default:
                            imagPartDecimals /= 10.0;
                            imagPart = imagPart + imagPartDecimals * (double)Character.getNumericValue(theChar);
                            imagPartDigitFound = true;
                            break;
                    }
                    break;
                case IN_I_PART:
                    if(theChar == 'I')
                        state = DONE;
                    else
                        throw new NumberFormatException("I expected at end of imaginary part.");
                    break;
                case DONE:
                    throw new NumberFormatException("Illegal character after end of Complex.");
            }
        }

        // Create the new complex number and return it.

        if(realPart != 0)
            realPart = (double)realSign * realPart;
        if(imagPart != 0)
            imagPart = (double)imagSign * imagPart;
//        System.out.println("realPart = " + realPart + ", imagPart = " + imagPart);
        return new Complex(realPart,imagPart);
    }

    /**
     * This method returns the precision value for determining equality for complex numbers. A number will be considered
     * to be equal to a complex real or imaginary part if it is in the range value - getPrecision()...value + getPrecision().
     * @return  double value representing the precision for determining equality.
     */
    public static double getPrecision()
    {
        return Complex.deltaPrecision;
    }

    /**
     * This method sets the precision value for determining equality for complex numbers. Note that this must be
     * greater than 0.0 and that setting it to 0.0 can cause problems when double operations (like sqrt) are used.
     * Similarly, too small a precision can cause equals() to return strange results.
     * @param newPrecision  New precision value, which must be >= 0.0.
     * @return  Old precision value.
     * @throws IllegalArgumentException Thrown if newPrecision < 0.0.
     */
    public static double setPrecision(final double newPrecision) throws IllegalArgumentException
    {
        if (newPrecision < 0.0) throw new IllegalArgumentException("newPrecision < 0.0.");
        final double returnVal = Complex.deltaPrecision;
        Complex.deltaPrecision = newPrecision;
        return returnVal;
    }

    /**
     * This method returns the real part of this complex number.
     * @return  Real part of the complex number.
     */
    public double getReal()
    {
        return realPart;
    }

    /**
     * This method returns the imaginary part of this complex number.
     * @return  Imaginary part of the complex number.
     */
    public double getImag()
    {
        return imagPart;
    }

    /**
     * This method sets the real and imaginary parts of a complex number to the values contained in another complex number.
     * @param newValue  Complex number whose values are to be set into this complex number.
     */
    public void set(final Complex newValue)
    {
        Complex.set(this, newValue);
    }

    /**
     * This method sets the real and imaginary parts of a complex number to specified values.
     * @param newRealPart   New value of the real part of the complex number.
     * @param newImagPart   New value of the imaginary part of the complex number.
     */
    public void set(final double newRealPart, final double newImagPart)
    {
        Complex.set(this, newRealPart, newImagPart);
    }

    /**
     * This method sets the real part of this complex number to a given double value.
     * @param newRealPart   New value of the real part of the complex number.
     */
    public void setReal(final double newRealPart)
    {
        realPart = Complex.fixZero(newRealPart);
    }

    /**
     * This method sets the real part of this complex number to a given float value.
     * @param newRealPart   New value of the real part of the complex number.
     */
    public void setReal(final float newRealPart)
    {
        realPart = Complex.fixZero((double)newRealPart);
    }

    /**
     * This method sets the real part of this complex number to a given int value.
     * @param newRealPart   New value of the real part of the complex number.
     */
    public void setReal(final int newRealPart)
    {
        realPart = Complex.fixZero((double)newRealPart);
    }

    /**
     * This method sets the imaginary part of this complex number to a given double value.
     * @param newImagPart   New value of the imaginary part of the complex number.
     */
    public void setImag(final double newImagPart)
    {
        imagPart = Complex.fixZero(newImagPart);
    }

    /**
     * This method sets the imaginary part of this complex number to a given float value.
     * @param newImagPart   New value of the imaginary part of the complex number.
     */
    public void setImag(final float newImagPart)
    {
        imagPart = Complex.fixZero((double)newImagPart);
    }

    /**
     * This method sets the imaginary part of this complex number to a given int value.
     * @param newImagPart   New value of the imaginary part of the complex number.
     */
    public void setImag(final int newImagPart)
    {
        imagPart = Complex.fixZero((double)newImagPart);
    }

    /**
     * This method determines if a complex number is 0.
     * @return  true if the complex number is 0, false otherwise.
     */
    public boolean isZero(){
        if ((realPart == 0.0) && (imagPart == 0.0))
            return true;
        return false;
    }

    /**
     * This method checks if a complex number is arithmetically equal to this one (i.e., their real parts are the same and
     * their imaginary parts are the same).
     * @param theNum  Number to compare to this complex number.
     * @return  true if the real parts of the two complex numbers and the imaginary parts of the two complex numbers
     * have the same value.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public boolean equals(final Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null.");
        return Complex.equals(this, theNum);
    }

    /**
     * This method checks if a double is arithmetically equal to this complex number. The real part of the complex
     * number is checked to see if it is equal to theNum, and the imaginary part is checked to see that it is 0.0.
     * @param theNum    Number to compare to this one.
     * @return  true if theNum is arithmetically equal to this complex number.
     */
    public boolean equals(final double theNum)
    {
        return Complex.equals(this, theNum);
    }

    /**
     * This method checks if a float is arithmetically equal to this complex number. The real part of the complex
     * number is checked to see if it is equal to theNum, and the imaginary part is checked to see that it is 0.0.
     * @param theNum    Number to compare to this one.
     * @return  true if theNum is arithmetically equal to this complex number.
     */
    public boolean equals(final float theNum)
    {
        return Complex.equals(this, theNum);
    }

    /**
     * This method checks if an int is arithmetically equal to this complex number. The real part of the complex
     * number is checked to see if it is equal to theNum, and the imaginary part is checked to see that it is 0.0.
     * @param theNum    Number to compare to this one.
     * @return  true if theNum is arithmetically equal to this complex number.
     */
    public boolean equals(final int theNum)
    {
        return Complex.equals(this, theNum);
    }

    /**
     * Method to return the complex conjugate of a complex number.
     * @return  Complex conjugate of this complex number.
     */
    public Complex conjugate ()
    {
        return Complex.conjugate(this);
    }

    /**
     * Method to add this complex number to another complex number and return the result.
     * @param num1  Complex number to add to this one.
     * @return      Result of adding this complex number to num1.
     */
    public Complex add (final Complex num1)
    {
        return Complex.add(this, num1);
    }

    /**
     * This method adds a double real number to this complex number.
     * @param num1  number to add to the real part of the complex number.
     * @return      Result of adding num1 to the complex number.
     */
    public Complex add (final double num1) { return Complex.add(this, new Complex (num1));}

    /**
     * This method adds a float real number to this complex number.
     * @param num1  number to add to the real part of the complex number.
     * @return      Result of adding num1 to the complex number.
     */
    public Complex add (final float num1) {return Complex.add(this, new Complex ((double)num1));}

    /**
     * This method adds an integer real number to this complex number.
     * @param num1  number to add to the real part of the complex number.
     * @return      Result of adding num1 to the complex number.
     */
    public Complex add (final int num1) {return Complex.add(this, new Complex ((double) num1));}

    /**
     * Method to subtract a complex number from this complex number and return the result.
     * @param num1  Complex number to subtract from this one.
     * @return      Difference between this complex number and num1.
     */
    public Complex subtract (final Complex num1)
    {
        return Complex.subtract(this, num1);
    }

    /**
     * This method subtract a double real number from this complex number.
     * @param num1  number to subtract from the real part of the complex number.
     * @return      Result of subtracting num1 from the complex number.
     */
    public Complex subtract (final double num1) { return Complex.subtract(this, new Complex (num1));}

    /**
     * This method subtracts a float real number from this complex number.
     * @param num1  number to subtract from the real part of the complex number.
     * @return      Result of subtractiny num1 from the complex number.
     */
    public Complex subtract (final float num1) {return Complex.subtract(this, new Complex ((double)num1));}

    /**
     * This method subtracts an integer real number from this complex number.
     * @param num1  number to subtract from the real part of the complex number.
     * @return      Result of subtracting num1 from the complex number.
     */
    public Complex subtract (final int num1) {return Complex.subtract(this, new Complex ((double) num1));}

    /**
     * Method to multiply this complex number by another complex number and return the result.
     * @param num1  Complex number to multiply by this one.
     * @return      Product found by multiplying this complex number to another one.
     */
    public Complex multiply (final Complex num1)
    {
        return Complex.multiply(this, num1);
    }

    /**
     * This method multiplies a double real number to this complex number.
     * @param num1  number to multiply to the complex number.
     * @return      Result of multiplying num1 to the complex number.
     */
    public Complex multiply (final double num1) { return Complex.multiply(this, num1);}

    /**
     * This method multiplies a float real number to this complex number.
     * @param num1  number to multiply to the complex number.
     * @return      Result of multiplying num1 to the complex number.
     */
    public Complex multiply (final float num1) {return Complex.multiply(this, num1);}

    /**
     * This method multiplies an integer real number to this complex number.
     * @param num1  number to multiply to the complex number.
     * @return      Result of multiplying num1 to the complex number.
     */
    public Complex multiply (final int num1) {return Complex.multiply(this, num1);}

    /**
     * This method divides this complex number by another complex number.
     * @param num1  Number by which to divide this complex number.
     * @return      Quotient of this complex number divided by num1.
     */
    public Complex divide (final Complex num1) { return Complex.divide(this, num1);}

    /**
     * This method divides this complex number by a double.
     * @param num1  number by which to divide this complex number.
     * @return      Result of division by num1.
     */
    public Complex divide (final double num1) { return Complex.divide(this, num1);}

    /**
     * This method divides this complex number by a float.
     * @param num1  number by which to divide this complex number.
     * @return      Result of division by num1.
     */
    public Complex divide (final float num1) {return Complex.divide(this, num1);}

    /**
     * This method divides this complex number by an integer.
     * @param num1  number by which to divide this complex number.
     * @return      Result of division by num1.
     */
    public Complex divide (final int num1) {return Complex.divide(this, num1);}

    /**
     * This method raises this complex number to a power.
     * @param thePow    Power to which this complex number is to be raised.
     * @return  Result of raising this complex number to thePow (i.e., this^thePow).
     */
    public Complex pow (final double thePow)
    {
        return Complex.pow(this, thePow);
    }

    /**
     * This method converts the complex number into its polar coordinate representation.
     * @return  The polar coordinate representation of the complex number.
     */
    public PolarCoordinate toPolarCoordinate ()
    {
        return Complex.complexToPolarCoordinate(this);
    }

    /**
     * This method returns the absolute value (i.e., radius) of this complex number.
     * @return  Absolute value of this complex number.
     */
    public double abs()
    {
        return Complex.abs(this);
    }

    /**
     * This method returns the modulus value (i.e., sum of the square of the real part and the square of the
     * imaginary part) for this complex number.
     * @return  Modulus value of this complex number.
     */
    public double modulus()
    {
        return Complex.modulus(this);
    }

    /**
     * This method returns the negative of a complex number, i.e., the number multiplied by -1.
     * @return
     */
    public Complex negate()
    {
        return Complex.negate(this);
    }

    /**
     * This method converts the complex number into a string of the form: "a + b*I".
     * @return  String representation of the complex number.
     */
    public String toString()
    {
        return Complex.toString(this);
    }
}
