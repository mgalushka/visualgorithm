/*
 * IAVLNode.java v1.00 19/05/08
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
 * Additional methods of the nodes of AVL trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 19/05/08
 * @see IBinarySearchNode
 */
public interface IAVLNode<N extends IAVLNode<N>> extends IBinarySearchNode<N> {

    /**
     * Returns the height of the AVL node.
     * 
     * @return the height of the AVL node
     */
    public int getHeight();

    /**
     * Replaces the height of the AVL node by the new height.
     * 
     * @param height the new height of the AVL node
     */
    public void setHeight(int height);

    /**
     * Calculates the height of the AVL node.
     * 
     * @return the height of the AVL node
     */
    public int findHeight();

    /**
     * Calculates the balancing factor of the AVL node.
     * 
     * @return the balancing factor of the AVL node
     */
    public int findBalance();
}
