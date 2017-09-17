package qcfcs_parsing;

/**
 * This enumeration defines the types of errors detected by the parser.
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
//      20170917    D.E. Reese          Added ParserError_Empty

public enum EnumParserError
{
    /**
     * Unknown parser error.
     */
    ParserError_Unknown,

    /**
     * No parser error detected.
     */
    ParserError_None,

    /**
     * Indicates an empty parser tree entry. Note that this may not be an error, depending on where it occurred.
     */
    ParserError_Empty
}