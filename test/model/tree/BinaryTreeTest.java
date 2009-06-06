/*
 * BinaryTreeTest.java v1.00 25/02/09
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

package model.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import model.tree.RedBlackNode.RedBlackNodeColor;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of the three types of binary trees : binary search tree, AVL tree and
 * red black tree.
 * 
 * @author Julien Hannier
 * @version 1.00 25/02/09
 */
public class BinaryTreeTest {

    private BinarySearchTree bsTree;

    private BinaryTreeType bsTreeType;

    private AVLTree avlTree;

    private BinaryTreeType avlTreeType;

    private RedBlackTree rbTree;

    private BinaryTreeType rbTreeType;

    @Before
    public void setUp() {
        bsTree = new BinarySearchTree();
        bsTreeType = BinaryTreeType.BINARYSEARCHTREE;
        avlTree = new AVLTree(11);
        avlTreeType = BinaryTreeType.AVLTREE;
        rbTree = new RedBlackTree();
        rbTreeType = BinaryTreeType.REDBLACKTREE;
    }

    @Test
    public void testBST() {
        assertEquals(bsTreeType.createBinaryTree().getType(), "BINARYSEARCHTREE");
        assertNull(new BinarySearchTree().getRoot());
        try {
            assertEquals(new BinarySearchTree(-1).getRoot().getKey(), -1);
        } catch(IllegalArgumentException e) {
        }
        try {
            assertEquals(new BinarySearchTree(100).getRoot().getKey(), 100);
        } catch(IllegalArgumentException e) {
        }

        try {
            bsTree.setRoot(new AVLNode(0));
            assertEquals(bsTree.getRoot() instanceof BinarySearchNode, true);
        } catch (IllegalArgumentException e) {
        }
        bsTree.setRoot(new BinarySearchNode(13));
        assertEquals(bsTree.computeHeight(), 0);

        bsTree.getRoot().setLeft(new BinarySearchNode(3));
        bsTree.getRoot().setRight(new BinarySearchNode(23));
        bsTree.getRoot().getRight().setLeft(new BinarySearchNode(17));
        bsTree.getRoot().getRight().setRight(new BinarySearchNode(4));
        assertEquals(bsTree.isWellFormedTree(), false);

        bsTree.getRoot().getRight().getRight().setKey(44);
        assertEquals(bsTree.isWellFormedTree(), true);
        assertEquals(bsTree.computeHeight(), 2);
        assertEquals(bsTree.buildHeapFromBinaryTree().size(), 7);
        assertEquals(bsTree.buildHeapFromBinaryTree().get(2).getKey(), 23);
        assertEquals(bsTree.buildHeapFromBinaryTree().get(5).getKey(), 17);
        assertEquals(bsTree.buildHeapFromBinaryTree().get(6).getKey(), 44);
    }

    @Test
    public void testAVLT() {
        assertEquals(avlTreeType.createBinaryTree().getType(), "AVLTREE");
        assertNull(new AVLTree().getRoot());
        try {
            assertEquals(new AVLTree(-1).getRoot().getKey(), -1);
        } catch(IllegalArgumentException e) {
        }
        try {
            assertEquals(new AVLTree(100).getRoot().getKey(), 100);
        } catch(IllegalArgumentException e) {
        }
        
        try {
            avlTree.setRoot(new BinarySearchNode(45));
            assertEquals(avlTree.getRoot() instanceof AVLNode, true);
        } catch (IllegalArgumentException e) {
        }
        assertEquals(avlTree.computeHeight(), 0);

        avlTree.getRoot().setLeft(new AVLNode(3));
        avlTree.getRoot().setRight(new AVLNode(23));
        avlTree.getRoot().getRight().setLeft(new AVLNode(17));
        avlTree.getRoot().getRight().setRight(new AVLNode(4));
        assertEquals(avlTree.isWellFormedTree(), false);

        avlTree.getRoot().getRight().getRight().setKey(44);
        avlTree.getRoot().getRight().getRight().setRight(new AVLNode(69));
        assertEquals(avlTree.isWellFormedTree(), false);

        avlTree.getRoot().getLeft().setLeft(new AVLNode(2));
        assertEquals(avlTree.isWellFormedTree(), true);
        assertEquals(avlTree.computeHeight(), 3);
        assertEquals(avlTree.buildHeapFromBinaryTree().size(), 15);
        assertEquals(avlTree.buildHeapFromBinaryTree().get(2).getKey(), 23);
        assertEquals(avlTree.buildHeapFromBinaryTree().get(5).getKey(), 17);
        assertEquals(avlTree.buildHeapFromBinaryTree().get(6).getKey(), 44);
        assertNull(avlTree.buildHeapFromBinaryTree().get(13));
    }

    @Test
    public void testRBT() {
        assertEquals(rbTreeType.createBinaryTree().getType(), "REDBLACKTREE");
        assertNull(new RedBlackTree().getRoot());
        try {
            assertEquals(new RedBlackTree(-1).getRoot().getKey(), -1);
        } catch(IllegalArgumentException e) {
        }
        try {
            assertEquals(new RedBlackTree(100).getRoot().getKey(), 100);
        } catch(IllegalArgumentException e) {
        }
        
        try {
            rbTree.setRoot(new AVLNode(70));
            assertEquals(rbTree.getRoot() instanceof RedBlackNode, true);
        } catch (IllegalArgumentException e) {
        }
        rbTree.setRoot(new RedBlackNode(15, RedBlackNodeColor.BLACK));
        assertEquals(rbTree.computeHeight(), 0);

        rbTree.getRoot().setLeft(new RedBlackNode(3, RedBlackNodeColor.BLACK));
        rbTree.getRoot().setRight(new RedBlackNode(23, RedBlackNodeColor.BLACK));
        rbTree.getRoot().getRight().setLeft(new RedBlackNode(17));
        rbTree.getRoot().getRight().setRight(new RedBlackNode(4));
        assertEquals(rbTree.isWellFormedTree(), false);

        rbTree.getRoot().getRight().getRight().setKey(43);
        assertEquals(rbTree.isWellFormedTree(), true);

        rbTree.getRoot().getRight().setColor(RedBlackNodeColor.RED);
        rbTree.getRoot().getRight().getLeft().setColor(RedBlackNodeColor.BLACK);
        rbTree.getRoot().getRight().getRight().setColor(RedBlackNodeColor.BLACK);
        rbTree.getRoot().getRight().getRight().setRight(new RedBlackNode(56,
                RedBlackNodeColor.BLACK));
        assertEquals(rbTree.isWellFormedTree(), false);

        rbTree.getRoot().getRight().getRight().getRight().setColor(
                RedBlackNodeColor.RED);
        rbTree.getRoot().getLeft().setLeft(new RedBlackNode(2));
        assertEquals(rbTree.isWellFormedTree(), true);
        assertEquals(rbTree.computeHeight(), 3);
        assertEquals(rbTree.buildHeapFromBinaryTree().size(), 15);
        assertEquals(rbTree.buildHeapFromBinaryTree().get(2).getKey(), 23);
        assertEquals(rbTree.buildHeapFromBinaryTree().get(5).getKey(), 17);
        assertEquals(rbTree.buildHeapFromBinaryTree().get(6).getKey(), 43);
        assertNull(rbTree.buildHeapFromBinaryTree().get(13));
    }
}
