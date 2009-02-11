/*
 * BinaryNodeTest.java v1.00 08/02/09
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
import model.tree.RedBlackNode.RedBlackNodeColor;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of the three types of binary nodes : binary search node, AVL node and
 * red black node.
 * 
 * @author Julien Hannier
 * @version 1.00 08/02/09
 */
public class BinaryNodeTest {

    private IBinarySearchNode bsNode;

    private IAVLNode avlNode;

    private IRedBlackNode rbNode;

    @Before
    public void setUp() {
        bsNode = new BinarySearchNode(13);
        avlNode = new AVLNode(3);
        rbNode = new RedBlackNode(23);
    }

    @Test
    public void testBSN() {
        assertEquals(bsNode.getKey(), 13);
    }

    @Test
    public void testAVLN() {
        assertEquals(avlNode.getKey(), 3);
        assertEquals(avlNode.getAVLHeight(), 0);
    }

    @Test
    public void testRBN() {
        assertEquals(rbNode.getKey(), 23);
        assertEquals(rbNode.getColor(), RedBlackNodeColor.RED);
    }
}
