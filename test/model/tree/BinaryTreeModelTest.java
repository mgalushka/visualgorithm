/*
 * BinaryTreeModelTest.java v0.10 26/02/09
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

import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * Test of the binary tree model.
 * 
 * @author Julien Hannier
 * @version 0.10 26/02/09
 */
public class BinaryTreeModelTest {

    private static final String FILE_PATH = "test" + File.separator + "model" +
            File.separator + "tree" + File.separator;

    private IBinaryTreeModel btModel1;

    private IBinaryTreeModel btModel2;

    private IBinaryTreeModel btModel3;

    @Before
    public void setUp() {
        btModel1 = new BinaryTreeModel(BinaryTreeType.AVLTREE);
        try {
            btModel2 = new BinaryTreeModel(
                    new File(FILE_PATH + "loadBST.bt"));
        } catch (Exception e) {
            assertNotNull(btModel2);
        }
        btModel3 = new BinaryTreeModel(BinaryTreeType.REDBLACKTREE);
    }

    @Test
    public void testBinaryTreeModel() {
        assertEquals(btModel1.getDataStructureType(), BinaryTreeType.AVLTREE.toString());
        assertEquals(btModel2.getDataStructureType(), BinaryTreeType.BINARYSEARCHTREE.toString());
        assertEquals(btModel3.getDataStructureType(), BinaryTreeType.REDBLACKTREE.toString());
        assertEquals(btModel1.isDataStructureSaved(), false);
        assertEquals(btModel2.isDataStructureSaved(), true);
        assertEquals(btModel3.isDataStructureSaved(), false);

        try {
            btModel1.saveDataStructure(new File(FILE_PATH + "saveAVLT.bt"));
        } catch (Exception e) {
        }
        assertEquals(btModel1.isDataStructureSaved(), true);

        btModel1.insertRandomNodes(6);
        btModel1.insertNode(45);
        btModel1.deleteNode(23);
        btModel2.insertRandomNodes(6);
        btModel2.insertNode(45);
        btModel2.deleteNode(23);
        btModel3.insertRandomNodes(6);
        btModel3.insertNode(45);
        btModel3.deleteNode(23);
    }

    @Test
    public void insertionOfZeroRandomNodeDoNotModifyTree() {
        btModel2.insertRandomNodes(0);
        assertEquals(btModel2.isDataStructureSaved(), true);
}

    @Test
    public void insertionOfNegativeRandomNodeDoNotModifyTree() {
        btModel2.insertRandomNodes(-1);
        assertEquals(btModel2.isDataStructureSaved(), true);
    }
}
