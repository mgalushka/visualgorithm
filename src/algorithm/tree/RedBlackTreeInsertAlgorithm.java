/*
 * RedBlackTreeInsertAlgorithm.java v1.00 03/04/10
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
import model.tree.RedBlackNode;
import model.tree.RedBlackTree;

/**
 * This class defines the red black tree insert algorithm. It is composed by the
 * red black tree on which the algorithm is applied and the node to insert.
 *
 * @author Damien Rigoni
 * @version 1.00 24/03/08
 * @see AbstractBinarySearchTreeInsertAlgorithm
 */
public final class RedBlackTreeInsertAlgorithm extends AbstractBinarySearchTreeInsertAlgorithm {

    /**
     * Builds the red black tree insert algorithm.
     *
     * @param t the red black tree on which the algorithm is applied
     * @param n the red black node to insert
     */
    public RedBlackTreeInsertAlgorithm(RedBlackTree t, RedBlackNode n) {
        super(t, n);
    }

    @Override
    protected void specificCorrectionProcess(IBinarySearchTree t, IBinarySearchNode x) {
        RedBlackTree tRedBlack = (RedBlackTree) t;
        RedBlackNode xRedBlack = (RedBlackNode) x;

        xRedBlack.setLeft(null);
        xRedBlack.setRight(null);
        xRedBlack.setColor(RedBlackNode.RedBlackNodeColor.RED);
        new RedBlackTreeInsertCorrectionAlgorithm(tRedBlack, xRedBlack).applyAlgorithm();
    }
}
