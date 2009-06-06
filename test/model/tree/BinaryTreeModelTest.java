/*
 * BinaryTreeModelTest.java v1.00 26/02/09
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
 * @version 1.00 26/02/09
 */
public class BinaryTreeModelTest {

    private static String filePath = "test" + File.separator + "model" +
            File.separator + "tree" + File.separator;

    private BinaryTreeModel btModel1;

    private BinaryTreeModel btModel2;

    private BinaryTreeModel btModel3;

    @Before
    public void setUp() {
        btModel1 = new BinaryTreeModel(BinaryTreeType.AVLTREE);
        try {
            btModel2 = new BinaryTreeModel(
                    new File(filePath + "loadBST.bt"));
        } catch (Exception e) {
            assertNotNull(btModel2);
        }
        btModel3 = new BinaryTreeModel(BinaryTreeType.REDBLACKTREE);
    }

    @Test
    public void testBinaryTreeModel() {
        assertEquals(btModel1.getDataStructure().getType(),
                BinaryTreeType.AVLTREE.toString());
        assertEquals(btModel2.getDataStructure().getType(),
                BinaryTreeType.BINARYSEARCHTREE.toString());
        assertEquals(btModel3.getDataStructure().getType(),
                BinaryTreeType.REDBLACKTREE.toString());
        assertEquals(btModel1.isDataStructureSaved(), false);
        assertEquals(btModel2.isDataStructureSaved(), true);
        assertEquals(btModel3.isDataStructureSaved(), false);

        try {
            btModel1.saveDataStructure(new File(filePath + "saveAVLT.bt"));
        } catch (Exception e) {
        }
        assertEquals(btModel1.isDataStructureSaved(), true);

        //TODO Test with algorithms
        btModel3.insertRandomNodes(6);
        btModel3.insertNode(45);
        btModel3.deleteNode(23);
        btModel3.deleteNodeWithoutCorrection(89);

        btModel2.updateListeners();
    }
}
