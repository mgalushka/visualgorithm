/*
 * BinarySearchTreeFileTest.java v1.00 07/07/08
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

package io;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import model.tree.BinarySearchNode;
import model.tree.BinarySearchTree;
import model.tree.UnknownTreeTypeException;

/**
 * Test of the loading and saving of a binary search tree.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 07/07/08
 */
public class BinarySearchTreeFileTest {

    private static String loadFileName;

    private static String saveFileName;

    private static BinarySearchTree tree;

    @BeforeClass
    public static void setUp() {
        loadFileName = "./test/io/loadBinarySearchTreeTest";
        saveFileName = "./test/io/saveBinarySearchTreeTest";
        tree = new BinarySearchTree(8);

        tree.getRoot().setLeft(new BinarySearchNode(5));
        tree.getRoot().getLeft().setRight(new BinarySearchNode(6));
        tree.getRoot().getLeft().setLeft(new BinarySearchNode(3));
        tree.getRoot().getLeft().getLeft().setRight(new BinarySearchNode(4));
    }

    @Test
    public void testLoad() {
        try {
            BinarySearchTree t = (BinarySearchTree) TreeFile.load(loadFileName);
            assertEquals(t.getRoot().getKey(), 8);
            assertEquals(t.getRoot().getLeft().getKey(), 5);
            assertEquals(t.getRoot().getLeft().getLeft().getKey(), 3);
            assertEquals(t.getRoot().getLeft().getRight().getKey(), 6);
            assertEquals(t.getRoot().getLeft().getLeft().getRight().getKey(), 4);
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
