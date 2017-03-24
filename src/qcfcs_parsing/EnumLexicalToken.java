package qcfcs_parsing;

/**
 * This enumeration defines the tokens that can be parsed out of text.
 * Created by reesede on 3/24/2017.
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
//      20170324    D.E. Reese          Creation.
//

public enum EnumLexicalToken
{
    /**
     * Used to identify an unknown or null token.
     */
    TokenUnknown,

    /**
     * Label token which is not a keyword.
     */
    TokenLabel,

    /**
     * Integer number token.
     */
    TokenInteger,

    /**
     * Real number token.
     */
    TokenReal,

    /**
     * Plus sign token.
     */
    TokenPlus,

    /**
     * Minus sign token.
     */
    TokenMinus,

    /**
     * Times (*) sign token.
     */
    TokenTimes,

    /**
     * Keyword for I, i.e., sqrt (-1)
     */
    KeywordI
}