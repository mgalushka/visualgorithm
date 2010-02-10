/*
 * BinaryTreeFileFilter.java v1.00 26/10/08
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

package swing.tree;

import controller.IBinaryTreeController;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * This class defines the binary tree file filter that is used to open binary
 * tree files. It is not designed for inheritance. It is only instanciated in
 * the <tt>SoftwareViewIOOperation</tt> class.
 * 
 * @author Julien Hannier
 * @version 1.00 26/10/08
 */
public final class BinaryTreeFileFilter extends FileFilter {

    /**
     * Builds the tree file filter which only contains tree file extension.
     */
    public BinaryTreeFileFilter() {
        super();
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }

        String fileName = file.getName();
        int indexOfLastPoint = fileName.lastIndexOf('.');

        if ((indexOfLastPoint > 0) && (indexOfLastPoint < fileName.length() - 1)) {
            String extension = fileName.substring(indexOfLastPoint + 1).toLowerCase();

            return extension.equals(IBinaryTreeController.BINARY_TREE_FILE_EXTENSION);
        }
        
        return false;
    }

    @Override
    public String getDescription() {
        return "Binary Tree  ( ." + IBinaryTreeController.BINARY_TREE_FILE_EXTENSION + " )";
    }
}
