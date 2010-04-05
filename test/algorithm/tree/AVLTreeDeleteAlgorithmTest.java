/*
 * AVLTreeDeleteAlgorithmTest.java v1.00 05/04/10
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
import model.tree.AVLNode;
import model.tree.AVLTree;

/**
 * Test of the AVL tree delete algorithm.
 *
 * @author Damien Rigoni
 * @version 1.00 05/04/10
 */
public class AVLTreeDeleteAlgorithmTest {

    private AVLTree avlTree;

    @Before
    public void setUp() {
        avlTree = new AVLTree();
    }

    @Test
    public void testDeleteAlgorithm() {
        AVLNode deleteNode = new AVLNode(23);
        assertEquals(new AVLTreeDeleteAlgorithm(avlTree,
                deleteNode).applyAlgorithm(), deleteNode);

        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(4)).applyAlgorithm();
        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(2)).applyAlgorithm();
        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(3)).applyAlgorithm();
        new AVLTreeDeleteAlgorithm(avlTree, avlTree.getRoot()).applyAlgorithm();
        assertEquals(avlTree.getRoot().getKey(), 4);
        assertEquals(avlTree.getRoot().getLeft().getKey(), 2);

        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(6)).applyAlgorithm();
        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(8)).applyAlgorithm();
        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(10)).applyAlgorithm();
        new AVLTreeDeleteAlgorithm(avlTree,
                avlTree.getRoot().getRight().getLeft()).applyAlgorithm();
        new AVLTreeDeleteAlgorithm(avlTree,
                avlTree.getRoot().getLeft()).applyAlgorithm();
        assertEquals(avlTree.getRoot().getKey(), 8);
        assertEquals(avlTree.getRoot().getLeft().getKey(), 4);
        assertEquals(avlTree.getRoot().getRight().getKey(), 10);
        assertEquals(avlTree.getRoot().computeBalanceFactor(), 0);
    }
}
