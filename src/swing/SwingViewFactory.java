/*
 * SwingViewFactory.java v0.10 16/06/08
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

import controller.ISoftwareController;
import controller.IBinaryTreeController;
import swing.tree.BinaryTreeView;
import view.IBinaryTreeView;
import view.ISoftwareView;
import view.AbstractViewFactory;

/**
 * This class defines the concrete swing view factory of the different views of
 * the software. This class is not designed for inheritance. The class
 * <tt>SwingViewFactory</tt> uses the design pattern singleton to force the use
 * of a unique instance of the factory.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 * @see AbstractViewFactory
 */
public final class SwingViewFactory extends AbstractViewFactory {

    private static AbstractViewFactory instance = null;

    private SwingViewFactory() {
    }

    /**
     * Creates the factory if it has not been created yet or returns it in the
     * other case.
     * 
     * @return the single instance of the factory
     */
    public static AbstractViewFactory getFactory() {
        if (instance == null) {
            instance = new SwingViewFactory();
        }
        return instance;
    }

    @Override
    public ISoftwareView createSoftwareView(ISoftwareController controller) {
        return new SoftwareView(controller);
    }

    @Override
    public IBinaryTreeView createBinaryTreeView(String type,
            IBinaryTreeController controller) {
        return new BinaryTreeView(type, controller);
    }
}
