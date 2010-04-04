/*
 * BinarySearchTreeMinimumAlgorithm.java v1.00 03/04/10
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

/**
 * This class defines the binary search tree minimum algorithm. It is composed
 * by the binary search node on which the algorithm is applied.
 *
 * @author Damien Rigoni
 * @version 1.00 03/04/10
 * @see IBinaryTreeAlgorithm
 */
public final class BinarySearchTreeMinimumAlgorithm implements IBinaryTreeAlgorithm {

    /**
     * The binary search node on which the algorithm is applied.
     */
    private IBinarySearchNode node;

    /**
     * Builds a binary search tree minimum algorithm.
     *
     * @param n the binary search node on which the algorithm is applied
     */
    public BinarySearchTreeMinimumAlgorithm(IBinarySearchNode n) {
        node = n;
    }

    @Override
    public final Object applyAlgorithm() {
        if (node == null) {
            return null;
        } else {
            while (node.getLeft() != null) {
                node = node.getLeft();
            }
            return node;
        }
    }
}
