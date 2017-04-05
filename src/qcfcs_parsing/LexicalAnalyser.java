package qcfcs_parsing;

import java.util.ArrayList;

/**
 * This class implements a lexical analyzer to convert a string into a list of tokens.
 * Created by reesede on 3/26/2017.
 * @author David E. Reese
 * @version 3.3.2
 * @since 3.3.2
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
//      20170326    D.E. Reese          Creation.
//      20170328    D.E. Reese          Created constructor and stub for analyseString().
//      20170329    D.E. Reese          Added initializeAnalyser(), code to analyseString(), restructured constructor,
//                                      renamed to LexicalAnalyser, added doLexicalStateStart(), added
//                                      doLexicalStateInLabel().
//      20170330    D.E. Reese          Added code at end of analyseString() to handle the end of string. Added
//                                      processEndOfString() and added code to handle processing for
//                                      lexicalStateStart and lexicalStateInLabel.
//      20170331    D.E. Reese          Added doLexicalStateInDecimalInt(). Added stub for doLexicalStateStartSpecialInt().
//      20170401    D.E. Reese          Added skipToNextBreak().
//      20170402    D.E. Reese          Added doLexicalStateInOctalInt().
//      20170403    D.E. Reese          Added doLexicalStateStartBinaryInt().
//      20170404    D.E. Reese          Rewriting and simplifying just for complex numbers.
//      20170405    D.E. Reese          Added new state definitions for complex numbers, comments, labels.
//

public class LexicalAnalyser
{
    /**
     * Enumeration of lexical analysis states.
      */
    private enum EnumLexicalState
    {
        /**
         * State of lexical analysis - beginning of analysis.
         */
        lexicalStateStart,

        /**
         * State of lexical analysis - in a label
         */
        lexicalStateInLabel,

        /**
         * State of lexical analysis - starting a binary, octal, or hexidecimal integer, or a 0 or decimal real.
         */
        lexicalStateStartSpecialInteger,

        /**
         * State of lexical analysis - in a decimal integer.
         */
        lexicalStateInDecimalInteger,

        /**
         * State of lexical analysis - after an underscore in a decimal integer.
         */
        lexicalStateInDecimalIntegerUnderscore,

        /**
         * State of lexical analysis - after a dot (.) in a decimal real.
         */
        lexicalStateStartDecimalReal,

        /**
         * State of lexical analysis - in a decimal real.
         */
        lexicalStateInDecimalReal,

        /**
         * State of lexical analysis - after an underscore in a decimal real.
         */
        lexicalStateInDecimalRealUnderscore,

        /**
         * State of lexical analysis - in an octal integer.
         */
        lexicalStateInOctalInteger,

        /**
         * State of lexical analysis - after an underscore in an octal integer.
         */
        lexicalStateInOctalIntegerUnderscore,

        /**
         * State of lexical analysis - after the 'b' defining a binary integer, but before first bit.
         */
        lexicalStateStartBinaryInteger,

        /**
         * State of lexical analysis - in a binary integer.
         */
        lexicalStateInBinaryInteger,

        /**
         * State of lexical analysis - after an undersocre in a binary integer.
         */
        lexicalStateInBinaryIntegerUnderscore,

        /**
         * State of lexical analysis - after the 'h' defining a hexidecimal integer, but before first hexted.
         */
        lexicalStateStartHexInteger,

        /**
         * State of lexical analysis - in a hexidecimal integer.
         */
        lexicalStateInHexInteger,

        /**
         * State of lexical analysis - after an underscore in a hexidecimal integer.
         */
        lexicalStateInHexIntegerUnderscore,

        /**
         * State of lexical analysis - after a dot but before a digit.
         */
        lexicalStateDot,

        /**
         * State of lexical analysis - after a slash (/), checking if it is a divide or start of a comment.
         */
        lexicalStateDivideOrComment,

        /**
         * State of lexical analysis - in a comment.
         */
        lexicalStateInComment,

        /**
         * State of lexical analysis - looking for a slash (/) after a star (*) for comment end.
         */
        lexicalStateCheckEndComment


    }

    /**
     * State of analysis.
     */
    private EnumLexicalState lexicalState;

    /**
     * Location in the string to be analysed.
     */
    private int stringLocation;

    /**
     * Length of string to analyse.
     */
    private int stringLength;

    /**
     * Private copy of the string to analyse.
     */
    private String workString;

    /**
     * Indicates whether or not a string ended with a comment.
     */
    private boolean inComment;

    /**
     * Start of current token being processed.
     */
    private int curTokenStart;

    /**
     * List of tokens found.
     */
    private ArrayList<LexicalToken> tokenList;

    /**
     * List of strings containing errors found.
     */
    private ArrayList<String> errorList;

    /**
     * Constructor to create a lexical analysis with no string input (no analysis will be done).
     */
    public LexicalAnalyser()
    {
        initializeAnalyser();
    }

    /**
     * This method initializes the lexical analyser.
     */
    private void initializeAnalyser()
    {
        lexicalState = EnumLexicalState.lexicalStateStart;
        stringLocation = 0;
        stringLength = 0;
        workString = null;
        inComment = false;
        tokenList = new ArrayList<LexicalToken>();
        errorList = new ArrayList<String>();
    }

    /**
     * This method performs lexical analysis on a string and adds the tokens it detects to the end of the list
     * of tokens for this lexical analyser.
     * @param theString String to be analysed.
     * @return          List of tokens which include those contained in the string at the end.
     */
    public ArrayList<LexicalToken> analyseString(final String theString)
    {
        // If the string is null or empty, just return, since nothing has to be done.

        if (theString == null)
            return tokenList;
        if (theString.length() == 0)
            return tokenList;

        // Copy theString into a local copy.

        workString = theString;
        stringLength = workString.length();
        stringLocation = 0;

        // Process the location of the string until the end of the string is reached.

        for(stringLocation = 0; stringLocation < stringLength; stringLocation++)
        {
            // Get the next character to process.

            char theChar = workString.charAt(stringLocation);

            // Process the character based on the lexical state.

            switch(lexicalState)
            {
            }
        }

        // Process the end-of-string condition.

        processEndOfString();

        // Return the token list.

        return tokenList;
    }

    /**
     * This method does processing of an end-of-string condition, based on the lexical state.
     */
    private void processEndOfString()
    {
        // When the end of the string is reached, process based on the state.

        String tokenString;

        switch(lexicalState)
        {
        }
    }

    /**
     * This method processes the start of a token.
     * @param theChar   Character to analyse.
     */
    private void doLexicalStateStart(final char theChar)
    {
    }

    /**
     * This method skips to the next clearly defined token after an error has been detected.
     */
    private void skipToNextBreak()
    {
        for(int i = stringLocation; i < stringLength; i++)
        {
            char theChar = workString.charAt(i);

            if((theChar == ' ') || (theChar == '+') || (theChar == '-') || (theChar == '*') || (theChar == '/'))
                break;
        }

        if(stringLocation != stringLength)
            stringLocation--;
    }
}
