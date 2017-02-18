package qcfcs_toys.probability_game_01;

import javax.swing.*;

/**
 * This class implements the main program for the probability games from chapter 3 of "Quantum Computing for
 * Computer Scientists".
 * Created by reesede on 2/18/2017.
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
//      20170218    D.E. Reese          Creation (Programming Drill 3.1.1)

public class ProbabilityGame01
{
    /**
     * Main frame for game.
     */
    public static ProbabilityGame01MainFrame theMainFrame;

    /**
     * Main program.
     * @param args  Arguments to be sent to main program.
     */
    public static void main(String[] args)
    {
        // Kick off the user interface.

        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        ProbabilityGame01.theMainFrame = new ProbabilityGame01MainFrame("Probability Game 01");
                    }
                }
        );
    }
}
