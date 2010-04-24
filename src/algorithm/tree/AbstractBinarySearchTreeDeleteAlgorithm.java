/*
 * AbstractBinarySearchTreeDeleteAlgorithm.java v1.00 03/04/10
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

import model.tree.IBinarySearchNode;
import model.tree.IBinarySearchTree;

/**
 * This class defines the binary search tree delete algorithm. It is composed
 * by the binary search tree on which the algorithm is applied and the node to
 * delete.
 *
 * @author Damien Rigoni
 * @version 1.00 03/04/10
 * @see IBinaryTreeAlgorithm
 */
public abstract class AbstractBinarySearchTreeDeleteAlgorithm<NodeType extends IBinarySearchNode, TreeType extends IBinarySearchTree>
        implements IBinaryTreeAlgorithm {

    private TreeType tree;

    private NodeType deleteNode;

    /**
     * Builds the binary search tree delete algorithm.
     *
     * @param t the binary search tree on which the algorithm is applied
     * @param n the binary search node to delete
     */
    protected AbstractBinarySearchTreeDeleteAlgorithm(TreeType t, NodeType n) {
        tree = t;
        deleteNode = n;
    }

    /**
     * Handle to perform specific correction process at the end of the
     * algorithm. It depends on the type of the binary search tree.
     *
     * @param t the tree on which the corretion is applied
     * @param x the node from which the correction is made
     * @param y the deleted node
     */
    protected abstract void specificCorrectionProcess(TreeType t, NodeType x, NodeType y);

    @Override
    public final Object applyAlgorithm() {
        NodeType x, y;

        if ((deleteNode.getLeft() == null) || (deleteNode.getRight() == null)) {
            y = deleteNode;
        } else {
            BinarySearchTreeSuccessorAlgorithm algo = new BinarySearchTreeSuccessorAlgorithm(deleteNode);

            y = (NodeType) algo.applyAlgorithm();
        }

        if (y.getLeft() != null) {
            x = (NodeType) y.getLeft();
        } else {
            x = (NodeType) y.getRight();
        }

        if (x != null) {
            x.setFather(y.getFather());
        }

        if (y.getFather() == null) {
            tree.setRoot(x);
        } else if (y == y.getFather().getLeft()) {
            y.getFather().setLeft(x);
        } else {
            y.getFather().setRight(x);
        }

        if (y != deleteNode) {
            deleteNode.setKey(y.getKey());
        }
        specificCorrectionProcess(tree, x, y);
        
        return y;
    }
}
