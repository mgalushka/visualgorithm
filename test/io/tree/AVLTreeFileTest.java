/*
 * AVLTreeFileTest.java v0.10 07/07/08
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

package io.tree;

import java.io.File;
import model.tree.AVLNode;
import model.tree.AVLTree;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of the loading and saving of an AVL tree.
 * 
 * @author Damien Rigoni
 * @version 0.10 07/07/08
 */
public class AVLTreeFileTest {

    private static final String FILE_PATH = "test" + File.separator + "io" +
            File.separator + "tree" + File.separator;

    private String loadFileName;

    private String saveFileName;

    private AVLTree tree;

    @Before
    public void setUp() {
        loadFileName = FILE_PATH + "loadAVLT.bt";
        saveFileName = FILE_PATH + "saveAVLT.bt";
        tree = new AVLTree(8);

        tree.getRoot().setLeft(new AVLNode(5));
        tree.getRoot().setRight(new AVLNode(10));
        tree.getRoot().getLeft().setLeft(new AVLNode(4));
        tree.getRoot().getLeft().setRight(new AVLNode(6));
        tree.getRoot().getRight().setLeft(new AVLNode(9));
        tree.getRoot().getLeft().getLeft().setLeft(new AVLNode(3));
    }

    @Test
    public void testLoad() {
        try {
            AVLTree t = (AVLTree) TreeFile.load(loadFileName);
            assertEquals(t.getRoot().getAVLHeight(), 3);
            assertEquals(t.getRoot().getLeft().getAVLHeight(), 2);
            assertEquals(t.getRoot().getRight().getAVLHeight(), 1);
            assertEquals(t.getRoot().getLeft().getLeft().getAVLHeight(), 1);
            assertEquals(t.getRoot().getLeft().getRight().getAVLHeight(), 0);
            assertEquals(t.getRoot().getRight().getLeft().getAVLHeight(), 0);
            assertEquals(
                    t.getRoot().getLeft().getLeft().getLeft().getAVLHeight(), 0);
        } catch (Exception e) {
        }
    }

    @Test
    public void testSave() {
        try {
            TreeFile.save(tree, saveFileName);
        } catch (Exception e) {
        }
    }
}
