/*
 * AbstractBinaryNode.java v1.00 19/05/08
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
 * This abstract class defines all the common attributes and methods of all
 * binary nodes. It has been implemented to be inherited by all binary node
 * classes like BinaryNode.
 * 
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinaryNode
 */
public abstract class AbstractBinaryNode implements IBinaryNode {

    /**
     * The key of the binary node.
     */
    protected int key;

    /**
     * The left child of the binary node.
     */
    protected IBinaryNode left;

    /**
     * The right child of the binary node
     */
    protected IBinaryNode right;

    /**
     * The father of the binary node.
     */
    protected IBinaryNode father;

    /**
     * Builds a node with the key given in parameter, the children and the
     * father are initialized to null. If {@code k} is greater than 99 or less
     * than 0 then an IllegalArgumentException is thrown.
     * 
     * @param k the key of the new node
     * @throws IllegalArgumentException
     */
    protected AbstractBinaryNode(int k) throws IllegalArgumentException {
        if((k < 0) || (k > 99)) {
            throw new IllegalArgumentException(
                    "You have to pass a key between 0 and 99");
        }
        key = k;
        left = right = father = null;
    }

    @Override
    public final int getKey() {
        return key;
    }

    @Override
    public final void setKey(int newKey) throws IllegalArgumentException {
        if((newKey < 0) || (newKey > 99)) {
            throw new IllegalArgumentException(
                    "You have to pass a key between 0 and 99");
        }
        key = newKey;
    }
}
