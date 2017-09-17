package qcfcs_parsing;

import java.util.ArrayList;

/**
 * This class extends the AbstractParseTreeExpressionEntry to implement a level 3 expression (which contains subexpressions
 * joined by binary addition and subtraction operators.
 * Created by reesede on 9/17/2017.
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
//      20170917    D.E. Reese          Creation.


public class ParseTreeExpressionL3Entry extends AbstractParseTreeExpressionEntry
{
    /**
     * Constructor for Level 4 expressions.
     * @param theAnalyser   Lexical analyser to use when parsing the expression.
     * @throws IllegalArgumentException Thrown if theAnalyser is null.
     */
    public ParseTreeExpressionL3Entry(final LexicalAnalyser theAnalyser) throws IllegalArgumentException
    {
        super(EnumParseTreeEntry.ParseTreeEntry_ExpressionL4, theAnalyser);
    }

    /**
     * Abstract method to parse the tree based on the lexical analyser given for the tree. This method assumes that
     * the first token in the parse tree must be determined by the lexical analyser.
     * @return  A list of parser errors found during parsing. Null if no errors were found.
     * @throws  NullPointerException    Thrown if the lexical analyser for the tree is null.
     */
    public ArrayList<ParserError> parse() throws NullPointerException
    {
        ArrayList<ParserError> errorList = null;

        return errorList;
    }

    /**
     * Abstract method to parse the tree based on an existing token, which will be the first token of the entry.
     * @param initialToken  Initial token of parse tree entry detected at a higher level.
     * @return  List of parser errors found during parsing. Null if no errors were found.
     * @throws IllegalArgumentException Thrown if initialToken was null or erroneous.
     * @throws NullPointerException Thrown if the lexical analyser for the tree is null.
     */
    public ArrayList<ParserError> parse(final LexicalToken initialToken)
            throws IllegalArgumentException,NullPointerException
    {
        return null;
    }
}
