package qcfcs_parsing;

import java.util.ArrayList;

/**
 * This abstract class extends the AbstractParseTreeEntry class to define parse trees for expressions. It must be
 * extended into concrete classes for the various levels of the parse tree.
 * Created by reesede on 9/16/2017.
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
//      20170916    D.E. Reese          Creation.

public abstract class AbstractParseTreeExpressionEntry extends AbstractParseTreeEntry
{

    /**
     * Operators connecting subexpressions. Note that operators[0] connects subTrees[0] and subTrees[1].
     */
    private ArrayList<EnumExpressionOperator> operators;

    /**
     * Constructor to create a new expression.
     * @param theEntryType  Type of parse tree entry.
     * @param theLexicalAnalyser    Lexical analyser used to when parsing the expreesion.
     */
    public AbstractParseTreeExpressionEntry(final EnumParseTreeEntry theEntryType,
                                  final LexicalAnalyser theLexicalAnalyser)
    {
        super(theEntryType,theLexicalAnalyser);
        initialize();
    }

    /**
     * This method initializes the expression parse tree entry.
     */
    private void initialize()
    {
        operators=new ArrayList<EnumExpressionOperator>();
    }

    /**
     * This method appends an operator to the list of operators joining the subexpressions to this expression.
     * @param theOperator   Operator to append to list of operators.
     * @throws IllegalArgumentException Thrown if theOperator is ExpressionOperatorUnknown.
     * @throws NullPointerException Thrown if operators == null (internal software error).
     */
    public void appendOperator(final EnumExpressionOperator theOperator)
            throws IllegalArgumentException,NullPointerException
    {
        if(theOperator == EnumExpressionOperator.ExpressionOperatorUnknown)
            throw new IllegalArgumentException("theOperator == ExpressionOperatorUnknown.");
        if(operators == null) throw new NullPointerException("internal software error: operators == null.");
        operators.add(theOperator);
    }

    /**
     * This method returns the operator indexed by index from the list of operators.
     * @param index Index of operator to return.
     * @return  Operator in list indexed by index.
     * @throws IllegalArgumentException Thrown if index < 0 or index >= the size of the list of operators.
     * @throws NullPointerException Thrown if operators == null (internal software error).
     */
    public EnumExpressionOperator getOperator(final int index) throws IllegalArgumentException,NullPointerException
    {
        if(index < 0) throw new IllegalArgumentException("index < 0");
        if(operators==null) throw new NullPointerException("internal software error: operators == null.");
        if(index >= operators.size()) throw new IllegalArgumentException("index >= operators.size().");
        return (operators.get(index));
    }

    public int getNumberOfOperators() throws NullPointerException
    {
        if (operators == null) throw new NullPointerException("internal software error: operators == null.");
        return(operators.size());
    }

}
