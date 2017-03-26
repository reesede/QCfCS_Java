package qcfcs_parsing;

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

public class LexicalAnalyzer
{
    /**
     * State of lexical analysis - beginning of analysis.
     */
    private static final int lexicalStateStart = 0;

    /**
     * State of lexical analysis - in a label.
     */
    private static final int lexicalStateInLabel = 1;

    /**
     * State of lexical analysis - in a decimal integer.
     */
    private static final int lexicalStateInInt = 2;

    /**
     * State of lexical analysis - in a real number.
     */
    private static final int lexicalStateInReal = 3;

    /**
     * State of lexical analysis - in an octal integer.
     */
    private static final int lexicalStateInOctalInteger = 4;

    /**
     * State of lexical analysis - in a hexidecimal integer.
     */
    private static final int lexicalStateInHexInteger = 5;

    /**
     * State of lexical analysis - in a binary integer.
     */
    private static final int lexicalStateInBinaryInteger = 6;

    /**
     * State of lexical analysis - found a divide (/) or the beginning of a comment (//).
     */
    private static final int lexicalStateDivideOrComment = 4;

    /**
     * Decimal integer.
     */
    private static final int integerDecimal = 0;

    /**
     * Octal integer.
     */
    private static final int integerOctal = 1;

    /**
     * Hexidecimal integer.
     */
    private static final int integerHex = 2;

    /**
     * Binary integer.
     */
    private static final int integerBinary = 3;


}
