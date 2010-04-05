/*
 * BinarySearchTreeSuccessorAlgorithmTest.java v1.00 05/04/10
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
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import model.tree.BinarySearchNode;
import model.tree.BinarySearchTree;

/**
 * Test of the binary search tree successor algorithm.
 *
 * @author Damien Rigoni
 * @version 1.00 05/04/10
 */
public class BinarySearchTreeSuccessorAlgorithmTest {

    private BinarySearchTree bsTree;

    @Before
    public void setUp() {
        bsTree = new BinarySearchTree();
    }

    @Test
    public void testSuccessorAlgorithm() {
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(4)).applyAlgorithm();
        assertNull(new BinarySearchTreeSuccessorAlgorithm(
                bsTree.getRoot().getLeft()).applyAlgorithm());

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(2)).applyAlgorithm();
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(6)).applyAlgorithm();
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(5)).applyAlgorithm();
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(3)).applyAlgorithm();
        Object bsNode1 = new BinarySearchTreeSuccessorAlgorithm(
                bsTree.getRoot()).applyAlgorithm();
        Object bsNode2 = new BinarySearchTreeSuccessorAlgorithm(
                bsTree.getRoot().getLeft().getRight()).applyAlgorithm();
        Object bsNode3 = new BinarySearchTreeSuccessorAlgorithm(
                bsTree.getRoot().getRight().getLeft()).applyAlgorithm();
        assertEquals(((BinarySearchNode) bsNode1).getKey(), 5);
        assertEquals(((BinarySearchNode) bsNode2).getKey(), 4);
        assertEquals(((BinarySearchNode) bsNode3).getKey(), 6);
    }
}
