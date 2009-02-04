/*
 * AlgoCompiler.java 28/08/08
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

package compiler;

import compiler.syntax.AlgoSyntax;

/**
 * An implementation of compiler for the visualgorithm programming
 * language. You should use AlgoCompilerFactory to get an
 * instance of this class.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00
 */
public class AlgoCompiler implements ICompiler {

    private AlgoSyntax syntax;

    // Change to private when we are in java 7 with superpackage.
    @Deprecated
    public AlgoCompiler(AlgoSyntax syntax) {
        this.syntax = syntax;
    }
    
    /**
     * Compile the  .
     * 
     * @param algoFileName file name of the file which have to be
     *            compile.
     * @throws Exception
     */
    public void compile(String algoFileName) throws Exception {
        syntax.parse(algoFileName);
    }
}
