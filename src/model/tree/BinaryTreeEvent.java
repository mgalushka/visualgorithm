/*
 * DataStructureEvent.java v1.00 16/06/08
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

import java.util.EventObject;
import java.util.List;

/**
 * Definition of the binary tree event.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class BinaryTreeEvent<N extends IBinaryNode<N>> extends EventObject {

    private static final long serialVersionUID = 1L;
    
    private List<N> data;

    /**
     * Builds a binary tree event.
     * 
     * @param source the source of the binary tree event
     * @param d the data of the binary tree event
     */
    public BinaryTreeEvent(Object source, List<N> d) {
        super(source);
        data = d;
    }

    /**
     * Returns the data of the binary tree event.
     * 
     * @return the data of the binary tree event
     */
    public List<N> getData() {
        return data;
    }
}
