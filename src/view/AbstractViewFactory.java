/*
 * AbstractViewFactory.java v1.00 16/06/08
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

import swing.SwingViewFactory;
import controller.PrincipalController;
import controller.BinaryTreeTabController;

/**
 * Abstract factory of swing views.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public abstract class AbstractViewFactory {

    /**
     * Creates the factory.
     * 
     * @return the factory
     */
    public static AbstractViewFactory getFactory() {
        return new SwingViewFactory();
    }

    /**
     * Creates the principal view of the software.
     * 
     * @return the principal view
     */
    public abstract IPrincipalModelView createGraphicUserInterface(
            PrincipalController controller);

    /**
     * Creates the binary tree tab view.
     * 
     * @return the binary tree tab view
     */
    public abstract IBinaryTreeView createBinaryTreeTabPage(String type,
            BinaryTreeTabController controller);
}
