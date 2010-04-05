/*
 * BinarySearchTreeDeleteAlgorithmTest.java v1.00 05/04/10
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
 * Test of the binary search tree delete algorithm.
 *
 * @author Damien Rigoni
 * @version 1.00 05/04/10
 */
public class BinarySearchTreeDeleteAlgorithmTest {

    private BinarySearchTree bsTree;

    @Before
    public void setUp() {
        bsTree = new BinarySearchTree();
    }

    @Test
    public void testDeleteAlgorithm() {
        BinarySearchNode deleteNode = new BinarySearchNode(23);
        assertEquals(new BinarySearchTreeDeleteAlgorithm(bsTree,
                deleteNode).applyAlgorithm(), deleteNode);

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(2)).applyAlgorithm();
        new BinarySearchTreeDeleteAlgorithm(bsTree,
                bsTree.getRoot()).applyAlgorithm();
        assertNull(bsTree.getRoot());

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(2)).applyAlgorithm();
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(1)).applyAlgorithm();
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(3)).applyAlgorithm();
        new BinarySearchTreeDeleteAlgorithm(bsTree,
                bsTree.getRoot().getLeft()).applyAlgorithm();
        assertEquals(bsTree.getRoot().getKey(), 2);
        assertNull(bsTree.getRoot().getLeft());
        assertEquals(bsTree.getRoot().getRight().getKey(), 3);

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(4)).applyAlgorithm();
        new BinarySearchTreeDeleteAlgorithm(bsTree,
                bsTree.getRoot().getRight()).applyAlgorithm();
        assertEquals(bsTree.getRoot().getKey(), 2);
        assertNull(bsTree.getRoot().getLeft());
        assertEquals(bsTree.getRoot().getRight().getKey(), 4);
    }
}
