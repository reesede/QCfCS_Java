package qcfcs_toys.probability_game_01;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * This class implements an extension of JTable to handle matrices and vectors defined in the probability game.
 * Created by reesede on 2/27/2017.
 * @author David E. Reese
 * @version 3.1.1
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

        DefaultTableModel transitionTableModel = new DefaultTableModel(rowCount,columnCount);
        ProbabilityGameMatrixTableRenderer theRenderer = new ProbabilityGameMatrixTableRenderer();
        theRenderer.setHorizontalAlignment( JLabel.CENTER );

        // Initialize values in the table.

        for(int i = 0; i < rowCount; i++)
            for(int j = 0; j < columnCount; j++)
            {
                String theValue = "0";
                transitionTableModel.setValueAt(theValue,i,j);
            }

        // Set the model for the table.

        setModel(transitionTableModel);

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
    public int setTableType(int newTableType) throws IllegalArgumentException
    {
        int oldType = theTableType;

        if ((newTableType < TABLE_TYPE_BOOLEAN) || (newTableType > TABLE_TYPE_COMPLEX))
            throw new IllegalArgumentException("Invalid newTableType");
        theTableType = newTableType;

        return oldType;
    }
}
