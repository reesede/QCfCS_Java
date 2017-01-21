package qcfcs_math;

import java.util.ArrayList;

/**
 * This class implements a matrix of complex numbers.
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
//      20170118    D.E. Reese          Creation (Programming Drill 2.1.1).
//      20170119    D.E. Reese          Added set () (Programming Drill 2.1.1).
//      20170121    D.E. Reese          Added static get(), set(), and clone() methods (Programming Drill 2.1.1).
//

public class ComplexMatrix implements Cloneable
{
    /**
     * Number of rows in the matrix.
     */
    private int numRows;

    /**
     * Number of columns in the matrix.
     */
    private int numColumns;

    /**
     * Array of data contained in the matrix.
     */
    private Complex theData[][];

    /**
     * Constructor to create a matrix of complex numbers. All entries in the array will be set to 0.
     * @param rows  Number of rows in matrx.
     * @param columns   Number of columns in matrix.
     * @throws IllegalArgumentException Thrown if rows <= 0 or columns <= 0.
     */
    public ComplexMatrix(int rows, int columns) throws IllegalArgumentException
    {
        // Verify that the rows and columns are valid.

        if (rows <= 0) throw new IllegalArgumentException("rows <= 0.");
        if (columns <= 0) throw new IllegalArgumentException("columns <= 0.");

        // Set the local numbers of rows and columns.

        numRows = rows;
        numColumns = columns;

        // Create the matrix objects.

        theData = new Complex[rows][columns];
        for(int i = 0; i < numRows; i++)
            for(int j = 0; j < numColumns; j++)
                theData[i][j] = new Complex();
    }

    /**
     * Constructor to create a matrix of complex numbers of the same dimensions and elements of another
     * complex matrix. This makes a shallow copy of the matrix (i.e., the elements of the original and
     * new matrix will be the same, not copies).
     * @param theMatrix Matrix of complex numbers to be copied to the new matrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null or has internal data inconsistencies.
     */
    public ComplexMatrix(ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        // Verify that theMatrix is correct.
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if (theMatrix.numRows <= 0) throw new IllegalArgumentException("theMatrix.numRows <= 0.");
        if (theMatrix.numColumns <= 0) throw new IllegalArgumentException("theMatrix.theColumns <= 0.");
        if (theMatrix.theData == null) throw new IllegalArgumentException("theMatrix.theData is null.");

        numRows = theMatrix.numRows;
        numColumns = theMatrix.numColumns;

        theData = new Complex[numRows][numColumns];
        for(int i = 0; i < numRows; i++)
            for(int j = 0; j < numColumns; j++)
                theData[i][j] = new Complex(theMatrix.theData[i][j]);
    }

    /**
     * This method returns an element of a ComplexMatrix at a specified row and column.
     * @param theMatrix Matrix whose element is to be returned.
     * @param row   Row of desired element. Note that this is 0-based.
     * @param column    Column of desired element. Note that this is 0-based.
     * @return  Element in theMatrix at [row][column]
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     * @throws IndexOutOfBoundsException    Thrown if row or column are out of bounds.
     */
    public static Complex get (ComplexMatrix theMatrix, int row, int column) throws IllegalArgumentException, IndexOutOfBoundsException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if ((row < 0) || (row >= theMatrix.numRows)) throw new IndexOutOfBoundsException("row < 0 or row >= theMatrix.numRows");
        if ((column < 0) || (column >= theMatrix.numColumns)) throw new IndexOutOfBoundsException("column < 0 or column >= theMatrix.numColumns");

        return theMatrix.theData[row][column];
    }

    /**
     * This method sets an element in a complex matrix to a new complex number.
     * @param theMatrix Matrix whose element is to be set.
     * @param row   Row of the element to be set. Note that this is 0-based.
     * @param column    Column of the element to be set. Note that this is 0-based.
     * @param element   New value of the specified element.
     * @return  Previous value of the element.
     * @throws IndexOutOfBoundsException    Thrown if row or column are out of bounds.
     * @throws IllegalArgumentException     Thrown if element is null.
     */
    public static Complex set(ComplexMatrix theMatrix, int row, int column, Complex element) throws IllegalArgumentException, IndexOutOfBoundsException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if ((row < 0) || (row >= theMatrix.numRows)) throw new IndexOutOfBoundsException("row < 0 or row >= theMatrix.numRows.");
        if ((column < 0) || (column >= theMatrix.numColumns)) throw new IndexOutOfBoundsException("column < 0 or column >= theMatrix.numColumns.");
        if (element == null) throw new IllegalArgumentException("element is null.");

        Complex currentValue = theMatrix.theData[row][column];
        theMatrix.theData[row][column] = element;
        return currentValue;

    }

    /**
     * This method returns an element of the matrix at a specified row and column.
     * @param row   Row of the desired element. Note that this is 0-based.
     * @param column    Column of the desired element. Note that this is 0-based.
     * @return  Element at this[row][column]
     */
    public Complex get(int row, int column)
    {
        return ComplexMatrix.get(this, row, column);
    }

    /**
     * This method sets an element of the matrix to a new value.
     * @param row   Row of the element to be set. Note that this is 0-based.
     * @param column    Column of the element to be set. Note that this is 0-based.
     * @param element   New value of the specified element.
     * @return  Previous value of the element.
     */
    public Complex set(int row, int column, Complex element)
    {
        return ComplexMatrix.set(this, row, column, element);
    }

    /**
     * This method sets an element of the matrix to a new complex number derived from a double (i.e., the imaginary
     * part is 0.0).
     * @param row   Row of the element to be set. Note that this is 0-based.
     * @param column    Column of the element to be set. Note that this is 0-based.
     * @param element   New value of the specified element.
     * @return  Previous value of the element.
     */
    public Complex set(int row, int column, double element)
    {
        return ComplexMatrix.set(this, row, column, new Complex(element));
    }

    /**
     * This method sets an element of the matrix to a new complex number derived from a float (i.e., the imaginary
     * part is 0.0).
     * @param row   Row of the element to be set. Note that this is 0-based.
     * @param column    Column of the element to be set. Note that this is 0-based.
     * @param element   New value of the specified element.
     * @return  Previous value of the element.
     */
    public Complex set(int row, int column, float element) throws IndexOutOfBoundsException
    {
        return ComplexMatrix.set(this, row, column, new Complex(element));
    }

    /**
     * This method sets an element of the matrix to a new complex number derived from an int (i.e., the imaginary
     * part is 0.0).
     * @param row   Row of the element to be set. Note that this is 0-based.
     * @param column    Column of the element to be set. Note that this is 0-based.
     * @param element   New value of the specified element.
     * @return  Previous value of the element.
     */
    public Complex set(int row, int column, int element) throws IndexOutOfBoundsException
    {
        return ComplexMatrix.set(this, row, column, new Complex(element));
    }

    /**
     * This method sets an element of the matrix to a new complex number derived from a polar coordinate.
     * @param row   Row of the element to be set. Note that this is 0-based.
     * @param column    Column of the element to be set. Note that this is 0-based.
     * @param element   New value of the specified element.
     * @return  Previous value of the element.
     */
    public Complex set(int row, int column, PolarCoordinate element)
    {
        return ComplexMatrix.set(this, row, column, new Complex(element));
    }

    /**
     * This method clones the complex matrix. Note that this is a deep copy, i.e., all of the
     * elements of the matrix are copied from the original matrix, not just set to the values
     * of the existing matrix.
     * @return  A deep copy of the complex matrix.
     * @throws IllegalArgumentException Thrown if there is an error in the data for the existing matrix (i.e.,
     * the row or column is out of bounds or the data for the matrix does not exist).
     */
    public ComplexMatrix clone() throws IllegalArgumentException
    {
        if (this.numRows <= 0) throw new IllegalArgumentException("this.numRows <= 0.");
        if (this.numColumns <= 0) throw new IllegalArgumentException("this.theColumns <= 0.");
        if (this.theData == null) throw new IllegalArgumentException("this.theData is null.");
        ComplexMatrix newMatrix = new ComplexMatrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++)
                newMatrix.theData[i][j] = new Complex(this.theData[i][j].getReal(), this.theData[i][j].getImag());
        return newMatrix;
    }
}
