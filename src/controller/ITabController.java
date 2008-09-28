/*
 * ITabController.java v1.00 07/07/08
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

package controller;

import java.io.File;
import java.io.IOException;
import model.ITabModel;

/**
 * Interface describing the methods of tab controllers.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 07/07/08
 * @see IController
 */
public interface ITabController extends IController {

    /**
     * Returns the tab model.
     * 
     * @return the tab model
     */
    public ITabModel getTabModel();

    /**
     * Saves the tab model into the selected file.
     * 
     * @param file the file
     * @throws IOException
     */
    public void saveTabModel(File file) throws IOException;

    /**
     * Returns true if the data structure of the tab controller has been saved.
     * 
     * @return true if the data structure has been saved
     */
    public boolean isTabModelSaved();
}
