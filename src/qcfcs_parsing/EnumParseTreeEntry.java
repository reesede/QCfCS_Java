package qcfcs_parsing;

/**
 * This enumeration defines the types of entries in the parser's parse tree.
 * Created by reesede on 9/13/2017.
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
//      20170913    D.E. Reese          Creation.
//

public enum EnumParseTreeEntry
{
    /**
     * Unknown level of expression.
     */
    ParseTreeEntryExpression_Unknown,

    /**
     * Level 4 Expression (+, -)
     */
    ParseTreeEntryExpression_L4,

    /**
     * Level 3 Expression (*, /)
     */
    ParseTreeEntryExpression_L3,

    /**
     * Level 2 Expression (unary +, unary -)
     */
    ParseTreeEntryExpression_L2,

    /**
     * Level 1 Expression (Exponential)
     */
    ParseTreeEntryExpression_L1,

    /**
     * Level 0 Expression (Single token or parenthetical expression)
     */
    ParseTreeEntryExpression_L0,

    /**
     * Exponent
     */
    ParseTreeEntryExpression_Exp
}
