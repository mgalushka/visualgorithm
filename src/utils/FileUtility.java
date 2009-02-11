/*
 * FileUtils.java v1.00 08/12/08
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

package utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Definition of some utilities for files.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 08/12/08
 */
public class FileUtility {

    private FileUtility() {
    }

    /**
     * Returns the list of files which are in the indicated directory and which
     * correspond to the string fileType.
     * 
     * @param directory the indicated directory
     * @param fileType the string of selection
     * @return the list of files
     */
    public static String[] listOfFilesInDirectory(File directory,
            String fileType) {
        final String selection = fileType;
        return directory.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (name.contains(selection) && !name.startsWith("I")) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Returns the list of directories which are in the indicated directory.
     * 
     * @param directory the indicated directory
     * @return the list of directories
     */
    public static String[] listOfDirectoriesInDirectory(File directory) {
        return directory.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (!name.contains(".java")) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Transforms the name of the class file in order to be use with reflection.
     * 
     * @param name the name of the class file
     * @param directory the directory where the class is
     * @return the name after modifications
     */
    public static String wellFormedClassName(String name, File directory) {
        String className = name;
        int i = className.lastIndexOf('.');
        className = className.substring(0, i);
        className = directory.getPath().concat("/" + className);
        className = className.replaceAll("/", ".");
        int j = className.indexOf('.');
        className = className.substring(j + 1);
        return className;
    }
}
