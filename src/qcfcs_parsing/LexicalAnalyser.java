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
//      20170331    D.E. Reese          Added doLexicalStateInDecimalInt(). Added stub for doLexicalStateStartOctalInt().
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
         * State of lexical analysis - in a label.
         */
        lexicalStateInLabel,

        /**
         * State of lexical analysis - in a decimal integer.
         */
        lexicalStateInDecimalInt,

        /**
         * State of lexical analysis - in an octal integer after initial 0.
         */
        lexicalStateStartOctalInt,

        /**
         * State of lexical analysis - in an octal integer after second or greater octet (can not be binary or hex).
         */
        lexicalStateInOctalInt,

        /**
         * State of lexical analysis - in a binary integer.
         */
        lexicalStateInBinaryInt,

        /**
         * State of lexical analysis - in a hexidecimal integer.
         */
        lexicalStateInHexidecimalInt,

        /**
         * State of lexical analysis - at the start of a decimal real number, with a dot (.) found, but no digits
         * to the right of the dot.
         */
        lexicalStateStartDecimalReal,

        /**
         * State of lexical analysis - in a decimal real.
         */
        lexicalStateInDecimalReal,

        /**
         * State of lexical analysis - a plus (+) sign or beginning of a double plus (++) sign.
         */
        lexicalStatePlusOrDoublePlus,

        /**
         * State of lexical analysis = a minus (-) sign or beginning of a double minus (--) sign.
         */
        lexicalStateMinusOrDoubleMinus,

        /**
         * State of lexical analysis = a divide (/) sign or the beginning of a comment (// or /*)
         */
        lexicalStateDivideOrComment
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
                case lexicalStateStart:
                    doLexicalStateStart(theChar);
                    break;
                case lexicalStateInLabel:
                    doLexicalStateInLabel(theChar);
                    break;
                case lexicalStateInDecimalInt:
                    doLexicalStateInDecimalInt(theChar);
                    break;
                case lexicalStateStartOctalInt:
                    doLexicalStateStartOctalInt(theChar);
                    break;
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
            case lexicalStateStart:
                break;
            case lexicalStateInLabel:
                tokenString = workString.substring(curTokenStart, stringLocation);
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenLabel, tokenString, curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateInDecimalInt:
                tokenString = workString.substring(curTokenStart, stringLocation);
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, tokenString, curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateStartOctalInt:
                tokenString = workString.substring(curTokenStart, stringLocation);
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, tokenString, curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
        }
    }

    /**
     * This method processes the start of a token.
     * @param theChar   Character to analyse.
     */
    private void doLexicalStateStart(final char theChar)
    {
        // If the current character is a space character, then ignore it and increment the .

        if (Character.isSpaceChar(theChar))
        {
            return;
        }

        // If the current character is a letter, then it is the start of a label.

        if (Character.isLetter(theChar))
        {
            curTokenStart = stringLocation;
            lexicalState = EnumLexicalState.lexicalStateInLabel;
            return;
        }

        // If the current character is a digit, then it is the start of an integer.

        if (Character.isDigit(theChar))
        {
            // If the digit is a 0, then the integer will be an octal, binary, or hexidecimal integer. Otherwise,
            // it will be a decimal integer.

            if (theChar == '0')
            {
                curTokenStart = stringLocation;
                lexicalState = EnumLexicalState.lexicalStateStartOctalInt;
                return;
            }
            else
            {
                curTokenStart = stringLocation;
                lexicalState = EnumLexicalState.lexicalStateInDecimalInt;
                return;
            }
        }

        // Analyse single characters.

        switch (theChar)
        {
            case '+':   // Need to check for + or ++.
                curTokenStart = stringLocation;
                lexicalState = EnumLexicalState.lexicalStatePlusOrDoublePlus;
                return;
            case '-':   // Need to check for - or --.
                curTokenStart = stringLocation;
                lexicalState = EnumLexicalState.lexicalStateMinusOrDoubleMinus;
                return;
            case '*':   // Found a times (*) token.
                curTokenStart = stringLocation;
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenTimes, "*", curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                return;
            case '/':   // Need to check for divide (/) or comment (// or /*)
                curTokenStart = stringLocation;
                lexicalState = EnumLexicalState.lexicalStateDivideOrComment;
                return;
            case '(':   // Found a left paren (().
                curTokenStart = stringLocation;
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenLeftParen, "(", curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                return;
            case ')':   // Found a right paren ()).
                curTokenStart = stringLocation;
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenRightParen, ")", curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                return;
            default:
                curTokenStart = stringLocation;
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenUnknown, String.valueOf(theChar), curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                return;
        }
    }

    /**
     * This method analyses a label.
     * @param theChar   Character to process.
     */
    private void doLexicalStateInLabel(final char theChar)
    {
        // If the character is a letter, digit, or underscore, than just return, since we'll stay in this state and
        // collect more characters in the label.

        if((Character.isLetter(theChar)) || (Character.isDigit(theChar)) || (theChar == '_'))
        {
            return;
        }

        // If the character is anything other than a letter, digit, or underscore, then the previous character
        // was the end of the label. So, decrement the string location, so that the current character will be
        // caught again and create a label token.

        final String labelString = workString.substring(curTokenStart, stringLocation);
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenLabel, labelString, curTokenStart));
        lexicalState = EnumLexicalState.lexicalStateStart;
        stringLocation--;
        return;
    }

    /**
     * This method analyses a decimal integer.
     * @param theChar   Character to process.
     */
    private void doLexicalStateInDecimalInt(final char theChar)
    {
        // If the character is a digit, then just return, since we'll stay in this state and collect more characters
        // in the decimal integer.

        if((Character.isDigit(theChar)) || (theChar == '_'))
        {
            return;
        }

        // If the character is a dot (.), then the integer really was a real number, so collect the dot and transition
        // the state to indicate that we are at the start of a real number.

        if(theChar == '.')
        {
            lexicalState = EnumLexicalState.lexicalStateStartDecimalReal;
            return;
        }

        // If the character is anything other than a digit, dot, or underscore, than the previous character was
        // the end of the decimal label. So, decrement the string location, so that the current character will be
        // caught again and create a decimal integer token.

        final String integerString = workString.substring(curTokenStart, stringLocation);
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, integerString, curTokenStart));
        lexicalState = EnumLexicalState.lexicalStateStart;
        stringLocation--;
        return;
    }

    private void doLexicalStateStartOctalInt(final char theChar)
    {

    }
}
