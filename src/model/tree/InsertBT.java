package model.tree;

class InsertBT implements IBinaryTreeAlgorithm {

    private BinarySearchTree tree;

    private BinarySearchNode node;

    InsertBT(BinarySearchTree tree, BinarySearchNode node) {
        this.tree = tree;
        this.node = node;
    }

    public BinarySearchNode apply() {
        BinarySearchNode insertNode, y, z;
        insertNode = node;
        y = null;
        z = tree.getRoot();
        while (z != null) { 
            y = z;
            if (insertNode.getKey() < z.getKey()) { 
                z = z.getLeft();
            } else {
                z = z.getRight();
            }
        }
        insertNode.setFather(y);
        if (y == null) { 
            tree.setRoot(insertNode);
        } else {
            if (insertNode.getKey() < y.getKey()) { 
                y.setLeft(insertNode);
            } else {
                y.setRight(insertNode);
            }
        }
        return insertNode;
    }
}
