/*
 * TreeFile.java v1.00 02/07/08
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

package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.tree.IBinaryNode;
import model.tree.IBinaryTree;
import model.tree.UnknownTreeTypeException;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * Loading and saving tree file.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 02/07/08
 */
public abstract class TreeFile {

    /**
     * Position of the key in the file.
     */
    protected final static int KEY = 1;

    /**
     * Position of the left child in the file.
     */
    protected final static int LEFT_CHILD = 2;

    /**
     * Position of the right child in the file.
     */
    protected final static int RIGHT_CHILD = 3;

    /**
     * Definition of spaces.
     */
    protected static final String SPACE = "\t";

    /**
     * Definition of nil nodes.
     */
    protected static final String NIL_NODE = "nil";

    /**
     * Definition of the comment regular expression.
     */
    protected final static String REGEX_COMMENT = "\\p{Blank}*#.*";

    /**
     * Definition of the line comment regular expression : text # comment.
     */
    protected final static String REGEX_COMMENT_LINE = "("
            + "\\p{Blank}*|\\p{Blank}+#.*)?";

    /**
     * Definition of the blank regular expression.
     */
    protected final static String REGEX_BLANK = "\\p{Blank}+";

    /**
     * Definition of the key regular expression.
     */
    protected final static String REGEX_KEY = "\\d{1,2}";

    /**
     * Definition of the tree type regular expression.
     */
    protected final static String REGEX_WORD = "\\w+";

    /**
     * Definition of the empty line regular expression.
     */
    protected final static String REGEX_EMPTY_LINE = "\\p{Blank}*";

    /**
     * Definition of the regular expression for the nodes which have two child.
     */
    protected static String REGEX_2_CHILD;

    /**
     * Definition of the regular expression for the nodes which have only a left
     * child.
     */
    protected static String REGEX_LEFT_CHILD;

    /**
     * Definition of the regular expression for the nodes which have only a
     * right child.
     */
    protected static String REGEX_RIGHT_CHILD;

    /**
     * Definition of the regular expression for the nodes which have no child.
     */
    protected static String REGEX_NO_CHILD;

    /**
     * The file parser.
     */
    protected static HashMap<String, TreeFile> fileParser = new HashMap<String, TreeFile>();

    private int lineNumber;

    /**
     * The list of the nodes.
     */
    protected Vector<String[]> nodeVector;

    /**
     * The type of the tree.
     */
    protected String treeType;

    static {
        fileParser.put(BinaryTreeType.BINARYSEARCHTREE.toString(),
            new BinarySearchTreeFile());
        fileParser.put(BinaryTreeType.AVLTREE.toString(), new AVLTreeFile());
        fileParser.put(BinaryTreeType.REDBLACKTREE.toString(),
            new RedBlackTreeFile());
    }

    /**
     * Builds the tree file parser.
     */
    protected TreeFile() {
        nodeVector = new Vector<String[]>();
        treeType = new String();
    }

    private static TreeFile parse(String fileName) throws ParseException,
            IOException, FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int line = 0;
        String lineToParse = reader.readLine();
        Pattern pattern;
        Matcher matcher;
        TreeFile parser = null;

