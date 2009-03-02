/*
 * RedBlackTreeFileTest.java v1.00 07/07/08
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
import model.tree.RedBlackNode;
import model.tree.RedBlackTree;
import model.tree.RedBlackNode.RedBlackNodeColor;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of the loading and saving of a red black tree.
 * 
 * @author Damien Rigoni
 * @version 1.00 07/07/08
 */
public class RedBlackTreeFileTest {

    private String loadFileName;

    private String saveFileName;

    private RedBlackTree tree;

    @Before
    public void setUp() {
        loadFileName = "test" + File.separator + "io" + File.separator +
                "tree" + File.separator + "loadRBT.bt";
        saveFileName = "test" + File.separator + "io" + File.separator +
                "tree" + File.separator + "saveRBT.bt";
        tree = new RedBlackTree(6);

        tree.getRoot().setLeft(new RedBlackNode(4, RedBlackNodeColor.BLACK));
        tree.getRoot().setRight(new RedBlackNode(8, RedBlackNodeColor.BLACK));
        tree.getRoot().getLeft().setLeft(new RedBlackNode(3,
                RedBlackNodeColor.RED));
        tree.getRoot().getLeft().setRight(new RedBlackNode(5,
                RedBlackNodeColor.RED));
    }

    @Test
    public void testLoad() {
        try {
            RedBlackTree t = (RedBlackTree) TreeFile.load(loadFileName);
            assertEquals(t.getRoot().getKey(), 6);
            assertEquals(t.getRoot().getColor(), RedBlackNodeColor.BLACK);
            assertEquals(t.getRoot().getLeft().getKey(), 4);
            assertEquals(t.getRoot().getLeft().getColor(),
                    RedBlackNodeColor.BLACK);
            assertEquals(t.getRoot().getRight().getKey(), 8);
            assertEquals(t.getRoot().getRight().getColor(),
                    RedBlackNodeColor.BLACK);
            assertEquals(t.getRoot().getLeft().getLeft().getKey(), 3);
            assertEquals(t.getRoot().getLeft().getLeft().getColor(),
                    RedBlackNodeColor.RED);
            assertEquals(t.getRoot().getLeft().getRight().getKey(), 5);
            assertEquals(t.getRoot().getLeft().getRight().getColor(),
                    RedBlackNodeColor.RED);
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
