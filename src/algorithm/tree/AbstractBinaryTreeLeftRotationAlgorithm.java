/*
 * AbstractBinaryTreeLeftRotationAlgorithm.java v0.10 03/04/10
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

import model.tree.IBinaryNode;
import model.tree.IBinaryTree;

/**
 * This class defines the binary tree left rotation algorithm. It is composed
 * by the binary tree on which the algorithm is applied and the node on which
 * the rotation is applied.
 *
 * @author Damien Rigoni
 * @version 0.10 03/04/10
 * @see IBinaryTreeAlgorithm
 */
public abstract class AbstractBinaryTreeLeftRotationAlgorithm<NodeType extends IBinaryNode, TreeType extends IBinaryTree>
        implements IBinaryTreeAlgorithm {

    private TreeType tree;

    private NodeType node;

    /**
     * Builds the binary tree left rotation algorithm.
     *
     * @param t the binary tree on which the algorithm is applied
     * @param n the binary node on which the rotation is applied
     */
    protected AbstractBinaryTreeLeftRotationAlgorithm(TreeType t, NodeType n) {
        tree = t;
        node = n;
    }

    /**
     * Handle to perform specific process at the end of the algorithm. It
     * depends on the type of the binary tree.
     *
     * @param x the left child of y
     * @param y the node on which the rotation is applied
     */
    protected abstract void specificProcess(NodeType x, NodeType y);

    @Override
    public final Object applyAlgorithm() {
        if (tree.getRoot() == null) {
            return null;
        } else {
            NodeType y = (NodeType) node.getRight();
            node.setRight(y.getLeft());

            if (y.getLeft() != null) {
                y.getLeft().setFather(node);
            }
            y.setFather(node.getFather());
            
            if (node.getFather() == null) {
                tree.setRoot(y);
            } else {
                if (node == node.getFather().getLeft()) {
                    node.getFather().setLeft(y);
                } else {
                    node.getFather().setRight(y);
                }
            }
            y.setLeft(node);
            node.setFather(y);
            specificProcess(node, y);

            return node;
        }
    }
}
