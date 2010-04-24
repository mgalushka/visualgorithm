/*
 * AVLTreeLeftRotationAlgorithmTest.java v0.10 05/04/10
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
 * Test of the AVL tree left rotation algorithm.
 *
 * @author Damien Rigoni
 * @version 0.10 05/04/10
 */
public class AVLTreeLeftRotationAlgorithmTest {

    private AVLTree avlTree;

    @Before
    public void setUp() {
        avlTree = new AVLTree();

        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(4)).applyAlgorithm();
        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(2)).applyAlgorithm();
        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(6)).applyAlgorithm();
        new AVLTreeInsertAlgorithm(avlTree, new AVLNode(3)).applyAlgorithm();
    }

    @Test
    public void testLeftRotationAlgorithm() {
        new AVLTreeLeftRotationAlgorithm(avlTree,
                avlTree.getRoot().getLeft()).applyAlgorithm();
        assertEquals(avlTree.getRoot().getLeft().getKey(), 3);
        assertEquals(avlTree.getRoot().getLeft().getLeft().getKey(), 2);

        new AVLTreeLeftRotationAlgorithm(avlTree, avlTree.getRoot()).applyAlgorithm();
        assertEquals(avlTree.getRoot().getKey(), 6);
        assertEquals(avlTree.getRoot().getLeft().getKey(), 4);
        assertEquals(avlTree.getRoot().getLeft().getLeft().getKey(), 3);
        assertEquals(avlTree.getRoot().getLeft().getLeft().getLeft().getKey(), 2);
    }
}
