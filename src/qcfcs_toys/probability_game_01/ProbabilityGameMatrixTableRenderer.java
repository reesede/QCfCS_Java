package qcfcs_toys.probability_game_01;

import qcfcs_math.Complex;

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
//      20170306    D.E. Reese          Deleted getTableCellRendererComponent(). Added constructor.

public class ProbabilityGameMatrixTableRenderer extends DefaultTableCellRenderer
{
    /**
     * Default Constructor.
     */
    public ProbabilityGameMatrixTableRenderer()
    {
        super();

        // Set the horizontal alignment.

        setHorizontalAlignment( JLabel.CENTER );
    }
}
