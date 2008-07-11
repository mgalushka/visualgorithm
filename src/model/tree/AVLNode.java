/*
 * AVLNode.java v1.00 19/05/08
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

package model.tree;

/**
 * Definition of the nodes of AVL trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinarySearchNode
 * @see IAVLNode
 */
public final class AVLNode extends AbstractBinarySearchNode<AVLNode> implements
        IAVLNode<AVLNode> {

    private int height;

    /**
     * Builds an AVL node with the key given in parameter. The height is
     * initialized to 0.
     * 
     * @param key the key of the new AVL node
     */
    public AVLNode(int key) {
        super(key);
        height = 0;
    }

    @Override
    public int getAVLHeight() {
        return height;
    }

    @Override
    public void setAVLHeight(int height) {
        this.height = height;
    }

    @Override
    public int calculateAVLHeight() {
        int leftHeight = 0;
        int rightHeight = 0;

        if (getLeft() != null) {
            leftHeight = getLeft().getAVLHeight() + 1;
        }
        if (getRight() != null) {
            rightHeight = getRight().getAVLHeight() + 1;
        }
        return Math.max(leftHeight, rightHeight);
    }

    @Override
    public int calculateAVLBalance() {
        int l = 0;
        int r = 0;

        if (getLeft() != null) {
            l = getLeft().getAVLHeight() + 1;
        }
        if (getRight() != null) {
            r = getRight().getAVLHeight() + 1;
        }
        return r - l;
    }
}
