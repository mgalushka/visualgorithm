/*
 * AlgorithmAlgoNode.java 12/10/08
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

package compiler.syntax;

import compiler.lexical.TokenAlgo.TokenAlgoType;

/**
 *
 * 
 */
public class AlgorithmAlgoNode extends AlgoNode {

    void createNode() {
        addNode(new DefaultNode(TokenAlgoType.ALGORITHM));
        addNode(new DefaultNode(TokenAlgoType.LPAREN));
        addNode(new ParameterAlgoNode());
        addNode(new DefaultNode(TokenAlgoType.RPAREN));
        addNode(new DefaultNode(TokenAlgoType.RETURN));
        addNode(new TypeAlgoNode());
        addNode(new DefaultNode(TokenAlgoType.VAR));
        addNode(new VariableAlgoNode());
        addNode(new DefaultNode(TokenAlgoType.BEGIN));
        addNode(new InstrAlgoNode());
        addNode(new DefaultNode(TokenAlgoType.END));
    }
}
