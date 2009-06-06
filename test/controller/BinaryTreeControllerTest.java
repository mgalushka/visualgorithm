/*
 * BinaryTreeControllerTest.java v1.00 30/05/09
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

package controller;

import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import view.AbstractViewFactory;

/**
 * Test of the binary tree controller.
 * 
 * @author Julien Hannier
 * @version 1.00 30/05/09
 */
public class BinaryTreeControllerTest {

    private static String filePath = "test" + File.separator + "controller" +
            File.separator;

    private AbstractViewFactory viewFactory;

    private BinaryTreeController btController1;

    private BinaryTreeController btController2;

    private BinaryTreeController btController3;

    @Before
    public void setUp() {
        viewFactory = ViewFactoryMock.getFactory();

        btController1 = new BinaryTreeController();
        btController1.initializeDataStructureController(BinaryTreeType.AVLTREE,
                viewFactory, 200, 100);
        btController2 = new BinaryTreeController();
        try {
            btController2.initializeDataStructureController(
                    new File(filePath + "loadBST.bt"), viewFactory, 200, 100);
        } catch (Exception e) {
            assertNotNull(btController2);
        }
        btController3 = new BinaryTreeController();
        btController3.initializeDataStructureController(
            BinaryTreeType.REDBLACKTREE, viewFactory, 9, 200, 100);
    }

    @Test
    public void testBinaryTreeController() {
        assertEquals(btController1.getView().getClass(), BinaryTreeViewMock.class);
        assertEquals(
                btController1.getDataStructureModel().getDataStructure().getType(),
                BinaryTreeType.AVLTREE.toString());
        assertEquals(
                btController2.getDataStructureModel().getDataStructure().getType(),
                BinaryTreeType.BINARYSEARCHTREE.toString());
        assertEquals(
                btController3.getDataStructureModel().getDataStructure().getType(),
                BinaryTreeType.REDBLACKTREE.toString());
        assertEquals(btController1.isDataStructureModelSaved(), false);
        assertEquals(btController2.isDataStructureModelSaved(), true);
        assertEquals(btController3.isDataStructureModelSaved(), false);

        try {
            btController1.saveDataStructureModel(new File(filePath + "saveAVLT.bt"));
        } catch (Exception e) {
        }
        assertEquals(btController1.isDataStructureModelSaved(), true);

        //TODO Test with algorithms
        btController3.addNodeToBinaryTreeModel(45);
        btController3.deleteNodeFromBinaryTreeModel(23);
        btController3.deleteNodeFromBinaryTreeModelWithoutCorrection(89);
    }
}
