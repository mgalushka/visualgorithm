/*
 * IDataStructureMenu.java v1.00 13/12/09
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

/**
 * This interface defines the data structure menu of the software. It must be
 * implemented by all data structure menus directly or through a more specific
 * class. It is principally used in the view of the software.
 *
 * @author Julien Hannier
 * @version 1.00 13/12/09
 */
public interface IDataStructureMenu {

    /**
     * Initializes the data structure menu by seting the software controller
     * with {@code controller} and finishing the build of the menu.
     *
     * @param controller the software controller
     */
    public void initializeDataStructureMenu(ISoftwareController controller);
}
