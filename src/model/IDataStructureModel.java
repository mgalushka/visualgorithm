/*
 * IDataStructureModel.java v1.00 07/07/08
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

package model;

import java.io.File;
import java.io.IOException;

/**
 * This interface is the common interface of all data structure models. It must
 * be implemented by each model containing a particular data structure. It is
 * principally used in the software model.
 * 
 * @author Julien Hannier
 * @version 1.00 07/07/08
 */
public interface IDataStructureModel {

    /**
     * Returns the data structure of the model.
     * 
     * @return the data structure of the model
     */
    public IDataStructure getDataStructure();

    /**
     * Returns true if the data structure of the model has been saved, or else
     * false.
     * 
     * @return true if the data structure of the model has been saved, or else false
     */
    public boolean isDataStructureSaved();

    /**
     * Saves the data structure of the model into {@code file}.
     * 
     * @param file the file where to save the data structure
     * @throws IOException
     */
    public void saveDataStructure(File file) throws IOException;
}
