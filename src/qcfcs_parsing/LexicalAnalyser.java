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
//      20170408    D.E. Reese          Added doLexicalStateStart(), doLexicalStateInLabel(), doLexicalStateStartSpecialInteger().
//      20170409    D.E. Reese          Added doLexicalStateInDecimalInteger().
//      20170410    D.E. Reese          Added doLexicalStateInDecimalIntegerUnderscore().
//      20170413    D.E. Reese          Added doLexicalStateStartDecimalReal().
//      20170415    D.E. Reese          Added doLexicalStateInDecimalReal(), doLexicalStateInDecimalRealUnderscore(),
//                                      doLexicalStateInOctalInteger().
//      20170416    D.E. Reese          Added doLexicalStateInOctalIntegerUnderscore(), doLexicalStateStartBinaryInteger(),
//                                      doLexicalStateInBinaryInteger(), doLexicalStateInBinaryIntegerUnderscore().
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
     * Start of current token being processed.
     */
    private int curTokenStart;

    /**
     * List of tokens found.
     */
    private ArrayList<LexicalToken> tokenList;

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
        tokenList = new ArrayList<LexicalToken>();
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
                case lexicalStateStartSpecialInteger:
                    doLexicalStateStartSpecialInteger(theChar);
                    break;
                case lexicalStateInDecimalInteger:
                    doLexicalStateInDecimalInteger(theChar);
                    break;
                case lexicalStateInDecimalIntegerUnderscore:
                    doLexicalStateInDecimalIntegerUnderscore(theChar);
                    break;
                case lexicalStateStartDecimalReal:
                    doLexicalStateStartDecimalReal(theChar);
                    break;
                case lexicalStateInDecimalReal:
                    doLexicalStateInDecimalReal(theChar);
                    break;
                case lexicalStateInDecimalRealUnderscore:
                    doLexicalStateInDecimalRealUnderscore(theChar);
                    break;
                case lexicalStateInOctalInteger:
                    doLexicalStateInOctalInteger(theChar);
                    break;
                case lexicalStateInOctalIntegerUnderscore:
                    doLexicalStateInOctalIntegerUnderscore(theChar);
                    break;
                case lexicalStateStartBinaryInteger:
                    doLexicalStateStartBinaryInteger(theChar);
                    break;
                case lexicalStateInBinaryInteger:
                    doLexicalStateInBinaryInteger(theChar);
                    break;
                case lexicalStateInBinaryIntegerUnderscore:
                    doLexicalStateInBinaryIntegerUnderscore(theChar);
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
                tokenString = workString.substring(curTokenStart);
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenLabel, tokenString, curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateStartSpecialInteger:
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, "0", curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateInDecimalInteger:
                tokenString = workString.substring(curTokenStart);
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, tokenString, curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateInDecimalIntegerUnderscore:
                tokenString = "LEXICAL ERROR at " + stringLocation + ": Missing digit after underscore in decimal constant.";
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, tokenString, curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateStartDecimalReal:
                tokenString = "LEXICAL ERROR at " + stringLocation + ": Must have a digit after a decimal point.";
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, tokenString, curTokenStart));
                skipToNextBreak();
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateInDecimalReal:
                tokenString = workString.substring(curTokenStart);
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenReal, tokenString, curTokenStart));
                stringLocation--;
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateInDecimalRealUnderscore:
                tokenString = "LEXICAL ERROR at " + stringLocation + ": Illegal character in decimal real.";
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, tokenString, curTokenStart));
                skipToNextBreak();
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateInOctalInteger:
                tokenString = workString.substring(curTokenStart);
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, tokenString, curTokenStart));
                stringLocation--;
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateInOctalIntegerUnderscore:
                tokenString = "LEXICAL ERROR at " + stringLocation + ": Missing octet after underscore in octal constant.";
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, tokenString, curTokenStart));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateStartBinaryInteger:
                tokenString = "LEXICAL ERROR at " + stringLocation + ": Missing bit in binary constant.";
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, tokenString, curTokenStart));
                skipToNextBreak();
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateInBinaryInteger:
                tokenString = workString.substring(curTokenStart);
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, tokenString, curTokenStart));
                stringLocation--;
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case lexicalStateInBinaryIntegerUnderscore:
                tokenString = "LEXICAL ERROR at " + stringLocation + ": Missing bits after underscore in binary constant.";
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, tokenString, curTokenStart));
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
        // The current token always starts at this location.

        curTokenStart = stringLocation;

        // Handle letters. Change state to lexicalStateInLabel.

        if(Character.isLetter(theChar))
        {
            lexicalState = EnumLexicalState.lexicalStateInLabel;
            return;
        }

        // Handle a 0. Change state to lexicalStateStartSpecialInteger.

        if(theChar == '0')
        {
            lexicalState = EnumLexicalState.lexicalStateStartSpecialInteger;
            return;
        }

        // Handle a non-zero digit. Change state to lexicalStateInDecimalInteger.

        if((theChar >= '1') && (theChar <= '9'))
        {
            lexicalState = EnumLexicalState.lexicalStateInDecimalInteger;
            return;
        }

        // Handle single character tokens:

        switch(theChar)
        {
            case '+':
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenPlus, String.valueOf('+'), stringLocation));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case '-':
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenMinus, String.valueOf('-'), stringLocation));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case '(':
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenLeftParen, String.valueOf('('), stringLocation));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case ')':
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenRightParen, String.valueOf(')'), stringLocation));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case '.':
                lexicalState = EnumLexicalState.lexicalStateDot;
                break;
            case '*':
                tokenList.add(new LexicalToken(EnumLexicalToken.TokenTimes, String.valueOf('*'), stringLocation));
                lexicalState = EnumLexicalState.lexicalStateStart;
                break;
            case '/':
                lexicalState = EnumLexicalState.lexicalStateDivideOrComment;
                break;
        }
    }

    /**
     * This method processes characters when the lexical analyser is collecting a label.
     * @param theChar   Character to process.
     */
    private void doLexicalStateInLabel(final char theChar)
    {
        // Handle letters, digits, or underscore - just stay in this state.

        if (Character.isLetter(theChar) && Character.isDigit(theChar) || theChar == '_')
        {
            lexicalState = EnumLexicalState.lexicalStateInLabel;
            return;
        }

        // If another character is found, the previous character was the end of the label. So, collect the
        // label token and decrement the stringLocation so that the current character will be reprocessed.
        // Set the state to lexicalStateStart to begin a new token.

        final String labelString = workString.substring(curTokenStart, stringLocation);
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenLabel, labelString, curTokenStart));
        stringLocation--;
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method is called when the first character in a new token is a zero (0), which may be a zero, the
     * start of an octal integer, the start of a hex integer, the start of a binary integer, or the start of
     * a real number.
     * @param theChar   Character to be processed.
     */
    private void doLexicalStateStartSpecialInteger(final char theChar)
    {
        String errorString;

        // If the theChar is in the range 0...7, then this is an octal integer.

        if((theChar >= '0') && (theChar <= '7'))
        {
            lexicalState = EnumLexicalState.lexicalStateInOctalInteger;
            return;
        }

        // If theChar is in the range 8...9, then it is an error (invalid digit in octal integer).

        if((theChar >= '8') && (theChar <= '9'))
        {
            errorString = "LEXICAL ERROR at " + stringLocation + ": Invalid character in octal constant.";
            tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
            skipToNextBreak();
            lexicalState = EnumLexicalState.lexicalStateStart;
            return;
        }

        // If theChar is a 'b', then this is the start of a binary integer.

        if(theChar == 'b')
        {
            lexicalState = EnumLexicalState.lexicalStateStartBinaryInteger;
            return;
        }

        // If theChar is an 'h', then this is the start of a binary integer.

        if(theChar == 'h')
        {
            lexicalState = EnumLexicalState.lexicalStateStartHexInteger;
            return;
        }

        // If theChar is a dot (.), then this is the start of a decimal integer.

        if(theChar == '.')
        {
            lexicalState = EnumLexicalState.lexicalStateStartDecimalReal;
            return;
        }

        // If theChar is a letter (other than 'b' or 'h'), then it is an error.

        if(Character.isLetter(theChar))
        {
            errorString = "LEXICAL ERROR at " + stringLocation + ": Invalid character in octal constant.";
            tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
            skipToNextBreak();
            lexicalState = EnumLexicalState.lexicalStateStart;
            return;
        }

        // Otherwise, we have found a zero (0).

        tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, "0", curTokenStart));
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method processes a decimal integer.
     * @param theChar   Character to process.
     */
    private void doLexicalStateInDecimalInteger(final char theChar)
    {
        // If the character is a digit, just stay in this state.

        if(Character.isDigit(theChar))
        {
            lexicalState = EnumLexicalState.lexicalStateInDecimalInteger;
            return;
        }

        // If the character is an underscore, enter the state to wait for a new character (multiple consecutive
        // underscores are not allowed).

        if(theChar == '_')
        {
            lexicalState = EnumLexicalState.lexicalStateInDecimalIntegerUnderscore;
            return;
        }

        // If the character is a dot (.), then it is the start of a decimal real.

        if(theChar == '.')
        {
            lexicalState = EnumLexicalState.lexicalStateStartDecimalReal;
            return;
        }

        // If the character is a letter, generate an error.

        if(Character.isLetter(theChar))
        {
            final String errorString = "LEXICAL ERROR at " + stringLocation + ": Invalid character in decimal constant.";
            tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
            skipToNextBreak();
            lexicalState = EnumLexicalState.lexicalStateStart;
            return;
        }

        // If another character is found, the previous character was the end of the integer. So, collect the
        // integer token and decrement the stringLocation so that the current character will be reprocessed.
        // Set the state to lexicalStateStart to begin a new token.

        final String integerString = workString.substring(curTokenStart, stringLocation);
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, integerString, curTokenStart));
        stringLocation--;
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method handles a decimal integer after an underscore if found. Note that repeated underscores
     * are not allowed.
     * @param theChar   Character to process.
     */
    private void doLexicalStateInDecimalIntegerUnderscore(final char theChar)
    {
        // If the character is a digit, then return to processing digits in the decimal integer.

        if(Character.isDigit(theChar))
        {
            lexicalState = EnumLexicalState.lexicalStateInDecimalInteger;
            return;
        }

        // Otherwise, generate an error.

        final String errorString = "LEXICAL ERROR at " + stringLocation + ": Missing digits after underscore in decimal constant.";
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
        skipToNextBreak();
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method handles the start of a decimal real after the decimal point. The next character after the decimal
     * point must be a digit.
     * @param theChar   Character to process.
     */
    private void doLexicalStateStartDecimalReal(final char theChar)
    {
        // If the character is a digit, the move into the decimal real state.

        if(Character.isDigit(theChar))
        {
            lexicalState = EnumLexicalState.lexicalStateInDecimalReal;
            return;
        }

        // Otherwise, generate an error.

        final String errorString = "LEXICAL ERROR at " + stringLocation + ": Must have a digit after a decimal point.";
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
        skipToNextBreak();
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method collects digits and an isolated underscore as part of a decimal real.
     * @param theChar   Character to be processed.
     */
    private void doLexicalStateInDecimalReal(final char theChar)
    {
        // If the character is a digit, stay in this state.

        if(Character.isDigit(theChar))
        {
            lexicalState = EnumLexicalState.lexicalStateInDecimalReal;
            return;
        }

        // If the character is an underscore, transition to the state to prevent sequential underscores.

        if(theChar == '_')
        {
            lexicalState = EnumLexicalState.lexicalStateInDecimalRealUnderscore;
            return;
        }

        // If the character is a letter, generate an error.

        if(Character.isLetter(theChar))
        {
            final String errorString = "LEXICAL ERROR at " + stringLocation + ": Illegal character in decimal real.";
            tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
            skipToNextBreak();
            lexicalState = EnumLexicalState.lexicalStateStart;
            return;
        }

        // Otherwise, we have reached the end of the token, so collect it.

        final String realString = workString.substring(curTokenStart, stringLocation);
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenReal, realString, curTokenStart));
        stringLocation--;
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method collects digits during collecting of a decimal real immediately after an underscore.
     * @param theChar   Character to be processed.
     */
    private void doLexicalStateInDecimalRealUnderscore(final char theChar)
    {
        // If the character is a digit, then return to the state for collecting decimal reals.

        if(Character.isDigit(theChar))
        {
            lexicalState = EnumLexicalState.lexicalStateInDecimalReal;
            return;
        }

        // Otherwise, generate an error.

        final String errorString = "LEXICAL ERROR at " + stringLocation + ": Missing digits after underscore in decimal real.";
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
        skipToNextBreak();
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method handles an octal integer after an underscore if found. Note that repeated underscores
     * are not allowed.
     * @param theChar   Character to process.
     */
    private void doLexicalStateInOctalInteger (final char theChar)
    {
        String errorString;

        // If the character is in the range 0...7, then stay in this state.

        if((theChar >= '0') && (theChar <= '7'))
        {
            lexicalState = EnumLexicalState.lexicalStateInOctalInteger;
            return;
        }

        // If the character is in the range 8...9, generate an error.

        if((theChar >= '8') && (theChar <= '9'))
        {
            errorString = "LEXICAL ERROR at " + stringLocation + ": Illegal character in octal integer.";
            tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
            skipToNextBreak();
            lexicalState = EnumLexicalState.lexicalStateStart;
            return;
        }

        // If the character is a letter, than generate an error.

        if(Character.isLetter(theChar))
        {
            errorString = "LEXICAL ERROR at " + stringLocation + ": Illegal character in octal integer.";
            tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
            skipToNextBreak();
            lexicalState = EnumLexicalState.lexicalStateStart;
            return;
        }

        // If the character is an underscore, transition to the state that will prevent sequential underscores.

        if(theChar == '_')
        {
            lexicalState = EnumLexicalState.lexicalStateInOctalIntegerUnderscore;
            return;
        }

        // Otherwise, the end of the token has been found, so collect it.

        final String integerString = workString.substring(curTokenStart, stringLocation);
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, integerString, curTokenStart));
        stringLocation--;
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method collects octets during collecting of a octal real immediately after an underscore.
     * @param theChar   Character to be processed.
     */
    private void doLexicalStateInOctalIntegerUnderscore(final char theChar)
    {
        // If the character is an octet, then return to processing octets in the octal integer.

        if((theChar >= '0') && (theChar <= '7'))
        {
            lexicalState = EnumLexicalState.lexicalStateInOctalInteger;
            return;
        }

        // Otherwise, generate an error.

        final String errorString = "LEXICAL ERROR at " + stringLocation + ": Missing octet after underscore in octal constant.";
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
        skipToNextBreak();
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method starts collection of a binary integer.
     * @param theChar   Character to process.
     */
    private void doLexicalStateStartBinaryInteger(final char theChar)
    {
        // If the character is in the range 0...1, then collect it and transition to collecting a binary integer.

        if((theChar == '0') || (theChar == '1'))
        {
            lexicalState = EnumLexicalState.lexicalStateInBinaryInteger;
            return;
        }

        // Otherwise, generate an error.

        final String errorString = "LEXICAL ERROR at " + stringLocation + ": Invalid character in binary constant.";
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
        skipToNextBreak();
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method collects bits for a binary integer.
     * @param theChar   Character to process.
     */
    private void doLexicalStateInBinaryInteger(final char theChar)
    {
        String errorString;

        // If the character is in the range 0...1, just stay in this state.

        if((theChar == '0') || (theChar == '1'))
        {
            lexicalState = EnumLexicalState.lexicalStateInBinaryInteger;
            return;
        }

        // If the character is an underscore, enter the state to ensure that there are not sequential underscores.

        if(theChar == '_')
        {
            lexicalState = EnumLexicalState.lexicalStateInBinaryIntegerUnderscore;
            return;
        }

        // If the character is in the range 2...9, generate an error.

        if((theChar >= '2') && (theChar <= '9'))
        {
            errorString = "LEXICAL ERROR at " + stringLocation + ": Invalid character in binary constant.";
            tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
            skipToNextBreak();
            lexicalState = EnumLexicalState.lexicalStateStart;
            return;
        }

        // If the character is a letter, generate an error.

        if(Character.isLetter(theChar))
        {
            errorString = "LEXICAL ERROR at " + stringLocation + ": Invalid character in binary constant.";
            tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
            skipToNextBreak();
            lexicalState = EnumLexicalState.lexicalStateStart;
            return;
        }

        // Otherwise, the end of the token has been reached, so collect the token.

        final String integerString = workString.substring(curTokenStart, stringLocation);
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenInteger, integerString, curTokenStart));
        stringLocation--;
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method collects bits during collecting of a binary real immediately after an underscore.
     * @param theChar   Character to be processed.
     */
    private void doLexicalStateInBinaryIntegerUnderscore(final char theChar)
    {
        // If the character is a bit, then return to processing bit in the decimal integer.

        if((theChar >= '0') && (theChar <= '1'))
        {
            lexicalState = EnumLexicalState.lexicalStateInBinaryInteger;
            return;
        }

        // Otherwise, generate an error.

        final String errorString = "LEXICAL ERROR at " + stringLocation + ": Missing bit after undersocre in binary constant.";
        tokenList.add(new LexicalToken(EnumLexicalToken.TokenError, errorString, curTokenStart));
        skipToNextBreak();
        lexicalState = EnumLexicalState.lexicalStateStart;
    }

    /**
     * This method skips to the next clearly defined token after an error has been detected.
     */
    private void skipToNextBreak()
    {
        for(int i = stringLocation + 1; i < stringLength; i++)
        {
            char theChar = workString.charAt(i);

            if((theChar == ' ') || (theChar == '+') || (theChar == '-') || (theChar == '*') || (theChar == '/'))
                break;
        }

        if(stringLocation != stringLength)
            stringLocation--;
    }
}