        while (lineToParse != null && parser == null) {
            if (lineToParse.matches(REGEX_COMMENT)
                    | lineToParse.matches(REGEX_EMPTY_LINE)) {
                lineToParse = reader.readLine();
                ++line;
            } else if (lineToParse.matches(REGEX_EMPTY_LINE + "\\w+"
                    + REGEX_COMMENT_LINE)) {
                pattern = Pattern.compile("\\w+");
                matcher = pattern.matcher(lineToParse);
                matcher.find();
                String treeType = matcher.group();
                if (fileParser.containsKey(treeType)) {
                    parser = fileParser.get(treeType);
                    parser.nodeVector.clear();
                    parser.treeType = matcher.group();
                    parser.parse(reader);
                    parser.lineNumber = ++line;
                } else {
                    throw new ParseException("The tree type " + matcher.group()
                            + " is unknown", line);
                }
            } else {
                throw new ParseException(
                        "The type of the tree is not specified,"
                                + " or specified after the nodes", line);
            }
        }
        if (parser != null) {
            return parser;
        } else {
            throw new ParseException("The file is empty", line);
        }
    }

    private IBinaryTree<?> createBinaryTree() throws UnknownTreeTypeException {
        IBinaryTree<?> tree;
        if (nodeVector.size() != 0) {
            tree = createBinaryTree(Integer.parseInt(nodeVector.get(0)[KEY]));
            generateNode(tree.getRoot(), 0);

        } else {
            tree = createEmptyBinaryTree();
        }
        if (tree.isGoodTree())
            return tree;
        else {
            String article = treeType.charAt(0) == 'A' ? "an " : "a ";
            throw new UnknownTreeTypeException(
                    "The tree does not satisfy the properties " + "of "
                            + article + treeType);
        }
    }

    private <N extends IBinaryNode<N>> void generateNode(N node,
            int currentNodeNumber) {
        if (currentNodeNumber >= nodeVector.size()) {
        } else {
            if (nodeVector.get(currentNodeNumber)[LEFT_CHILD].equals(NIL_NODE)) {
            } else {
                int childNodeNumber = Integer.parseInt(nodeVector
                        .get(currentNodeNumber)[LEFT_CHILD]);
                setLeftNode(node, childNodeNumber);
                node.getLeft().setFather(node);
                generateNode(node.getLeft(), childNodeNumber);
            }
            if (nodeVector.get(currentNodeNumber)[RIGHT_CHILD].equals(NIL_NODE)) {
            } else {
                int childNodeNumber = Integer.parseInt(nodeVector
                        .get(currentNodeNumber)[RIGHT_CHILD]);
                setRightNode(node, childNodeNumber);
                node.getRight().setFather(node);
                generateNode(node.getRight(), childNodeNumber);
            }
        }
    }

    private void parse(BufferedReader reader) throws IOException,
            ParseException {
        int currentNodeNumber = 0;
        int nextNodeNumber = 1;
        String lineToParse = reader.readLine();
        Pattern pattern;
        Matcher matcher;

        if (lineToParse == null) {
            ++currentNodeNumber;
        }
        while (lineToParse != null) {
            initREGEX(currentNodeNumber, nextNodeNumber);
            if (lineToParse.matches(REGEX_COMMENT)
                    | lineToParse.matches(REGEX_EMPTY_LINE)) {
            } else if (lineToParse.matches(REGEX_2_CHILD + REGEX_COMMENT_LINE)) {
                pattern = Pattern.compile(REGEX_2_CHILD);
                matcher = pattern.matcher(lineToParse);
                matcher.find();
                nodeVector.add(matcher.group().split(REGEX_BLANK));
                ++currentNodeNumber;
                nextNodeNumber += 2;
            } else if (lineToParse.matches(REGEX_LEFT_CHILD
                    + REGEX_COMMENT_LINE)) {
                pattern = Pattern.compile(REGEX_LEFT_CHILD);
                matcher = pattern.matcher(lineToParse);
                matcher.find();
                nodeVector.add(matcher.group().split(REGEX_BLANK));
                ++currentNodeNumber;
                ++nextNodeNumber;
            } else if (lineToParse.matches(REGEX_RIGHT_CHILD
                    + REGEX_COMMENT_LINE)) {
                pattern = Pattern.compile(REGEX_RIGHT_CHILD);
                matcher = pattern.matcher(lineToParse);
                matcher.find();
                nodeVector.add(matcher.group().split(REGEX_BLANK));
                ++currentNodeNumber;
                ++nextNodeNumber;
            } else if (lineToParse.matches(REGEX_NO_CHILD + REGEX_COMMENT_LINE)) {
                if (currentNodeNumber >= nextNodeNumber) {
                    throw new ParseException(
                            "Too many nodes have been specified", lineNumber);
                } else {
                    pattern = Pattern.compile(REGEX_NO_CHILD);
                    matcher = pattern.matcher(lineToParse);
                    matcher.find();
                    nodeVector.add(matcher.group().split(REGEX_BLANK));
                    ++currentNodeNumber;
                }
            } else {
                throw new ParseException(
                        "There is a syntax error on the line : "
                                + lineToParse.replace(SPACE, " "), lineNumber);
            }
            lineToParse = reader.readLine();
            ++lineNumber;
        }
        if (currentNodeNumber != nextNodeNumber) {
            throw new ParseException("There is not enough nodes", lineNumber);
        }
    }

    /**
     * Creates the string corresponding to the node.
     * 
     * @param node the node of the line
     * @param currentNodeNumber the number of the current node
     * @param leftNodeNumber the number of the left child
     * @param rightNodeNumber the number of the right child
     * @return the string corresponding to the node
     */
    protected <N extends IBinaryNode<?>> String getNode(N node,
            int currentNodeNumber, String leftNodeNumber, String rightNodeNumber) {
        return currentNodeNumber + SPACE + node.getKey() + SPACE
                + leftNodeNumber + SPACE + rightNodeNumber;
    }

    /**
     * Initializes the regular expressions.
     * 
     * @param currentNodeNumber the index of the current node
     * @param nextNodeNumber the index of the next node
     */
    protected void initREGEX(int currentNodeNumber, int nextNodeNumber) {
        String lineBegin = currentNodeNumber + REGEX_BLANK + REGEX_KEY
                + REGEX_BLANK;
        REGEX_2_CHILD = lineBegin + nextNodeNumber + REGEX_BLANK
                + (nextNodeNumber + 1);
        REGEX_LEFT_CHILD = lineBegin + nextNodeNumber + REGEX_BLANK + "nil";
        REGEX_RIGHT_CHILD = lineBegin + "nil" + REGEX_BLANK + nextNodeNumber;
        REGEX_NO_CHILD = lineBegin + "nil" + REGEX_BLANK + "nil";
    }

    /**
     * Creates an empty tree with for root null.
     * 
     * @return the created tree
     */
    protected abstract IBinaryTree<?> createEmptyBinaryTree();

    /**
     * Creates a tree with for root the key given in parameter.
     * 
     * @param key the key of the node
     * @return the created tree
     */
    protected abstract IBinaryTree<?> createBinaryTree(int key);

    /**
     * Creates the left child of the node given in parameter.
     * 
     * @param node the node which the left child has to be assigned
     * @param childNodeNumber the index of the key
     */
    protected abstract <N extends IBinaryNode<?>> void setLeftNode(N node,
            int childNodeNumber);

    /**
     * Creates the right child of the node given in parameter.
     * 
     * @param node the node which the right child has to be assigned
     * @param childNodeNumber the index of the key
     */
    protected abstract <N extends IBinaryNode<?>> void setRightNode(N node,
            int childNodeNumber);

    /**
     * Creates a tree from a load file.
     * 
     * @param fileName the file to load
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws IOException
     * @throws UnknownTreeTypeException
     */
    public static IBinaryTree<?> load(String fileName)
            throws FileNotFoundException, ParseException, IOException,
            UnknownTreeTypeException {
        TreeFile parser = TreeFile.parse(fileName);
        return parser.createBinaryTree();
    }

    /**
     * Saves the tree into a file.
     * 
     * @param tree the tree to save
     * @param fileName the name of the file
     * @throws IOException
     * @throws UnknownTreeTypeException
     */
    public final static <N extends IBinaryNode<N>> void save(
            IBinaryTree<N> tree, String fileName) throws IOException {
        FileWriter file = new FileWriter(fileName);
        N node;
        int currentNodeNumber = 0;
        int maxNodeNumber = 0;
        String leftNodeNumber;
        String rightNodeNumber;
        List<N> array = tree.treeToArrayList();

        file.write(tree.getType() + "\n");
        for (int i = 0; i < array.size(); i++) {
            node = array.get(i);
            if (node != null) {
                if (node.getLeft() == null) {
                    leftNodeNumber = NIL_NODE;
                } else {
                    ++maxNodeNumber;
                    leftNodeNumber = Integer.toString(maxNodeNumber);
                }
                if (node.getRight() == null) {
                    rightNodeNumber = NIL_NODE;
                } else {
                    ++maxNodeNumber;
                    rightNodeNumber = Integer.toString(maxNodeNumber);
                }
                file.write(fileParser.get(tree.getType().toString()).getNode(
                    node, currentNodeNumber, leftNodeNumber, rightNodeNumber)
                        + "\n");
                ++currentNodeNumber;
            }
        }
        file.close();
    }
}
