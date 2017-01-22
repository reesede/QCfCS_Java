package qcfcs_math;

/**
 * This class implements a (column) vector of complex numbers.
 * Created by reesede on 1/4/2017.
 * @author David E. Reese
 * @version 2.1.1
 * @since 2.1.1
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
// along with AsianFlashCard.  If not, see <http://www.gnu.org/licenses/>.
//
// History:
//      20170122    D.E. Reese          Creation (Programming Drill 2.1.1).
//

public class ComplexVector extends ComplexMatrix
{
    /**
     * Constructor to create a complex vector of a given number of elements. This is equivalent to a ComplexMatrix
     * with a given number of rows and 1 column.
     * @param rows  Number of rows in the vector.
     */
    public ComplexVector(int rows)
    {
        super(rows,1);
    }
}
