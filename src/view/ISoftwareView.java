/*
 * ISoftwareView.java v0.10 16/06/08
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

/**
 * This interface defines the software view. This interface is used in the
 * abstract view factory.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 * @see IView
 */
public interface ISoftwareView extends IView {

    /**
     * Displays the view of the software. This method must be used to show the
     * software view at the first time.
     */
    public void displayView();

    /**
     * Closes the view of the software. This method must be used to hide and
     * close the software view.
     */
    public void closeView();
    
    /**
     * Adds a data structure view to the software view. The data structure view is
     * already created into the parameter {@code view}. The parameter {@code name}
     * is a string representing the data structure view.
     * 
     * @param name the name of the data structure view to add
     * @param view the data structure view to add
     */
    public void addDataStructureView(String name, IDataStructureView view);
    
    /**
     * Deletes the data structure view from the software view. The parameter
     * {@code index} indicates the place of the data structure view among
     * others.
     * 
     * @param index the index of the data structure view to delete
     */
    public void deleteDataStructureView(int index);
}
