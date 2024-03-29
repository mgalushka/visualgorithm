/*
 * IRedBlackNode.java v0.10 19/05/08
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

import model.tree.RedBlackNode.RedBlackNodeColor;

/**
 * This interface contains all the methods in order to use red black nodes. It
 * must be implemented by classes defining red black nodes.
 * 
 * @author Damien Rigoni
 * @version 0.10 19/05/08
 * @see IBinarySearchNode
 */
public interface IRedBlackNode extends IBinarySearchNode {

    @Override
    public IRedBlackNode getRight();

    @Override
    public IRedBlackNode getLeft();

    @Override
    public IRedBlackNode getFather();

    /**
     * Returns the color of the red black node.
     * 
     * @return the color of the red black node
     */
    public RedBlackNodeColor getColor();

    /**
     * Replaces the color of the node by {@code c}.
     * 
     * @param c the new color of the node
     */
    public void setColor(RedBlackNodeColor c);

    /**
     * Returns true if the node is red or else false.
     * 
     * @return true if the node is red or else false
     */
    public boolean isRed();

    /**
     * Returns true if the node is black or else false.
     * 
     * @return true if the node is black or else false
     */
    public boolean isBlack();

    /**
     * Calculates the left black height of the node. This method is recursive.
     * 
     * @return the left black height of the node
     */
    public int calculateLeftBlackHeight();

    /**
     * Calculates the right black height of the node. This method is recursive.
     * 
     * @return the right black height of the node
     */
    public int calculateRightBlackHeight();
}
