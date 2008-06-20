/*
 * RedBlackNode.java v1.00 19/05/08
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

package model.datastructure.tree;

/**
 * Definition of the nodes of red black trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinarySearchNode
 * @see IRedBlackNode
 */
public final class RedBlackNode extends AbstractBinarySearchNode<RedBlackNode>
        implements IRedBlackNode<RedBlackNode> {

    /**
     * Enumeration which defines the color of red black nodes.
     * 
     * @author Julien Hannier
     * @author Pierre Pironin
     * @author Damien Rigoni
     * @version 1.00 24/03/08
     */
    public static enum RedBlackNodeColor {
        RED, BLACK;
    }

    private RedBlackNodeColor color;

    /**
     * Builds a red black node with the key given in parameter. The color is
     * initialized to red by default.
     * 
     * @param key the key of the new red black node
     */
    public RedBlackNode(int key) {
        super(key);
        this.color = RedBlackNodeColor.RED;
    }

    /**
     * Builds a red black node with the key and the color given in parameters.
     * 
     * @param key the key of the new red black node
     * @param color the color of the new red black node
     */
    public RedBlackNode(int key, RedBlackNodeColor color) {
        super(key);
        this.color = color;
    }

    @Override
    public void setColor(RedBlackNodeColor color) {
        this.color = color;
    }

    @Override
    public RedBlackNodeColor getColor() {
        return color;
    }

    @Override
    public boolean isBlack() {
        return color == RedBlackNodeColor.BLACK;
    }

    @Override
    public boolean isRed() {
        return color == RedBlackNodeColor.RED;
    }
}
