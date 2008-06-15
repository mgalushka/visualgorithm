/*
 * BinaryTree.java v1.00 19/05/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (visualgorithm@googlegroups.com)
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
 * Definition of binary trees, with as node <tt>BinaryNode</tt>.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinaryNode
 * @see IBinaryTree
 */
public class BinaryTree extends AbstractBinaryTree<BinaryNode> {

    /**
     * Builds an empty binary tree.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Builds a binary tree whose root is initialized with the specified key.
     * 
     * @param key the key of the root
     */
    public BinaryTree(int key) {
        root = new BinaryNode(key);
    }
}
