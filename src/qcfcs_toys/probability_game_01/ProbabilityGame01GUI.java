package qcfcs_toys.probability_game_01;

import qcfcs_math.Complex;
import qcfcs_math.ComplexMatrix;
import qcfcs_math.ComplexVector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class implements the GUI for the probability game. It was generated by the Intellij Idea GUI designer. The
 * GUI form is located in ProbabilityGame01GUI.form.
 * Created by reesede on 2/19/2017.
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
//      20170219    D.E. Reese          Creation (Programming Drill 3.1.1)
//      20170228    D.E. Reese          Added createUIComponents(). Changed JTable elements to
//                                      ProbabilityGameMatrixTable classes.
//      20170301    D.E. Reese          Initialize numStatesTextField. Added PropertyChangeListener to
//                                      numStatesTextField.
//      20170307    D.E. Reese          Added code to numStatesTextField.PropertyChangeListener() to change the
//                                      number of states and change the sizes of the tables appropriately. Initially,
//                                      disable the execute button until the start button is clicked. Added
//                                      executeGame(). Added gameType.
//      20170308    D.E. Reese          Added code in executeGame() to perform the boolean game.
//      20170309    D.E. Reese          Added checkGameMatrixTables(), checkBooleanGameMatrixTables().
//      20170310    D.E. Reese          Added checkRealGameMatrixTables() and enabled real game.
//      20170311    D.E. Reese          Added code to checkRealGameMatrixTables() stub.
//      20170312    D.E. Reese          Added transitionPowers, transitionPowerTextField (and its propertyChangedListener).
//                                      Commented out code in checkRealGameMatrixTables() so that rows don't have to add
//                                      to 1.0 (make tables stochastic, not doubly stochastic). Added restartButton and
//                                      its actionListener, in order to restart the game.
//      20170313    D.E. Reese          Enabled complex matrices.
//      20170315    D.E. Reese          Corrected bug in restartButton actionListener() where the boolean, real, and
//                                      complex radio buttons were not being enabled on reset.
//      20170318    D.E. Reese          Added code to execute complex game.

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
     * Number of states.
     */
    private int numStates;

    /**
     * Power of the transition matrix.
     */
    private int transitionPowers;

    /**
     * Type of the game (same as the type of the transitionMatrixTable).
     */
    private int gameType;

    /**
     * Number of iterations run in game.
     */
    private int numIterations;

    /**
     * Matrix containing transitions.
     */
    private ComplexMatrix transitionMatrix;

    /**
     * Vector containing state.
     */
    private ComplexVector stateVector;

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
    private JButton quitButton;
    private JLabel transitionMatrixLabel;
    private JLabel stateVectorLabel;
    private ProbabilityGameMatrixTable transitionMatrixTable;
    private ProbabilityGameMatrixTable stateVectorTable;
    private JFormattedTextField transitionPowerTextField;
    private JButton restartButton;
    private ButtonGroup gameTypeButtonGroup;

    public ProbabilityGame01GUI()
    {
        // Set the transition matrix and state vector to null. These will be set to non-null values the first
        // time the execute button is clicked.

        transitionMatrix = null;
        stateVector = null;

        // Set up main frame.

        initializeMainFrame();

        // Create the game type radio button group.

        initializeGameTypeButtonGroup();

        // Set up the number of states in the editable text field.

        numStatesTextField.setPreferredSize(new Dimension(50, numStatesTextField.getPreferredSize().height));
        numStatesTextField.setHorizontalAlignment(JFormattedTextField.CENTER);
        numStatesTextField.setText(Integer.toString(initialNumberOfStates));
        numStates = initialNumberOfStates;

        // Set up the power of the transition matrix and text field.

        transitionPowerTextField.setPreferredSize(new Dimension(50,transitionPowerTextField.getPreferredSize().height));
        transitionPowerTextField.setHorizontalAlignment(JTextField.CENTER);
        transitionPowers = 1;

        // Initialize the number of iterations.

        numIterations = 0;
        iterationCountLabel.setText("Iteration Count = " + numIterations);

        // Disable the execute button until the start button is clicked.

        executeButton.setEnabled(false);

        // Create listeners.

        booleanGameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                gameType = ProbabilityGameMatrixTable.TABLE_TYPE_BOOLEAN;
                transitionMatrixTable.setTableType(ProbabilityGameMatrixTable.TABLE_TYPE_BOOLEAN);
                stateVectorTable.setTableType(ProbabilityGameMatrixTable.TABLE_TYPE_INTEGER);
            }
        });

        realGameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                gameType = ProbabilityGameMatrixTable.TABLE_TYPE_REAL;
                transitionMatrixTable.setTableType(ProbabilityGameMatrixTable.TABLE_TYPE_REAL);
                stateVectorTable.setTableType(ProbabilityGameMatrixTable.TABLE_TYPE_REAL);
            }
        });

        complexGameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                gameType = ProbabilityGameMatrixTable.TABLE_TYPE_COMPLEX;
                transitionMatrixTable.setTableType(ProbabilityGameMatrixTable.TABLE_TYPE_COMPLEX);
                stateVectorTable.setTableType(ProbabilityGameMatrixTable.TABLE_TYPE_COMPLEX);
            }
        });

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
                // Enable the game matrices.

                transitionMatrixTable.setEnabled(true);
                stateVectorTable.setEnabled(true);

                // Disable the game type buttons.

                booleanGameButton.setEnabled(false);
                realGameButton.setEnabled(false);
                complexGameButton.setEnabled(false);

                // Disable the start button itself.

                startButton.setEnabled(false);

                // Enable the execute button.

                executeButton.setEnabled(true);

                // Disable the number of states text field and the power text field.

                numStatesTextField.setEnabled(false);
                transitionPowerTextField.setEnabled(false);

            }
        });

        executeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                // If the transitionMatrix and stateVector are null, check the tables for validity and
                // attempt to generate the matrices. If not, just return. Note that the checkGameMatrixTables()
                // method should identify the problem with the matrices to the user.

                if((transitionMatrix == null) || (stateVector == null))
                {
                    // Check that the transitionMatrixTable and stateMatrixTable are valid. If so, then attempt
                    // to create the matrices.

                    if (checkGameMatrixTables())
                    {
                        try
                        {
                            createGameMatrices();
                        }
                        catch (Exception e)
                        {
                            System.out.println("Could not create matrices.");
                            System.exit(-1);
                        }
                    }
                    else
                        return;
                }

                // Disable the tables so that they can not be edited.

                transitionMatrixTable.setEnabled(false);
                stateVectorTable.setEnabled(false);

                // Run an iteration of the game.

                executeGame();
            }
        });

        restartButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                // Set the number of states.

                numStatesTextField.setText(Integer.toString(initialNumberOfStates));
                numStates = initialNumberOfStates;
                transitionMatrixTable.resizeTable(numStates,numStates);
                stateVectorTable.resizeTable(numStates,1);

                // Reinitialize the powers.

                transitionPowerTextField.setText(Integer.toString(1));
                transitionPowers = 1;

                // Initialize the number of iterations.

                numIterations = 0;
                iterationCountLabel.setText("Iteration Count = " + numIterations);

                // Reset the buttons and fields.

                executeButton.setEnabled(false);
                startButton.setEnabled(true);
                booleanGameButton.setEnabled(true);
                realGameButton.setEnabled(true);
                complexGameButton.setEnabled(true);
                booleanGameButton.setSelected(true);
                realGameButton.setSelected(false);
                complexGameButton.setSelected(false);
                numStatesTextField.setEnabled(true);
                transitionPowerTextField.setEnabled(true);
            }
        });

        numStatesTextField.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent)
            {
                // Get the changed text.

                String theText = numStatesTextField.getText();

                // Parse the text containing the new number of states. If it is not an unsigned integer, then
                // set the text back to the current number of states and return.

                int newNumStates;
                try
                {
                    newNumStates = Integer.parseUnsignedInt(theText);
                }
                catch(NumberFormatException e)
                {
                    numStatesTextField.setText(new Integer(numStates).toString());
                    return;
                }

                // If the new number of states is 0 or the same as the existing number of states, set the text back
                // to the current number of states (there must be at least 1 state) and return.

                if ((newNumStates == 0) || (newNumStates == numStates))
                {
                    numStatesTextField.setText(new Integer(numStates).toString());
                    return;
                }

                // Set the number of states to the new value.

                numStates = newNumStates;
                transitionMatrixTable.resizeTable(numStates,numStates);
                stateVectorTable.resizeTable(numStates,1);
            }
        });

        transitionPowerTextField.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent)
            {
                // Get the changed text.

                String theText = transitionPowerTextField.getText();

                // Parse the text containing the new power. If it is not an unsigned integer, then
                // set the text back to the current number of states and return.

                int newPower;
                try
                {
                    newPower = Integer.parseUnsignedInt(theText);
                }
                catch(NumberFormatException e)
                {
                    transitionPowerTextField.setText(new Integer(transitionPowers).toString());
                    return;
                }

                // If the new power is 0 or the same as the existing power, set the text back
                // to the current power (power must be at least 1) and return.

                if ((newPower == 0) || (newPower == transitionPowers))
                {
                    transitionPowerTextField.setText(new Integer(transitionPowers).toString());
                    return;
                }

                // Set the power level to the new value.

                transitionPowers = newPower;
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

        // Set the initial game type to boolean.

        gameType = ProbabilityGameMatrixTable.TABLE_TYPE_BOOLEAN;
    }

    /**
     * This method initializes components from the GUI form.
     */
    private void createUIComponents()
    {
        transitionMatrixTable = new ProbabilityGameMatrixTable(ProbabilityGameMatrixTable.TABLE_TYPE_BOOLEAN,
                initialNumberOfStates,initialNumberOfStates);

        stateVectorTable = new ProbabilityGameMatrixTable(ProbabilityGameMatrixTable.TABLE_TYPE_INTEGER,
                initialNumberOfStates,1);
    }

    /**
     * This method creates the transitionMatrix and stateVector from the information in the corresponding table.
     * @throws Exception    Thrown if numStates <= 0.
     */
    private void createGameMatrices() throws Exception
    {
        // Throw an exception if the number of states is invalid.

        if(numStates <= 0) throw new Exception("numStates <= 0.");

        // Create a new transition matrix if needed.

        if(transitionMatrix == null)
        {
            // Create a new complex square matrix containing the number of states.

            transitionMatrix = new ComplexMatrix(numStates,numStates);

            // Copy the complex values from the GUI table into the transition matrix.

            for(int i = 0; i < numStates; i++)
                for(int j = 0; j < numStates; j++)
                    transitionMatrix.set(i,j,Complex.parseComplex((String)transitionMatrixTable.getValueAt(i,j)));

            // If the transitionPowers is greater than 1, then multiply the transition matrix by itself the given
            // number of times. This is valid only on a real or complex game.

            if ((transitionPowers > 1) && ((gameType == ProbabilityGameMatrixTable.TABLE_TYPE_REAL) ||
                                           (gameType == ProbabilityGameMatrixTable.TABLE_TYPE_COMPLEX)))
            {
                for(int i = 2; i <= transitionPowers; i++)
                    transitionMatrix = ComplexMatrix.multiply(transitionMatrix, transitionMatrix);

                // Update the visible matrix to match the transition matrix taken to the appropriate power.

                for(int i = 0; i < transitionMatrixTable.getRowCount(); i++)
                    for(int j = 0; j < transitionMatrixTable.getColumnCount(); j++)
                        transitionMatrixTable.setValueAt(transitionMatrix.get(i,j).toString(),i,j);

                // Update the table label to indicate that the transition matrix has been raised to a power.

                transitionMatrixLabel.setText("Transition Matrix (original raised by " + transitionPowers + " powers)");
            }
        }

        // Create a new state vector if needed.

        if(stateVector == null)
        {
            // Create a new complex vector with a number of rows equal to the number of states.

            stateVector = new ComplexVector(numStates);

            // Copy the complex values from the GUI table into the state vector.

            for(int i = 0; i < numStates; i++)
                stateVector.set(i,Complex.parseComplex((String)stateVectorTable.getValueAt(i,0)));
        }
    }

    /**
     * This method checks that the game matrices are valid for the type of game.
     * @return  true if the transitionMatrixTable and stateVectorTable are valid, false otherwise.
     */
    private boolean checkGameMatrixTables()
    {
        switch(gameType)
        {
            case ProbabilityGameMatrixTable.TABLE_TYPE_BOOLEAN:
                return checkBooleanGameMatrixTables();
            case ProbabilityGameMatrixTable.TABLE_TYPE_REAL:
                return checkRealGameMatrixTables();
            case ProbabilityGameMatrixTable.TABLE_TYPE_COMPLEX:
                return checkComplexGameMatrixTables();
            default:
                return false;
        }
    }

    /**
     * This method verifies the format of the transitionMatrix and stateVector at the start of a boolean game.
     * @return  true if the tables are valid, false otherwise.
     */
    private boolean checkBooleanGameMatrixTables()
    {
        boolean matricesGood = true;
        String errorString = new String();

        for(int i = 0; i < transitionMatrixTable.getRowCount(); i++)
            for(int j = 0; j < transitionMatrixTable.getColumnCount(); j++)
            {
                String theBooleanString = (String)transitionMatrixTable.getValueAt(i,j);
                if((theBooleanString.compareTo("0") != 0) &&
                        theBooleanString.compareTo("1") != 0)
                {
                    errorString = errorString.concat("Value at transitionMatrix[" + i + "," + j + "] must be in set (0,1).\n");
                    matricesGood = false;
                }
            }
        for(int i = 0; i < stateVectorTable.getRowCount(); i++)
        {
            String IntegerString = (String)stateVectorTable.getValueAt(i,0);
            try
            {
                Integer.parseUnsignedInt(IntegerString);
            }
            catch(NumberFormatException e)
            {
                errorString = errorString.concat("Value at stateVector[" + i + "] must be a positive integer.\n");
                matricesGood = false;
            }
        }

        // If an error is found, display it.

        if (!matricesGood)
        {
            System.out.print(errorString);
        }

        // Return whether or not the matrices are good.

        return matricesGood;
    }

    public boolean checkRealGameMatrixTables()
    {
        boolean matricesGood = true;
        String errorString = new String();
        String theRealString;
//        double rowSums[] = new double[transitionMatrixTable.getRowCount()];
        double columnSums[] = new double[transitionMatrixTable.getColumnCount()];
        double vectorSum = 0.0;
        double theReal;
        double theSum;

        final double TOLERANCE = 0.00000001;

        // Initialize the row, column, and vector sums.

//        for(int i = 0; i < transitionMatrixTable.getRowCount(); i++)
//            rowSums[i] = 0.0;
        for(int j = 0; j < transitionMatrixTable.getColumnCount(); j++)
            columnSums[j] = 0.0;

        // Iterate through all entries in the transition table to check that they are reals in the range 0.0...1.0.

        for(int i = 0; i < transitionMatrixTable.getRowCount(); i++)
            for(int j = 0; j < transitionMatrixTable.getColumnCount(); j++)
            {
                // Get the string from the table entry and attempt to convert it to a real number.

                theRealString = (String)transitionMatrixTable.getValueAt(i,j);
                try
                {
                    theReal = Double.parseDouble(theRealString);
                }
                catch (NumberFormatException e)
                {
                    errorString = errorString.concat("Value at transitionMatrix[" + i + "," + j + "] must be a positive real between 0.0 and 1.0.\n");
                    matricesGood = false;
                    continue;
                }

                // If a real number was found, check that it is between 0.0 and 1.0.

                if ((theReal < 0.0) || (theReal > 1.0))
                {
                    errorString = errorString.concat("Value at transitionMatrix[" + i + "," + j + "] must be a positive real between 0.0 and 1.0.\n");
                    matricesGood = false;
                    continue;
                }

                // Add the real number to the appropriate row sum and column sum.

//                rowSums[i] += theReal;
                columnSums[j] += theReal;
            }

        // Iterate through each element in the state vector and verify that it is a real in the range 0.0...1.0.

        for(int i = 0; i < stateVectorTable.getRowCount(); i++)
        {
            // Get the string from the table entry and attempt to convert it to a real number.

            theRealString = (String)stateVectorTable.getValueAt(i,0);
            try
            {
                theReal = Double.parseDouble(theRealString);
            }
            catch (NumberFormatException e)
            {
                errorString = errorString.concat("Value at stateVector[" + i + "] must be a positive real between 0.0 and 1.0.\n");
                matricesGood = false;
                continue;
            }

            // If a real number was found, check that it is between 0.0 and 1.0.

            if ((theReal < 0.0) || (theReal > 1.0))
            {
               errorString = errorString.concat("Value at stateVector[" + i + "] must be a positive real between 0.0 and 1.0.\n");
                matricesGood = false;
                continue;
            }

            // Add the real number to the vector column sum.

            vectorSum += theReal;
        }

        // Verify that each row of the transition matrix sums to 1.0.

//        theSum = 0.0;
//        for(int i = 0; i < transitionMatrixTable.getRowCount(); i++)
//        {
//            if((rowSums[i] < 1.0 - TOLERANCE) || (rowSums[i] > 1.0 + TOLERANCE))
//            {
//                errorString = errorString.concat("transitionMatrix row " + i + "must sum to 1.0 +/- " + TOLERANCE +
//                        ", but sums to " + rowSums[i] + ".\n");
//                matricesGood = false;
//            }
//        }

        // Verify that each column of the transition matrix sums to 1.0.

        theSum = 0.0;
        for(int j = 0; j < transitionMatrixTable.getColumnCount(); j++)
        {
            if((columnSums[j] < 1.0 - TOLERANCE) || (columnSums[j] > 1.0 + TOLERANCE))
            {
                errorString = errorString.concat("transitionMatrix column " + j + "must sum to 1.0 +/- " + TOLERANCE +
                        ", but sums to " + columnSums[j] + ".\n");
                matricesGood = false;
            }
        }

        // Verify that the state vector elements sum to 1.0

        if((vectorSum < 1.0 - TOLERANCE) || (vectorSum > 1.0 + TOLERANCE))
        {
            errorString = errorString.concat("stateVector elements must sum to 1.0 +/- " + TOLERANCE +
                    ", but sum to " + vectorSum + ".\n");
            matricesGood = false;
        }

        // If an error is found, display it.

        if (!matricesGood)
        {
            System.out.print(errorString);
        }

        // Return whether or not the matrices are good.

        return matricesGood;
    }

    /**
     * This method verifies that the tables are correct for a complex game.
     * @return  true if the tables are valid for a complex game, false otherwise.
     */
    private boolean checkComplexGameMatrixTables()
    {
        return true;
    }

    /**
     * This method executes the game, based on the type of game.
     */
    private void executeGame()
    {
        // Generate a new state vector by multiplying the transition table by the current state vector.

        stateVector = ComplexVector.convertComplexMatrixToVector(ComplexMatrix.multiply(transitionMatrix,stateVector));

        // Process the new state vector based on the type of game.

        switch(gameType)
        {
            case ProbabilityGameMatrixTable.TABLE_TYPE_BOOLEAN:
                for(int i = 0; i < numStates; i++)
                {
                    int theValue = (int)stateVector.get(i).getReal();
                    stateVectorTable.setValueAt((new Integer(theValue)).toString(),i,0);
                }
                break;
            case ProbabilityGameMatrixTable.TABLE_TYPE_REAL:
                for(int i = 0; i < numStates; i++)
                {
                    double theValue = (double)stateVector.get(i).getReal();
                    stateVectorTable.setValueAt((new Double(theValue)).toString(),i,0);
                }
                break;
            case ProbabilityGameMatrixTable.TABLE_TYPE_COMPLEX:
                for(int i = 0; i < numStates; i++)
                {
                    String theString = stateVector.get(i).toString();
                    stateVectorTable.setValueAt(theString, i, 0);
                }
                break;
        }

        // Increment the number of iterations.

        numIterations++;
        iterationCountLabel.setText("Iteration Count = " + numIterations);
    }
}
