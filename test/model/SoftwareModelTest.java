/*
 * SoftwareModelTest.java v1.00 07/07/08
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

package model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import model.tree.BinaryTreeModel;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * Test of the software model.
 * 
 * @author Julien Hannier
 * @version 1.00 07/07/08
 */
public class SoftwareModelTest {

    private SoftwareModel softwareModel;

    private BinaryTreeModel btModel1;

    private BinaryTreeModel btModel2;

    private BinaryTreeModel btModel3;

    @Before
    public void setUp() {
        softwareModel = new SoftwareModel();
        btModel1 = new BinaryTreeModel(BinaryTreeType.AVLTREE);
        btModel2 = new BinaryTreeModel(BinaryTreeType.BINARYSEARCHTREE);
        btModel3 = new BinaryTreeModel(BinaryTreeType.REDBLACKTREE);
    }

    @Test
    public void testSoftwareModel() {
        softwareModel.addDataStructureModel(btModel1);
        softwareModel.addDataStructureModel(btModel2);
        softwareModel.addDataStructureModel(btModel3);
        assertEquals(softwareModel.getDataStructureModel(0), btModel1);
        assertEquals(softwareModel.getDataStructureModel(1), btModel2);
        assertEquals(softwareModel.getDataStructureModel(2), btModel3);
        softwareModel.removeDataStructureModel(1);
        assertEquals(softwareModel.getDataStructureModel(0), btModel1);
        assertEquals(softwareModel.getDataStructureModel(1), btModel3);
        softwareModel.removeDataStructureModel(0);
        assertEquals(softwareModel.getDataStructureModel(0), btModel3);
    }
}
