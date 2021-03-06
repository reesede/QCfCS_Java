package qcfcs_math;

/**
 * This class implements a matrix of complex numbers.
 * Created by reesede on 1/4/2017.
 * @author David E. Reese
 * @version 2.7.1
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
//      20170118    D.E. Reese          Creation (Programming Drill 2.1.1).
//      20170119    D.E. Reese          Added set () (Programming Drill 2.1.1).
//      20170121    D.E. Reese          Added static get(), set(), and clone() methods (Programming Drill 2.1.1).
//      20170122    D.E. Reese          Added add(), subtract() (Programming Drill 2.1.1).
//      20170131    D.E. Reese          Added multiply() for two ComplexMatrices (Programming Drill 2.2.2).
//      20170201    D.E. Reese          Added code to stub multiply() of two matrices and added instance multiply().
//      20170202    D.E. Reese          Corrected bug in multiply() of two complex matrices where test for matrix2
//                                      being null tested matrix1 (resulted in NullPointerException instead of
//                                      IllegalArgumentException).
//                                      Added is1By1() and convert1By1ToScalar().
//      20170205    D.E. Reese          Added transpose(), transposeConjugate(), isSquare().
//      20170209    D.E. Reese          Added trace().
//      20170210    D.E. Reese          Added innerProduct(). Finalized method parameters.
//      20170213    D.E. Reese          Added equals(), isHermitian().
//      20170215    D.E. Reese          Added identityMatrix(), isIdentity().
//      20170216    D.E. Reese          Added isUnitary().
//      20170217    D.E. Reese          Added tensorProduct().
//      20170218    D.E. Reese          Fixed bug in tensorProduct() to get the correct indices.
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
    public ComplexMatrix(final int rows, final int columns) throws IllegalArgumentException
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
    public ComplexMatrix(final ComplexMatrix theMatrix) throws IllegalArgumentException
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
    public static Complex get (final ComplexMatrix theMatrix, final int row, final int column) throws IllegalArgumentException, IndexOutOfBoundsException
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
    public static ComplexMatrix add(final ComplexMatrix matrix1, final ComplexMatrix matrix2) throws IllegalArgumentException
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
    public static ComplexMatrix subtract(final ComplexMatrix matrix1, final ComplexMatrix matrix2) throws IllegalArgumentException
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
    public static ComplexMatrix multiply(final ComplexMatrix theMatrix, final Complex multiplier) throws IllegalArgumentException
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
    public static ComplexMatrix multiply(final ComplexMatrix theMatrix, final double multiplier) throws IllegalArgumentException
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
    public static ComplexMatrix multiply(final ComplexMatrix theMatrix, final float multiplier) throws IllegalArgumentException
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
    public static ComplexMatrix multiply(final ComplexMatrix theMatrix, final int multiplier) throws IllegalArgumentException
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
    public static ComplexMatrix multiply(final ComplexMatrix matrix1, final ComplexMatrix matrix2) throws IllegalArgumentException
    {
        if (matrix1 == null) throw new IllegalArgumentException("matrix1 is null.");
        if (matrix2 == null) throw new IllegalArgumentException("matrix2 is null.");
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
    public static ComplexMatrix negate(final ComplexMatrix theMatrix) throws IllegalArgumentException
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
    public static int getNumRows(final ComplexMatrix theMatrix) throws IllegalArgumentException
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
    public static int getNumColumns(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        return theMatrix.numColumns;
    }

    /**
     * This method determines if a ComplexMatrix is a 1x1 matrix (i.e., it's really a scalar).
     * @param theMatrix Matrix to test.
     * @return  true if theMatrix has 1 row and 1 column; false otherwise.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static boolean is1By1(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        if ((theMatrix.numRows == 1) && (theMatrix.numColumns == 1))
            return true;
        return false;
    }

    /**
     * This method converts a 1x1 ComplexMatrix into a scalar.
     * @param theMatrix Matrix to be converted into a scalar.
     * @return  Complex number set to the value of the only element in the matrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null, theMatrix is not 1x1.
     */
    public static Complex convert1By1ToScalar(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if ((theMatrix.numRows != 1) || (theMatrix.numColumns != 1))
            throw new IllegalArgumentException("theMatrix is not 1x1.");
        return new Complex(theMatrix.theData[0][0]);
    }

    /**
     * This method returns the transpose of a matrix, i.e., the result matrix is result[j,i] = theMatrix[i,j]. Note
     * that this is a deep copy, i.e., each element of the result is a copy of the source element.
     * @param theMatrix Matrix whose transpose is to be found.
     * @return  Transpose of the matrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static ComplexMatrix transpose(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        ComplexMatrix theResult = new ComplexMatrix(theMatrix.numColumns, theMatrix.numRows);
        for(int i = 0; i < theMatrix.numRows; i++)
            for(int j = 0; j < theMatrix.numColumns; j++)
                theResult.theData[j][i] = new Complex(theMatrix.theData[i][j]);
        return theResult;
    }

    /**
     * This method returns the transpose conjugate of a matrix, i.e., the result matrix is result[j,i] = theMatrix[i,j]*.
     * Note that this is a deep copy, i.e., each element of the result is a copy of the source element.
     * @param theMatrix Matrix whose transpose conjugate is to be found.
     * @return  Transpose conjugate of the matrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static ComplexMatrix transposeConjugate(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        ComplexMatrix theResult = new ComplexMatrix(theMatrix.numColumns, theMatrix.numRows);
        for(int i = 0; i < theMatrix.numRows; i++)
            for(int j = 0; j < theMatrix.numColumns; j++)
                theResult.theData[j][i] = new Complex(theMatrix.theData[i][j]).conjugate();
        return theResult;
    }

    /**
     * This method determines if a matrix is square, i.e., the number of rows equals the number of columns.
     * @param theMatrix Matrix to be tested.
     * @return  true if theMatrix is square; false otherwise.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static boolean isSquare(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if (theMatrix.numRows == theMatrix.numColumns) return true;
        return false;
    }

    /**
     * This method returns the trace of a square matrix, i.e., the sum of the diagonal elements.
     * @param theMatrix Matrix whose trace is to be found.
     * @return  Trace of the matrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null or theMatrix is not square.
     */
    public static Complex trace(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");
        if (!(theMatrix.isSquare())) throw new IllegalArgumentException("theMatrix is not square.");

        Complex result = new Complex();
        for(int i = 0; i < theMatrix.numRows; i++)
            result = result.add(theMatrix.theData[i][i]);
        return result;
    }

    /**
     * This method returns the inner product of two square complex matrices of the same size, i.e. the trace of the
     * product of multiplying the transpose conjugate of matrix1 times matrix2.
     * @param matrix1   First matrix for finding inner product.
     * @param matrix2   Second matrix for finding inner product.
     * @return  Complex which represents the inner product of the two matrices.
     * @throws IllegalArgumentException Thrown if either matrix is null, either is not square, or their dimensions are different.
     */
    public static Complex innerProduct(final ComplexMatrix matrix1, final ComplexMatrix matrix2) throws IllegalArgumentException
    {
        if (matrix1 == null) throw new IllegalArgumentException("matrix1 is null.");
        if (matrix2 == null) throw new IllegalArgumentException("matrix2 is null.");
        if (!matrix1.isSquare()) throw new IllegalArgumentException("matrix1 is not square.");
        if (!matrix2.isSquare()) throw new IllegalArgumentException("matrix2 is not square.");
        if (matrix1.numRows != matrix2.numRows) throw new IllegalArgumentException("matrix1 and matrix2 are not the same dimension.");

        return matrix1.transposeConjugate().multiply(matrix2).trace();
    }

    /**
     * This method determines if two matrices are mathematically equal, i.e., the corresponding elements in each matrix
     * have the same values.
     * @param matrix1   First matrix to compare.
     * @param matrix2   Second matrix to compare.
     * @return  true if each element in matrix1 has the same real and imaginary values as the corresponding element in matrix2.
     * @throws IllegalArgumentException Thrown if matrix1 is null or matrix2 is null.
     */
    public static boolean equals(final ComplexMatrix matrix1, final ComplexMatrix matrix2) throws IllegalArgumentException
    {
        if(matrix1 == null) throw new IllegalArgumentException("matrix1 is null.");
        if(matrix2 == null) throw new IllegalArgumentException("matrix2 is null.");

        // If the matrices are not the same size, then they are not equal.

        if(matrix1.numRows != matrix2.numRows) return false;
        if(matrix1.numColumns != matrix2.numColumns) return false;

        // If any corresponding elements of the matrices are not equal, return false.

        for(int i = 0; i < matrix1.numRows; i++)
            for(int j = 0; j < matrix1.numColumns; j++)
                if (!(Complex.equals(matrix1.theData[i][j], matrix2.theData[i][j])))
                    return false;

        // All corresponding elements of the matrices are equal, so return true.

        return true;
    }

    /**
     * This method determines if a complex matrix is Hermitian, i.e., it equals its transpose conjugate.
     * @param theMatrix Matrix to test to determine if it is Hermitian.
     * @return  true if theMatrix is Hermitian; false otherwise.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static boolean isHermitian(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if(theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        // If the matrix is not square, it can not be Hermitian, so return false.

        if(!theMatrix.isSquare()) return false;

        // If the matrix is equal to its transpose conjugate, then it is Hermitian, so return true. Otherwise,
        // return false.

        ComplexMatrix transposedMatrix = theMatrix.transposeConjugate();
        if(theMatrix.equals(transposedMatrix))
            return true;
        return false;
    }

    /**
     * This method returns an identity matrix, i.e., a square matrix with 1's on the diagonals and 0's elsewhere.
     * @param size  Size of the matrix. This must be >= 1.
     * @return  A size X size matrix with values of 1.0 in the diagonal elements and 0.0 in other elements.
     * @throws IllegalArgumentException Thrown if size <= 0.
     */
    public static ComplexMatrix identityMatrix(final int size) throws IllegalArgumentException
    {
        if (size <= 0) throw new IllegalArgumentException("size <= 0.");

        ComplexMatrix theResult = new ComplexMatrix(size,size);
        for(int i = 0; i < size; i++)
            theResult.set(i,i,new Complex(1.0,0.0));
        return theResult;
    }

    /**
     * This method determines if a matrix is the identity matrix, i.e., it is square with diagonal elements of 1.0
     * and all off-diagonal elements of 0.0.
     * @param theMatrix Matrix to test.
     * @return  true if theMatrix is the identity matrix; false if it is not square or not an identity matrix.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static boolean isIdentity(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        if (!theMatrix.isSquare()) return false;
        for(int i = 0; i < theMatrix.numRows; i++)
            for(int j = 0; j < theMatrix.numColumns; j++)
                if(i==j)
                {
                    if (!theMatrix.get(i,j).equals(new Complex(1.0,0.0)))
                        return false;
                }
                else
                {
                    if (!theMatrix.get(i,j).equals(new Complex(0.0,0.0)))
                        return false;
                }
        return true;
    }

    /**
     * This method determines if a matrix is unitary, i.e., it is square and the product of itself and its transpose
     * conjugate is the identity matrix.
     * @param theMatrix Matrix to be tested to determine if it is unitary.
     * @return  true if the matrix is unitary, false otherwise.
     * @throws IllegalArgumentException Thrown if theMatrix is null.
     */
    public static boolean isUnitary(final ComplexMatrix theMatrix) throws IllegalArgumentException
    {
        if (theMatrix == null) throw new IllegalArgumentException("theMatrix is null.");

        if (!theMatrix.isSquare()) return false;

        return theMatrix.multiply(theMatrix.transposeConjugate()).isIdentity();
    }

    /**
     * This method calculates the tensor product of two matrices. If matrix1 is an n1 X m1 matrix and matrix2 is an
     * n2 X m2 matrix, this method will return an (n1 n2) X (m1 m2) matrix.
     * @param matrix1   First matrix to use to find the tensor product.
     * @param matrix2   Second matrix to use to find the tensor product.
     * @return  Tensor product of matrix1 and matrix2.
     * @throws IllegalArgumentException Thrown if matrix1 is null or matrix2 is null.
     */
    public static ComplexMatrix tensorProduct(final ComplexMatrix matrix1,final ComplexMatrix matrix2) throws IllegalArgumentException
    {
        if(matrix1 == null) throw new IllegalArgumentException("matrix1 is null.");
        if(matrix2 == null) throw new IllegalArgumentException("matrix2 is null.");

        ComplexMatrix theResult = new ComplexMatrix(matrix1.numRows*matrix2.numRows,
                matrix1.numColumns*matrix2.numColumns);

        for(int matrix1Row = 0; matrix1Row < matrix1.numRows; matrix1Row++)
        {
            for(int matrix1Col = 0; matrix1Col < matrix1.numColumns; matrix1Col++)
            {
                final Complex matrix1El = matrix1.get(matrix1Row,matrix1Col);
                for(int matrix2Row = 0; matrix2Row < matrix2.numRows; matrix2Row++)
                {
                    for(int matrix2Col = 0; matrix2Col < matrix2.numColumns; matrix2Col++)
                    {
                        final Complex matrix2El = matrix2.get(matrix2Row,matrix2Col);
                        final int resultRow = matrix1Row * matrix2.numRows + matrix2Row;
                        final int resultCol = matrix1Col * matrix2.numColumns + matrix2Col;
                        theResult.theData[resultRow][resultCol] = Complex.multiply(matrix1El, matrix2El);
                    }

                }
            }
        }
        return theResult;
    }

    /**
     * This method returns an element of the matrix at a specified row and column.
     * @param row   Row of the desired element. Note that this is 0-based.
     * @param column    Column of the desired element. Note that this is 0-based.
     * @return  Element at this[row][column]
     */
    public Complex get(final int row, final int column)
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
    public Complex set(final int row, final int column, final Complex element)
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
    public Complex set(final int row, final int column, final double element)
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
    public Complex set(final int row, final int column, final float element) throws IndexOutOfBoundsException
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
    public Complex set(final int row, final int column, final int element) throws IndexOutOfBoundsException
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
    public Complex set(final int row, final int column, final PolarCoordinate element)
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
    public ComplexMatrix add(final ComplexMatrix matrix1)
    {
        return ComplexMatrix.add(this, matrix1);
    }

    /**
     * This method subtract a complex matrix from this complex matrix and returns the sum.
     * @param matrix1   Matrix to subtract from this matrix.
     * @return  Differance of this matrix and matrix1.
     */
    public ComplexMatrix subtract(final ComplexMatrix matrix1)
    {
        return ComplexMatrix.subtract(this, matrix1);
    }

    /**
     * This method multiplies this complex number by a scalar, i.e., each element is multiplied by the same scalar.
     * @param multiplier    Complex scalar by which to multiply the matrix.
     * @return  Product of scalar multiplication of this matrix by multiplier.
     */
    public ComplexMatrix multiply(final Complex multiplier)
    {
        return ComplexMatrix.multiply(this, multiplier);
    }

    /**
     * This method multiplies this complex matrix by a scalar, i.e., each element is multiplied by the same scalar.
     * @param multiplier    Double scalar by which to multiply the matrix.
     * @return  Product of scalar multiplication of this matrix by multiplier.
     */
    public ComplexMatrix multiply(final double multiplier)
    {
        return ComplexMatrix.multiply(this, multiplier);
    }

    /**
     * This method multiplies this complex matrix by a scalar, i.e., each element is multiplied by the same scalar.
     * @param multiplier    Float scalar by which to multiply the matrix.
     * @return  Product of scalar multiplication of this matrix by multiplier.
     */
    public ComplexMatrix multiply(final float multiplier)
    {
        return ComplexMatrix.multiply(this, multiplier);
    }

    /**
     * This method multiplies this complex matrix by a scalar, i.e., each element is multiplied by the same scalar.
     * @param multiplier    Int scalar by which to multiply the matrix.
     * @return  Product of scalar multiplication of this matrix by multiplier.
     */
    public ComplexMatrix multiply(final int multiplier)
    {
        return ComplexMatrix.multiply(this, multiplier);
    }

    /**
     * This method multiplies a complex matrix by another complex matrix.
     * @param multiplier    Matrix by which this matrix is to be multiplied.
     * @return  Product of this matrix multiplied by multiplier.
     */
    public ComplexMatrix multiply(final ComplexMatrix multiplier)
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

    /**
     * This method determines if a ComplexMatrix is 1x1 (i.e., it's really a scalar).
     * @return  true if this matrix is 1x1; false otherwise.
     */
    public boolean is1By1()
    {
        return ComplexMatrix.is1By1(this);
    }

    /**
     * This method converts a 1x1 ComplexMatrix into a scalar (a Complex with the same value of the sole element).
     * @return  A Complex that has the same value as the sole element in the matrix.
     */
    public Complex convert1By1ToScalar()
    {
        return ComplexMatrix.convert1By1ToScalar(this);
    }

    /**
     * This method returns the transpose of the matrix.
     * @return  Transpose of the matrix.
     */
    public ComplexMatrix transpose()
    {
        return ComplexMatrix.transpose(this);
    }

    /**
     * This method returns the transpose conjugate (i.e., complex conjugate of the transpose matrix) of a matrix.
     * @return  Transpose conjugate of the matrix.
     */
    public ComplexMatrix transposeConjugate()
    {
        return ComplexMatrix.transposeConjugate(this);
    }

    /**
     * This method determines if a matrix is square, i.e., if the number of rows equals the number of columns.
     * @return  true if the matrix is square; false otherwise.
     */
    public boolean isSquare()
    {
        return ComplexMatrix.isSquare(this);
    }

    /**
     * This method returns the trace of a square matrix, i.e., the sum of the diagonal elements.
     * @return  Trace of this matrix.
     */
    public Complex trace()
    {
        return ComplexMatrix.trace(this);
    }

    /**
     * This method finds the inner product between this matrix and another matrix. Note that this matrix is
     * transposed.
     * @param matrix2   Matrix to find the inner product with this matrix.
     * @return  Inner product of this matrix and matrix2.
     */
    public Complex innerProduct(final ComplexMatrix matrix2)
    {
        return ComplexMatrix.innerProduct(this, matrix2);
    }

    /**
     * This method determines if a second matrix is mathematically equal to this matrix, i.e., corresponding elements in
     * this matrix and matrix2 have the same real and imaginary values.
     * @param matrix2   Matrix to compare to this matrix.
     * @return  true if each element in this matrix has the same real and imaginary values as the corresponding element in matrix2.
     */
    public boolean equals(final ComplexMatrix matrix2)
    {
        return ComplexMatrix.equals(this, matrix2);
    }

    /**
     * This method determines if this matrix is Hermitian, i.e., it equals its transpose conjugate.
     * @return  true if this matrix is Hermitian; false otherwise.
     */
    public boolean isHermitian()
    {
        return ComplexMatrix.isHermitian(this);
    }

    /**
     * This method determines if a ComplexMatrix is the identity matrix, i.e., a square matrix with diagonal elements
     * set to 1.0 and off-diagonal elements set to 0.0.
     * @return  true if the matrix is the identity matrix, false otherwise.
     */
    public boolean isIdentity()
    {
        return ComplexMatrix.isIdentity(this);
    }

    /**
     * This method determines if a matrix is unitary.
     * @return  true if the matrix is unitary; false otherwise.
     */
    public boolean isUnitary()
    {
        return ComplexMatrix.isUnitary(this);
    }

    /**
     * This method returns the tensor product of this matrix with another matrix.
     * @param matrix2   Matrix with which the tensor product of this matrix is to be found.
     * @return  Tensor product of this matrix and matrix2.
     */
    public ComplexMatrix tensorProduct(final ComplexMatrix matrix2)
    {
        return ComplexMatrix.tensorProduct(this, matrix2);
    }
}
