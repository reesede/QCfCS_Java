package qcfcs_toys.probability_game_01;

import qcfcs_math.Complex;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * This class implements an extension of JTable to handle matrices and vectors defined in the probability game.
 * Created by reesede on 2/27/2017.
 * @author David E. Reese
 * @version 3.3.1
 * @since 3.1.1
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
//      20170227    D.E. Reese          Creation (Programming Drill 3.1.1)
//      20170228    D.E. Reese          Reorganized constructor.
//      20170301    D.E. Reese          Added tableEnabled, isEnabled(), setEnabled().
//      20170303    D.E. Reese          Added use of ProbabilityGameMatrixTableRenderer to handle rendering.
//                                      Added getTableType(), setTableType().
//      20170304    D.E. Reese          Added getMatrixElement(), setMatrixElement().
//      20170306    D.E. Reese          Added setValueAt() and moved format checking from ProbabilityGameMatrixTableRenderer
//                                      to there. Deleted setMatrixElement() and getMatrixElement().
//      20170307    D.E. Reese          Moved theTableModel and theRenderer to be private instance variables.
//                                      Renamed theTableModel to theTableModel. Added resizeTable().
//      20170310    D.E. Reese          Added code to setValueAt() for real numbers to enable a real table.
//      20170311    D.E. Reese          Added setDefaultRenderer() call in constructor for strings.
//      20170313    D.E. Reese          Modified setValueAt() to handle complex numbers.
//      20170318    D.E. Reese          Added checks for complex game data entry.

public class ProbabilityGameMatrixTable extends JTable
{
    /**
     * Table type indicating a table of booleans.
     */
    public static final int TABLE_TYPE_BOOLEAN = 0;

    /**
     * Table type indicating a table of integers.
     */
    public static final int TABLE_TYPE_INTEGER = 1;

    /**
     * Table type indicating a table of reals.
     */
    public static final int TABLE_TYPE_REAL = 2;

    /**
     * Table type indicating a table of complex numbers.
     */
    public static final int TABLE_TYPE_COMPLEX = 3;

    /**
     * Type of the table.
     */
    private int theTableType;

    /**
     * Preferred width of columns.
     */
    private int tableColumnWidth = 50;

    /**
     * Model used by the table.
     */
    private DefaultTableModel theTableModel;

    /**
     * Renderer used by the table.
     */
    private ProbabilityGameMatrixTableRenderer theRenderer;

    /**
     * Constructor for the table allowing specification of the type of table and number of rows and columns.
     * @param tableType Type of table (TABLE_TYPE_BOOLEAN, TABLE_TYPE_INTEGER, TABLE_TYPE_REAL, TABLE_TYPE_COMPLEX).
     * @param rowCount  Number of rows (must be > 0).
     * @param columnCount   Number of columns (must be > 0).
     * @throws IllegalArgumentException Thrown if tableType is not valid.
     */
    public ProbabilityGameMatrixTable(final int tableType, final int rowCount, final int columnCount) throws IllegalArgumentException
    {
        super(rowCount, columnCount);

        // Verify that the table type is correct and set the local value.

        if ((tableType < TABLE_TYPE_BOOLEAN) || (tableType > TABLE_TYPE_COMPLEX)) throw new IllegalArgumentException("Invalid tableType");
        theTableType = tableType;

        // Set up model and renderer.

        theTableModel = new DefaultTableModel(rowCount,columnCount);
        theRenderer = new ProbabilityGameMatrixTableRenderer();
        setDefaultRenderer(String.class, theRenderer);

        // Initialize values in the table.

        for(int i = 0; i < rowCount; i++)
            for(int j = 0; j < columnCount; j++)
            {
                String theValue = "0";
                theTableModel.setValueAt(theValue,i,j);
            }

        // Set the model for the table.

        setModel(theTableModel);

        // Set up the renderer for all cells.

        for(int i = 0; i < getColumnCount(); i++)
        {
            getColumnModel().getColumn(i).setPreferredWidth(tableColumnWidth);
            getColumnModel().getColumn(i).setCellRenderer( theRenderer );
        }

        // Clear out the header.

        setTableHeader(null);

        // Set the table border.

        setBorder(new LineBorder(Color.BLACK, 1));

        // Set the table so that it is not enabled. This can be changed later.

        setEnabled(false);
    }

