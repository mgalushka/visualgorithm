/*
 * ImageLoadingUtilityTest.java v0.10 14/03/10
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

package util;

import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.Before;

/**
 * Test of the image loading utility.
 *
 * @author Julien Hannier
 * @version 0.10 14/03/10
 */
public class ImageLoadingUtilityTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testImageLoadingUtility() {
        assertNull(ImageLoadingUtility.loadImageFromVisualgorithmJar("Nothing"));
    }
}
