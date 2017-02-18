package qcfcs_math;

/**
 * This class implements a (row) covector of complex numbers.
 * Created by reesede on 1/22/2017.
 * @author David E. Reese
 * @version 2.4.3
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
// along with QCfCS_java.  If not, see <http://www.gnu.org/licenses/>.
//
// History:
//      20170122    D.E. Reese          Creation (Programming Drill 2.1.1).
//      20170130    D.E. Reese          Added code, copying from ComplexVector and modifying.
//      20170204    D.E. Reese          Added transpose(), transposeConjugate(), innerProduct().
//      20170205    D.E. Reese          Added size(). Converted conversion routines to deep copies.
//      20170211    D.E. Reese          Added norm(). Finalized method parameters.
//      20170212    D.E. Reese          Added innerProduct() between two covectors. Added distance().
//

public class ComplexCovector extends ComplexMatrix
{
    /**
     * Constructor to create a complex covector of a given number of elements. This is equivalent to a ComplexMatrix
     * with  1 row and a given number of columns.
     * @param columns  Number of columns in the covector.
     */

    public ComplexCovector(final int columns)
    {
        super(1, columns);
    }

    /**
     * This method sets an element in a complex covector to a new complex value.
     * @param theCovec    Complex covector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexCovector theCovec, final int index, final Complex newValue)
    {
        return theCovec.set(0, index,  newValue);
    }

    /**
     * This method sets an element in a complex covector to a new double value.
     * @param theCovec    Complex covector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexCovector theCovec, final int index, final double newValue)
    {
        return theCovec.set(0, index, newValue);
    }

    /**
     * This method sets an element in a complex covector to a new float value.
     * @param theCovec    Complex covector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexCovector theCovec, final int index, final float newValue)
    {
        return theCovec.set(0, index, newValue);
    }

    /**
     * This method sets an element in a complex covector to a new polar coordinate value.
     * @param theCovec    Complex covector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexCovector theCovec, final int index, final PolarCoordinate newValue)
    {
        return theCovec.set(0, index, newValue);
    }

    /**
     * This method sets an element in a complex covector to a new int value.
     * @param theCovec    Complex covector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexCovector theCovec, final int index, final int newValue)
    {
        return theCovec.set(0, index,  newValue);
    }

    /**
     * This method returns the value of the element of a complex covector.
     * @param theCovec    Complex covector whose element is to be returned.
     * @param index     Index of element to be return.
     * @return  Value of element.
     */
    public static Complex get(final ComplexCovector theCovec, final int index)
    {
        return theCovec.get(0, index);
    }

    /**
     * This method adds two complex covectors.
     * @param covector1   First covector to add.
     * @param covector2   Second covector to add.
     * @return  Sum of the two covectors.
     */
    public static ComplexCovector add(final ComplexCovector covector1, final ComplexCovector covector2)
    {
        return ComplexCovector.convertComplexMatrixToCovector(ComplexMatrix.add(covector1, covector2));
    }

    /**
     * This method subtracts two complex covectors.
     * @param covector1   Covector from which covector2 is to be subtracted.
     * @param covector2   Covector to subtract from covector1.
     * @return  Difference of the two covectors.
     */
    public static ComplexCovector subtract(final ComplexCovector covector1, final ComplexCovector covector2)
    {
        return ComplexCovector.convertComplexMatrixToCovector(ComplexMatrix.subtract(covector1, covector2));
    }

    /**
     * This method multiplies a covector by a complex scalar.
     * @param theCovector     Covector to multiply.
     * @param theScalar     Scalar by which all elements of theCovector are to be multiplied.
     * @return  Product of scalar multiplication of theCovector by theScalar.
     */
    public static ComplexCovector multiply(final ComplexCovector theCovector, final Complex theScalar)
    {
        return ComplexCovector.convertComplexMatrixToCovector(ComplexMatrix.multiply(theCovector, theScalar));
    }

    /**
     * This method multiplies a covector by a double scalar.
     * @param theCovector     Covector to multiply.
     * @param theScalar     Scalar by which all elements of theCovector are to be multiplied.
     * @return  Product of scalar multiplication of theCovector by theScalar.
     */
    public static ComplexCovector multiply(final ComplexCovector theCovector, final double theScalar)
    {
        return ComplexCovector.convertComplexMatrixToCovector(ComplexMatrix.multiply(theCovector, theScalar));
    }

    /**
     * This method multiplies a covector by a float scalar.
     * @param theCovector     Covector to multiply.
     * @param theScalar     Scalar by which all elements of theCovector are to be multiplied.
     * @return  Product of scalar multiplication of theCovector by theScalar.
     */
    public static ComplexCovector multiply(final ComplexCovector theCovector, final float theScalar)
    {
        return ComplexCovector.convertComplexMatrixToCovector(ComplexMatrix.multiply(theCovector, theScalar));
    }

    /**
     * This method multiplies a covector by a int scalar.
     * @param theCovector     Covector to multiply.
     * @param theScalar     Scalar by which all elements of theCovector are to be multiplied.
     * @return  Product of scalar multiplication of theCovector by theScalar.
     */
    public static ComplexCovector multiply(final ComplexCovector theCovector, final int theScalar)
    {
        return ComplexCovector.convertComplexMatrixToCovector(ComplexMatrix.multiply(theCovector, theScalar));
    }

    /**
     * This method negates a complex covector, i.e., it multiplies each element of the covector by -1.0.
     * @param theCovector Covector to be negated.
     * @return  Negated covector.
     */
    public static ComplexCovector negate(final ComplexCovector theCovector)
    {
        return ComplexCovector.convertComplexMatrixToCovector(ComplexMatrix.negate(theCovector));
    }

    /**
     * This method converts a ComplexMatrix with 1 row into a ComplexCovector. Note that the method does not copy
     * the elements of the matrix into the covector: the source matrix and the target covector will share elements.
     * @param theMatrix Source matrix.
     * @return  A ComplexCovector instance with the same elements as theMatrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null or it is not 1 column.
     */
    public static ComplexCovector convertComplexMatrixToCovector(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if (theMatrix.getNumRows() != 1) throw new IllegalArgumentException("theMatrix is not 1 row.");

        ComplexCovector theResult = new ComplexCovector(theMatrix.getNumColumns());
        for(int i = 0; i < theMatrix.getNumColumns(); i++)
            theResult.set(i, new Complex(theMatrix.get(0, i)));
        return theResult;
    }

    /**
     * This method converts a ComplexCovector into a ComplexMatrix with 1 row. Note that the method does not copy
     * the elements of the covector into the matrix: the source covector and the target matrix will share elements.
     * @param theCovector Source covector.
     * @return  A ComplexMatrix instance with the same elements as theCoector.
     * @throws IllegalArgumentException Thrown if theCovector is null.
     */
    public static ComplexMatrix convertComplexCovectorToMatrix(final ComplexCovector theCovector) throws IllegalArgumentException
    {
        if (theCovector == null) throw new IllegalArgumentException("theCovector is null");

        ComplexMatrix theResult = new ComplexMatrix(1, theCovector.getNumColumns());
        for(int i = 0; i < theCovector.getNumColumns(); i++)
            theResult.set(0, i, new Complex(theCovector.get(i)));
        return theResult;
    }

    /**
     * This method transposes a ComplexCovector into a ComplexVector.
     * @param theCovector Covector to be transposed.
     * @return  ComplexVector with elements that have the same value as the corresponding elements in theCovector.
     * @throws IllegalArgumentException Thrown if theCovector is null.
     */
    public static ComplexVector transpose (final ComplexCovector theCovector) throws IllegalArgumentException
    {
        if (theCovector == null) throw new IllegalArgumentException("theCovector is null.");

        ComplexVector result = new ComplexVector(theCovector.getNumColumns());
        for (int i = 0; i < theCovector.getNumColumns(); i++)
            result.set(i, new Complex(theCovector.get(i)));
        return result;
    }

    /**
     * This method takes the transpose conjugate of a ComplexVector into a ComplexCovector.
     * @param theCovector ComplexCovector whose complex conjugate is to be returned.
     * @return  ComplexVector with elements that are the complex conjugate of the corresponding elements in theCovector.
     * @throws IllegalArgumentException Thrown if theCovector is null.
     */
    public static ComplexVector transposeConjugate (final ComplexCovector theCovector) throws IllegalArgumentException
    {
        if (theCovector == null) throw new IllegalArgumentException("theCovector is null.");

        ComplexVector result = new ComplexVector(theCovector.getNumColumns());
        for (int i = 0; i < theCovector.getNumColumns(); i++)
            result.set(i, new Complex(theCovector.get(i).conjugate()));
        return result;
    }

    /**
     * This method takes the inner product of two covectors, i.e., the sum of the products of the first covector multiplied
     * by the complex conjugate of the corresponding element of the second covector.
     * @param covector1 First covector to use when calculating the inner product.
     * @param covector2 Second covector to use when calculating the inner product. Note that this is transposed conjugated.
     * @return  Inner product of covector1 and covector2.
     * @throws IllegalArgumentException Thrown if covector1 is null, covector2 is null, or covector1 and covector2 are not the same size.
     */
    public static Complex innerProduct(final ComplexCovector covector1, final ComplexCovector covector2) throws IllegalArgumentException
    {
        if (covector1 == null) throw new IllegalArgumentException("covector1 is null.");
        if (covector2 == null) throw new IllegalArgumentException("covector2 is null.");
        if (covector1.size() != covector2.size()) throw new IllegalArgumentException("covector1 and covector2 are not the same size");

        ComplexVector transposedCovector2 = covector2.transposeConjugate();
        Complex result = new Complex();
        for(int i = 0; i < covector1.size(); i++)
            result = result.add(covector1.get(i).multiply(transposedCovector2.get(i)));
        return result;
    }

    /**
     * This method takes the inner product of a ComplexCovector and a ComplexVector. Note that it does not take the
     * complex conjugate of the ComplexCovector.
     * @param theCovector   ComplexCovector whose inner product is to be found with theVector.
     * @param theVector ComplexVector whose inner product is to be found with theCovector.
     * @return  Inner product of theCovector and theVector (sum of the product of each corresponding element).
     * @throws IllegalArgumentException Thrown if theCovector is null, theVector is null, or theCovector and theVector are not the same size.
     */
    public static Complex innerProduct(final ComplexCovector theCovector, final ComplexVector theVector) throws IllegalArgumentException
    {
        if (theCovector == null) throw new IllegalArgumentException("theCovector is null.");
        if (theVector == null) throw new IllegalArgumentException("theVector is null.");
        if (theCovector.getNumColumns() != theVector.getNumRows()) throw new IllegalArgumentException("theCovector and theVector are not the same size.");

        Complex theResult = new Complex();

        for(int i = 0; i < theCovector.getNumColumns(); i++)
            theResult = theResult.add(theCovector.get(i).multiply(theVector.get(i)));
        return (theResult);
    }

    /**
     * This method finds the norm of a covector.
     * @param theCovector   Covector whose norm is to be found.
     * @return  Norm of the covector.
     * @throws IllegalArgumentException Thrown if theCovector is null.
     */
    public static double norm (final ComplexCovector theCovector) throws IllegalArgumentException
    {
        if (theCovector == null) throw new IllegalArgumentException("theCovector is null.");

        ComplexVector transposedCovec = theCovector.transposeConjugate();
        Complex result = ComplexCovector.innerProduct(theCovector, transposedCovec);
        return Math.sqrt(result.getReal());
    }

    /**
     * This method returns the size (number of elements) of a ComplexCovector.
     * @param theCovector ComplexCovector whose size is to be found.
     * @return  Size of the covector, i.e., the number of columns.
     * @throws IllegalArgumentException Thrown if theCovector is null.
     */
    public static int size(final ComplexCovector theCovector) throws IllegalArgumentException
    {
        if (theCovector == null) throw new IllegalArgumentException("theCovector is null.");
        return theCovector.getNumColumns();
    }

    /**
     * This method finds the distance between two covectors, i.e., the norm of the difference between the two covectors.
     * @param covector1 First covector to use when finding distance.
     * @param covector2 Second covector to use when finding distance.
     * @return  Distance between the two covectors.
     * @throws IllegalArgumentException Thrown if covector1 is null, covector2 is null, or the two covectors have different sizes.
     */
    public static double distance(final ComplexCovector covector1, final ComplexCovector covector2) throws IllegalArgumentException
    {
        if (covector1 == null) throw new IllegalArgumentException("covector1 is null.");
        if (covector2 == null) throw new IllegalArgumentException("covector2 is null.");
        if (covector1.size() != covector2.size()) throw new IllegalArgumentException("covector1 and covector2 are not the same size.");

        return covector1.subtract(covector2).norm();
    }

    /**
     * This method sets an element in a complex covector to a new complex value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final Complex newValue)
    {
        return ComplexCovector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex covector to a new double value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final double newValue)
    {
        return ComplexCovector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex covector to a new float value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final float newValue)
    {
        return ComplexCovector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex covector to a new int value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final int newValue)
    {
        return ComplexCovector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex covector to a new polar coordinate value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final PolarCoordinate newValue)
    {
        return ComplexCovector.set(this, index, newValue);
    }

    /**
     * This method returns the value of a given element in a complex covector.
     * @param index Index of the element to return.
     * @return  Value of the indexed element.
     */
    public Complex get(final int index)
    {
        return ComplexCovector.get(this, index);
    }

    /**
     * This method adds a covector to this covector and returns the sum.
     * @param theCovec    Covector to add to this covector.
     * @return  Sum of this covector and theCovec.
     */
    public ComplexCovector add (final ComplexCovector theCovec)
    {
        return ComplexCovector.add(this, theCovec);
    }

    /**
     * This method subtracts a covector from this covector and returns the difference.
     * @param theCovec    Covector to subtract from this covector.
     * @return  Difference of this covector and theCovec.
     */
    public ComplexCovector subtract (final ComplexCovector theCovec)
    {
        return ComplexCovector.subtract(this, theCovec);
    }

    /**
     * This method performs scalar multiplication of a complex covector with a complex scalar.
     * @param theScalar Scalar by which this complex covector is to be multiplied.
     * @return  Product of scalar multiplication of the covector and theScalar.
     */
    public ComplexCovector multiply (final Complex theScalar)
    {
        return ComplexCovector.multiply(this, theScalar);
    }

    /**
     * This method performs scalar multiplication of a complex covector with a double scalar.
     * @param theScalar Scalar by which this complex covector is to be multiplied.
     * @return  Product of scalar multiplication of the covector and theScalar.
     */
    public ComplexCovector multiply (final double theScalar)
    {
        return ComplexCovector.multiply(this, theScalar);
    }

    /**
     * This method performs scalar multiplication of a complex covector with a float scalar.
     * @param theScalar Scalar by which this complex covector is to be multiplied.
     * @return  Product of scalar multiplication of the covector and theScalar.
     */
    public ComplexCovector multiply (final float theScalar)
    {
        return ComplexCovector.multiply(this, theScalar);
    }

    /**
     * This method performs scalar multiplication of a complex covector with a complex scalar.
     * @param theScalar Scalar by which this complex covector is to be multiplied.
     * @return  Product of scalar multiplication of the covector and theScalar.
     */
    public ComplexCovector multiply (final int theScalar)
    {
        return ComplexCovector.multiply(this, theScalar);
    }

    /**
     * This method negates a complex covector, i.e., it multiplies each element of the covector by -1.0.
     * @return  Negated covector.
     */
    public ComplexCovector negate()
    {
        return ComplexCovector.negate(this);
    }

    /**
     * This method returns a complex matrix of the same size as the complex covector and with the same elements.
     * @return  A ComplexMatrix with the same elements as the source ComplexCovector.
     */
    public ComplexMatrix convertComplexCovectorToMatrix()
    {
        return ComplexCovector.convertComplexCovectorToMatrix(this);
    }

    /**
     * This method returns the transpose of this ComplexCovector.
     * @return  ComplexVector with elements that have the values of the corresponding elements in this ComplexCovector.
     */
    public ComplexVector transpose()
    {
        return ComplexCovector.transpose(this);
    }

    /**
     * This method returns the complex conjugate of this ComplexCovector.
     * @return  ComplexVector with elements that are the complex conjugate of the corresponding elements in this ComplexCovector.
     */
    public ComplexVector transposeConjugate()
    {
        return ComplexCovector.transposeConjugate(this);
    }

    /**
     * This method finds the inner product of this covector and another covector.
     * @param theCovector   The covector to take the inner product with this covector. Note that theCovector is transpose conjugated.
     * @return  Inner product of this covector and theCovector.
     */
    public Complex innerProduct(final ComplexCovector theCovector)
    {
        return ComplexCovector.innerProduct(this, theCovector);
    }

    /**
     * This method finds the inner product of this ComplexCovector and a ComplexVector. Note that it does not take the
     * complex conjugate of this ComplexCovector before taking the inner product.
     * @param theVector ComplexVector whose inner product is to be found with this ComplexCovector.
     * @return  Inner product of this ComplexCovector and theVector (i.e., the sum of the product of corresponding elements).
     */
    public Complex innerProduct(final ComplexVector theVector)
    {
        return ComplexCovector.innerProduct(this, theVector);
    }

    /**
     * This method returns the size (number of columns or elements) of this ComplexCovector.
     * @return  Size of the covector (i.e., number of elements or number of columns).
     */
    public int size()
    {
        return ComplexCovector.size(this);
    }

    /**
     * This method returns the norm of a covector.
     * @return  Norm of this complex covector.
     */
    public double norm()
    {
        return ComplexCovector.norm(this);
    }

    /**
     * This method finds the distance between this covector and another covector.
     * @param theCovector   Covector to find the distance from this covector.
     * @return  Distance between this covector and theCovector.
     */
    public double distance(final ComplexCovector theCovector)
    {
        return ComplexCovector.distance(this, theCovector);
    }
}
