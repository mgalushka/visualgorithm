/*
 * AVLTreeFileTest.java v1.00 07/07/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (bx1gl@googlegroups.com)
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

package io;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import model.tree.AVLNode;
import model.tree.AVLTree;
import model.tree.UnknownTreeTypeException;

/**
 * Test of the loading and saving of an AVL tree.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 07/07/08
 */
public class AVLTreeFileTest {

    private static String loadFileName;

    private static String saveFileName;

    private static AVLTree tree;

    @BeforeClass
    public static void setUp() {
        loadFileName = "./test/io/loadAVLTreeTest";
        saveFileName = "./test/io/saveAVLTreeTest";
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
            AVLTree t = (AVLTree)TreeFile.load(loadFileName);
            assertEquals(t.getRoot().getHeight(), 3);
            assertEquals(t.getRoot().getLeft().getHeight(), 2);
            assertEquals(t.getRoot().getRight().getHeight(), 1);
            assertEquals(
                t.getRoot().getLeft().getLeft().getHeight(), 1);
            assertEquals(
                t.getRoot().getLeft().getRight().getHeight(), 0);
            assertEquals(
                t.getRoot().getRight().getLeft().getHeight(), 0);
            assertEquals(
                t.getRoot().getLeft().getLeft().getLeft().getHeight(), 0);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnknownTreeTypeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave() {
        try {
            TreeFile.save(tree, saveFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
