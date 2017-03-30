package qcfcs_parsing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements JUnit testing for the lexical analyser.
 * Created by reesede on 3/30/2017.
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
//      20170330    D.E. Reese          Creation.

class LexicalAnalyserTest
{
    @Test
    void constructors()
    {
    }

    @Test
    void testLabel()
    {
        ArrayList<LexicalToken> theTokenList;
        LexicalAnalyser         theAnalyser;
        LexicalToken            theToken;

        // Test creation of a single label of a single letter.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("A");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenLabel);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("A") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Test creation of a single label of multiple letters.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("Sam");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenLabel);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("Sam") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Test creation of a single label of multiple letters, numbers, and underscores.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("Sam_I_Am_0_is__Not");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenLabel);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("Sam_I_Am_0_is__Not") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Test creation of a single label of multiple letters ending with a space.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("Sam ");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenLabel);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("Sam") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Test creation of a single label of multiple letters ending with a non-label token.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("Sam(");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenLabel);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("Sam") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

    }

}