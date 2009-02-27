/*
 * BinaryTreeModelListener.java v1.00 16/06/08
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

import java.util.EventListener;

/**
 * This interface contains all the methods for the binary tree model listener.
 * It must be implemented by all the classes that want to listen to the binary
 * tree model in order to be notified of a modification of the binary tree model.
 * 
 * @author Julien Hannier
 * @version 1.00 16/06/08
 * @see BinaryTreeModelEvent
 */
public interface BinaryTreeModelListener extends EventListener {

    /**
     * This method is called when the binary tree of the model has changed. The
     * binary tree model event will contain the last version of the binary tree.
     * 
     * @param event the binary tree model event
     */
    public void binaryTreeHasChanged(BinaryTreeModelEvent event);
}
