package qcfcs_math;

/**
 * This class implements a (column) vector of complex numbers.
 * Created by reesede on 1/4/2017.
 * @author David E. Reese
 * @version 2.1.1
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
// along with AsianFlashCard.  If not, see <http://www.gnu.org/licenses/>.
//
// History:
//      20170122    D.E. Reese          Creation (Programming Drill 2.1.1).
//      20170128    D.E. Reese          Added set(), get(), add(), convertComplexMatrixToVector(),
//                                      and convertComplexVectorToMatrix() methods.
//

public class ComplexVector extends ComplexMatrix
{
    /**
     * Constructor to create a complex vector of a given number of elements. This is equivalent to a ComplexMatrix
     * with a given number of rows and 1 column.
     * @param rows  Number of rows in the vector.
     */
    public ComplexVector(int rows)
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
    public static Complex set(ComplexVector theVec, int index, Complex newValue)
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
    public static Complex set(ComplexVector theVec, int index, double newValue)
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
    public static Complex set(ComplexVector theVec, int index, float newValue)
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
    public static Complex set(ComplexVector theVec, int index, PolarCoordinate newValue)
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
    public static Complex set(ComplexVector theVec, int index, int newValue)
    {
        return theVec.set(index, 0, newValue);
    }

    /**
     * This method returns the value of the element of a complex vector.
     * @param theVec    Complex vector whose element is to be returned.
     * @param index     Index of element to be return.
     * @return  Value of element.
     */
    public static Complex get(ComplexVector theVec, int index)
    {
        return theVec.get(index, 0);
    }

    /**
     * This method adds two complex vectors.
     * @param vector1   First vector to add.
     * @param vector2   Second vector to add.
     * @return  Sum of the two vectors.
     */
    public static ComplexVector add(ComplexVector vector1, ComplexVector vector2)
    {
        return ComplexVector.convertComplexMatrixToVector(ComplexMatrix.add(vector1, vector2));
    }

    /**
     * This method converts a ComplexMatrix with 1 column into a ComplexVector. Note that the method does not copy
     * the elements of the matrix into the vector: the source matrix and the target vector will share elements.
     * @param theMatrix Source matrix.
     * @return  A ComplexVector instance with the same elements as theMatrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null or it is not 1 column.
     */
    public static ComplexVector convertComplexMatrixToVector(ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if (theMatrix.getNumColumns() != 1) throw new IllegalArgumentException("theMatrix is not 1 column.");

        ComplexVector theResult = new ComplexVector(theMatrix.getNumRows());
        for(int i = 0; i < theMatrix.getNumRows(); i++)
            theResult.set(i, theMatrix.get(i, 0));
        return theResult;
    }

    /**
     * This method converts a ComplexVector into a ComplexMatrix with 1 column. Note that the method does not copy
     * the elements of the vector into the matrix: the source vector and the target matrix will share elements.
     * @param theVector Source vector.
     * @return  A ComplexMatrix instance with the same elements as theVector.
     * @throws IllegalArgumentException Thrown if theVector is null.
     */
    public static ComplexMatrix convertComplexVectorToMatrix(ComplexVector theVector) throws IllegalArgumentException
    {
        if (theVector == null) throw new IllegalArgumentException("theVector is null");

        ComplexMatrix theResult = new ComplexMatrix(theVector.getNumRows(), 1);
        for(int i = 0; i < theVector.getNumRows(); i++)
            theResult.set(i, 0, theVector.get(i));
        return theResult;
    }

    /**
     * This method sets an element in a complex vector to a new complex value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(int index, Complex newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new double value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(int index, double newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new float value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(int index, float newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new int value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(int index, int newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method sets an element in a complex vector to a new polar coordinate value.
     * @param index     Index of element to be set.
     * @param newValue  New value of the element to be set.
     * @return  Previous value of the element.
     */
    public Complex set(int index, PolarCoordinate newValue)
    {
        return ComplexVector.set(this, index, newValue);
    }

    /**
     * This method returns the value of a given element in a complex vector.
     * @param index Index of the element to return.
     * @return  Value of the indexed element.
     */
    public Complex get(int index)
    {
        return ComplexVector.get(this, index);
    }

    /**
     * This method adds a vector to this vector and returns the sum.
     * @param theVec    Vector to add to this vector.
     * @return  Sum of this vector and theVec.
     */
    public ComplexVector add (ComplexVector theVec)
    {
        return ComplexVector.add(this, theVec);
    }

    /**
     * This method returns a complex matrix of the same size as the complex vector and with the same elements.
     * @return  A ComplexMatrix with the same elements as the source ComplexVector.
     */
    public ComplexMatrix convertComplexVectorToMatrix()
    {
        return ComplexVector.convertComplexVectorToMatrix(this);
    }
}
