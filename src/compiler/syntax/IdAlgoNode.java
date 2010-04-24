/*
 * IdAlgoNode.java v1.00 12/10/08
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

import compiler.lexical.TokenAlgo;
import compiler.lexical.TokenAlgo.TokenAlgoType;

/**
 *
 *
 * @author Damien Rigoni
 * @version 1.00 12/10/08
 */
public class IdAlgoNode extends AlgoNode {
    
    private TokenAlgo id;

    public IdAlgoNode(TokenAlgo id) {
        super();
        
        assert(id.getTokenType().equals(TokenAlgoType.ID));
        this.id = id;
        children = null;
    }
}
