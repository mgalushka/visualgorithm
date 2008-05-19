/*
 * AbstractBinaryNode.java v1.00 19/05/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (visualgorithm@googlegroups.com)
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

package datastructure.tree;

/**
 * Abstract class containing the common methods of all binary nodes.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinaryNode
 */
abstract class AbstractBinaryNode<N extends IBinaryNode<N>> implements
        IBinaryNode<N> {

    private int key;

    private N left;

    private N right;

    private N father;

    /**
     * Build a node with the key given in parameter, the sons and
     * father are initialized to null.
     * 
     * @param key the key of the new node
     */
    AbstractBinaryNode(int key) {
        this.key = key;
        left = right = father = null;
    }

    @Override
    public final N getFather() {
        return father;
    }

    @Override
    public final void setFather(N father) {
        this.father = father;
    }

    @Override
    public final N getLeft() {
        return left;
    }

    @Override
    public final void setLeft(N left) {
        this.left = left;
    }

    @Override
    public final N getRight() {
        return right;
    }

    @Override
    public final void setRight(N right) {
        this.right = right;
    }

    @Override
    public final int getKey() {
        return key;
    }

    @Override
    public final void setKey(int newKey) {
        this.key = newKey;
    }
}
