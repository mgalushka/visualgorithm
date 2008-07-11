/*
 * IBinaryNode.java v1.00 19/05/08
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
 * Methods of the nodes of binary trees. Keys are only integers.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 */
public interface IBinaryNode<N extends IBinaryNode<N>> {

    /**
     * Returns the key of the node.
     * 
     * @return the key of the node
     */
    public int getKey();

    /**
     * Returns the right child of the node.
     * 
     * @return the right child of the node
     */
    public N getRight();

    /**
     * Returns the left child of the node.
     * 
     * @return the left child of the node
     */
    public N getLeft();

    /**
     * Returns the father of the node.
     * 
     * @return the father of the node
     */
    public N getFather();

    /**
     * Replaces the key of the node by the new key.
     * 
     * @param newKey the new key of the node
     */
    public void setKey(int newKey);

    /**
     * Replaces the left child of the node by the new node.
     * 
     * @param newNode the new left child of the node
     */
    public void setLeft(N newNode);

    /**
     * Replaces the right child of the node by the new node.
     * 
     * @param newNode the new right child of the node
     */
    public void setRight(N newNode);

    /**
     * Replaces the father of the node by the new node.
     * 
     * @param newNode the new father of the node
     */
    public void setFather(N newNode);
}
