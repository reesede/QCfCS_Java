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
//      20170425    D.E. Reese          Added isMoreTextNeeded(), testDecimalInteger().
//      20170428    D.E. Reese          Added testOctalInteger().
//      20170501    D.E. Reese          Added testHexidecimalInteger().
//      20170505    D.E. Reese          Changed start of hex integers from "0h" to "0x" and "0X".
//      20170506    D.E. Reese          Added testBinaryInteger(), testDot(), testComment().
//      20170507    D.E. Reese          Added testSmallTokens(), testKeywords().
//      20170902    D.E. Reese          Added code in testSmallTokens() to handle TokenUpArrow.
//

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

    @Test
    public void testDecimalInteger()
    {
        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        // Test 0.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theTokenList = theAnalyser.analyseString(" 0 ");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(1).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(1).getStringValue().compareTo("0") == 0);
        assertTrue(theTokenList.get(1).getSourceStart() == 1);

        theTokenList = theAnalyser.analyseString("000000000");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 3);
        assertTrue(theTokenList.get(2).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(2).getStringValue().compareTo("000000000") == 0);
        assertTrue(theTokenList.get(2).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0+");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
        assertTrue(theTokenList.get(1).getTokenType() == EnumLexicalToken.TokenPlus);

        // Test non-zero integer.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("1");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("1") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString(" 1 ");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("1") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 1);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString(" 123 ");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("123") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 1);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("123_456 ");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("123_456") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("123_456_789");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("123_456_789") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Check errors related to underscores and characters.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("12_");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 3: Missing digits after underscore in decimal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("12__3");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 3: Missing digits after underscore in decimal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("123abc");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 3: Invalid character in decimal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
    }

    @Test
    public void testOctalInteger()
    {
        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        // Test 0.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("00");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("00") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Test non-zero values.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("01");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("01") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString(" 01 ");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("01") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 1);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("012345670");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("012345670") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("007654321");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("007654321") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("01234_5670");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("01234_5670") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("012_34_56_70");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("012_34_56_70") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0123(");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0123") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Test errors related to underscore and letters in constant.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("012_");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 4: Missing octets after underscore in octal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("012__3");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 4: Missing octets after underscore in octal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0123abc");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 4: Invalid character in octal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
    }

    @Test
    public void testHexidecimalInteger()
    {
        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        // Test 0.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0x0");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0x0") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0X0");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0X0") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0x00");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0x00") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Test non-zero values.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0x1");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0x1") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0xa");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0xa") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0xA");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0xA") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0XA");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0XA") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0xb");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0xb") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0xc");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0xc") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0xe");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0xe") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0xf");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0xf") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0x0123456789abcdef");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0x0123456789abcdef") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0x0123456789ABCDEF");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0x0123456789ABCDEF") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0xFEDCBA9876543210");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0xFEDCBA9876543210") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0xFED_CBA_987_654_321_0+");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0xFED_CBA_987_654_321_0") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Test errors related to underscore and letters in constant.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0x12_");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 5: Missing hextet after underscore in hexidecimal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0x12__3");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 5: Missing hextet after underscore in hexidecimal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0x123ghi");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 5: Invalid character in hexidecimal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0xg");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 2: Invalid character in hexidecimal constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
    }

    @Test
    public void testBinaryInteger()
    {
        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        // Test 0.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0b0");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0b0") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0B0");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0B0") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0B00");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0B00") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        // Test non-zero values.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0b1");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0b1") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0b10100011");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0b10100011") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0b1000_0101");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0b1000_0101") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0B1000_0101_1111");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("0B1000_0101_1111") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("+0B1000_0101_1111*");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 3);
        assertTrue(theTokenList.get(1).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(1).getStringValue().compareTo("0B1000_0101_1111") == 0);
        assertTrue(theTokenList.get(1).getSourceStart() == 1);

        // Test errors related to underscore and letters in constant.

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0b1_");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 4: Missing bit after underscore in binary constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0b11__1");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 5: Missing bit after underscore in binary constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0b12");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 3: Invalid character in binary constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("0b2");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 2);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenError);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("LEXICAL ERROR at 2: Invalid character in binary constant.") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
    }

    @Test
    public void testDot()
    {
        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString(".012 .sam");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 3);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenReal);
        assertTrue(theTokenList.get(0).getStringValue().compareTo(".012") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
        assertTrue(theTokenList.get(1).getTokenType() == EnumLexicalToken.TokenDot);
        assertTrue(theTokenList.get(1).getStringValue().compareTo(".") == 0);
        assertTrue(theTokenList.get(1).getSourceStart() == 5);
        assertTrue(theTokenList.get(2).getTokenType() == EnumLexicalToken.TokenLabel);
        assertTrue(theTokenList.get(2).getStringValue().compareTo("sam") == 0);
        assertTrue(theTokenList.get(2).getSourceStart() == 6);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString(" . ");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenDot);
        assertTrue(theTokenList.get(0).getStringValue().compareTo(".") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 1);
    }

    @Test
    public void testSlash()
    {
        // Note that comments will be tested in testComment().

        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("/");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenDivide);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("/") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        assertTrue(theTokenList != null);
        theTokenList = theAnalyser.analyseString(" / ");
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenDivide);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("/") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 1);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("sam/2");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 3);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenLabel);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("sam") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
        assertTrue(theTokenList.get(1).getTokenType() == EnumLexicalToken.TokenDivide);
        assertTrue(theTokenList.get(1).getStringValue().compareTo("/") == 0);
        assertTrue(theTokenList.get(1).getSourceStart() == 3);
        assertTrue(theTokenList.get(2).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(2).getStringValue().compareTo("2") == 0);
        assertTrue(theTokenList.get(2).getSourceStart() == 4);
    }

    @Test
    public void testComment()
    {
        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("//");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 0);
        assertFalse(theAnalyser.isMoreTextNeeded());

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("/**/");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 0);
        assertFalse(theAnalyser.isMoreTextNeeded());

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("/* This is a comment */");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 0);
        assertFalse(theAnalyser.isMoreTextNeeded());

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("/***/");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 0);
        assertFalse(theAnalyser.isMoreTextNeeded());

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("/****/");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 0);
        assertFalse(theAnalyser.isMoreTextNeeded());

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("/*****/");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 0);
        assertFalse(theAnalyser.isMoreTextNeeded());

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("2+2 // add 2 to 2.");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 3);
        assertFalse(theAnalyser.isMoreTextNeeded());
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("2") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
        assertTrue(theTokenList.get(1).getTokenType() == EnumLexicalToken.TokenPlus);
        assertTrue(theTokenList.get(1).getStringValue().compareTo("+") == 0);
        assertTrue(theTokenList.get(1).getSourceStart() == 1);
        assertTrue(theTokenList.get(2).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(2).getStringValue().compareTo("2") == 0);
        assertTrue(theTokenList.get(2).getSourceStart() == 2);
        assertFalse(theAnalyser.isMoreTextNeeded());

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("2+2 /* add 2 to 2. */");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 3);
        assertFalse(theAnalyser.isMoreTextNeeded());
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("2") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
        assertTrue(theTokenList.get(1).getTokenType() == EnumLexicalToken.TokenPlus);
        assertTrue(theTokenList.get(1).getStringValue().compareTo("+") == 0);
        assertTrue(theTokenList.get(1).getSourceStart() == 1);
        assertTrue(theTokenList.get(2).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(2).getStringValue().compareTo("2") == 0);
        assertTrue(theTokenList.get(2).getSourceStart() == 2);
        assertFalse(theAnalyser.isMoreTextNeeded());

        theAnalyser = new LexicalAnalyser();
        theAnalyser.analyseString("/****");
        assertTrue(theAnalyser.isMoreTextNeeded());
        theAnalyser.analyseString("This is a multiline comment test.");
        assertTrue(theAnalyser.isMoreTextNeeded());
        theAnalyser.analyseString("More multiline comment testing.");
        assertTrue(theAnalyser.isMoreTextNeeded());
        theTokenList = theAnalyser.analyseString("Comment ends with this line. */");
        assertFalse(theAnalyser.isMoreTextNeeded());
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("/* add 2 to 2. */2+2");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 3);
        assertFalse(theAnalyser.isMoreTextNeeded());
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("2") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 17);
        assertTrue(theTokenList.get(1).getTokenType() == EnumLexicalToken.TokenPlus);
        assertTrue(theTokenList.get(1).getStringValue().compareTo("+") == 0);
        assertTrue(theTokenList.get(1).getSourceStart() == 18);
        assertTrue(theTokenList.get(2).getTokenType() == EnumLexicalToken.TokenInteger);
        assertTrue(theTokenList.get(2).getStringValue().compareTo("2") == 0);
        assertTrue(theTokenList.get(2).getSourceStart() == 19);
        assertFalse(theAnalyser.isMoreTextNeeded());
    }

    @Test
    public void testSmallTokens()
    {
        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("+");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenPlus);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("+") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("-");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenMinus);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("-") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("*");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenTimes);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("*") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("/");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenDivide);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("/") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("(");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenLeftParen);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("(") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString(")");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenRightParen);
        assertTrue(theTokenList.get(0).getStringValue().compareTo(")") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("^");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.TokenUpArrow);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("^") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
    }

    @Test
    public void testKeywords()
    {
        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        theAnalyser = new LexicalAnalyser();
        theTokenList = theAnalyser.analyseString("%I");
        assertTrue(theTokenList != null);
        assertTrue(theTokenList.size() == 1);
        assertTrue(theTokenList.get(0).getTokenType() == EnumLexicalToken.KeywordI);
        assertTrue(theTokenList.get(0).getStringValue().compareTo("%I") == 0);
        assertTrue(theTokenList.get(0).getSourceStart() == 0);
    }

    @Test
    public void isMoreTextNeeded()
    {
        LexicalAnalyser theAnalyser;
        ArrayList<LexicalToken> theTokenList;

        // Test that a newly created lexical analyser does not need text.

        theAnalyser = new LexicalAnalyser();
        assertFalse(theAnalyser.isMoreTextNeeded());

        // Test after a single label.

        theTokenList = theAnalyser.analyseString("Sam");
        assertFalse(theAnalyser.isMoreTextNeeded());

        // Test after multiple labels.

        theTokenList = theAnalyser.analyseString("Sam1 Sam2 Sam3");
        assertFalse(theAnalyser.isMoreTextNeeded());

        // Test while in a comment at the end of a line.

        theTokenList = theAnalyser.analyseString("Sam4 // This is a comment.");
        assertFalse(theAnalyser.isMoreTextNeeded());

        // Test while in a multiline comment.

        theTokenList = theAnalyser.analyseString("Sam4 /* start of multiline comment.");
        assertTrue(theAnalyser.isMoreTextNeeded());
        theTokenList = theAnalyser.analyseString("still in multiline comment.");
        assertTrue(theAnalyser.isMoreTextNeeded());
        theTokenList = theAnalyser.analyseString("end of multiline comment. */");
        assertFalse(theAnalyser.isMoreTextNeeded());

    }

}