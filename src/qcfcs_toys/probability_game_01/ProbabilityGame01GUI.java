package qcfcs_toys.probability_game_01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private static final int width = 800;

    /**
     * Height of window.
     */
    private static final int height = 600;

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

        mainFrame = new JFrame();
        mainFrame.setSize(width, height);
        mainFrame.setLocation(xLoc,yLoc);
        mainFrame.setResizable(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(panel1);

        // Make the window visible.

        mainFrame.pack();
        mainFrame.setVisible(true);

        // Create the game type radio buttons.

        gameTypeButtonGroup = new ButtonGroup();
        gameTypeButtonGroup.add(booleanGameButton);
        gameTypeButtonGroup.add(realGameButton);
        gameTypeButtonGroup.add(complexGameButton);
        booleanGameButton.setSelected(true);
        realGameButton.setSelected(false);
        complexGameButton.setSelected(false);

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
    }
}
