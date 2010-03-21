/*
 * ImageLoadingUtility.java v1.00 14/03/10
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

import java.awt.image.BufferedImage;
import java.net.URLClassLoader;
import javax.imageio.ImageIO;

/**
 * This class defines the image loading utility. It is composed by static
 * methods in order to deal with the loading of images. It is a utility class,
 * so it cannot be instanciated. This class is not designed for inheritance.
 *
 * @author Julien Hannier
 * @version 1.00 14/03/10
 */
public final class ImageLoadingUtility {

    private static URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    
    private ImageLoadingUtility() {
    }

    /**
     * Loads the image indicated with {@code imageFileName} from the jar file of
     * the application. If the image cannot be found then null is returned.
     *
     * @param imageFileName the name of the image to load
     * @return the loaded image or else null
     */
    public static BufferedImage loadImageFromVisualgorithmJar(String imageFileName) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(urlClassLoader.findResource(imageFileName));
        } catch (Exception ex) {
        }

        return image;
    }
}
