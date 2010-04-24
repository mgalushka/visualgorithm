/*
 * BinarySearchTreeSearchAlgorithm.java v0.10 17/04/10
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
 * This class defines the binary search tree search algorithm. It is composed
 * by the binary search node on which the algorithm is applied and the key to
 * research. This class is not designed for inheritance.
 *
 * @author Damien Rigoni
 * @version 0.10 17/04/10
 * @see IBinaryTreeAlgorithm
 */
public final class BinarySearchTreeSearchAlgorithm implements IBinaryTreeAlgorithm {

    private IBinarySearchNode node;

    private int key;

    /**
     * Builds the binary search tree search algorithm.
     *
     * @param n the binary search node on which the algorithm is applied
     * @param k the key to research
     */
    public BinarySearchTreeSearchAlgorithm(IBinarySearchNode n, int k) {
        node = n;
        key = k;
    }

    @Override
    public final Object applyAlgorithm() {
        while ((node != null) && (key != node.getKey())) {
            if (key < node.getKey()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return node;
    }
}
