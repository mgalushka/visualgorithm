/*
 * AbstractDataStructureMenu.java v0.10 13/12/09
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

package view.swing;

import controller.ISoftwareController;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * This class defines the data structure menu. It is composed by the software
 * controller which must be set with the
 * {@code void initializeDataStructureMenu()} method. This method will also
 * finish the build of the menu by creating menu items. The data structure menu
 * contains two sub menus wich are <tt>New</tt> and <tt>Info</tt>. This class is
 * designed for inheritance in order to create specific data structure menus
 * thanks to the design pattern template method.
 *
 * @author Julien Hannier
 * @version 0.10 13/12/09
 * @see IDataStructureMenu
 */
public abstract class AbstractDataStructureMenu extends JMenu implements IDataStructureMenu {

    private JMenu newMenu;

    private JMenu infoMenu;

    /**
     * The software controller;
     */
    protected ISoftwareController softwareController;

    /**
     * Builds the data structure menu. The data structure menu is composed by
     * the software controller and two sub menus.
     *
     * @param menuName the name of the menu
     */
    protected AbstractDataStructureMenu(String menuName) {
        super(menuName);
        
        newMenu = new JMenu("New");
        infoMenu = new JMenu("Info");
    }

    @Override
    public void initializeDataStructureMenu(ISoftwareController controller) {
        softwareController = controller;
        
        for (JMenuItem menuItem: createNewMenuItems()) {
            newMenu.add(menuItem);
        }
        for (JMenuItem menuItem: createInfoMenuItems()) {
            infoMenu.add(menuItem);
        }
        add(newMenu);
        add(infoMenu);
    }

    /**
     * Creates the specific menu items of the <tt>New</tt> menu.
     *
     * @return the list of the <tt>New</tt> menu items
     */
    protected abstract List<JMenuItem> createNewMenuItems();

    /**
     * Creates the specific menu items of the <tt>Info</tt> menu.
     *
     * @return the list of the <tt>Info</tt> menu items
     */
    protected abstract List<JMenuItem> createInfoMenuItems();
}
