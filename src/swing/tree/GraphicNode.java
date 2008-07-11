/*
 * GraphicNode.java v1.00 16/06/08
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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Definition of the graphic node of binary trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class GraphicNode {

    /**
     * Definition of the graphic node color.
     * 
     * @author Julien Hannier
     * @author Pierre Pironin
     * @author Damien Rigoni
     * @version 1.00 16/06/08
     */
    enum GraphicNodeColor {
        BLACK, BLUE, RED, YELLOW
    };

    private Integer key;

    private BufferedImage image;

    private GraphicNodeColor color;

    private int xPosition, yPosition, nodeSize;

    /**
     * Builds the graphic node.
     * 
     * @param k the key of the node
     * @param x the x position of the node
     * @param y the y position of the node
     * @param s the size of the node
     * @param c the color of the node
     */
    GraphicNode(int k, int x, int y, int s, GraphicNodeColor c) {
        key = k;
        xPosition = x;
        yPosition = y;
        nodeSize = s;
        color = c;
        if (color == GraphicNodeColor.BLACK) {
            image = loadImage("black", nodeSize);
        } else if (color == GraphicNodeColor.BLUE) {
            image = loadImage("blue", nodeSize);
        } else if (color == GraphicNodeColor.RED) {
            image = loadImage("red", nodeSize);
        } else {
            image = loadImage("yellow", nodeSize);
        }
    }

    private BufferedImage loadImage(String name, int size) {
        String imgFileName = "img/node_" + name + "_" + size + ".png";
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgFileName));
        } catch (Exception e) {
            System.out.println("Image could not be read");
            System.exit(1);
        }
        return img;
    }

    /**
     * Returns the key of the node.
     * 
     * @return the key of the node
     */
    int getNodeKey() {
        return key;
    }
    
    /**
     * Returns the color of the node.
     * 
     * @return the color of the node
     */
    GraphicNodeColor getNodeColor() {
        return color;
    }

    /**
     * Returns the size of the node.
     * 
     * @return the size of the node
     */
    int getNodeSize() {
        return nodeSize;
    }

    /**
     * Returns the x position of the node.
     * 
     * @return the x position of the node
     */
    int getXPosition() {
        return xPosition;
    }

    /**
     * Returns the y position of the node.
     * 
     * @return the y position of the node
     */
    int getYPosition() {
        return yPosition;
    }

    /**
     * Changes the color of the node.
     * 
     * @param c the new color of the node
     */
    void changeColor(GraphicNodeColor c) {
        color = c;
        if (color == GraphicNodeColor.BLACK) {
            image = loadImage("black", nodeSize);
        } else if (color == GraphicNodeColor.BLUE) {
            image = loadImage("blue", nodeSize);
        } else if (color == GraphicNodeColor.RED) {
            image = loadImage("red", nodeSize);
        } else {
            image = loadImage("yellow", nodeSize);
        }
    }

    /**
     * Changes the position of the node.
     * 
     * @param x the new x position of the node
     * @param y the new y position of the node
     */
    void move(int x, int y) {
        xPosition = x;
        yPosition = y;
    }

    /**
     * Draws the node.
     * 
     * @param g the graphics
     */
    void paint(Graphics g) {
        g.drawImage(image, xPosition - nodeSize / 2, yPosition - nodeSize / 2,
            nodeSize, nodeSize, null);
        String stringKey = key.toString();
        if ((color == GraphicNodeColor.BLACK)
                || (color == GraphicNodeColor.RED)) {
            g.setColor(Color.WHITE);
        }
        if (stringKey.length() == 1) {
            g.drawString(stringKey, xPosition - 3, yPosition + 4);
        } else {
            g.drawString(stringKey, xPosition - 8, yPosition + 4);
        }
    }
}
