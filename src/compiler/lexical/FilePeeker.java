/*
 * FilePeeker.java 28/08/08
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class peek a character in a file.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00
 */
public class FilePeeker extends AbstractPeeker {

    private BufferedReader file;

    private boolean fileClose = true;

    private int peek = ' '; // current char in int

    // Change to private when we are in java 7 with superpackage.
    @Deprecated
    public FilePeeker() {
        super("");
    }

    public int read() throws IOException {
        int localPeek = file.read();
        if (peek == '\n') {
            ++rowNumber;
            columnNumber = 0;
        } else {
            ++columnNumber;
        }
        return localPeek;
    }

    @Override
    public int nextChar() throws IOException {
        if (fileClose == true) {
            return -1;
        } else {
            peek = read();
            if (peek == -1) {
                file.close();
                fileClose = true;
            }
            return peek;
        }
    }

    @Override
    public void setAlgoFileName(String algoFileName) throws FileNotFoundException {
        file = new BufferedReader(new FileReader(algoFileName));
    }
}
