/*
 * TreeFile.java v0.10 02/07/08
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

package io.tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.UnknownDataStructureException;
import model.tree.IBinaryNode;
import model.tree.IBinaryTree;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * This abstract class defines the file input and output operations for binary
 * trees. It also defines a readable file format for binary trees that is :
 * {@literal [index] [key] [index of left child] [index of right child]}. This
 * class is designed for inheritance in order to obtain the concrete type of
 * binary trees or to add new node attributes in the file format. The class
 * <tt>TreeFile</tt> does everything. In fact, it uses the design pattern
 * template method to perform the creation of the different types of binary
 * trees. Thus, these are static methods from <tt>TreeFile</tt> that are used to
 * save and load binary trees. If you would like to add other tree files, do not
 * forget to register your tree file classes in the data structure
 * {@code fileParser} in this class.
 *
 * @author Damien Rigoni
 * @version 0.10 02/07/08
 */
public abstract class TreeFile {

    /**
     * Definition of the extension of the binary tree files.
     */
    public static final String FILE_EXTENSION = "bt";
    
    /**
     * Definition of the position of the key in the string tab of the node list.
     */
    protected static final int KEY_INDEX = 1;

    /**
     * Definition of the position of the left child in the string tab of the
     * node list.
     */
    protected static final int LEFT_CHILD_INDEX = 2;

    /**
     * Definition of the position of the right child in the string tab of the
     * node list.
     */
    protected static final int RIGHT_CHILD_INDEX = 3;

    /**
     * Definition of spaces.
     */
    protected static final String SPACE = "\t";

    /**
     * Definition of null nodes. It is defined by {@literal nil}.
     */
    protected static final String NIL_NODE = "nil";

    /**
     * Definition of the comment regular expression. A comment is defined by
     * {@literal #} followed by text.
     */
    protected static final String REGEX_COMMENT = "\\p{Blank}*#.*";

    /**
     * Definition of the line comment regular expression. A comment is defined
     * by {@literal #} followed by text.
     */
    protected static final String REGEX_COMMENT_LINE = "(\\p{Blank}*|" +
            "\\p{Blank}+#.*)?";

    /**
     * Definition of the blank regular expression.
     */
    protected static final String REGEX_BLANK = "\\p{Blank}+";

    /**
     * Definition of the key regular expression. The key is composed by one or
     * two digits.
     */
    protected static final String REGEX_KEY = "\\d{1,2}";

    /**
     * Definition of the empty line regular expression.
     */
    protected static final String REGEX_EMPTY_LINE = "\\p{Blank}*";

    /**
     * Definition of the regular expression for nodes that have two children.
     */
    protected static String regex2Child;

    /**
     * Definition of the regular expression for nodes that have only a left
     * child.
     */
    protected static String regexLeftChild;

    /**
     * Definition of the regular expression for nodes that have only a right
     * child.
     */
    protected static String regexRightChild;

    /**
     * Definition of the regular expression for nodes that have no children.
     */
    protected static String regexNoChild;

    /**
     * The file parser associates each type of binary tree with the good type of
     * tree file.
     */
    private static final HashMap<String, TreeFile> fileParsers =
            new HashMap<String, TreeFile>();

    private int lineNumber;

    /**
     * The list of the nodes represented as string tab. The string tab contains
     * one string for each attribute of a node. There are the key and the number
     * of the children nodes.
     */
    protected List<String[]> nodeVector;

    /**
     * The type of the binary tree.
     */
    protected String treeType;

    /**
     * Registration of the tree files according to the types of binary trees.
     * You have to register new tree files here.
     */
    static {
        fileParsers.put(BinaryTreeType.BINARYSEARCHTREE.toString(),
                new BinarySearchTreeFile());
        fileParsers.put(BinaryTreeType.AVLTREE.toString(), new AVLTreeFile());
        fileParsers.put(BinaryTreeType.REDBLACKTREE.toString(),
                new RedBlackTreeFile());
    }

    /**
     * Builds the tree file with an empty node vector and an empty tree type.
     */
    protected TreeFile() {
        nodeVector = new ArrayList<String[]>();
        treeType = new String();
    }

    private static TreeFile parse(String fileName) throws ParseException,
            IOException, FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String lineToParse = reader.readLine();
        int line = 0;
        Pattern pattern = null;
        Matcher matcher = null;
        TreeFile parser = null;

