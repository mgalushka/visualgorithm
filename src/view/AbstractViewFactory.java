/*
 * AbstractViewFactory.java v0.10 16/06/08
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

package view;

import controller.ISoftwareController;
import controller.IBinaryTreeController;

/**
 * This abstract class defines an abstract factory according to the design
 * pattern abstract factory. It is used to implement all the views of the
 * software with a specific technology that could be changed. In order to do so,
 * all the controllers of the software must use only this abstract factory to
 * create the views. Thus, there are no dependencies between the controllers and
 * the technology of the views.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 */
public abstract class AbstractViewFactory {

    /**
     * Creates the software view.
     * 
     * @param controller the software controller
     * @return the software view
     */
    public abstract ISoftwareView createSoftwareView(
            ISoftwareController controller);

    /**
     * Creates the binary tree view. The parameter {@code type} is used to
     * indicate the type of the binary tree in the view.
     * 
     * @param type the type of the binary tree
     * @param controller the binary tree controller
     * @return the binary tree view
     */
    public abstract IBinaryTreeView createBinaryTreeView(String type,
            IBinaryTreeController controller);
}
