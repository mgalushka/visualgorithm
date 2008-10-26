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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Definition of the graphic node.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
class GraphicNode {

    /**
     * Definition of the graphic node color.
     * 
     * @author Julien Hannier
     * @author Pierre Pironin
     * @author Damien Rigoni
     * @version 1.00 16/06/08
     */
    enum GraphicNodeColor {
        BLACK, BLUE, GREEN, RED, YELLOW
    };

    private Integer key;

    private BufferedImage nodeImage;

    private GraphicNodeColor nodeColor;

    private int xPosition, yPosition, nodeSize;

    /**
     * Builds the graphic node.
     * 
     * @param k the key of the graphic node
     * @param x the x position of the graphic node
     * @param y the y position of the graphic node
     * @param s the size of the graphic node
     * @param c the color of the graphic node
     */
    GraphicNode(int k, int x, int y, int s, GraphicNodeColor c) {
        key = k;
        xPosition = x;
        yPosition = y;
        nodeSize = s;
        nodeColor = c;
        if (getNodeColor() == GraphicNodeColor.BLACK) {
            nodeImage = loadImage("black", getNodeSize());
        } else if (getNodeColor() == GraphicNodeColor.BLUE) {
            nodeImage = loadImage("blue", getNodeSize());
        } else if (getNodeColor() == GraphicNodeColor.GREEN) {
            nodeImage = loadImage("green", getNodeSize());
        } else if (getNodeColor() == GraphicNodeColor.RED) {
            nodeImage = loadImage("red", getNodeSize());
        } else {
            nodeImage = loadImage("yellow", getNodeSize());
        }
    }

    private BufferedImage loadImage(String name, int size) {
        String imgFileName = "img/node_" + name + "_" + size + ".png";
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Returns the key of the graphic node.
     * 
     * @return the key of the graphic node
     */
    int getNodeKey() {
        return key;
    }

    /**
     * Returns the color of the graphic node.
     * 
     * @return the color of the graphic node
     */
    GraphicNodeColor getNodeColor() {
        return nodeColor;
    }

    /**
     * Returns the size of the graphic node.
     * 
     * @return the size of the graphic node
     */
    int getNodeSize() {
        return nodeSize;
    }

    /**
     * Returns the x position of the graphic node.
     * 
     * @return the x position of the graphic node
     */
    int getXPosition() {
        return xPosition;
    }

    /**
     * Returns the y position of the graphic node.
     * 
     * @return the y position of the graphic node
     */
    int getYPosition() {
        return yPosition;
    }

    /**
     * Changes the size of the graphic node. The node size must be 30, 45, 60 or
     * 75 or else an exception is launched.
     * 
     * @param value the new size of the graphic node
     */
    void changeNodeSize(int value) {
        nodeSize = value;
        nodeImage = loadImage(getNodeColor().toString().toLowerCase(),
            getNodeSize());
    }

    /**
     * Changes the color of the graphic node.
     * 
     * @param c the new color of the graphic node
     */
    void changeNodeColor(GraphicNodeColor c) {
        nodeColor = c;
        if (getNodeColor() == GraphicNodeColor.BLACK) {
            nodeImage = loadImage("black", getNodeSize());
        } else if (getNodeColor() == GraphicNodeColor.BLUE) {
            nodeImage = loadImage("blue", getNodeSize());
        } else if (getNodeColor() == GraphicNodeColor.GREEN) {
            nodeImage = loadImage("green", getNodeSize());
        } else if (getNodeColor() == GraphicNodeColor.RED) {
            nodeImage = loadImage("red", getNodeSize());
        } else {
            nodeImage = loadImage("yellow", getNodeSize());
        }
    }

    /**
     * Changes the position of the graphic node.
     * 
     * @param x the new x position of the graphic node
     * @param y the new y position of the graphic node
     */
    void changeNodePosition(int x, int y) {
        xPosition = x;
        yPosition = y;
    }

    /**
     * Draws the graphic node.
     * 
     * @param g the graphics
     */
    void paint(Graphics g) {
        int smallestNodeSize = 30;
        int stringSizeFactor = getNodeSize() / 15 - 2;
        int initialFont = 12;
        int initialYString = 4;
        int initialXStringOneChar = 4;
        int initialXStringTwoChar = 7;
        int differenceBetweenFontSize = 8;
        int yStringDifferenceBetweenFont = 3;
        int xStringOneCharDifferenceBetweenFont = 3;
        int xStringTwoCharDifferenceBetweenFont = 5;
        int xString = 0;
        int yString = 0;
        String stringKey = key.toString();

        g.drawImage(nodeImage, getXPosition() - getNodeSize() / 2,
            getYPosition() - getNodeSize() / 2, getNodeSize(), getNodeSize(),
            null);
        if ((getNodeColor() == GraphicNodeColor.BLACK)
                || (getNodeColor() == GraphicNodeColor.RED)) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }
        g.setFont(new Font(null, Font.PLAIN,
                getNodeSize() == smallestNodeSize ? initialFont : initialFont
                        + differenceBetweenFontSize * stringSizeFactor));
        yString = getYPosition()
                + (getNodeSize() == smallestNodeSize ? initialYString
                        : initialYString + yStringDifferenceBetweenFont
                                * stringSizeFactor);
        if (stringKey.length() == 1) {
            xString = getXPosition()
                    - (getNodeSize() == smallestNodeSize ? initialXStringOneChar
                            : initialXStringOneChar
                                    + xStringOneCharDifferenceBetweenFont
                                    * stringSizeFactor);
        } else if (stringKey.length() == 2) {
            xString = getXPosition()
                    - (getNodeSize() == smallestNodeSize ? initialXStringTwoChar
                            : initialXStringTwoChar
                                    + xStringTwoCharDifferenceBetweenFont
                                    * stringSizeFactor);
        }
        g.drawString(stringKey, xString, yString);
    }
}
