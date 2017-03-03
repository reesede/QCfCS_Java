package qcfcs_toys.probability_game_01;

import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;

/**
 * This class extends the DefaultTableCellRenderer to handle rendering for the ProbabilityGameMatrixTable.
 * Created by reesede on 3/3/2017.
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
//      20170303    D.E. Reese          Creation (Programming Drill 3.1.1)

public class ProbabilityGameMatrixTableRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column)
    {
        // First format the cell value as required

        // value = formatter.format((Number)value);

        if (!ProbabilityGameMatrixTable.class.isInstance(table))
            throw new IllegalArgumentException("table is not an instance of ProbabilityGameMatrixtable");
        ProbabilityGameMatrixTable theTable = (ProbabilityGameMatrixTable)table;

        // Process value in the table based on the type of the table.

        if (theTable.getTableType() == ProbabilityGameMatrixTable.TABLE_TYPE_BOOLEAN)
        {
        }

        // And pass it on to parent class

        return super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column );
    }
}
