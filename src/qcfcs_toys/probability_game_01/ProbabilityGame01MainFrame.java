package qcfcs_toys.probability_game_01;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This class implements the main frame for probability game 01.
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

public class ProbabilityGame01MainFrame extends JFrame implements WindowListener
{
    /**
     * Serial version ID.
     */
    public static final long serialVersionUID = 0L;

    /**
     * Horizontal location of top left of window.
     */
    private static final int xLoc = 200;

    /**
     * Vertical location of top left of window.
     */
    private static final int yLoc = 200;

    /**
     * Width of window.
     */
    private static final int width = 800;

    /**
     * Height of window.
     */
    private static final int height = 600;

    /**
     * Default constructor, giving the window a title.
     * @param title String containing the title of the window.
     */
    public ProbabilityGame01MainFrame(final String title)
    {
        super(title);

        // Set window attributes.

        this.setSize(width, height);
        this.setLocation(xLoc,yLoc);
        this.setResizable(true);
        this.addWindowListener(this);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Make the window visible.

        this.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent)
    {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent)
    {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent windowEvent)
    {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent)
    {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent)
    {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent)
    {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent)
    {

    }
}
