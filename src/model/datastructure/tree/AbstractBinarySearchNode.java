/*
 * AbstractBinarySearchNode.java v1.00 19/05/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (bx1gl@googlegroups.com)
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

package model.datastructure.tree;

/**
 * Abstract class containing common methods of all binary search nodes.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinarySearchNode
 */
abstract class AbstractBinarySearchNode<N extends IBinarySearchNode<N>> extends
        AbstractBinaryNode<N> implements IBinarySearchNode<N> {

    /**
     * Build a node with the key given in parameter, the children and
     * father are initialized to null.
     * 
     * @param key the key of the new node
     */
    AbstractBinarySearchNode(int key) {
        super(key);
    }
}
