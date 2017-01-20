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
//

public class ComplexMatrix
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
     * Constructor to create a matrix of complex numbers of the same dimensions and with elements with the same
     * values of another complex matrix.
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
     * This method returns an element of the matrix at a specified row and column.
     * @param row   Row of the desired element. Note that this is 0-based.
     * @param column    Column of the desired element. Note that this is 0-based.
     * @return  Element at this[row][column]
     * @throws IndexOutOfBoundsException    Thrown if row or column are out of bounds.
     */
    public Complex get(int row, int column) throws IndexOutOfBoundsException
    {
        if ((row < 0) || (row >= numRows)) throw new IndexOutOfBoundsException("row < 0 or row >= this.numRows");
        if ((column < 0) || (column >= numColumns)) throw new IndexOutOfBoundsException("column < 0 or column >= this.numColumns");

        return theData[row][column];
    }

    /**
     * This method sets an element of the matrix to a new value.
     * @param row   Row of the element to be set. Note that this is 0-based.
     * @param column    Column of the element to be set. Note that this is 0-based.
     * @param element   New value of the specified element.
     * @return  Previous value of the element.
     * @throws IndexOutOfBoundsException    Thrown if row or column are out of bounds.
     * @throws IllegalArgumentException     Thrown if element is null.
     */
    public Complex set(int row, int column, Complex element) throws IndexOutOfBoundsException, IllegalArgumentException
    {
        if ((row < 0) || (row >= numRows)) throw new IndexOutOfBoundsException("row < 0 or row >= this.numRows.");
        if ((column < 0) || (column >= numColumns)) throw new IndexOutOfBoundsException("column < 0 or column >= this.numColumns.");
        if (element == null) throw new IllegalArgumentException("element is null.");

        Complex currentValue = theData[row][column];
        theData[row][column] = element;
        return currentValue;
    }
}
