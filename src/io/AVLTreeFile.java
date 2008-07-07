/*
 * AVLTreeFile.java v1.00 02/07/08
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

import model.tree.AVLNode;
import model.tree.AVLTree;

/**
 * Loading and saving AVL tree file.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 02/07/08
 * @see TreeFile
 */
class AVLTreeFile extends TreeFile<AVLNode, AVLTree> {

    @Override
    protected void setLeftNode(AVLNode node, int childNodeNumber) {
        node.setLeft(new AVLNode(Integer.parseInt(nodeVector
                .get(childNodeNumber)[KEY])));

    }

    @Override
    protected void setRightNode(AVLNode node, int childNodeNumber) {
        node.setRight(new AVLNode(Integer.parseInt(nodeVector
                .get(childNodeNumber)[KEY])));

    }

    @Override
    protected AVLTree createEmptyBinaryTree() {
        return new AVLTree();
    }
    
    @Override
    protected AVLTree createBinaryTree(int key) {
        return new AVLTree(key);
    }
}