        while ((lineToParse != null) && (parser == null)) {
            if (lineToParse.matches(REGEX_COMMENT)
                    || lineToParse.matches(REGEX_EMPTY_LINE)) {
                lineToParse = reader.readLine();
                ++line;
            } else if (lineToParse.matches(REGEX_EMPTY_LINE + "\\w+"
                    + REGEX_COMMENT_LINE)) {
                pattern = Pattern.compile("\\w+");
                matcher = pattern.matcher(lineToParse);
                matcher.find();
                String treeType = matcher.group();
                if (fileParsers.containsKey(treeType)) {
                    parser = fileParsers.get(treeType);
                    parser.nodeVector.clear();
                    parser.treeType = matcher.group();
                    parser.parse(reader);
                    parser.lineNumber = ++line;
                } else {
                    throw new ParseException("The tree type " + matcher.group() +
                            " is unknown", line);
                }
            } else {
                throw new ParseException("The type of the tree is not" +
                        " specified, or specified after the nodes", line);
            }
        }
        if (parser != null) {
            return parser;
        } else {
            throw new ParseException("The file is empty", line);
        }
    }

    private IBinaryTree createBinaryTree() throws UnknownDataStructureException {
        IBinaryTree tree = null;
        if (!nodeVector.isEmpty()) {
            tree = createBinaryTree(Integer.parseInt(nodeVector.get(0)[KEY_INDEX]));
            generateNode(tree.getRoot(), 0);
        } else {
            tree = createEmptyBinaryTree();
        }
        if (tree.isWellFormedTree()) {
            return tree;
        } else {
            throw new UnknownDataStructureException("The tree does not satisfy" +
                    " the properties of " + treeType);
        }
    }

    private void generateNode(IBinaryNode node, int currentNodeNumber) {
        if (currentNodeNumber < nodeVector.size()) {
            if (!nodeVector.get(currentNodeNumber)[LEFT_CHILD_INDEX].equals(NIL_NODE)) {
                int childNodeNumber = Integer.parseInt(
                        nodeVector.get(currentNodeNumber)[LEFT_CHILD_INDEX]);
                setLeftNode(node, childNodeNumber);
                node.getLeft().setFather(node);
                generateNode(node.getLeft(), childNodeNumber);
            }
            if (!nodeVector.get(currentNodeNumber)[RIGHT_CHILD_INDEX].equals(NIL_NODE)) {
                int childNodeNumber = Integer.parseInt(
                        nodeVector.get(currentNodeNumber)[RIGHT_CHILD_INDEX]);
                setRightNode(node, childNodeNumber);
                node.getRight().setFather(node);
                generateNode(node.getRight(), childNodeNumber);
            }
        }
    }

    private void parse(BufferedReader reader) throws IOException, ParseException {
        String lineToParse = reader.readLine();
        int currentNodeNumber = 0;
        int nextNodeNumber = 1;
        Pattern pattern = null;
        Matcher matcher = null;

        if (lineToParse == null) {
            ++currentNodeNumber;
        }
        while (lineToParse != null) {
            initREGEX(currentNodeNumber, nextNodeNumber);
            if (lineToParse.matches(regex2Child + REGEX_COMMENT_LINE)) {
                pattern = Pattern.compile(regex2Child);
                matcher = pattern.matcher(lineToParse);
                matcher.find();
                nodeVector.add(matcher.group().split(REGEX_BLANK));
                ++currentNodeNumber;
                nextNodeNumber += 2;
            } else if (lineToParse.matches(regexLeftChild + REGEX_COMMENT_LINE)) {
                pattern = Pattern.compile(regexLeftChild);
                matcher = pattern.matcher(lineToParse);
                matcher.find();
                nodeVector.add(matcher.group().split(REGEX_BLANK));
                ++currentNodeNumber;
                ++nextNodeNumber;
            } else if (lineToParse.matches(regexRightChild + REGEX_COMMENT_LINE)) {
                pattern = Pattern.compile(regexRightChild);
                matcher = pattern.matcher(lineToParse);
                matcher.find();
                nodeVector.add(matcher.group().split(REGEX_BLANK));
                ++currentNodeNumber;
                ++nextNodeNumber;
            } else if (lineToParse.matches(regexNoChild + REGEX_COMMENT_LINE)) {
                if (currentNodeNumber >= nextNodeNumber) {
                    throw new ParseException("Too many nodes have been specified",
                            lineNumber);
                } else {
                    pattern = Pattern.compile(regexNoChild);
                    matcher = pattern.matcher(lineToParse);
                    matcher.find();
                    nodeVector.add(matcher.group().split(REGEX_BLANK));
                    ++currentNodeNumber;
                }
            } else {
                if (!lineToParse.matches(REGEX_COMMENT)
                        && !lineToParse.matches(REGEX_EMPTY_LINE)) {
                    throw new ParseException("There is a syntax error on the " +
                            "line : " + lineToParse.replace(SPACE, " "), lineNumber);
                }
            }
            lineToParse = reader.readLine();
            ++lineNumber;
        }
        if (currentNodeNumber != nextNodeNumber) {
            throw new ParseException("There is not enough nodes", lineNumber);
        }
    }

    /**
     * Creates a string corresponding to the binary node. The format is defined
     * by <tt>TreeFile</tt>. There are the number of the node, the key and the
     * number of the children nodes. This method must be redefined in a subclass
     * if there is another new attribute concerning binary nodes to add in the
     * string representation.
     * 
     * @param node the node to transform into a string
     * @param currentNodeNumber the number of the current node
     * @param leftNodeNumber the number of the left child
     * @param rightNodeNumber the number of the right child
     * @return the string representation of the binary node
     */
    protected String transformNodeToString(IBinaryNode node, int currentNodeNumber,
            String leftNodeNumber, String rightNodeNumber) {
        return currentNodeNumber + SPACE + node.getKey() + SPACE
                + leftNodeNumber + SPACE + rightNodeNumber;
    }

    /**
     * Initializes the regular expressions for the current line of the file. The
     * current line is indicated by {@code currentNodeNumber}. This method must
     * be redefined in a subclass if there is another new regular expression
     * concerning binary nodes to initialize.
     * 
     * @param currentNodeNumber the number of the current node
     * @param nextNodeNumber the number of the next node
     */
    protected void initREGEX(int currentNodeNumber, int nextNodeNumber) {
        String lineBegin = currentNodeNumber + REGEX_BLANK + REGEX_KEY
                + REGEX_BLANK;
        
        regex2Child = lineBegin + nextNodeNumber + REGEX_BLANK
                + (nextNodeNumber + 1);
        regexLeftChild = lineBegin + nextNodeNumber + REGEX_BLANK + NIL_NODE;
        regexRightChild = lineBegin + NIL_NODE + REGEX_BLANK + nextNodeNumber;
        regexNoChild = lineBegin + NIL_NODE + REGEX_BLANK + NIL_NODE;
    }

    /**
     * Creates an empty binary tree that is to say a binary tree with a null
     * root node.
     * 
     * @return the created binary tree
     */
    protected abstract IBinaryTree createEmptyBinaryTree();

    /**
     * Creates a binary tree with the key given in parameter.
     * 
     * @param key the key of the root node
     * @return the created binary tree
     */
    protected abstract IBinaryTree createBinaryTree(int key);

    /**
     * Creates the left child of the binary node passed as parameter with the
     * node indicated by {@code childNodeNumber}.
     *
     * @param node the node from which the left child has to be assigned
     * @param childNodeNumber the number of the left child of the node
     */
    protected abstract void setLeftNode(IBinaryNode node, int childNodeNumber);

    /**
     * Creates the right child of the binary node passed as parameter with the
     * node indicated by {@code childNodeNumber}.
     * 
     * @param node the node from which the right child has to be assigned
     * @param childNodeNumber the number of the right child of the node
     */
    protected abstract void setRightNode(IBinaryNode node, int childNodeNumber);

    /**
     * Creates a binary tree from the loading of a file which name is
     * {@code fileName}.
     * 
     * @param fileName the name of the file where to load the binary tree
     * @return the loaded binary tree
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws UnknownDataStructureException
     */
    public static IBinaryTree load(String fileName) throws FileNotFoundException,
            IOException, ParseException, UnknownDataStructureException {
        TreeFile parser = TreeFile.parse(fileName);
        return parser.createBinaryTree();
    }

    /**
     * Saves the binary tree represented by {@code tree} into a file which name
     * is {@code fileName}.
     * 
     * @param tree the binary tree to save
     * @param fileName the name of the file where to save the binary tree
     * @throws IOException
     */
    public static void save(IBinaryTree tree,
            String fileName) throws IOException {
        FileWriter file = new FileWriter(fileName);
        int currentNodeNumber = 0;
        int maxNodeNumber = 0;
        String leftNodeNumber = null;
        String rightNodeNumber = null;
        List<IBinaryNode> heap = tree.buildHeapFromBinaryTree();

        file.write(tree.getType() + "\n");
        for (IBinaryNode node : heap) {
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
                file.write(fileParsers.get(
                        tree.getType().toString()).transformNodeToString(node,
                        currentNodeNumber, leftNodeNumber, rightNodeNumber) + "\n");
                ++currentNodeNumber;
            }
        }
        file.close();
    }
}
