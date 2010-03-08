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
import java.net.URLClassLoader;
import javax.imageio.ImageIO;

/**
 * This class defines the graphic node. The graphic node is composed by an image
 * that represents the node and a key that is drawn above it. A graphic node
 * also has a color and a size that are used to load the image representing the
 * node. Then, the node can be placed on a panel. This class is not designed for
 * inheritance.
 * 
 * @author Julien Hannier
 * @version 1.00 16/06/08
 */
final class GraphicNode {

    /**
     * Enumeration that defines the different sizes of graphic nodes. This
     * enumeration also gives access to the integer corresponding to the color
     * with the method {@code int getSizeAsInt()}. This integer is used to
     * load the image. It is also possible to increment or decrement a graphic
     * node size respectively with the methods
     * {@code GraphicNodeSize incrementSize()} and
     * {@code GraphicNodeSize decrementSize()}.
     *
     * @author Julien Hannier
     * @version 1.00 16/06/08
     */
    static enum GraphicNodeSize {
        ONE(30), TWO(45), THREE(60), FOUR(75);

        private int sizeInt;

        private GraphicNodeSize(int size) {
            sizeInt = size;
        }

        /**
         * Returns the integer corresponding to the size on which the method
         * is applied.
         *
         * @return the integer corresponding to the size
         */
        int getSizeAsInt() {
            return sizeInt;
        }

        /**
         * Returns an increment of the current size.
         *
         * @return the increment of the current size
         */
        GraphicNodeSize incrementSize() {
            switch (sizeInt) {
                case 30:
                    return TWO;
                case 45:
                    return THREE;
                case 60:
                    return FOUR;
                case 75:
                    return FOUR;
                default:
                   return this;
            }
        }

        /**
         * Returns a decrement of the current size.
         *
         * @return the decrement of the current size
         */
        GraphicNodeSize decrementSize() {
            switch (sizeInt) {
                case 30:
                    return ONE;
                case 45:
                    return ONE;
                case 60:
                    return TWO;
                case 75:
                    return THREE;
                default:
                   return this;
            }
        }
    }
    
    /**
     * Enumeration that defines the different colors of graphic nodes. This
     * enumeration also gives access to the string corresponding to the color
     * with the method {@code String getColorAsString()}. This string is used to
     * load the image.
     * 
     * @author Julien Hannier
     * @version 1.00 16/06/08
     */
    static enum GraphicNodeColor {
        BLACK("black"), BLUE("blue"), GREEN("green"), RED("red"),
        YELLOW("yellow");

        private String colorString;

        private GraphicNodeColor(String color) {
            colorString = color;
        }

        /**
         * Returns the string corresponding to the color on which the method
         * is applied.
         *
         * @return the string corresponding to the color
         */
        String getColorAsString() {
            return colorString;
        }
    }

    private static URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

    private int nodeKey;

    private int xPosition;

    private int yPosition;

    private GraphicNodeSize nodeSize;

    private GraphicNodeColor nodeColor;

    private BufferedImage nodeImage;

    /**
     * Builds the graphic node. The graphic node is composed by an image that
     * represents the node and a key that is drawn above it.
     * 
     * @param key the key of the graphic node
     * @param xPos the x position of the graphic node
     * @param yPos the y position of the graphic node
     * @param size the size of the graphic node
     * @param color the color of the graphic node
     */
    GraphicNode(int key, int xPos, int yPos, GraphicNodeSize size, GraphicNodeColor color) {
        nodeKey = key;
        xPosition = xPos;
        yPosition = yPos;
        nodeSize = size;
        nodeColor = color;
        // TODO The node image might be null
        nodeImage = loadNodeImage(nodeColor, nodeSize);
    }

    private BufferedImage loadNodeImage(GraphicNodeColor color, GraphicNodeSize size) {
        BufferedImage image = null;
        String imageFileName = "img/node_" + color.getColorAsString() + "_" +
                size.getSizeAsInt() + ".png";

        try {
            image = ImageIO.read(urlClassLoader.findResource(imageFileName));
        } catch (Exception ex) {
        }
        
        return image;
    }

    /**
     * Returns the key of the graphic node.
     * 
     * @return the key of the graphic node
     */
    int getNodeKey() {
        return nodeKey;
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
    GraphicNodeSize getNodeSize() {
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
     * Changes the size of the graphic node.
     * 
     * @param size the new size of the graphic node
     */
    void changeNodeSize(GraphicNodeSize size) {
        nodeSize = size;
        nodeImage = loadNodeImage(nodeColor, nodeSize);
    }

    /**
     * Changes the color of the graphic node.
     * 
     * @param color the new color of the graphic node
     */
    void changeNodeColor(GraphicNodeColor color) {
        nodeColor = color;
        nodeImage = loadNodeImage(nodeColor, nodeSize);
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
     * Paints the graphic node and its key.
     * 
     * @param graphics the graphics to paint the node
     */
    void paintNode(Graphics graphics) {
        int currentNodeSize = nodeSize.getSizeAsInt();
        int smallestNodeSize = GraphicNodeSize.ONE.getSizeAsInt();
        int smallestFontSize = 12;
        int fontSizeFactor = currentNodeSize / 15 - 2;
        int differenceBetweenFontSize = 8;
        int initialYPositionKeyString = 4;
        int initialXPositionOneCharKeyString = 4;
        int initialXPositionTwoCharKeyString = 7;
        int yDifferenceBetweenFont = 3;
        int xDifferenceBetweenOneCharFont = 3;
        int xDifferenceBetweenTwoCharFont = 5;
        int xPositionKeyString = 0;
        int yPositionKeyString = 0;
        String nodeKeyString = Integer.toString(nodeKey);

        graphics.drawImage(nodeImage, xPosition - currentNodeSize / 2,
                yPosition - currentNodeSize / 2, currentNodeSize,
                currentNodeSize, null);
        
        if ((nodeColor == GraphicNodeColor.BLACK)
                || (nodeColor == GraphicNodeColor.RED)) {
            graphics.setColor(Color.WHITE);
        } else {
            graphics.setColor(Color.BLACK);
        }
        if (currentNodeSize == smallestNodeSize) {
            graphics.setFont(new Font(null, Font.PLAIN, smallestFontSize));
            yPositionKeyString = yPosition + initialYPositionKeyString;
            
            if (nodeKeyString.length() == 1) {
                xPositionKeyString = xPosition - initialXPositionOneCharKeyString;
            } else if (nodeKeyString.length() == 2) {
                xPositionKeyString = xPosition - initialXPositionTwoCharKeyString;
            }
        } else {
            graphics.setFont(new Font(null, Font.PLAIN,
                    smallestFontSize + differenceBetweenFontSize * fontSizeFactor));
            yPositionKeyString = yPosition + initialYPositionKeyString +
                    yDifferenceBetweenFont * fontSizeFactor;

            if (nodeKeyString.length() == 1) {
                xPositionKeyString = xPosition - (initialXPositionOneCharKeyString +
                        xDifferenceBetweenOneCharFont * fontSizeFactor);
            } else if (nodeKeyString.length() == 2) {
                xPositionKeyString = xPosition - (initialXPositionTwoCharKeyString +
                        xDifferenceBetweenTwoCharFont * fontSizeFactor);
            }
        }
        graphics.drawString(nodeKeyString, xPositionKeyString, yPositionKeyString);
    }
}
