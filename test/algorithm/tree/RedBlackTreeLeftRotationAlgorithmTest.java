/*
 * RedBlackTreeLeftRotationAlgorithmTest.java v1.00 05/04/10
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
 * Test of the red black tree left rotation algorithm.
 *
 * @author Damien Rigoni
 * @version 1.00 05/04/10
 */
public class RedBlackTreeLeftRotationAlgorithmTest {

    private RedBlackTree rbTree;

    @Before
    public void setUp() {
        rbTree = new RedBlackTree();

        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(4)).applyAlgorithm();
        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(2)).applyAlgorithm();
        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(6)).applyAlgorithm();
        new RedBlackTreeInsertAlgorithm(rbTree, new RedBlackNode(3)).applyAlgorithm();
    }

    @Test
    public void testLeftRotationAlgorithm() {
        new RedBlackTreeLeftRotationAlgorithm(rbTree,
                rbTree.getRoot().getLeft()).applyAlgorithm();
        assertEquals(rbTree.getRoot().getLeft().getKey(), 3);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getKey(), 2);

        new RedBlackTreeLeftRotationAlgorithm(rbTree, rbTree.getRoot()).applyAlgorithm();
        assertEquals(rbTree.getRoot().getKey(), 6);
        assertEquals(rbTree.getRoot().getLeft().getKey(), 4);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getKey(), 3);
        assertEquals(rbTree.getRoot().getLeft().getLeft().getLeft().getKey(), 2);
    }
}