    /**
     * This method returns the type of the table.
     * @return  Type of table.
     */
    public int getTableType()
    {
        return theTableType;
    }

    /**
     * This method sets the table type.
     * @param newTableType  New type of table.
     * @return  Old type of table.
     * @throws IllegalArgumentException Thrown if newTableType is not valid.
     */
    public int setTableType(final int newTableType) throws IllegalArgumentException
    {
        int oldType = theTableType;

        if ((newTableType < TABLE_TYPE_BOOLEAN) || (newTableType > TABLE_TYPE_COMPLEX))
            throw new IllegalArgumentException("Invalid newTableType");
        theTableType = newTableType;

        return oldType;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column)
    {
        Object tValue = aValue;

        // Get a string containing the existing element in the table.

        String oldValue = this.getValueAt(row, column).toString();

        // Get a complex number corresponding to the new value. If the new value does not parse to a complex
        // number, then newValue is set to null.
        Complex newComplexValue = null;
        String newValue = null;

        try
        {
            newComplexValue = Complex.parseComplex((String)tValue);
            newValue = newComplexValue.toString();
        }
        catch (NumberFormatException e)
        {
            newComplexValue = null;
            newValue = null;
        }

        // If the newValue is not null, then process it based on the type of table. If it is an invalid value, then
        // set it back to null.

        if ((newValue != null) && (newComplexValue != null))
        {
            switch(getTableType())
            {
                case TABLE_TYPE_BOOLEAN:

                    // For a boolean table, only values of 0 and 1 are allowed.

                    if(newComplexValue.equals(new Complex(1.0, 0.0)))
                        newValue = "1";
                    else if (newComplexValue.equals(new Complex(0.0,0.0)))
                        newValue = "0";
                    else
                        newValue = null;
                    break;

                case TABLE_TYPE_INTEGER:

                    // For an integer table, the imaginary part of the complex number must be 0.0, and the
                    // real part must equal the floor of the real part.

                    if(newComplexValue.getImag() == 0.0)
                    {
                        if ((newComplexValue.getReal() == Math.floor(newComplexValue.getReal())) &&
                                !Double.isInfinite(newComplexValue.getReal()))
                        {
                            int theInt = (int)newComplexValue.getReal();
                            newValue = new Integer(theInt).toString();
                        }
                        else
                            newValue = null;
                    }
                    else
                        newValue = null;
                    break;

                case TABLE_TYPE_REAL:

                    // For a real table, the imaginary part of the complex number must be 0.0.

                    if(newComplexValue.getImag() == 0.0)
                    {
                        if((newComplexValue.getReal() < 0.0) || (newComplexValue.getReal() > 1.0))
                        {
                            newValue = null;
                        }
                    }
                    else
                        newValue = null;
                    break;

                case TABLE_TYPE_COMPLEX:

                    // For a complex table, the modulus of the complex number must be between 0.0 and 1.0.

                    if(newComplexValue.modulus() > 1.00000001)
                        newValue = null;
                    break;
                default:
                    newValue = null;
                    break;
            }
        }

        // If the newValue is null, then set the value to be set in the table to it. Otherwise, set the value
        // to be set in the table to the old value.

        if (newValue != null)
        {
            tValue = newValue;
        }
        else
        {
            tValue = oldValue;
        }

        // Set the value using the super method.

        super.setValueAt(tValue, row, column);
    }

    /**
     * This method resizes the table to a given number of rows and columns.
     * @param newRows       New number of rows (must be > 0).
     * @param newColumns    New number of columns (must be > 0).
     * @throws IllegalArgumentException Thrown if newRows <= 0 or newColumns <= 0.
     */
    public void resizeTable(final int newRows, final int newColumns) throws IllegalArgumentException
    {
        // Verify the parameters.

        if(newRows<=0) throw new IllegalArgumentException("newRows <= 0.");
        if(newColumns<=0) throw new IllegalArgumentException("newColumns <= 0.");

        // Set the rows and columns in the model.

        theTableModel.setRowCount(newRows);
        theTableModel.setColumnCount(newColumns);

        // Reinitialize the data in the table.

        for(int i = 0; i < newRows; i++)
            for(int j = 0; j < newColumns; j++)
            {
                theTableModel.setValueAt(new String("0"), i, j);
            }

        for(int j=0; j<newColumns;j++)
            getColumnModel().getColumn(j).setCellRenderer(theRenderer);
    }
}
