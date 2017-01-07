package qcfcs_math;

/**
 * This class implements complex numbers.
 * Created by reesede on 1/4/17.
 * @author David E. Reese
 * @version 1.2.1
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
//

public class Complex
{

    /**
     * Real part of complex number.
     */
    public double realPart;

    /**
     * Imaginary part of complex number.
     */
    public double imagPart;

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
     * This method divides two complex numbers.
     * @param num1  Dividend
     * @param num2  Divisor
     * @return      Quotiant of num1 / num2.
     * @throws  IllegalArgumentException if either parameter is null or if num2 is 0.
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
     * This method determines if a complex number is 0.
     * @return  true if the complex number is 0, false otherwise.
     */
    public boolean isZero(){
        if ((realPart == 0.0) && (imagPart == 0.0))
            return true;
        return false;
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
     * Method to subtract a complex number from this complex number and return the result.
     * @param num1  Complex number to subtract from this one.
     * @return      Difference between this complex number and num1.
     */
    public Complex subtract (Complex num1)
    {
        return Complex.subtract(this, num1);
    }

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
     * This method divides this complex number by another complex number.
     * @param num1  Number by which to divide this complex number.
     * @return      Quotient of this complex number divided by num1.
     */
    public Complex divide (Complex num1) { return Complex.divide(this, num1);}
}
