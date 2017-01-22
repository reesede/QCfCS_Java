package qcfcs_math;

/**
 * This class implements complex numbers.
 * Created by reesede on 1/4/2017.
 * @author David E. Reese
 * @version 2.1.1
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
// along with AsianFlashCard.  If not, see <http://www.gnu.org/licenses/>.
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
//

public class Complex
{

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
    public Complex(double theReal)
    {
        realPart = theReal;
        imagPart = 0.0;
    }

    /**
     * Constructor which sets the complex number to given real and imaginary parts.
     * @param theReal   Real part of the new complex number.
     * @param theImag   Imaginary part of the new complex number.
     */

    public Complex (double theReal, double theImag)
    {
        realPart = theReal;
        imagPart = theImag;
    }

    /**
     * Constructor which sets the complex number to the values derived from a polar coordinate.
     * @param theCoord  Polar coordinate whose values are used to form a new complex number.
     * @throws IllegalArgumentException Thrown if theCoord is null.
     */
    public Complex(PolarCoordinate theCoord) throws IllegalArgumentException
    {
        if(theCoord == null) throw new IllegalArgumentException("theCoord is null");
        Complex newNum = theCoord.toComplex();
        realPart = newNum.realPart;
        imagPart = newNum.imagPart;
    }

    /**
     * Constructor to create a new complex number that has the same real and imaginary parts as an existing
     * complex number. Note that this is a deep copy.
     * @param theNum    Complex number whose real and imaginary parts will be set to those of the new complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public Complex (Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        this.realPart = theNum.realPart;
        this.imagPart = theNum.imagPart;
    }

    /**
     * This method returns the real part of a complex number.
     * @param theNum    Complex number whose real part is to be returned.
     * @return          Real part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static double getReal (Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        return theNum.realPart;
    }

    /**
     * This method returns the imaginary part of a complex number.
     * @param theNum    Complex number whose imaginary part is to be returned.
     * @return          Imaginary part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static double getImag (Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        return theNum.imagPart;
    }

    /**
     * This method sets the real part of a given complex number to a given double value.
     * @param theNum        Complex number whose real part is to be changed.
     * @param newRealPart   New value of the real part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setReal (Complex theNum, double newRealPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.realPart = newRealPart;
    }

    /**
     * This method sets the real part of a given complex number to a given float value.
     * @param theNum        Complex number whose real part is to be changed.
     * @param newRealPart   New value of the real part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setReal (Complex theNum, float newRealPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.realPart = (double)newRealPart;
    }

    /**
     * This method sets the real part of a given complex number to a given int value.
     * @param theNum        Complex number whose real part is to be changed.
     * @param newRealPart   New value of the real part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setReal (Complex theNum, int newRealPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.realPart = (double)newRealPart;
    }

    /**
     * This method sets the imaginary part of a given complex number to a given double value.
     * @param theNum        Complex number whose imaginary part is to be changed.
     * @param newImagPart   New value of the imaginary part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setImag (Complex theNum, double newImagPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.imagPart = newImagPart;
    }

    /**
     * This method sets the imaginary part of a given complex number to a given float value.
     * @param theNum        Complex number whose imaginary part is to be changed.
     * @param newImagPart   New value of the imaginary part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setImag (Complex theNum, float newImagPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.imagPart = (double)newImagPart;
    }

    /**
     * This method sets the imaginary part of a given complex number to a given int value.
     * @param theNum        Complex number whose imaginary part is to be changed.
     * @param newImagPart   New value of the imaginary part of the complex number.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static void setImag (Complex theNum, int newImagPart) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        theNum.imagPart = (double)newImagPart;
    }

    /**
     * This method checks if a complex number is 0.
     * @param theNum    Complex number to check.
     * @return          True if theNum is 0; returns false otherwise.
     * @throws IllegalArgumentException
     */
    public static boolean isZero(Complex theNum) throws IllegalArgumentException {
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
    public static boolean equals(Complex num1, Complex num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");

        if (num1.realPart != num2.realPart)
            return false;
        if (num1.imagPart != num2.imagPart)
            return false;
        return true;
    }

    /**
     * This method takes the complex conjugate of a complex number.
     * @param theNum    Complex number whose conjugate is to be taken.
     * @return          Complex conjugate of theNum.
     * @throws IllegalArgumentException Thrown if theNum is null.
     */
    public static Complex conjugate (Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null");
        Complex newComplex = new Complex (theNum.realPart, theNum.imagPart);
        if (newComplex.imagPart != 0.0)
            newComplex.imagPart = - newComplex.imagPart;
        return newComplex;
    }

    /**
     * Method to add two complex numbers.
     * @param num1  First number to add.
     * @param num2  Second number to add.
     * @return      Sum of the two complex numbers.
     * @throws IllegalArgumentException Thrown if num1 or num2 is null.
     */
    public static Complex add (Complex num1, Complex num2) throws IllegalArgumentException
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
    public static Complex subtract (Complex num1, Complex num2) throws IllegalArgumentException
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
    public static Complex multiply (Complex num1, Complex num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");

        Complex newComplex = new Complex ();
        newComplex.realPart = num1.realPart * num2.realPart - num1.imagPart * num2.imagPart;
        newComplex.imagPart = num1.realPart * num2.imagPart + num1.imagPart * num2.realPart;
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
    public static Complex multiply (Complex num1, double num2) throws IllegalArgumentException
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
    public static Complex multiply (Complex num1, float num2) throws IllegalArgumentException
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
    public static Complex multiply (Complex num1, int num2) throws IllegalArgumentException
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
    public static Complex divide (Complex num1, Complex num2) throws IllegalArgumentException
    {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");
        if (num2.isZero()) throw new IllegalArgumentException("num2 == 0");

        double divisor = multiply(num2, num2.conjugate()).realPart;
        Complex result = multiply(num1, num2.conjugate());
        result.realPart /= divisor;
        result.imagPart /= divisor;
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
    public static Complex divide (Complex num1, double num2) throws IllegalArgumentException
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
    public static Complex divide (Complex num1, float num2) throws IllegalArgumentException
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
    public static Complex divide (Complex num1, int num2) throws IllegalArgumentException
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
    public static Complex pow (Complex theNum, double thePow) throws IllegalArgumentException
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
    public static PolarCoordinate complexToPolarCoordinate (Complex theComplex) throws IllegalArgumentException
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
    public static double abs(Complex theComplex) throws IllegalArgumentException
    {
        if (theComplex == null) throw new IllegalArgumentException("theComplex is null.");

        return Math.sqrt(Math.pow(theComplex.realPart, 2.0) + Math.pow(theComplex.imagPart, 2.0));
    }

    /**
     * This method returns the negative of a complex number, i.e., the number multiplied by -1.
     * @param theComplex    Complex number to be negated.
     * @return  Negative of theComplex.
     * @throws IllegalArgumentException Thrown if theComplex is null.
     */
    public static Complex negate(Complex theComplex) throws IllegalArgumentException
    {
        if (theComplex == null) throw new IllegalArgumentException("theComplex is null.");

        return new Complex(-(theComplex.realPart), -(theComplex.imagPart));
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
     * This method sets the real part of this complex number to a given double value.
     * @param newRealPart   New value of the real part of the complex number.
     */
    public void setReal(double newRealPart)
    {
        realPart = newRealPart;
    }

    /**
     * This method sets the real part of this complex number to a given float value.
     * @param newRealPart   New value of the real part of the complex number.
     */
    public void setReal(float newRealPart)
    {
        realPart = (double)newRealPart;
    }

    /**
     * This method sets the real part of this complex number to a given int value.
     * @param newRealPart   New value of the real part of the complex number.
     */
    public void setReal(int newRealPart)
    {
        realPart = (double)newRealPart;
    }

    /**
     * This method sets the imaginary part of this complex number to a given double value.
     * @param newImagPart   New value of the imaginary part of the complex number.
     */
    public void setImag(double newImagPart)
    {
        imagPart = newImagPart;
    }

    /**
     * This method sets the imaginary part of this complex number to a given float value.
     * @param newImagPart   New value of the imaginary part of the complex number.
     */
    public void setImag(float newImagPart)
    {
        imagPart = (double)newImagPart;
    }

    /**
     * This method sets the imaginary part of this complex number to a given int value.
     * @param newImagPart   New value of the imaginary part of the complex number.
     */
    public void setImag(int newImagPart)
    {
        imagPart = (double)newImagPart;
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
    public boolean equals(Complex theNum) throws IllegalArgumentException
    {
        if (theNum == null) throw new IllegalArgumentException("theNum is null.");

        if (this.realPart != theNum.realPart)
            return false;
        if (this.imagPart != theNum.imagPart)
            return false;
        return true;
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
    public Complex add (Complex num1)
    {
        return Complex.add(this, num1);
    }

    /**
     * This method adds a double real number to this complex number.
     * @param num1  number to add to the real part of the complex number.
     * @return      Result of adding num1 to the complex number.
     */
    public Complex add (double num1) { return Complex.add(this, new Complex (num1));}

    /**
     * This method adds a float real number to this complex number.
     * @param num1  number to add to the real part of the complex number.
     * @return      Result of adding num1 to the complex number.
     */
    public Complex add (float num1) {return Complex.add(this, new Complex ((double)num1));}

    /**
     * This method adds an integer real number to this complex number.
     * @param num1  number to add to the real part of the complex number.
     * @return      Result of adding num1 to the complex number.
     */
    public Complex add (int num1) {return Complex.add(this, new Complex ((double) num1));}

    /**
     * Method to subtract a complex number from this complex number and return the result.
     * @param num1  Complex number to subtract from this one.
     * @return      Difference between this complex number and num1.
     */
    public Complex subtract (Complex num1)
    {
        return Complex.subtract(this, num1);
    }

    /**
     * This method subtract a double real number from this complex number.
     * @param num1  number to subtract from the real part of the complex number.
     * @return      Result of subtracting num1 from the complex number.
     */
    public Complex subtract (double num1) { return Complex.subtract(this, new Complex (num1));}

    /**
     * This method subtracts a float real number from this complex number.
     * @param num1  number to subtract from the real part of the complex number.
     * @return      Result of subtractiny num1 from the complex number.
     */
    public Complex subtract (float num1) {return Complex.subtract(this, new Complex ((double)num1));}

    /**
     * This method subtracts an integer real number from this complex number.
     * @param num1  number to subtract from the real part of the complex number.
     * @return      Result of subtracting num1 from the complex number.
     */
    public Complex subtract (int num1) {return Complex.subtract(this, new Complex ((double) num1));}

    /**
     * Method to multiply this complex number by another complex number and return the result.
     * @param num1  Complex number to multiply by this one.
     * @return      Product found by multiplying this complex number to another one.
     */
    public Complex multiply (Complex num1)
    {
        return Complex.multiply(this, num1);
    }

    /**
     * This method multiplies a double real number to this complex number.
     * @param num1  number to multiply to the complex number.
     * @return      Result of multiplying num1 to the complex number.
     */
    public Complex multiply (double num1) { return Complex.multiply(this, num1);}

    /**
     * This method multiplies a float real number to this complex number.
     * @param num1  number to multiply to the complex number.
     * @return      Result of multiplying num1 to the complex number.
     */
    public Complex multiply (float num1) {return Complex.multiply(this, num1);}

    /**
     * This method multiplies an integer real number to this complex number.
     * @param num1  number to multiply to the complex number.
     * @return      Result of multiplying num1 to the complex number.
     */
    public Complex multiply (int num1) {return Complex.multiply(this, num1);}

    /**
     * This method divides this complex number by another complex number.
     * @param num1  Number by which to divide this complex number.
     * @return      Quotient of this complex number divided by num1.
     */
    public Complex divide (Complex num1) { return Complex.divide(this, num1);}

    /**
     * This method divides this complex number by a double.
     * @param num1  number by which to divide this complex number.
     * @return      Result of division by num1.
     */
    public Complex divide (double num1) { return Complex.divide(this, num1);}

    /**
     * This method divides this complex number by a float.
     * @param num1  number by which to divide this complex number.
     * @return      Result of division by num1.
     */
    public Complex divide (float num1) {return Complex.divide(this, num1);}

    /**
     * This method divides this complex number by an integer.
     * @param num1  number by which to divide this complex number.
     * @return      Result of division by num1.
     */
    public Complex divide (int num1) {return Complex.divide(this, num1);}

    /**
     * This method raises this complex number to a power.
     * @param thePow    Power to which this complex number is to be raised.
     * @return  Result of raising this complex number to thePow (i.e., this^thePow).
     */
    public Complex pow (double thePow)
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
     * This method returns the negative of a complex number, i.e., the number multiplied by -1.
     * @return
     */
    public Complex negate()
    {
        return Complex.negate(this);
    }
}
