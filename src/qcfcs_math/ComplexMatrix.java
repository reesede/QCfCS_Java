package qcfcs_math;

/**
 * This class implements a matrix of complex numbers.
 * Created by reesede on 1/4/2017.
 * @author David E. Reese
 * @version 2.2.2
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
//      20170122    D.E. Reese          Added add(), subtract() (Programming Drill 2.1.1).
//      20170131    D.E. Reese          Added multiply() for two ComplexMatrices (Programming Drill 2.2.2).
//      20170201    D.E. Reese          Added code to stub multiply() of two matrices and added instance multiply().
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
     * This method adds two complex matrices and returns the sum. The corresponding elements of each matrix are added.
     * @param matrix1   First matrix to add.
     * @param matrix2   Second matrix to add.
     * @return  New matrix with element [i][j] the sum of matrix1[i][j] + matrix2[i][j].
     * @throws IllegalArgumentException Thrown if matrix1 is null, matrix2 is null, or matrix1 and matrix2 are not the same size.
     */
    public static ComplexMatrix add(ComplexMatrix matrix1, ComplexMatrix matrix2) throws IllegalArgumentException
    {
        if (matrix1 == null) throw new IllegalArgumentException("matrix1 is null.");
        if (matrix2 == null) throw new IllegalArgumentException("matrix2 is null.");
        if (matrix1.numRows != matrix2.numRows) throw new IllegalArgumentException("matrix1 not the same size as matrix2");
        if (matrix1.numColumns != matrix2.numColumns) throw new IllegalArgumentException("matrix1 not the same size as matrix2");

        ComplexMatrix newMatrix = new ComplexMatrix(matrix1.numRows, matrix1.numColumns);
        for(int i = 0; i < matrix1.numRows; i++)
            for(int j = 0; j < matrix1.numColumns; j++)
                newMatrix.theData[i][j] = new Complex(Complex.add(matrix1.get(i,j), matrix2.get(i,j)));
        return newMatrix;
    }

    /**
     * This method subtracts two complex matrices and returns the sum. The corresponding elements of each matrix are subtracted.
     * @param matrix1   First matrix from which matrix2 is to be subtracted.
     * @param matrix2   Second matrix to subtract from matrix1.
     * @return  New matrix with element [i][j] the sum of matrix1[i][j] - matrix2[i][j].
     * @throws IllegalArgumentException Thrown if matrix1 is null, matrix2 is null, or matrix1 and matrix2 are not the same size.
     */
    public static ComplexMatrix subtract(ComplexMatrix matrix1, ComplexMatrix matrix2) throws IllegalArgumentException
    {
        if (matrix1 == null) throw new IllegalArgumentException("matrix1 is null.");
        if (matrix2 == null) throw new IllegalArgumentException("matrix2 is null.");
        if (matrix1.numRows != matrix2.numRows) throw new IllegalArgumentException("matrix1 not the same size as matrix2");
        if (matrix1.numColumns != matrix2.numColumns) throw new IllegalArgumentException("matrix1 not the same size as matrix2");

        ComplexMatrix newMatrix = new ComplexMatrix(matrix1.numRows, matrix1.numColumns);
        for(int i = 0; i < matrix1.numRows; i++)
            for(int j = 0; j < matrix1.numColumns; j++)
                newMatrix.theData[i][j] = new Complex(Complex.subtract(matrix1.get(i,j), matrix2.get(i,j)));
        return newMatrix;
    }

    /**
     * This method performs scalar multiplication on a complex matrix, i.e., multiplying each element by a scalar.
     * @param theMatrix Matrix to be multiplier by multiplier.
     * @param multiplier    Complex scalar by which to multiply each element of theMatrix.
     * @return  New matrix whose elements are the elements of theMatrix multiplied by multiplier.
     * @throws IllegalArgumentException Thrown if theMatrix is null or multiplier is null.
     */
    public static ComplexMatrix multiply(ComplexMatrix theMatrix, Complex multiplier) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if (multiplier == null) throw new IllegalArgumentException("multiplier is null.");

        ComplexMatrix newMatrix = new ComplexMatrix(theMatrix.numRows, theMatrix.numColumns);
        for(int i = 0; i < theMatrix.numRows; i++)
            for(int j = 0; j < theMatrix.numColumns; j++)
                newMatrix.theData[i][j] = theMatrix.theData[i][j].multiply(multiplier);
        return newMatrix;
    }

    /**
     * This method performs scalar multiplication on a complex matrix, i.e., multiplying each element by a scalar.
     * @param theMatrix Matrix to be multiplier by multiplier.
     * @param multiplier    Double scalar by which to multiply each element of theMatrix.
     * @return  New matrix whose elements are the elements of theMatrix multiplied by multiplier.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static ComplexMatrix multiply(ComplexMatrix theMatrix, double multiplier) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        ComplexMatrix newMatrix = new ComplexMatrix(theMatrix.numRows, theMatrix.numColumns);
        for(int i = 0; i < theMatrix.numRows; i++)
            for(int j = 0; j < theMatrix.numColumns; j++)
                newMatrix.theData[i][j] = theMatrix.theData[i][j].multiply(multiplier);
        return newMatrix;
    }

    /**
     * This method performs scalar multiplication on a complex matrix, i.e., multiplying each element by a scalar.
     * @param theMatrix Matrix to be multiplier by multiplier.
     * @param multiplier    Float scalar by which to multiply each element of theMatrix.
     * @return  New matrix whose elements are the elements of theMatrix multiplied by multiplier.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static ComplexMatrix multiply(ComplexMatrix theMatrix, float multiplier) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        ComplexMatrix newMatrix = new ComplexMatrix(theMatrix.numRows, theMatrix.numColumns);
        for(int i = 0; i < theMatrix.numRows; i++)
            for(int j = 0; j < theMatrix.numColumns; j++)
                newMatrix.theData[i][j] = theMatrix.theData[i][j].multiply(multiplier);
        return newMatrix;
    }

    /**
     * This method performs scalar multiplication on a complex matrix, i.e., multiplying each element by a scalar.
     * @param theMatrix Matrix to be multiplier by multiplier.
     * @param multiplier    Int scalar by which to multiply each element of theMatrix.
     * @return  New matrix whose elements are the elements of theMatrix multiplied by multiplier.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static ComplexMatrix multiply(ComplexMatrix theMatrix, int multiplier) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        ComplexMatrix newMatrix = new ComplexMatrix(theMatrix.numRows, theMatrix.numColumns);
        for(int i = 0; i < theMatrix.numRows; i++)
            for(int j = 0; j < theMatrix.numColumns; j++)
                newMatrix.theData[i][j] = theMatrix.theData[i][j].multiply(multiplier);
        return newMatrix;
    }

    /**
     * This method multiplies two complex matrices.
     * @param matrix1   First matrix to multiply.
     * @param matrix2   Second matrix to multiply.
     * @return  The product of matrix1 * matrix2.
     * @throws IllegalArgumentException Thrown if matrix1 is null, matrix2 is null, or the dimensions of the matrices do not allow matrix multiplication.
     */
    public static ComplexMatrix multiply(ComplexMatrix matrix1, ComplexMatrix matrix2) throws IllegalArgumentException
    {
        if (matrix1 == null) throw new IllegalArgumentException("matrix1 is null.");
        if (matrix1 == null) throw new IllegalArgumentException("matrix2 is null.");
        if (matrix1.numColumns != matrix2.numRows) throw new IllegalArgumentException("matrix1.numColumns != matrix2.numRows.");

        ComplexMatrix result = new ComplexMatrix(matrix1.numRows, matrix2.numColumns);

        for (int i = 0; i < result.numRows; i++)
            for (int j = 0; j < result.numColumns; j++)
            {
                Complex theElement = new Complex();
                for (int l = 0; l < matrix1.numColumns; l++)
                {
                    theElement = theElement.add(matrix1.get(i, l).multiply(matrix2.get(l, j)));
                }
                result.set(i, j, theElement);
            }
        return result;
    }

    /**
     * This method negates a complex matrix, i.e., multiplies each element by -1.
     * @param theMatrix Matrix to be negated.
     * @return  The arithmetic inverse of the matrix, i.e., each element multiplied by -1.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static ComplexMatrix negate(ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        ComplexMatrix newMatrix = new ComplexMatrix(theMatrix.numRows, theMatrix.numColumns);
        for(int i = 0; i < theMatrix.numRows; i++)
            for(int j = 0; j < theMatrix.numColumns; j++)
                newMatrix.theData[i][j] = theMatrix.theData[i][j].negate();
        return newMatrix;
    }

    /**
     * This method returns the number of rows in a complex matrix.
     * @param theMatrix Matrix whose number of rows is to be returned.
     * @return  Number of rows in the matrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static int getNumRows(ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        return theMatrix.numRows;
    }

    /**
     * This method returns the number of columns in a complex matrix.
     * @param theMatrix Matrix whose number of columns is to be returned.
     * @return  Number of rows in the matrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static int getNumColumns(ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        return theMatrix.numColumns;
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

    /**
     * This method adds a complex matrix to this complex matrix and returns the sum.
     * @param matrix1   Matrix to add to this matrix.
     * @return  Sum of this matrix and matrix1.
     */
    public ComplexMatrix add(ComplexMatrix matrix1)
    {
        return ComplexMatrix.add(this, matrix1);
    }

    /**
     * This method subtract a complex matrix from this complex matrix and returns the sum.
     * @param matrix1   Matrix to subtract from this matrix.
     * @return  Differance of this matrix and matrix1.
     */
    public ComplexMatrix subtract(ComplexMatrix matrix1)
    {
        return ComplexMatrix.subtract(this, matrix1);
    }

    /**
     * This method multiplies this complex number by a scalar, i.e., each element is multiplied by the same scalar.
     * @param multiplier    Complex scalar by which to multiply the matrix.
     * @return  Product of scalar multiplication of this matrix by multiplier.
     */
    public ComplexMatrix multiply(Complex multiplier)
    {
        return ComplexMatrix.multiply(this, multiplier);
    }

    /**
     * This method multiplies this complex matrix by a scalar, i.e., each element is multiplied by the same scalar.
     * @param multiplier    Double scalar by which to multiply the matrix.
     * @return  Product of scalar multiplication of this matrix by multiplier.
     */
    public ComplexMatrix multiply(double multiplier)
    {
        return ComplexMatrix.multiply(this, multiplier);
    }

    /**
     * This method multiplies this complex matrix by a scalar, i.e., each element is multiplied by the same scalar.
     * @param multiplier    Float scalar by which to multiply the matrix.
     * @return  Product of scalar multiplication of this matrix by multiplier.
     */
    public ComplexMatrix multiply(float multiplier)
    {
        return ComplexMatrix.multiply(this, multiplier);
    }

    /**
     * This method multiplies this complex matrix by a scalar, i.e., each element is multiplied by the same scalar.
     * @param multiplier    Int scalar by which to multiply the matrix.
     * @return  Product of scalar multiplication of this matrix by multiplier.
     */
    public ComplexMatrix multiply(int multiplier)
    {
        return ComplexMatrix.multiply(this, multiplier);
    }

    /**
     * This method multiplies a complex matrix by another complex matrix.
     * @param multiplier    Matrix by which this matrix is to be multiplied.
     * @return  Product of this matrix multiplied by multiplier.
     */
    public ComplexMatrix multiply(ComplexMatrix multiplier)
    {
        return ComplexMatrix.multiply(this, multiplier);
    }

    /**
     * This method returns the arithmetic inverse of this matrix, i.e., each element multiplied by -1.
     * @return
     */
    public ComplexMatrix negate()
    {
        return ComplexMatrix.negate(this);
    }

    /**
     * This method returns the number of rows in a complex matrix.
     * @return  Number of rows in the matrix.
     */
    public int getNumRows()
    {
        return this.numRows;
    }

    /**
     * This method returns the number of columns in a complex matrix.
     * @return  Number of columns in the matrix.
     */
    public int getNumColumns()
    {
        return this.numColumns;
    }
}
