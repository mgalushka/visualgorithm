/*
 * RedBlackTreeLeftRotationAlgorithm.java v0.10 03/04/10
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
 * This class defines the red black tree left rotation algorithm. It is
 * composed by the red black tree on which the algorithm is applied and the node
 * on which the rotation is applied. This class is not designed for inheritance.
 *
 * @author Damien Rigoni
 * @version 0.10 24/03/08
 * @see AbstractBinaryTreeLeftRotationAlgorithm
 */
public final class RedBlackTreeLeftRotationAlgorithm
        extends AbstractBinaryTreeLeftRotationAlgorithm<RedBlackNode, RedBlackTree> {

    /**
     * Builds the red black tree left rotation algorithm.
     *
     * @param t the red black tree on which the algorithm is applied
     * @param n the red black node on which the rotation is applied
     */
    public RedBlackTreeLeftRotationAlgorithm(RedBlackTree t, RedBlackNode n) {
        super(t, n);
    }

    @Override
    protected void specificProcess(RedBlackNode x, RedBlackNode y) {
    }
}
