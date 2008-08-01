/*
 * PedagogicalTreeVisualization.java v1.00 01/08/08
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

package swing.tree;

import java.util.List;
import model.tree.IBinaryNode;
import controller.BinaryTreeTabController;

/**
 * Pedagogical visualization of all types of binary trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 01/08/08
 * @see TreeVisualization
 */
public class PedagogicalTreeVisualization extends TreeVisualization {

    private static final long serialVersionUID = 1L;

    PedagogicalTreeVisualization(BinaryTreeTabController c, int width,
            int height) {
        super(c, width, height);
    }
    
    @Override
    <N extends IBinaryNode<N>> void calculatePositions(List<N> array) {
        
    }
}
