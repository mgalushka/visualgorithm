/*
 * BinaryTreeControllerTest.java v0.10 30/05/09
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
import model.ISoftwareModel;
import model.SoftwareModel;
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
 * @version 0.10 30/05/09
 */
public class BinaryTreeControllerTest {

    private static final String FILE_PATH = "test" + File.separator + "controller" +
            File.separator;

    private ISoftwareModel softwareModel;

    private AbstractViewFactory viewFactory;

    private IBinaryTreeController btController1;

    private IBinaryTreeController btController2;

    private IBinaryTreeController btController3;

    @Before
    public void setUp() {
        softwareModel = new SoftwareModel();
        viewFactory = ViewFactoryMock.getFactory();

        btController1 = new BinaryTreeController();
        btController1.initializeDataStructureController(BinaryTreeType.AVLTREE,
                viewFactory);
        btController2 = new BinaryTreeController();
        try {
            btController2.initializeDataStructureController(
                    new File(FILE_PATH + "loadBST.bt"), viewFactory);
        } catch (Exception e) {
            assertNotNull(btController2);
        }
        btController3 = new BinaryTreeController();
        btController3.initializeDataStructureController(
            BinaryTreeType.REDBLACKTREE, viewFactory, 9);
    }

    @Test
    public void testBinaryTreeController() {
        assertEquals(btController1.getView().getClass(), BinaryTreeViewMock.class);

        btController1.addDataStructureModelToSoftwareModel(softwareModel);
        btController2.addDataStructureModelToSoftwareModelFromFile(softwareModel,
                "loadBST.bt");
        btController3.addDataStructureModelToSoftwareModel(softwareModel);

        assertEquals(softwareModel.getDataStructureModel(0).getDataStructureType(),
                BinaryTreeType.AVLTREE.toString());
        assertEquals(softwareModel.getDataStructureModel(1).getDataStructureType(),
                BinaryTreeType.BINARYSEARCHTREE.toString());
        assertEquals(softwareModel.getDataStructureModel(2).getDataStructureType(),
                BinaryTreeType.REDBLACKTREE.toString());

        assertEquals(btController1.isDataStructureModelSaved(), false);
        assertEquals(btController2.isDataStructureModelSaved(), true);
        assertEquals(btController3.isDataStructureModelSaved(), false);

        try {
            btController1.saveDataStructureModel(new File(FILE_PATH + "saveAVLT.bt"));
        } catch (Exception e) {
        }
        assertEquals(btController1.isDataStructureModelSaved(), true);

        btController1.addNodeToBinaryTreeModel(45);
        btController1.deleteNodeFromBinaryTreeModel(23);
        btController2.addNodeToBinaryTreeModel(45);
        btController2.deleteNodeFromBinaryTreeModel(23);
        btController3.addNodeToBinaryTreeModel(45);
        btController3.deleteNodeFromBinaryTreeModel(23);
    }
}
