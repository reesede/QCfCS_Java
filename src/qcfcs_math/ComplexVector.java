package qcfcs_math;

/**
 * This class implements a (column) vector of complex numbers.
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
//      20170128    D.E. Reese          Added set(), get(), add(), convertComplexMatrixToVector(),
//                                      and convertComplexVectorToMatrix() methods.
//      20170204    D.E. Reese          Added transpose(), transposeConjugate(), innerProduct().
//      20170205    D.E. Reese          Added size(). Made conversion routines deep copies.
//      20170211    D.E. Reese          Added norm(). Finalized method parameters.
//      20170212    D.E. Reese          Added distance().
//

public class ComplexVector extends ComplexMatrix
{
    /**
     * Constructor to create a complex vector of a given number of elements. This is equivalent to a ComplexMatrix
     * with a given number of rows and 1 column.
     * @param rows  Number of rows in the vector.
     */
    public ComplexVector(final int rows)
    {
        super(rows,1);
    }

    /**
     * This method sets an element in a complex vector to a new complex value.
     * @param theVec    Complex vector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexVector theVec, final int index, final Complex newValue)
    {
        return theVec.set(index, 0, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new double value.
     * @param theVec    Complex vector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexVector theVec, final int index, final double newValue)
    {
        return theVec.set(index, 0, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new float value.
     * @param theVec    Complex vector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexVector theVec, final int index, final float newValue)
    {
        return theVec.set(index, 0, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new polar coordinate value.
     * @param theVec    Complex vector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexVector theVec, final int index, final PolarCoordinate newValue)
    {
        return theVec.set(index, 0, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new int value.
     * @param theVec    Complex vector whose element is to be set.
     * @param index     Index of element to be set.
     * @param newValue  New value to which the element is to be set.
     * @return  Previous value of element.
     */
    public static Complex set(final ComplexVector theVec, final int index, final int newValue)
    {
        return theVec.set(index, 0, newValue);
    }

    /**
     * This method returns the value of the element of a complex vector.
     * @param theVec    Complex vector whose element is to be returned.
     * @param index     Index of element to be return.
     * @return  Value of element.
     */
    public static Complex get(final ComplexVector theVec, final int index)
    {
        return theVec.get(index, 0);
    }

    /**
     * This method adds two complex vectors.
     * @param vector1   First vector to add.
     * @param vector2   Second vector to add.
     * @return  Sum of the two vectors.
     */
    public static ComplexVector add(final ComplexVector vector1, final ComplexVector vector2)
    {
        return ComplexVector.convertComplexMatrixToVector(ComplexMatrix.add(vector1, vector2));
    }

    /**
     * This method subtracts two complex vectors.
     * @param vector1   Vector from which vector2 is to be subtracted.
     * @param vector2   Vector to subtract from vector1.
     * @return  Difference of the two vectors.
     */
    public static ComplexVector subtract(final ComplexVector vector1, final ComplexVector vector2)
    {
        return ComplexVector.convertComplexMatrixToVector(ComplexMatrix.subtract(vector1, vector2));
    }

    /**
     * This method multiplies a vector by a complex scalar.
     * @param theVector     Vector to multiply.
     * @param theScalar     Scalar by which all elements of theVector are to be multiplied.
     * @return  Product of scalar multiplication of theVector by theScalar.
     */
    public static ComplexVector multiply(final ComplexVector theVector, final Complex theScalar)
    {
        return ComplexVector.convertComplexMatrixToVector(ComplexMatrix.multiply(theVector, theScalar));
    }

    /**
     * This method multiplies a vector by a double scalar.
     * @param theVector     Vector to multiply.
     * @param theScalar     Scalar by which all elements of theVector are to be multiplied.
     * @return  Product of scalar multiplication of theVector by theScalar.
     */
    public static ComplexVector multiply(final ComplexVector theVector, final double theScalar)
    {
        return ComplexVector.convertComplexMatrixToVector(ComplexMatrix.multiply(theVector, theScalar));
    }

    /**
     * This method multiplies a vector by a float scalar.
     * @param theVector     Vector to multiply.
     * @param theScalar     Scalar by which all elements of theVector are to be multiplied.
     * @return  Product of scalar multiplication of theVector by theScalar.
     */
    public static ComplexVector multiply(final ComplexVector theVector, final float theScalar)
    {
        return ComplexVector.convertComplexMatrixToVector(ComplexMatrix.multiply(theVector, theScalar));
    }

    /**
     * This method multiplies a vector by a int scalar.
     * @param theVector     Vector to multiply.
     * @param theScalar     Scalar by which all elements of theVector are to be multiplied.
     * @return  Product of scalar multiplication of theVector by theScalar.
     */
    public static ComplexVector multiply(final ComplexVector theVector, final int theScalar)
    {
        return ComplexVector.convertComplexMatrixToVector(ComplexMatrix.multiply(theVector, theScalar));
    }

    /**
     * This method negates a complex vector, i.e., it multiplies each element of the vector by -1.0.
     * @param theVector Vector to be negated.
     * @return  Negated vector.
     */
    public static ComplexVector negate(final ComplexVector theVector)
    {
        return ComplexVector.convertComplexMatrixToVector(ComplexMatrix.negate(theVector));
    }

    /**
     * This method converts a ComplexMatrix with 1 column into a ComplexVector. Note that the method does not copy
     * the elements of the matrix into the vector: the source matrix and the target vector will share elements.
     * @param theMatrix Source matrix.
     * @return  A ComplexVector instance with the same elements as theMatrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null or it is not 1 column.
     */
    public static ComplexVector convertComplexMatrixToVector(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if (theMatrix.getNumColumns() != 1) throw new IllegalArgumentException("theMatrix is not 1 column.");

        ComplexVector theResult = new ComplexVector(theMatrix.getNumRows());
        for(int i = 0; i < theMatrix.getNumRows(); i++)
            theResult.set(i, new Complex(theMatrix.get(i, 0)));
        return theResult;
    }

    /**
     * This method converts a ComplexVector into a ComplexMatrix with 1 column. Note that the method does not copy
     * the elements of the vector into the matrix: the source vector and the target matrix will share elements.
     * @param theVector Source vector.
     * @return  A ComplexMatrix instance with the same elements as theVector.
     * @throws IllegalArgumentException Thrown if theVector is null.
     */
    public static ComplexMatrix convertComplexVectorToMatrix(final ComplexVector theVector) throws IllegalArgumentException
    {
        if (theVector == null) throw new IllegalArgumentException("theVector is null");

        ComplexMatrix theResult = new ComplexMatrix(theVector.getNumRows(), 1);
        for(int i = 0; i < theVector.getNumRows(); i++)
            theResult.set(i, 0, new Complex(theVector.get(i)));
        return theResult;
    }

    /**
     * This method transposes a ComplexVector into a ComplexCovector.
     * @param theVector Vector to be transposed.
     * @return  ComplexCovector with elements that have the same values as the corresponding elements in theVector.
     * @throws IllegalArgumentException Thrown if theVector is null.
     */
    public static ComplexCovector transpose (final ComplexVector theVector) throws IllegalArgumentException
    {
        if (theVector == null) throw new IllegalArgumentException("theVector is null.");

        ComplexCovector result = new ComplexCovector(theVector.getNumRows());
        for (int i = 0; i < theVector.getNumRows(); i++)
            result.set(i, new Complex(theVector.get(i)));
        return result;
    }

    /**
     * This method takes the transpose conjugate of a ComplexVector into a ComplexCovector.
     * @param theVector Vector whose complex conjugate is to be returned.
     * @return  ComplexCovector with elements that are the complex conjugate of the corresponding elements in theVector.
     * @throws IllegalArgumentException Thrown if theVector is null.
     */
    public static ComplexCovector transposeConjugate (final ComplexVector theVector) throws IllegalArgumentException
    {
        if (theVector == null) throw new IllegalArgumentException("theVector is null.");

        ComplexCovector result = new ComplexCovector(theVector.getNumRows());
        for (int i = 0; i < theVector.getNumRows(); i++)
            result.set(i, new Complex(theVector.get(i).conjugate()));
        return result;
    }

    /**
     * This method takes the inner product of two ComplexVectors. The product is the sum of the products of the
     * elements of the conjugate transpose of the first vector with the corresponding elements of the second
     * vector.
     * @param vector1   First vector of the inner product, whose conjugate transpose will be used.
     * @param vector2   Second vector of the inner product.
     * @return  Inner product of the two vectors.
     * @throws IllegalArgumentException Thrown if vector1 is null, vector2 is null, or the two vectors are not the same size.
     */
    public static Complex innerProduct(final ComplexVector vector1, final ComplexVector vector2) throws IllegalArgumentException
    {
        if (vector1 == null) throw new IllegalArgumentException("vector1 is null.");
        if (vector2 == null) throw new IllegalArgumentException("vector2 is null.");
        if (vector1.getNumRows() != vector2.getNumRows()) throw new IllegalArgumentException("vector1 and vector2 are not the same size.");

        Complex theResult = new Complex();
        ComplexCovector covector1 = vector1.transposeConjugate();

        for(int i = 0; i < vector1.getNumRows(); i++)
            theResult = theResult.add(covector1.get(i).multiply(vector2.get(i)));
        return (theResult);
    }

    /**
     * This method returns the size (number of elements) of a ComplexVector.
     * @param theVector ComplexVector whose size is to be found.
     * @return  Size of the vector, i.e., the number of rows.
     * @throws IllegalArgumentException Thrown if theVector is null.
     */
    public static int size(final ComplexVector theVector) throws IllegalArgumentException
    {
        if (theVector == null) throw new IllegalArgumentException("theVector is null.");
        return theVector.getNumRows();
    }

    /**
     * This method calculates the norm of a complex vector, i.e., the square root of the inner product of the
     * vector and itself (which should be real).
     * @param theVector Vector whose norm is to be calculated.
     * @return  The norm of the vector.
     * @throws IllegalArgumentException Thrown if theVector is null.
     */
    public static double norm (final ComplexVector theVector) throws IllegalArgumentException
    {
        if (theVector == null) throw new IllegalArgumentException("theVector is null.");
        Complex result = ComplexVector.innerProduct(theVector, theVector);
        return Math.sqrt(result.getReal());
    }

    /**
     * This method finds the distance between two vector, i.e., the norm of the difference between the two covectors.
     * @param vector1 First vector to use when finding distance.
     * @param vector2 Second vector to use when finding distance.
     * @return  Distance between the two vectors.
     * @throws IllegalArgumentException Thrown if vector1 is null, vector2 is null, or the two vectors have different sizes.
     */
    public static double distance(final ComplexVector vector1, final ComplexVector vector2) throws IllegalArgumentException
    {
        if (vector1 == null) throw new IllegalArgumentException("vector1 is null.");
        if (vector2 == null) throw new IllegalArgumentException("vector2 is null.");
        if (vector1.size() != vector2.size()) throw new IllegalArgumentException("vector1 and vector2 are not the same size.");

        return vector1.subtract(vector2).norm();
    }

    /**
     * This method sets an element in a complex vector to a new complex value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final Complex newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new double value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final double newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new float value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final float newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new int value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final int newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new polar coordinate value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(final int index, final PolarCoordinate newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method returns the value of a given element in a complex vector.
     * @param index Index of the element to return.
     * @return  Value of the indexed element.
     */
    public Complex get(final int index)
    {
        return ComplexVector.get(this, index);
    }

    /**
     * This method adds a vector to this vector and returns the sum.
     * @param theVec    Vector to add to this vector.
     * @return  Sum of this vector and theVec.
     */
    public ComplexVector add (final ComplexVector theVec)
    {
        return ComplexVector.add(this, theVec);
    }

    /**
     * This method subtracts a vector from this vector and returns the difference.
     * @param theVec    Vector to subtract from this vector.
     * @return  Difference of this vector and theVec.
     */
    public ComplexVector subtract (final ComplexVector theVec)
    {
        return ComplexVector.subtract(this, theVec);
    }

    /**
     * This method performs scalar multiplication of a complex vector with a complex scalar.
     * @param theScalar Scalar by which this complex vector is to be multiplied.
     * @return  Product of scalar multiplication of the vector and theScalar.
     */
    public ComplexVector multiply (final Complex theScalar)
    {
        return ComplexVector.multiply(this, theScalar);
    }

    /**
     * This method performs scalar multiplication of a complex vector with a double scalar.
     * @param theScalar Scalar by which this complex vector is to be multiplied.
     * @return  Product of scalar multiplication of the vector and theScalar.
     */
    public ComplexVector multiply (final double theScalar)
    {
        return ComplexVector.multiply(this, theScalar);
    }

    /**
     * This method performs scalar multiplication of a complex vector with a float scalar.
     * @param theScalar Scalar by which this complex vector is to be multiplied.
     * @return  Product of scalar multiplication of the vector and theScalar.
     */
    public ComplexVector multiply (final float theScalar)
    {
        return ComplexVector.multiply(this, theScalar);
    }

    /**
     * This method performs scalar multiplication of a complex vector with a complex scalar.
     * @param theScalar Scalar by which this complex vector is to be multiplied.
     * @return  Product of scalar multiplication of the vector and theScalar.
     */
    public ComplexVector multiply (final int theScalar)
    {
        return ComplexVector.multiply(this, theScalar);
    }

    /**
     * This method negates a complex vector, i.e., it multiplies each element of the vector by -1.0.
     * @return  Negated vector.
     */
    public ComplexVector negate()
    {
        return ComplexVector.negate(this);
    }

    /**
     * This method returns a complex matrix of the same size as the complex vector and with the same elements.
     * @return  A ComplexMatrix with the same elements as the source ComplexVector.
     */
    public ComplexMatrix convertComplexVectorToMatrix()
    {
        return ComplexVector.convertComplexVectorToMatrix(this);
    }

    /**
     * This method returns the transpose of this ComplexVector.
     * @return  ComplexCovector with elements that have the values of the corresponding elements in this ComplexVector.
     */
    public ComplexCovector transpose()
    {
        return ComplexVector.transpose(this);
    }

    /**
     * This method returns the transpose conjugate of this ComplexVector.
     * @return  ComplexCovector with elements that have the complex conjugate of the corresponding elements in this ComplexVector.
     */
    public ComplexCovector transposeConjugate()
    {
        return ComplexVector.transposeConjugate(this);
    }

    /**
     * This method takes the inner product of this vector and another. The product is the sum of the products of the
     * elements of the conjugate transpose of this vector with the corresponding elements of the second
     * vector.
     * @param theVector   Vector to be used to find the inner product with this vector.
     * @return  Inner product of the two vectors.
     * @throws IllegalArgumentException Thrown if vector1 is null, vector2 is null, or the two vectors are not the same size.
     */

    public Complex innerProduct(final ComplexVector theVector)
    {
        return ComplexVector.innerProduct(this, theVector);
    }

    /**
     * This method returns the norm of the complex vector.
     * @return  Norm of the vector, i.e., the square root of the inner product of the vector with itself (which should be real).
     */
    public double norm()
    {
        return ComplexVector.norm(this);
    }

    /**
     * This method returns the size (number of rows or elements) of this ComplexVector.
     * @return  Size of the vector (i.e., number of elements or number of rows).
     */
    public int size()
    {
        return ComplexVector.size(this);
    }

    /**
     * This method finds the distance between this vector and another vector.
     * @param theVector   Vector to find the distance from this vector.
     * @return  Distance between this vector and theVector.
     */
    public double distance(final ComplexVector theVector)
    {
        return ComplexVector.distance(this, theVector);
    }
}
