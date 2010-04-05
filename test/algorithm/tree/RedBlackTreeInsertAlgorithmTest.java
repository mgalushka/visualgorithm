/*
 * RedBlackTreeInsertAlgorithmTest.java v1.00 05/04/10
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

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import model.tree.RedBlackNode;
import model.tree.RedBlackTree;

/**
 * Test of the red black tree insert algorithm.
 *
 * @author Damien Rigoni
 * @version 1.00 05/04/10
 */
public class RedBlackTreeInsertAlgorithmTest {

    private RedBlackTree rbTree;

    @Before
    public void setUp() {
        rbTree = new RedBlackTree();
    }

    @Test
    public void testInsertAlgorithm() {
        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(8)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getKey(), 8);
        assertEquals(rbTree.getRoot().isBlack(), true);

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(5)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getLeft().getKey(), 5);
        assertEquals(rbTree.getRoot().getLeft().isRed(), true);

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(6)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getKey(), 6);
        assertEquals(rbTree.getRoot().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getKey(), 5);
        assertEquals(rbTree.getRoot().getLeft().isRed(), true);
        assertEquals(rbTree.getRoot().getRight().getKey(), 8);
        assertEquals(rbTree.getRoot().getRight().isRed(), true);

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(3)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getLeft().getKey(), 5);
        assertEquals(rbTree.getRoot().getLeft().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getKey(), 3);
        assertEquals(rbTree.getRoot().getLeft().getLeft().isRed(), true);

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(4)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getKey(), 6);
        assertEquals(rbTree.getRoot().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getKey(), 4);
        assertEquals(rbTree.getRoot().getLeft().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getKey(), 8);
        assertEquals(rbTree.getRoot().getRight().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getKey(), 3);
        assertEquals(rbTree.getRoot().getLeft().getLeft().isRed(), true);
        assertEquals(rbTree.getRoot().getLeft().getRight().getKey(), 5);
        assertEquals(rbTree.getRoot().getLeft().getRight().isRed(), true);

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(20)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getRight().getRight().getKey(), 20);
        assertEquals(rbTree.getRoot().getRight().getRight().isRed(), true);

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(10)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getKey(), 6);
        assertEquals(rbTree.getRoot().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getKey(), 4);
        assertEquals(rbTree.getRoot().getLeft().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getKey(), 10);
        assertEquals(rbTree.getRoot().getRight().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getKey(), 3);
        assertEquals(rbTree.getRoot().getLeft().getLeft().isRed(), true);
        assertEquals(rbTree.getRoot().getLeft().getRight().getKey(), 5);
        assertEquals(rbTree.getRoot().getLeft().getRight().isRed(), true);
        assertEquals(rbTree.getRoot().getRight().getLeft().getKey(), 8);
        assertEquals(rbTree.getRoot().getRight().getLeft().isRed(), true);
        assertEquals(rbTree.getRoot().getRight().getRight().getKey(), 20);
        assertEquals(rbTree.getRoot().getRight().getRight().isRed(), true);

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(20)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getKey(), 6);
        assertEquals(rbTree.getRoot().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getKey(), 4);
        assertEquals(rbTree.getRoot().getLeft().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getKey(), 10);
        assertEquals(rbTree.getRoot().getRight().isRed(), true);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getKey(), 3);
        assertEquals(rbTree.getRoot().getLeft().getLeft().isRed(), true);
        assertEquals(rbTree.getRoot().getLeft().getRight().getKey(), 5);
        assertEquals(rbTree.getRoot().getLeft().getRight().isRed(), true);
        assertEquals(rbTree.getRoot().getRight().getLeft().getKey(), 8);
        assertEquals(rbTree.getRoot().getRight().getLeft().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getRight().getKey(), 20);
        assertEquals(rbTree.getRoot().getRight().getRight().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getRight().getRight().getKey(), 20);
        assertEquals(rbTree.getRoot().getRight().getRight().getRight().isRed(), true);

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(30)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getKey(), 6);
        assertEquals(rbTree.getRoot().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getKey(), 4);
        assertEquals(rbTree.getRoot().getLeft().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getKey(), 10);
        assertEquals(rbTree.getRoot().getRight().isRed(), true);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getKey(), 3);
        assertEquals(rbTree.getRoot().getLeft().getLeft().isRed(), true);
        assertEquals(rbTree.getRoot().getLeft().getRight().getKey(), 5);
        assertEquals(rbTree.getRoot().getLeft().getRight().isRed(), true);
        assertEquals(rbTree.getRoot().getRight().getLeft().getKey(), 8);
        assertEquals(rbTree.getRoot().getRight().getLeft().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getRight().getKey(), 20);
        assertEquals(rbTree.getRoot().getRight().getRight().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getRight().getLeft().getKey(), 20);
        assertEquals(rbTree.getRoot().getRight().getRight().getLeft().isRed(), true);
        assertEquals(rbTree.getRoot().getRight().getRight().getRight().getKey(), 30);
        assertEquals(rbTree.getRoot().getRight().getRight().getRight().isRed(), true);

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(16)).applyAlgorithm();
        assertEquals(rbTree.getRoot().getKey(), 10);
        assertEquals(rbTree.getRoot().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getKey(), 6);
        assertEquals(rbTree.getRoot().getLeft().isRed(), true);
        assertEquals(rbTree.getRoot().getRight().getKey(), 20);
        assertEquals(rbTree.getRoot().getRight().isRed(), true);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getKey(), 4);
        assertEquals(rbTree.getRoot().getLeft().getLeft().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getRight().getKey(), 8);
        assertEquals(rbTree.getRoot().getLeft().getRight().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getLeft().getKey(), 20);
        assertEquals(rbTree.getRoot().getRight().getLeft().isBlack(), true);
        assertEquals(rbTree.getRoot().getRight().getRight().getKey(), 30);
        assertEquals(rbTree.getRoot().getRight().getRight().isBlack(), true);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getLeft().getKey(), 3);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getLeft().isRed(), true);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getRight().getKey(), 5);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getRight().isRed(), true);
        assertEquals(rbTree.getRoot().getRight().getLeft().getLeft().getKey(), 16);
        assertEquals(rbTree.getRoot().getRight().getLeft().getLeft().isRed(), true);
    }
}
