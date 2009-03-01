/*
 * SwingViewFactory.java v1.00 16/06/08
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

package swing;

import controller.SoftwareController;
import controller.BinaryTreeController;
import swing.tree.BinaryTreeTabView;
import view.IBinaryTreeView;
import view.ISoftwareView;
import view.AbstractViewFactory;

/**
 * Concrete factory of swing views.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see AbstractViewFactory
 */
public class SwingViewFactory extends AbstractViewFactory {

    private static AbstractViewFactory instance = null;

    private SwingViewFactory() {
    }
    
    /**
     * Creates the factory.
     * 
     * @return the factory
     */
    public static AbstractViewFactory getFactory() {
        if (instance == null) {
            instance = new SwingViewFactory();
        }
        return instance;
    }

    @Override
    public ISoftwareView createSoftwareView(SoftwareController controller) {
        return new SoftwareView(controller);
    }

    @Override
    public IBinaryTreeView createBinaryTreeView(String type,
            BinaryTreeController controller, int width, int height) {
        return new BinaryTreeTabView(type, controller, width, height);
    }
}
