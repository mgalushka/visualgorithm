/*
 * TreeFileFilter.java v1.00 26/10/08
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

import io.tree.TreeFile;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Declares the tree file filter.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 26/10/08
 */
public class TreeFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String name = file.getName();
        int i = name.lastIndexOf('.');
        String extension = null;
        if ((i > 0) && (i < name.length() - 1)) {
            extension = name.substring(i + 1).toLowerCase();
        }
        if (extension != null) {
            if (extension.equals(TreeFile.fileExtension)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Binary Tree  ( ." + TreeFile.fileExtension + " )";
    }
}
