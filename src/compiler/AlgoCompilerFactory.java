/*
 * AlgoCompilerFactory.java v0.10 28/08/08
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

import compiler.lexical.AlgoLexer;
import compiler.lexical.FilePeeker;
import compiler.syntax.AlgoSyntax;

/**
 * Factory which create an instance of AlgoCompiler.
 *
 * @author Damien Rigoni
 * @version 0.10 28/08/08
 */
public class AlgoCompilerFactory implements ICompilerFactory {

    private static AlgoCompilerFactory instance = new AlgoCompilerFactory();

    private AlgoCompilerFactory() {
    }

    public static AlgoCompilerFactory getInstance() {
        return instance;
    }

    @Override
    @SuppressWarnings("deprecation")
    public AlgoCompiler newCompiler() {
        return new AlgoCompiler(new AlgoSyntax(new AlgoLexer(new FilePeeker())));
    }

    @Override
    @SuppressWarnings("deprecation")
    public AlgoLexer newLexer() {
        return new AlgoLexer(new FilePeeker());
    }
}
