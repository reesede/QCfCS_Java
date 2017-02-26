package qcfcs_toys.probability_game_01;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Created by reesede on 2/19/17.
 */
public class ProbabilityGame01GUI
{
    /**
     * Title of window.
     */
    private static final String title = "Probabilty Game 01";

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
    private static final int width = 1000;

    /**
     * Height of window.
     */
    private static final int height = 600;

    /**
     * Initial number of states in the game.
     */
    private static final int initialNumberOfStates = 5;

    /**
     * Preferred width of columns.
     */
    private static final int tableColumnWidth = 50;

    private JFrame mainFrame;
    private JPanel panel1;
    private JPanel gameTypePanel;
    private JPanel controlPanel;
    private JRadioButton booleanGameButton;
    private JRadioButton realGameButton;
    private JRadioButton complexGameButton;
    private JButton startButton;
    private JPanel mainPanel;
    private JLabel iterationCountLabel;
    private JButton executeButton;
    private JLabel numStateLabel;
    private JFormattedTextField numStatesTextField;
    private JScrollPane mainScrollPane;
    private JPanel gamePanel;
    private JPanel matrixPanel;
    private JPanel statePanel;
    private JTable transitionMatrixTable;
    private JTable stateVectorTable;
    private JButton quitButton;
    private JLabel transitionMatrixLabel;
    private JLabel stateVectorLabel;
    private ButtonGroup gameTypeButtonGroup;

    public ProbabilityGame01GUI()
    {
        // Set up main frame.

        initializeMainFrame();

        // Create the game type radio button group.

        initializeGameTypeButtonGroup();

        // Initialize the transition matrix and state vector.

        initializeTransitionMatrixAndStateVector();

        quitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.exit(0);
            }
        });

        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {

            }
        });

        // Make the window visible.

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * This method initializes the frame for the program GUI.
     */
    private void initializeMainFrame ()
    {
        mainFrame = new JFrame();
        mainFrame.setSize(width, height);
        mainFrame.setLocation(xLoc,yLoc);
        mainFrame.setResizable(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(panel1);
    }

    /**
     * This method initializes the game type radio button group.
     */
    private void initializeGameTypeButtonGroup()
    {
        gameTypeButtonGroup = new ButtonGroup();
        gameTypeButtonGroup.add(booleanGameButton);
        gameTypeButtonGroup.add(realGameButton);
        gameTypeButtonGroup.add(complexGameButton);
        booleanGameButton.setSelected(true);
        realGameButton.setSelected(false);
        complexGameButton.setSelected(false);
    }

    private void initializeTransitionMatrixAndStateVector()
    {
        DefaultTableModel transitionTableModel = new DefaultTableModel(initialNumberOfStates,initialNumberOfStates);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        for(int i = 0; i < initialNumberOfStates; i++)
            for(int j = 0; j < initialNumberOfStates; j++)
            {
                String theValue = "0";
                if (i == j)
                    theValue = "1";
                transitionTableModel.setValueAt(theValue,i,j);
            }

        transitionMatrixTable.setTableHeader(null);
        transitionMatrixTable.setModel(transitionTableModel);
        for(int i = 0; i < transitionMatrixTable.getColumnCount(); i++)
        {
            transitionMatrixTable.getColumnModel().getColumn(i).setPreferredWidth(tableColumnWidth);
            transitionMatrixTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        // Set up the initial state vector.

        DefaultTableModel vectorTableModel = new DefaultTableModel(initialNumberOfStates,1);


    }
}
