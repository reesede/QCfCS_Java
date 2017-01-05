package qcfcs_math;

/**
 * Created by reesede on 1/4/17.
 * @author David E. Reese
 * @version 1.1.1
 */
public class Complex {

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
        return new Complex(num1.realPart + num2.realPart, num1.imagPart + num2.imagPart);
    }

    /**
     * Method to multiply two complex numbers.
     * @param num1  First number to multiply.
     * @param num2  Second number to multiply.
     * @return
     */
    public static Complex multiply (Complex num1, Complex num2) {
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

    public Complex multiply (Complex num1) {
        return Complex.multiply(this, num1);
    }
}
