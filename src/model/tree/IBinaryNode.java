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
 * This interface contains all the methods in order to use binary nodes. It must
 * be implemented by all types of binary nodes directly or through one defined
 * abstract class. All the getters must be defined with the concrete type of the
 * node. For instance, the method {@code IBinaryNode getRight()} will look like
 * {@code AVLNode getRight()} for the class AVLNode. All the setters must be
 * used with the same type of binary node that the object on which they are
 * used. If not, an IllegalArgumentException is thrown. Keys are only integers
 * greater equal than 0 and less than 100.
 * 
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 */
public interface IBinaryNode {

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
    public IBinaryNode getRight();

    /**
     * Returns the left child of the node.
     * 
     * @return the left child of the node
     */
    public IBinaryNode getLeft();

    /**
     * Returns the father of the node.
     * 
     * @return the father of the node
     */
    public IBinaryNode getFather();

    /**
     * Replaces the key of the node by {@code newKey}. If {@code newKey} is
     * greater than 99 or less than 0 then an IllegalArgumentException is
     * thrown.
     * 
     * @param newKey the new key of the node
     * @throws IllegalArgumentException
     */
    public void setKey(int newKey) throws IllegalArgumentException;

    /**
     * Replaces the right child of the node by {@code newNode}. If 
     * {@code newNode} does not have the same type that the node on which the
     * method is applied then an IllegalArgumentException is thrown.
     * 
     * @param newNode the new right child of the node
     * @throws IllegalArgumentException
     */
    public void setRight(IBinaryNode newNode) throws IllegalArgumentException;

    /**
     * Replaces the left child of the node by {@code newNode}. If
     * {@code newNode} does not have the same type that the node on which the
     * method is applied then an IllegalArgumentException is thrown.
     * 
     * @param newNode the new left child of the node
     * @throws IllegalArgumentException
     */
    public void setLeft(IBinaryNode newNode) throws IllegalArgumentException;

    /**
     * Replaces the father of the node by {@code newNode}. If {@code newNode}
     * does not have the same type that the node on which the method is applied
     * then an IllegalArgumentException is thrown.
     * 
     * @param newNode the new father of the node
     * @throws IllegalArgumentException
     */
    public void setFather(IBinaryNode newNode) throws IllegalArgumentException;
}
