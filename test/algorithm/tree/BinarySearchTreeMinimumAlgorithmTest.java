/*
 * BinarySearchTreeMinimumAlgorithmTest.java v1.00 05/04/10
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
 * Test of the binary search tree minimum algorithm.
 *
 * @author Damien Rigoni
 * @version 1.00 05/04/10
 */
public class BinarySearchTreeMinimumAlgorithmTest {

    private BinarySearchTree bsTree;

    @Before
    public void setUp() {
        bsTree = new BinarySearchTree();
    }

    @Test
    public void testMinimumAlgorithm() {
        assertNull(new BinarySearchTreeMinimumAlgorithm(
                bsTree.getRoot()).applyAlgorithm());

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(4)).applyAlgorithm();
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(2)).applyAlgorithm();
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(3)).applyAlgorithm();
        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(6)).applyAlgorithm();
        Object bsNode1 = new BinarySearchTreeMinimumAlgorithm(
                bsTree.getRoot()).applyAlgorithm();
        assertEquals(((BinarySearchNode) bsNode1).getKey(), 2);

        new BinarySearchTreeInsertAlgorithm(bsTree,
                new BinarySearchNode(1)).applyAlgorithm();
        Object bsNode2 = new BinarySearchTreeMinimumAlgorithm(
                bsTree.getRoot()).applyAlgorithm();
        assertEquals(((BinarySearchNode) bsNode2).getKey(), 1);
    }
}
