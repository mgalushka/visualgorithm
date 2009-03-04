/*
 * FileUtility.java v1.00 08/12/08
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
import java.util.regex.Matcher;

/**
 * This utility class contains functions to work with directories and files.
 * There are functions to list directories or classes but also to modify the
 * name of a class file in order to use it with Java reflection. All the class
 * names must be defined according to Java conventions.
 * 
 * @author Julien Hannier
 * @version 1.00 08/12/08
 */
public class FileUtility {

    private FileUtility() {
    }

    /**
     * Returns the list of directories that are in {@code directory}.
     *
     * @param directory the directory where to list directories
     * @return the list of directories in the directory
     */
    public static String[] listOfDirectoriesInDirectory(File directory) {
        return directory.list(new FilenameFilter() {

            @Override
            public boolean accept(File file, String name) {
                if (isDirectory(name)) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Returns the list of classes that are in {@code directory} and which the
     * name corresponds to {@code select}. The selection eliminates interfaces
     * and abstract classes.
     * 
     * @param directory the directory where to list classes
     * @param select the string of selection
     * @return the list of classes in the directory
     */
    public static String[] listOfClassesInDirectory(File directory,
            final String select) {
        return directory.list(new FilenameFilter() {

            @Override
            public boolean accept(File file, String name) {
                if (isFile(name) && isClass(name) && name.contains(select)) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Transforms the name of the class file in order to use it with Java
     * reflection. This method deletes the file extension of the class file in
     * order to have the class name. Then, it concats the package path to the
     * class name. After, it replaces each file separator ('/' for Unix systems
     * and '\\' for Windows) by a point. Finally, it deletes the src folder from
     * the name.
     * 
     * @param fileName the name of the class with file extension
     * @param directory the directory where the class is
     * @return the class name with package path
     */
    public static String classNameWithPackagePath(String fileName, File directory) {
        String classNameWithPackagePath = fileName;
        int lastPointIndex = classNameWithPackagePath.lastIndexOf('.');
        classNameWithPackagePath = classNameWithPackagePath.substring(0,
                lastPointIndex);
        classNameWithPackagePath = directory.getPath().concat(File.separator +
                classNameWithPackagePath);
        classNameWithPackagePath = classNameWithPackagePath.replaceAll(
                Matcher.quoteReplacement(File.separator), ".");
        int firstPointIndex = classNameWithPackagePath.indexOf('.');
        classNameWithPackagePath = classNameWithPackagePath.substring(
                firstPointIndex + 1);
        return classNameWithPackagePath;
    }

    private static boolean isDirectory(String fileName) {
        return !fileName.contains(".");
    }

    private static boolean isFile(String fileName) {
        return fileName.contains(".");
    }

    private static boolean isInterface(String fileName) {
        return fileName.startsWith("I") &&
                Character.isUpperCase(fileName.charAt(1));
    }

    private static boolean isAbstractClass(String fileName) {
        return fileName.startsWith("Abstract");
    }

    private static boolean isClass(String fileName) {
        return !isInterface(fileName) && !isAbstractClass(fileName) &&
                Character.isUpperCase(fileName.charAt(0));
    }
}
