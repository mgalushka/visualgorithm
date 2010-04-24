/*
 * RedBlackTreeAlgorithmStrategy.java v1.00 18/04/10
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

import model.tree.RedBlackNode;
import model.tree.RedBlackTree;

/**
 * This class defines the red black tree algorithm strategy. Its goal is to link
 * the red black tree type with the corresponding algorithms. It is not designed
 * for inheritance.
 *
 * @author Julien Hannier
 * @version 1.00 18/04/10
 * @see IBinaryTreeAlgorithmStrategy
 */
public final class RedBlackTreeAlgorithmStrategy implements IBinaryTreeAlgorithmStrategy {

    private RedBlackTree rbTree;

    /**
     * Builds the red black tree algorithm strategy. It is composed by a red
     * black tree.
     *
     * @param tree the tree on which algorithms are applied
     */
    public RedBlackTreeAlgorithmStrategy(RedBlackTree tree) {
        rbTree = tree;
    }

    @Override
    public void insertNode(int key) {
        new RedBlackTreeInsertAlgorithm(rbTree,
                new RedBlackNode(key)).applyAlgorithm();
    }

    @Override
    public void deleteNode(int key) {
        Object node = new BinarySearchTreeSearchAlgorithm(rbTree.getRoot(),
                key).applyAlgorithm();

        if (node != null) {
            new RedBlackTreeDeleteAlgorithm(rbTree,
                    (RedBlackNode) node).applyAlgorithm();
        }
    }
}
