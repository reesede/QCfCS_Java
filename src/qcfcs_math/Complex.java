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
//

public class Complex {

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
    public Complex () {
        realPart = 0.0;
        imagPart = 0.0;
    }

    /**
     * Constructor which sets the complex number to a real number.
     * @param theReal   Real part of the new complex number.
     */
    public Complex(double theReal){
        realPart = theReal;
        imagPart = 0.0;
    }

    /**
     * Constructor which sets the complex number to given real and imaginary parts.
     * @param theReal   Real part of the new complex number.
     * @param theImag   Imaginary part of the new complex number.
     */

    public Complex (double theReal, double theImag){
        realPart = theReal;
        imagPart = theImag;
    }

    /**
     * Method to add two complex numbers.
     * @param num1  First number to add.
     * @param num2  Second number to add.
     * @return      Sum of the two complex numbers.
     */
    public static Complex add (Complex num1, Complex num2) {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");
        return new Complex(num1.realPart + num2.realPart, num1.imagPart + num2.imagPart);
    }

    /**
     * Method to multiply two complex numbers.
     * @param num1  First number to multiply.
     * @param num2  Second number to multiply.
     * @return
     */
    public static Complex multiply (Complex num1, Complex num2) {
        if (num1 == null) throw new IllegalArgumentException("num1 is null.");
        if (num2 == null) throw new IllegalArgumentException("num2 is null.");

        Complex newComplex = new Complex ();
        newComplex.realPart = num1.realPart * num2.realPart - num1.imagPart * num2.imagPart;
        newComplex.imagPart = num1.realPart * num2.imagPart + num1.imagPart * num2.realPart;
        return newComplex;
    }

    /**
     * Method to add this complex number to another complex number and return the result.
     * @param num1  Complex number to add to this one.
     * @return      Result of adding this complex number to another one.
     */
    public Complex add (Complex num1) {
        return Complex.add(this, num1);
    }

    /**
     * Method to multiply this complex number by another complex number and return the result.
     * @param num1  Complex number to multiply by this one.
     * @return      Product found by multiplying this complex number to another one.
     */
    public Complex multiply (Complex num1) {
        return Complex.multiply(this, num1);
    }
}
