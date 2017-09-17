package qcfcs_parsing;

/**
 * This class contains information about errors detected by the parser.
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
//      20170916    D.E. Reese          Creation as stub.
//      20170917    D.E. Reese          Added constructor and initialize().

public class ParserError
{
    /**
     * Type of error.
     */
    private EnumParserError error;

    /**
     * String describing the error.
     */
    private String errorString;

    /**
     * Line number containing the error.
     */
    private int lineNumber;

    /**
     * Location within a line of the error.
     */
    private int location;

    /**
     * Constructor to create a new parser error.
     * @param theError  Type of error.
     * @param theLineNumber Line number of error (must be > 0).
     * @param theLocation   Location in line of error (must be >= 0).
     * @param supplementaryText Array of strings containing supplementary text to insert into string.
     * @throws IllegalArgumentException
     */
    public ParserError(final EnumParserError theError, final int theLineNumber, final int theLocation,
                       String[] supplementaryText) throws IllegalArgumentException
    {
        if (theError == EnumParserError.ParserError_Unknown)
            throw new IllegalArgumentException("theError == ParserError_Unknown");
        if (theLineNumber <= 0) throw new IllegalArgumentException("theLineNumber <= 0");
        if (theLocation <= 0) throw new IllegalArgumentException("theLocation <= 0");

        initialize();
    }

    /**
     * This method initializes the error structure.
     */
    private void initialize()
    {
        error = EnumParserError.ParserError_Unknown;
        errorString = null;
        lineNumber = 0;
        location = 0;
    }
}
