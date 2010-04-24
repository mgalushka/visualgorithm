/*
 * SoftwareControllerTest.java v0.10 30/05/09
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import view.AbstractViewFactory;

/**
 * Test of the software controller.
 * 
 * @author Julien Hannier
 * @version 0.10 30/05/09
 */
public class SoftwareControllerTest {

    private static final String FILE_PATH = "test" + File.separator + "controller" +
            File.separator;

    private ISoftwareModel softwareModel;

    private AbstractViewFactory viewFactory;

    private ISoftwareController softwareController;

    @Before
    public void setUp() {
        softwareModel = new SoftwareModel();
        viewFactory = ViewFactoryMock.getFactory();

        softwareController = new SoftwareController(softwareModel, viewFactory);
    }

    @Test
    public void testSoftwareController() {
        assertEquals(softwareController.getView().getClass(), SoftwareViewMock.class);

        try {
            softwareController.addDataStructure("avlt", BinaryTreeType.AVLTREE);
        } catch (Exception e) {
        }
        try {
            softwareController.addDataStructure("rbt", BinaryTreeType.REDBLACKTREE, 9);
        } catch (Exception e) {
        }
        try {
            softwareController.addDataStructure(new File(FILE_PATH + "loadBST.bst"));
        } catch (Exception e) {
        }
        try {
            assertNull(softwareController.getDataStructureController(0));
        } catch (Exception e) {
        }

        softwareController.addDataStructure("bt", BinaryTreeType.AVLTREE);
        assertNotNull(softwareController.getDataStructureController(0));
        softwareController.addDataStructure("bt", BinaryTreeType.REDBLACKTREE, 9);
        assertNotNull(softwareController.getDataStructureController(1));
        try {
            softwareController.addDataStructure(new File(FILE_PATH + "loadBST.bt"));
        } catch (Exception e) {
        }
        assertNotNull(softwareController.getDataStructureController(2));

        try {
            softwareController.deleteDataStructure(8);
        } catch (Exception e) {
        }
        assertNotNull(softwareController.getDataStructureController(2));
        softwareController.deleteDataStructure(1);
        try {
            assertNull(softwareController.getDataStructureController(2));
        } catch (Exception e) {
        }

        try {
            softwareController.saveDataStructure(
                    new File(FILE_PATH + "saveAVLT.bt"), 0);
        } catch (Exception e) {
        }
        assertEquals(softwareController.getDataStructureController(0).
                isDataStructureModelSaved(), true);

        softwareController.removeAllDataStructureModelsAndControllers();
        try {
            assertNull(softwareController.getDataStructureController(0));
        } catch (Exception e) {
        }
    }
}
