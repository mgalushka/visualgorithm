/*
 * BinarySearchTreeDeleteAlgorithm.java v0.10 03/04/10
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

import model.tree.BinarySearchNode;
import model.tree.BinarySearchTree;

/**
 * This class defines the binary search tree delete algorithm. It is composed
 * by the binary search tree on which the algorithm is applied and the node to
 * delete. This class is not designed for inheritance.
 *
 * @author Damien Rigoni
 * @version 0.10 24/03/08
 * @see AbstractBinarySearchTreeDeleteAlgorithm
 */
public final class BinarySearchTreeDeleteAlgorithm
        extends AbstractBinarySearchTreeDeleteAlgorithm<BinarySearchNode, BinarySearchTree> {

    /**
     * Builds the binary search tree delete algorithm.
     *
     * @param t the binary search tree on which the algorithm is applied
     * @param n the binary search node to delete
     */
    public BinarySearchTreeDeleteAlgorithm(BinarySearchTree t, BinarySearchNode n) {
        super(t, n);
    }

    @Override
    protected void specificCorrectionProcess(BinarySearchTree t, BinarySearchNode x,
            BinarySearchNode y) {
    }
}
