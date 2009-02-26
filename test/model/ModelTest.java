/*
 * ModelTest.java v1.00 07/07/08
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
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import model.tree.BinaryTreeModel;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * Test of the models.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 07/07/08
 */
public class ModelTest {

    private SoftwareModel softwareModel;

    private BinaryTreeModel tabModel1;

    private BinaryTreeModel tabModel2;

    private BinaryTreeModel tabModel3;

    @Before
    public void setUp() {
        softwareModel = new SoftwareModel();
        tabModel1 = new BinaryTreeModel(BinaryTreeType.AVLTREE);
        tabModel2 = new BinaryTreeModel(BinaryTreeType.BINARYSEARCHTREE);
        tabModel3 = new BinaryTreeModel(BinaryTreeType.REDBLACKTREE);
    }

    @Test
    public void testSoftwareModel() {
        softwareModel.addTabModel(tabModel1);
        softwareModel.addTabModel(tabModel2);
        softwareModel.addTabModel(tabModel3);
        assertEquals(softwareModel.getTabModel(0), tabModel1);
        assertEquals(softwareModel.getTabModel(1), tabModel2);
        assertEquals(softwareModel.getTabModel(2), tabModel3);
        softwareModel.removeTabModel(1);
        assertEquals(softwareModel.getTabModel(0), tabModel1);
        assertEquals(softwareModel.getTabModel(1), tabModel3);
        softwareModel.removeTabModel(0);
        assertEquals(softwareModel.getTabModel(0), tabModel3);
    }

    @Test
    public void testBinaryTreeTabModel() {
        assertFalse(tabModel1.isTabModelSaved());
        assertEquals(tabModel1.getTabModel().getType(), BinaryTreeType.AVLTREE
                .toString());
        assertEquals(tabModel2.getTabModel().getType(),
            BinaryTreeType.BINARYSEARCHTREE.toString());
        assertEquals(tabModel3.getTabModel().getType(),
            BinaryTreeType.REDBLACKTREE.toString());
    }
}
