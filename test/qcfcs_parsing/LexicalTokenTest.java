package qcfcs_parsing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements JUnit tests for the LexicalToken class.
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
//      20170325    D.E. Reese          Added setAndGetTokenType(), setAndGetStringValue(), setAndGetSourceStart().
//

class LexicalTokenTest
{
    @Test
    void constructors()
    {
        LexicalToken testToken;

        testToken = new LexicalToken();
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenUnknown);
        assertTrue(testToken.getStringValue() == null);
        assertTrue(testToken.getSourceStart() == -1);

        testToken = new LexicalToken(EnumLexicalToken.TokenInteger, "5", 10);
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(testToken.getStringValue().compareTo("5") == 0);
        assertTrue(testToken.getSourceStart() == 10);

        assertThrows(IllegalArgumentException.class, () -> {
            LexicalToken exceptionToken = new LexicalToken(EnumLexicalToken.TokenLabel, "Sam", -2);
        });

    }

    @Test
    void setAndGetTokenType()
    {
        LexicalToken testToken;
        EnumLexicalToken theTokenType;

        testToken = new LexicalToken();
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenUnknown);
        assertTrue(testToken.getStringValue() == null);
        assertTrue(testToken.getSourceStart() == -1);

        theTokenType = LexicalToken.setTokenType(testToken, EnumLexicalToken.TokenInteger);
        assertTrue(theTokenType == EnumLexicalToken.TokenUnknown);
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(testToken.getStringValue() == null);
        assertTrue(testToken.getSourceStart() == -1);

        theTokenType = LexicalToken.getTokenType(testToken);
        assertTrue(theTokenType == EnumLexicalToken.TokenInteger);
        assertTrue(testToken.getStringValue() == null);
        assertTrue(testToken.getSourceStart() == -1);

        assertThrows(IllegalArgumentException.class, () -> {
            LexicalToken.setTokenType(null,EnumLexicalToken.TokenPlus);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            LexicalToken.getTokenType(null);
        });
    }

    @Test
    void setAndGetStringValue()
    {
        LexicalToken testToken;
        String theString;

        testToken = new LexicalToken();
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenUnknown);
        assertTrue(testToken.getStringValue() == null);
        assertTrue(testToken.getSourceStart() == -1);

        theString = LexicalToken.setStringValue(testToken, "Sam");
        assertTrue(theString == null);
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenUnknown);
        assertTrue(testToken.getStringValue().compareTo("Sam") == 0);
        assertTrue(testToken.getSourceStart() == -1);

        theString = LexicalToken.setStringValue(testToken, null);
        assertTrue(theString.compareTo("Sam") == 0);
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenUnknown);
        assertTrue(testToken.getStringValue() == null);
        assertTrue(testToken.getSourceStart() == -1);

        assertThrows(IllegalArgumentException.class, () -> {
            LexicalToken.setStringValue(null, "Sam");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            LexicalToken.getStringValue(null);
        });
    }

    @Test
    void setAndGetSourceStart()
    {
        LexicalToken testToken;
        int theSourceStart;

        testToken = new LexicalToken();
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenUnknown);
        assertTrue(testToken.getStringValue() == null);
        assertTrue(testToken.getSourceStart() == -1);

        theSourceStart = LexicalToken.setSourceStart(testToken, 5);
        assertTrue(theSourceStart == -1);
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenUnknown);
        assertTrue(testToken.getStringValue() == null);
        assertTrue(testToken.getSourceStart() == 5);

        theSourceStart = LexicalToken.setSourceStart(testToken, 10);
        assertTrue(theSourceStart == 5);
        assertTrue(testToken.getTokenType() == EnumLexicalToken.TokenUnknown);
        assertTrue(testToken.getStringValue() == null);
        assertTrue(testToken.getSourceStart() == 10);

        assertThrows(IllegalArgumentException.class, () -> {
            LexicalToken.setSourceStart(null, 5);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            LexicalToken.setSourceStart(testToken, -1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            LexicalToken.getSourceStart(null);
        });
    }
}