/*
 * AbstractBinaryTree.java v1.00 19/05/08
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

package model.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class containing common methods of all binary trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinaryNode
 * @see IBinaryTree
 */
public abstract class AbstractBinaryTree<N extends IBinaryNode<N>> implements
        IBinaryTree<N> {

    /**
     * Definition of the type of binary trees.
     * 
     * @author Julien Hannier
     * @author Pierre Pironin
     * @author Damien Rigoni
     * @version 1.00 16/06/08
     */
    public enum BinaryTreeType {
        AVLTREE, BINARYSEARCHTREE, REDBLACKTREE;
    }
    
    protected static BinaryTreeType type;
    
    protected N root;

    @Override
    public void setRoot(N newNode) {
        root = newNode;
    }

    @Override
    public final N getRoot() {
        return root;
    }
    
    @Override
    public final String getType() {
        return type.toString();
    }
    
    @Override
    public final int getHeight() {
        return findHeight(root);
    }
    
    private int findHeight(N node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(findHeight(node.getLeft()),
                findHeight(node.getRight())) + 1;
        }
    }

    @Override
    public final List<N> treeToArrayList() {
        int length = (2*(int)Math.pow(2, getHeight()))-1;
        List<N> arrayTree = new ArrayList<N>();
        for (int i = 0 ; i<length ; i++ ){
            arrayTree.add(null);
        }
        if (length > 0) {
            buildArray(arrayTree, 0, root);
        }
        return arrayTree;
    }
    
    private void buildArray(List<N> array,
            int index, N node) {
        if ((node.getLeft() == null) &&
                (node.getRight() == null)) {
            array.set(index, node);
        } else {
            if (node.getLeft() == null) {
                buildArray(array, 2*index+2, node.getRight());
            } else if (node.getRight() == null) {
                buildArray(array, 2*index+1, node.getLeft());
            } else {
                buildArray(array, 2*index+1, node.getLeft());
                buildArray(array, 2*index+2, node.getRight());
            }
            array.set(index, node);
        }
    }
}
