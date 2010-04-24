/*
 * BinarySearchTreeInsertAlgorithmTest.java v0.10 05/04/10
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
 * Test of the binary search tree insert algorithm.
 *
 * @author Damien Rigoni
 * @version 0.10 05/04/10
 */
public class BinarySearchTreeInsertAlgorithmTest {

    private BinarySearchTree bsTree;

    @Before
    public void setUp() {
        bsTree = new BinarySearchTree();
    }

    @Test
    public void testInsertAlgorithm() {
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(2)).applyAlgorithm();
        assertEquals(bsTree.getRoot().getKey(), 2);
        assertNull(bsTree.getRoot().getFather());

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(1)).applyAlgorithm();
        assertEquals(bsTree.getRoot().getLeft().getKey(), 1);
        assertEquals(bsTree.getRoot().getLeft().getFather().getKey(), 2);

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(3)).applyAlgorithm();
        assertEquals(bsTree.getRoot().getRight().getKey(), 3);
        assertEquals(bsTree.getRoot().getRight().getFather().getKey(), 2);

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(4)).applyAlgorithm();
        assertEquals(bsTree.getRoot().getRight().getRight().getKey(), 4);
        assertEquals(bsTree.getRoot().getRight().getRight().getFather().
                getKey(), 3);

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(15)).applyAlgorithm();
        assertEquals(bsTree.getRoot().getRight().getRight().getRight().
                getKey(), 15);
        assertEquals(bsTree.getRoot().getRight().getRight().getRight().
                getFather().getKey(), 4);

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(10)).applyAlgorithm();
        assertEquals(bsTree.getRoot().getRight().getRight().getRight().
                getLeft().getKey(), 10);
        assertEquals(bsTree.getRoot().getRight().getRight().getRight().
                getLeft().getFather().getKey(), 15);

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(5)).applyAlgorithm();
        assertEquals(bsTree.getRoot().getRight().getRight().getRight().
                getLeft().getLeft().getKey(), 5);
        assertEquals(bsTree.getRoot().getRight().getRight().getRight().
                getLeft().getLeft().getFather().getKey(), 10);
    }
}
