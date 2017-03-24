package qcfcs_parsing;

/**
 * This class implements lexical analysis tokens which are created from text. These tokens are used for when structures
 * are to be parsed from text.
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

public class LexicalToken
{
    /**
     * Type of token.
     */
    private EnumLexicalToken theTokenType;

    /**
     * String value of token.
     */
    private String stringValue;

    /**
     * Character position of start of token in source string.
     */
    private int sourceStart;

    /**
     * Default constructor, which creates a new token of type TokenUnknown.
     */
    public LexicalToken()
    {
        initializeToken();
    }

    /**
     * Constructor to create a token with given type, string, and source start position.
     * @param tokenType         Type of token to create.
     * @param theString         String value of token to create.
     * @param theSourceStart    Start position of token.
     */
    public LexicalToken(final EnumLexicalToken tokenType, final String theString, final int theSourceStart)
    {
        initializeToken();
        theTokenType = tokenType;
        stringValue = theString;
        sourceStart = theSourceStart;
    }

    /**
     * This method initializes a token to default values.
     */
    private void initializeToken()
    {
        theTokenType = EnumLexicalToken.TokenUnknown;
        stringValue = null;
        sourceStart = -1;
    }

    /**
     * This method returns the token type of a given token.
     * @param theToken  Token whose type is to be returned.
     * @return  Enumerated value of theToken's tokenType.
     * @throws IllegalArgumentException Thrown if theToken is null.
     */
    public static EnumLexicalToken getTokenType (final LexicalToken theToken) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");
        return theToken.theTokenType;
    }

    /**
     * This method sets the type of a token to a given value.
     * @param theToken  Token whose type is to be set.
     * @param theType   New type of token.
     * @return          Old value of theToken's token type.
     * @throws IllegalArgumentException Thrown if theToken is null.
     */
    public static EnumLexicalToken setTokenType (final LexicalToken theToken, final EnumLexicalToken theType) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");

        EnumLexicalToken oldValue = theToken.theTokenType;
        theToken.theTokenType = theType;
        return oldValue;
    }

    /**
     * This method returns the token type of this token.
     * @return  Enumerated value of this token's tokenType.
     */
    public EnumLexicalToken getTokenType()
    {
        return LexicalToken.getTokenType(this);
    }

    /**
     * This method sets the type of this token to a given value.
     * @param theType   New type of token.
     * @return          Old value of this token's token type.
     */
    public EnumLexicalToken setTokenType (final EnumLexicalToken theType)
    {
        return LexicalToken.setTokenType(this, theType);
    }
}
