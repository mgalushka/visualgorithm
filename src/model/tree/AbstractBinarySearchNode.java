/*
 * AbstractBinarySearchNode.java v0.10 19/05/08
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

package model.tree;

/**
 * This abstract class defines all the common attributes and methods of all
 * binary search nodes. It has been implemented to be inherited by all binary
 * search node classes like AVLNode, etc.
 * 
 * @author Damien Rigoni
 * @version 0.10 19/05/08
 * @see IBinarySearchNode
 */
public abstract class AbstractBinarySearchNode extends AbstractBinaryNode implements
        IBinarySearchNode {

    /**
     * Builds a node with the key given in parameter, the children and the
     * father are initialized to null.
     * 
     * @param key the key of the new node
     */
    protected AbstractBinarySearchNode(int key) {
        super(key);
    }
}
