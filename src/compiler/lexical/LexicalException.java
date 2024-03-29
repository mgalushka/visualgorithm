/*
 * LexicalException.java v0.10 05/09/08
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

/**
 * Represents a lexical exception.
 *
 * @author Damien Rigoni
 * @version 0.10 05/09/08
 */
public class LexicalException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    private final char aChar;
    
    private final int rowNumber;
    
    private final int columnNumber;
    
    public LexicalException(final char ch, final int rowNb, final int columnNb) {
        super("Encoutered an unknown char: " + ch + " on row: " + rowNb +
                " and column: " + columnNb + ".");

        aChar = ch;
        rowNumber = rowNb;
        columnNumber = columnNb;
    }

    final public int getRowNumber() {
        return rowNumber;
    }
    
    final public int getColumnNumber() {
        return columnNumber;
    }
    
    final public char getChar() {
        return aChar;
    }
}
