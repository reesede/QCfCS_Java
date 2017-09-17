package qcfcs_parsing;

import java.util.ArrayList;

/**
 * This abstract class defines the basis for entries in a parse tree. It must be extended for different types of
 * parse tree entries.
 * Created by reesede on 9/14/2017.
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
//      20170914    D.E. Reese          Creation as stub.
//      20170917    D.E. Reese          Added validTree, setValidTree (), and isValid().
//

public abstract class AbstractParseTreeEntry
{
    /**
     * Type of parse tree entry.
     */
    private EnumParseTreeEntry  entryType;

    /**
     * Sub-trees which make up this tree.
     */
    private ArrayList<AbstractParseTreeEntry> subTrees;

    /**
     * Lexical analyser to use to collect more tokens.
     */
    private LexicalAnalyser lexicalAnalyser;

    /**
     * Indicates if the parse tree is valid (i.e., error-free).
     */
    private boolean validTree;

    /**
     * Constructor for the tree parse tree.
     * @param theEntryType  Type of entry in the parse tree.
     * @param theLexicalAnalyser    Lexical analyzer to use to collect tokens.
     */
    public AbstractParseTreeEntry(final EnumParseTreeEntry theEntryType,
                                  final LexicalAnalyser theLexicalAnalyser) throws IllegalArgumentException
    {
        if (theEntryType == EnumParseTreeEntry.ParseTreeEntry_Unknown)
            throw new IllegalArgumentException("theEntryType == ParsetreeEntryException_Unknown");
        if (theLexicalAnalyser == null)
            throw new IllegalArgumentException("theLexicalAnalyser == null");
        initialize();
        entryType = theEntryType;
        lexicalAnalyser = theLexicalAnalyser;
    }

    /**
     * This method initializes an empty AbstractParseTreeEntry to default values.
     */
    private void initialize()
    {
        entryType = EnumParseTreeEntry.ParseTreeEntry_Unknown;
        subTrees =new ArrayList<AbstractParseTreeEntry>();
        lexicalAnalyser = null;
        validTree = true;       // Assume that an empty parse tree is valid.
    }

    /**
     * This method sets the entry type of the parse tree.
     * @param newEntry
     */
    public void setEntryType(final EnumParseTreeEntry newEntry)
    {
        entryType = newEntry;
    }

    /**
     * This method returns the entry type of the parse tree.
     * @return  Entry type of parse tree.
     */
    public EnumParseTreeEntry getEntryType()
    {
        return entryType;
    }

    /**
     * This method returns the lexical analyser used to parse the parse tree.
     * @return
     */
    public LexicalAnalyser getLexicalAnalyser()
    {
        return lexicalAnalyser;
    }

    /**
     * This method adds a sub-tree to the end of the list of subtrees.
     * @param theTree Tree to add to the end of the list of subtrees.
     * @throws IllegalArgumentException Thrown if theTree is null.
     * @throws NullPointerException Thrown if subTrees is null.
     */
    public void appendSubTree(final AbstractParseTreeEntry theTree)
            throws IllegalArgumentException, NullPointerException
    {
        if (theTree == null) throw new IllegalArgumentException("theTree == null.");
        if (subTrees == null) throw new NullPointerException("internal software error: subTrees == null.");
        subTrees.add(theTree);
    }

    /**
     * This method returns a subtrees from the list of subtrees indexed by the index parameter.
     * @param index Entry in the list of subtrees to return.
     * @return  Subtree indexed by index.
     * @throws IllegalArgumentException Thrown if index < 0 or >= the size of the subTrees list.
     * @throws NullPointerException Thrown if the list of subtrees is null (internal software error).
     */
    public AbstractParseTreeEntry getSubTree(final int index)
            throws IllegalArgumentException, NullPointerException
    {
        if (index < 0) throw new IllegalArgumentException("index < 0.");
        if (subTrees == null) throw new NullPointerException("internal software error: subTrees == null.");
        if (index >= subTrees.size()) throw new IllegalArgumentException("index >= subTrees.size().");
        return(subTrees.get(index));
    }

    /**
     * This method returns the number of subTrees of the tree.
     * @return  Returns the number of subtrees in the tree.
     * @throws NullPointerException
     */
    public int getNumberOfSubTrees() throws NullPointerException
    {
        if (subTrees == null) throw new NullPointerException("internal software error: subTrees == null.");
        return(subTrees.size());
    }

    /**
     * This method sets the indication as to whether or not the parse tree is valid.
     * @param validTree true if the tree is valid; false if it is invalid and contains errors.
     */
    public void setValidTree(final boolean validTree)
    {
        this.validTree = validTree;
    }

    /**
     * This method returns an indication as to whether or not the parse tree is valid.
     * @return  true if the tree if valid; false if it is invalid and contains errors.
     */
    public boolean isValid()
    {
        return validTree;
    }

    /**
     * Abstract method to parse the tree based on the lexical analyser given for the tree. This method assumes that
     * the first token in the parse tree must be determined by the lexical analyser.
     * @return  A list of parser errors found during parsing. Null if no errors were found.
     * @throws  NullPointerException    Thrown if the lexical analyser for the tree is null.
     */
    public abstract ArrayList<ParserError> parse() throws NullPointerException;

    /**
     * Abstract method to parse the tree based on an existing token, which will be the first token of the entry.
     * @param initialToken  Initial token of parse tree entry detected at a higher level.
     * @return  List of parser errors found during parsing. Null if no errors were found.
     * @throws IllegalArgumentException Thrown if initialToken was null or erroneous.
     * @throws NullPointerException Thrown if the lexical analyser for the tree is null.
     */
    public abstract ArrayList<ParserError> parse(final LexicalToken initialToken)
            throws IllegalArgumentException,NullPointerException;
}

