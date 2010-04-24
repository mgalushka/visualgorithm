/*
 * AbstractPeeker.java v1.00 28/08/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (visualgo@googlegroups.com)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package compiler.lexical;

import java.io.IOException;

/**
 * This class provide some implementation for peekers.
 *
 * @author Damien Rigoni
 * @version 1.00 28/08/08
 */
public abstract class AbstractPeeker implements IPeeker {

    protected int rowNumber;

    protected int columnNumber;
    
    protected String algoName;

    protected AbstractPeeker(String algoNm) {
        rowNumber = columnNumber = 0;
        algoName = algoNm;
    }

    @Override
    final public int getColumnNumber() {
        return columnNumber;
    }

    @Override
    final public int getRowNumber() {
        return rowNumber;
    }

    @Override
    public abstract int nextChar() throws IOException;
}
