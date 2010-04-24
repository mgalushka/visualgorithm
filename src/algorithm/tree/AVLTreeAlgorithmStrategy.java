/*
 * AVLTreeAlgorithmStrategy.java v0.10 18/04/10
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

package algorithm.tree;

import model.tree.AVLNode;
import model.tree.AVLTree;

/**
 * This class defines the AVL tree algorithm strategy. Its goal is to link the
 * AVL tree type with the corresponding algorithms. It is not designed for
 * inheritance.
 *
 * @author Julien Hannier
 * @version 0.10 18/04/10
 * @see IBinaryTreeAlgorithmStrategy
 */
public final class AVLTreeAlgorithmStrategy implements IBinaryTreeAlgorithmStrategy {

    private AVLTree avlTree;

    /**
     * Builds the AVL tree algorithm strategy. It is composed by an AVL tree.
     *
     * @param tree the tree on which algorithms are applied
     */
    public AVLTreeAlgorithmStrategy(AVLTree tree) {
        avlTree = tree;
    }

    @Override
    public void insertNode(int key) {
        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(key)).applyAlgorithm();
    }

    @Override
    public void deleteNode(int key) {
        Object node = new BinarySearchTreeSearchAlgorithm(avlTree.getRoot(),
                key).applyAlgorithm();

        if (node != null) {
            new AVLTreeDeleteAlgorithm(avlTree, (AVLNode) node).applyAlgorithm();
        }
    }
}
