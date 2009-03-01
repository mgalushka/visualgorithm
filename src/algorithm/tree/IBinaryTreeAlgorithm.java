/*
 * IBinaryTreeAlgorithm.java v1.00 09/07/08
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

package algorithm.tree;

/**
 * This is the common interface to apply an algorithm to a binary tree. If you
 * need parameters to apply an algorithm, you must implement this interface and
 * create a constructor with the desired parameters.
 * 
 * @author Damien Rigoni
 * @version 1.00 09/07/08
 */
public interface IBinaryTreeAlgorithm {

    /**
     * This method is used to apply an algorithm to a binary tree. An object is
     * returned when the algorithm need to return something. In the other case,
     * nothing is returned.
     *
     * @return the result of the algorithm, if needed
     */
    public Object applyAlgorithm();
}
