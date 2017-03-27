package qcfcs_parsing;

import java.util.ArrayList;

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
//      20170325    D.E. Reese          Added getStringValue(), setStringValue(), getSourceStart(), setSourceStart().
//      20170326    D.E. Reese          Added integerValue, realValue, getIntegerValue(), setIntegerValue(),
//                                      getRealValue(), setRealValue().
//      20170327    D.E. Reese          Fixed exception in setRealValue (failed on TokenInteger, not TokenReal).
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
     * Value of an integer token.
     */
    private int integerValue;

    /**
     * Value of a real token.
     */
    private double realValue;

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
     * @throws IllegalArgumentException Thrown if theSourceStart < -1.
     */
    public LexicalToken(final EnumLexicalToken tokenType, final String theString, final int theSourceStart)
    {
        if(theSourceStart < -1) throw new IllegalArgumentException("theSourceStart < -1.");
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
        integerValue = 0;
        realValue = 0.0;
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
     * This method returns the string value of a token.
     * @param theToken  Token whose string value is to be returned.
     * @return  String value of theToken.
     * @throws IllegalArgumentException Thrown if theToken is null.
     */
    public static String getStringValue (final LexicalToken theToken) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");
        return theToken.stringValue;
    }

    /**
     * This method sets the string value of a given token.
     * @param theToken  Token whose string value is to be set.
     * @param theString String to which theToken's string value is to be set.
     * @return          Old string value of theToken.
     * @throws IllegalArgumentException Thrown if theToken is null.
     */
    public static String setStringValue(final LexicalToken theToken, final String theString) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");
        String oldValue = theToken.stringValue;
        theToken.stringValue = theString;
        return oldValue;
    }

    /**
     * This method returns the source start (i.e., position in the string where the token starts) of a given token.
     * @param theToken  Token whose source start is to be returned.
     * @return          Value of the source start for theToken.
     * @throws IllegalArgumentException Thrown if theToken is null.
     */
    public static int getSourceStart(final LexicalToken theToken) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");
        return theToken.sourceStart;
    }

    /**
     * This method sets the source start (i.e., position in the string where the token starts) of a given token. The
     * new position must be >= 0.
     * @param theToken  Token whose source start is to be set.
     * @param theStart  New source start (i.e., position in the string where the token starts).
     * @return          Old value of the source start of theToken.
     * @throws IllegalArgumentException Thrown if theToken is null or theStart < 0.
     */
    public static int setSourceStart(final LexicalToken theToken, final int theStart) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");
        if(theStart < 0) throw new IllegalArgumentException("theStart < 0.");
        int oldValue = theToken.sourceStart;
        theToken.sourceStart = theStart;
        return oldValue;
    }

    /**
     * This method returns the integer value of a given integer token.
     * @param theToken  Token whose integer value is to be returned.
     * @return          Integer value of the token.
     * @throws IllegalArgumentException Thrown if theToken is null or theToken is not an EnumLexicalToken.TokenInteger.
     */
    public static int getIntegerValue(final LexicalToken theToken) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");
        if(theToken.theTokenType != EnumLexicalToken.TokenInteger)
            throw new IllegalArgumentException("theToken is not of type EnumLexicalToken.TokenInteger.");

        return theToken.integerValue;
    }

    /**
     * This method sets the integer value of a given integer token.
     * @param theToken  Token whose integer value is to be set.
     * @param theInt    New value for the integer token.
     * @return          Old value of the integer token.
     * @throws IllegalArgumentException Thrown if theToken is null or theToken is not an EnumLexicalToken.TokenInteger.
     */
    public static int setIntegerValue(final LexicalToken theToken, final int theInt) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");
        if(theToken.theTokenType != EnumLexicalToken.TokenInteger)
            throw new IllegalArgumentException("theToken is not of type EnumLexicalToken.TokenInteger.");

        int oldValue = theToken.integerValue;
        theToken.integerValue = theInt;
        return oldValue;
    }

    /**
     * This method returns the real value of a given real token.
     * @param theToken  Token whose real value is to be returned.
     * @return          real value of the token.
     * @throws IllegalArgumentException Thrown if theToken is null or theToken is not an EnumLexicalToken.TokenReal.
     */
    public static double getRealValue(final LexicalToken theToken) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");
        if(theToken.theTokenType != EnumLexicalToken.TokenReal)
            throw new IllegalArgumentException("theToken is not of type EnumLexicalToken.TokenReal.");
        return theToken.realValue;
    }

    /**
     * This method sets the real value of a given real token.
     * @param theToken  Token whose real value is to be set.
     * @param theReal    New value for the real token.
     * @return          Old value of the real token.
     * @throws IllegalArgumentException Thrown if theToken is null or theToken is not an EnumLexicalToken.TokenReal.
     */
    public static double setRealValue(final LexicalToken theToken, final double theReal) throws IllegalArgumentException
    {
        if(theToken == null) throw new IllegalArgumentException("theToken == null.");
        if(theToken.theTokenType != EnumLexicalToken.TokenReal)
            throw new IllegalArgumentException("theToken is not of type EnumLexicalToken.TokenReal.");

        double oldValue = theToken.realValue;
        theToken.realValue = theReal;
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

    /**
     * This method returns the string value of this token.
     * @return  String value of this token.
     */
    public String getStringValue()
    {
        return LexicalToken.getStringValue(this);
    }

    /**
     * This method sets the string value of this token.
     * @param theString New string to which this token's string value is to be set.
     * @return          Old string value of this token.
     */
    public String setStringValue(final String theString)
    {
        return LexicalToken.setStringValue(this, theString);
    }

    /**
     * This method returns the source start (i.e., the position in the source string where the token starts) for this token.
     * @return  Source start position of the token.
     */
    public int getSourceStart()
    {
        return LexicalToken.getSourceStart(this);
    }

    /**
     * This method sets the source start (i.e., position in the string where the token starts) of this token. The
     * new position must be >= 0.
     * @param theStart  New source start (i.e., position in the string where the token starts).
     * @return          Old value of the source start of theToken.
     */
    public int setSourceStart(final int theStart)
    {
        return LexicalToken.setSourceStart(this, theStart);
    }

    /**
     * This method returns the integer value of this token.
     * @return  Integer value of this token.
     */
    public int getIntegerValue()
    {
        return LexicalToken.getIntegerValue(this);
    }

    /**
     * This method sets the integer value of this token.
     * @param theInt    New value of this integer token.
     * @return          Old value of this integer token.
     */
    public int setIntegerValue(final int theInt)
    {
        return LexicalToken.setIntegerValue(this, theInt);
    }

    /**
     * This method returns the real value of this token.
     * @return  Real value of this token.
     */
    public double getRealValue()
    {
        return LexicalToken.getRealValue(this);
    }

    /**
     * This method sets the real value of this token.
     * @param theReal    New value of this real token.
     * @return          Old value of this real token.
     */
    public double setRealValue(final double theReal)
    {
        return LexicalToken.setRealValue(this, theReal);
    }
}
