/*
 * FileUtilityTest.java v1.00 27/02/09
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
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of the file utility class.
 * 
 * @author Julien Hannier
 * @version 1.00 27/02/09
 */
public class FileUtilityTest {

    private File modelDirectory;

    private File viewDirectory;

    private File swingDirectory;

    private File utilsDirectory;

    @Before
    public void setUp() {
        modelDirectory = new File("src" + File.separator + "model");
        viewDirectory = new File("src" + File.separator + "view");
        swingDirectory = new File("src" + File.separator + "swing");
        utilsDirectory = new File("src" + File.separator + "utils");
    }

    @Test
    public void testFileUtility() {
        String[] directoriesInModel =
                FileUtility.listOfDirectoriesInDirectory(modelDirectory);
        String[] directoriesInSwing =
                FileUtility.listOfDirectoriesInDirectory(swingDirectory);
        assertEquals((directoriesInModel.length > 0), true);
        assertEquals((directoriesInSwing.length > 0), true);
        assertEquals(directoriesInModel.length, directoriesInSwing.length);
        
        List<String> listOfDirectoriesInModel = Arrays.asList(directoriesInModel);
        assertEquals(listOfDirectoriesInModel.contains("tree"), true);

        String[] classesInView = FileUtility.listOfClassesInDirectory(
                viewDirectory, ".java");
        assertEquals(classesInView.length, 0);

        String[] classesInUtils = FileUtility.listOfClassesInDirectory(
                utilsDirectory, "FileUtility");
        assertEquals(classesInUtils.length, 1);
        assertEquals(classesInUtils[0], "FileUtility.java");
        
        String classNameWithPackagePath = FileUtility.classNameWithPackagePath(
                classesInUtils[0], utilsDirectory);
        assertEquals(classNameWithPackagePath, "utils.FileUtility");
    }
}
