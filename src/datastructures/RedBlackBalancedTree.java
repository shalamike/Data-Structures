package datastructures;

public class RedBlackBalancedTree {

    /*
    Key properties of a Red-Black tree:

    1. A node is either RED or BLACK.
    2. the root and leaves (NIL/Null nodes) are BLACK
    3. if a node is RED, then both its children are BLACK
    4. ALl paths from a Node to its  (NIL/Null nodes) contain the same number of black nodes
                                        / \
                                         |
    this is the most important rule as it is the main factor that determines the height of the tree

    Bonus rule. BY DEFAULT, a node will be RED, unless it the Node adheres to rules 2 and 3.
     */

    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;


    //by default the root node of a red black tree is black
    public RedBlackBalancedTree(int value){
        this.root = new Node(value, BLACK);
    }

    public RedBlackBalancedTree(){
        this.root = null;    }

    // Node definition
    public static class Node {
        int info;
        Node leftlink, rightlink, parent;
        boolean color; // true = RED, false = BLACK


        public Node(int value, boolean color) {
            this.info = value;
            this.color = color;
            this.leftlink = this.rightlink = this.parent = null;
        }
    }



    public void leftRotate (Node pivot){

        //Step 1: first we check if our Pivot node has a right link to pivot around
        if (pivot.rightlink == null)
            return;

        //Step 2: Otherwise we then assign the identity of our upcoming new parent node to that child
        Node newParent = pivot.rightlink;

        //Step 3: we then make the pivots right link its roots right link
        pivot.rightlink = newParent.rightlink;
        if (newParent.leftlink != null){ //Step 3.1: we check if the left subtree is not empty
            newParent.leftlink.parent = pivot; //if it isn't, then the pivot becomes the parent of that subtree's root
        }

        //Step 4: we Link the newRoots to the pivot's parent node(taking the pivots old position)
        newParent.parent = pivot.parent;
        if (pivot.parent == null){ //Step 4.1: checking to see if the pivot node was the root
            root = newParent; // if it was, then our newParent node becomes the new root of the Binary tree
        }
        else if (pivot == pivot.parent.leftlink){//step 4.2 : Otherwise if pivot was a left child
            pivot.parent.leftlink = newParent; //if it was, then the new parent becomes that parents left child
        }
        else { //step 4.3 : Otherwise, the pivot would have been a right child,
            pivot.parent.rightlink = newParent;//then, we assign the new parent to be that left child
        }

        //Step 5: Now that the new parent is in its correct place, we move the pivot as the left child of the new parent
        newParent.leftlink = pivot;
        pivot.parent = newParent;
    }


    public void rotateRight(Node pivot){

        //Step 1: first we check to see if our pivot has a leftLink to pivot around
        if (pivot.leftlink == null) return;

        //Step 2: Otherwise we then assign the identity of our upcoming new parent node to that child
        Node newParent = pivot.leftlink;

        //Step 3: we then move the newRoot's right subtree into the pivots left subtree
        pivot.leftlink = newParent.rightlink;
        if (newParent.rightlink != null){ //Step 3.1: we check if the right subtree is not empty
            newParent.rightlink.parent = pivot; //if it isn't, then the pivot becomes the parent of that subtree's root
        }

        //Step 4: we Link the newRoots to the pivot's parent node(taking the pivots old position
        newParent.parent = pivot.parent;
        if (pivot.parent == null){ //Step 4.1: checking to see if the pivot node was the root
            root = newParent; // if it was, then our newParent node becomes the new root of the Binary tree
        }
        else if (pivot == pivot.parent.rightlink){//step 4.2 : Otherwise if pivot was a left child
            pivot.parent.rightlink = newParent;//if it was, then the new parent becomes that parents left child
        }
        else {//step 4.3 : Otherwise, the pivot would have been a right child,
            pivot.parent.leftlink = newParent;//then, we assign the new parent to be that left child
        }

        //Step 5: Now that the new parent is in its correct place, we move the pivot as the left child of the new parent
    }

    //Note the similarities between both the left rotate operation and the right rotate operation


    public void insert(int value) {
        // Step 1: Create a new node, default color is RED.
        Node newNode = new Node(value, RED);

        // Step 2: Perform a standard BST insert.
        Node parentNode = null;
        Node currentNode = root;

        // Step 2.1: Traverse the tree to find where to insert the new node.
        while (currentNode != null) {
            parentNode = currentNode;
            if (value < currentNode.info) {
                currentNode = currentNode.leftlink;
            } else if (value > currentNode.info) {
                currentNode = currentNode.rightlink;
            } else {
                // Duplicate values not allowed — stop insertion.
                return;
            }
        }

        // Step 3: Set the parent of the new node to the last non-null node found.
        newNode.parent = parentNode;

        // Step 4: Attach newNode to the appropriate parent side.
        if (parentNode == null) {
            // Tree was empty → new node becomes root.
            root = newNode;
        } else if (newNode.info < parentNode.info) {
            parentNode.leftlink = newNode;
        } else {
            parentNode.rightlink = newNode;
        }

        // Step 5: Fix any Red-Black property violations that may have occurred.
        fixInsertion(newNode);
    }

    private void fixInsertion(Node insertedNode) {
        // Continue fixing while the parent is RED.
        while (insertedNode != root && insertedNode.parent.color == RED) {

            // Case A: Parent is the left child of the grandparent.
            if (insertedNode.parent == insertedNode.parent.parent.leftlink) {
                Node uncleNode = insertedNode.parent.parent.rightlink;

                // Case 1: Uncle is RED → Recolor parent, uncle, and grandparent.
                if (uncleNode != null && uncleNode.color == RED) {
                    insertedNode.parent.color = BLACK;
                    uncleNode.color = BLACK;
                    insertedNode.parent.parent.color = RED;
                    insertedNode = insertedNode.parent.parent; // Move up the tree.
                } else {
                    // Case 2: Uncle is BLACK, and new node is a right child → rotate left.
                    if (insertedNode == insertedNode.parent.rightlink) {
                        insertedNode = insertedNode.parent;
                        leftRotate(insertedNode);
                    }
                    // Case 3: Uncle is BLACK, and new node is a left child → rotate right.
                    insertedNode.parent.color = BLACK;
                    insertedNode.parent.parent.color = RED;
                    rotateRight(insertedNode.parent.parent);
                }
            }

            // Case B: Parent is the right child of the grandparent (mirror of Case A).
            else {
                Node uncleNode = insertedNode.parent.parent.leftlink;

                if (uncleNode != null && uncleNode.color == RED) {
                    insertedNode.parent.color = BLACK;
                    uncleNode.color = BLACK;
                    insertedNode.parent.parent.color = RED;
                    insertedNode = insertedNode.parent.parent;
                } else {
                    if (insertedNode == insertedNode.parent.leftlink) {
                        insertedNode = insertedNode.parent;
                        rotateRight(insertedNode);
                    }
                    insertedNode.parent.color = BLACK;
                    insertedNode.parent.parent.color = RED;
                    leftRotate(insertedNode.parent.parent);
                }
            }
        }

        // Step 6: Ensure the root is always BLACK.
        root.color = BLACK;
    }


    public void delete(int value) {
        Node nodeToDelete = root;

        // Step 1: Search for the node with the given value.
        while (nodeToDelete != null && nodeToDelete.info != value) {
            if (value < nodeToDelete.info) {
                nodeToDelete = nodeToDelete.leftlink;
            } else {
                nodeToDelete = nodeToDelete.rightlink;
            }
        }

        // If value not found → do nothing.
        if (nodeToDelete == null) return;

        Node replacementNode;
        Node childNode;
        boolean originalColor = nodeToDelete.color;

        // Step 2: Case 1 - Node has no left child.
        if (nodeToDelete.leftlink == null) {
            childNode = nodeToDelete.rightlink;
            transplant(nodeToDelete, nodeToDelete.rightlink);
        }
        // Step 3: Case 2 - Node has no right child.
        else if (nodeToDelete.rightlink == null) {
            childNode = nodeToDelete.leftlink;
            transplant(nodeToDelete, nodeToDelete.leftlink);
        }
        // Step 4: Case 3 - Node has two children.
        else {
            replacementNode = minimum(nodeToDelete.rightlink); // Smallest in right subtree.
            originalColor = replacementNode.color;
            childNode = replacementNode.rightlink;

            if (replacementNode.parent == nodeToDelete) {
                if (childNode != null) {
                    childNode.parent = replacementNode;
                }
            } else {
                transplant(replacementNode, replacementNode.rightlink);
                replacementNode.rightlink = nodeToDelete.rightlink;
                replacementNode.rightlink.parent = replacementNode;
            }

            transplant(nodeToDelete, replacementNode);
            replacementNode.leftlink = nodeToDelete.leftlink;
            replacementNode.leftlink.parent = replacementNode;
            replacementNode.color = nodeToDelete.color;
        }

        // Step 5: If we removed a BLACK node, fix balance.
        if (originalColor == BLACK) {
            fixDeletion(childNode);
        }
    }

    private void transplant(Node oldNode, Node newNode) {
        if (oldNode.parent == null) {
            root = newNode;
        } else if (oldNode == oldNode.parent.leftlink) {
            oldNode.parent.leftlink = newNode;
        } else {
            oldNode.parent.rightlink = newNode;
        }
        if (newNode != null) {
            newNode.parent = oldNode.parent;
        }
    }

    private Node minimum(Node node) {
        while (node.leftlink != null) {
            node = node.leftlink;
        }
        return node;
    }

    private void fixDeletion(Node node) {
        while (node != root && (node == null || node.color == BLACK)) {
            if (node == node.parent.leftlink) {
                Node sibling = node.parent.rightlink;

                // Case 1: Sibling is RED → rotate left and recolor.
                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    leftRotate(node.parent);
                    sibling = node.parent.rightlink;
                }

                // Case 2: Both of sibling's children are BLACK.
                if ((sibling.leftlink == null || sibling.leftlink.color == BLACK) &&
                        (sibling.rightlink == null || sibling.rightlink.color == BLACK)) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    // Case 3: Sibling’s right child is BLACK → rotate right.
                    if (sibling.rightlink == null || sibling.rightlink.color == BLACK) {
                        if (sibling.leftlink != null) sibling.leftlink.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = node.parent.rightlink;
                    }

                    // Case 4: Sibling’s right child is RED → rotate left.
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    if (sibling.rightlink != null) sibling.rightlink.color = BLACK;
                    leftRotate(node.parent);
                    node = root;
                }
            } else {
                // Mirror case: node is a right child.
                Node sibling = node.parent.leftlink;
                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    sibling = node.parent.leftlink;
                }

                if ((sibling.leftlink == null || sibling.leftlink.color == BLACK) &&
                        (sibling.rightlink == null || sibling.rightlink.color == BLACK)) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.leftlink == null || sibling.leftlink.color == BLACK) {
                        if (sibling.rightlink != null) sibling.rightlink.color = BLACK;
                        sibling.color = RED;
                        leftRotate(sibling);
                        sibling = node.parent.leftlink;
                    }
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    if (sibling.leftlink != null) sibling.leftlink.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        if (node != null)
            node.color = BLACK;
    }


    // Find a node by its value (used in the main method for rotations)
    public Node findNode(int value) {
        Node current = root;
        while (current != null && current.info != value) {
            if (value < current.info) {
                current = current.leftlink;
            } else {
                current = current.rightlink;
            }
        }
        return current;
    }

    // Pretty-print the tree structure in ASCII format
    public void printTree() {
        printTreeHelper(root, "", true);
        System.out.println();
    }

    private void printTreeHelper(Node node, String indent, boolean isTail) {
        if (node == null) return;

        System.out.println(indent + (isTail ? "└── " : "├── ")
                + node.info + (node.color == RED ? "(R)" : "(B)"));

        if (node.leftlink != null || node.rightlink != null) {
            printTreeHelper(node.leftlink, indent + (isTail ? "    " : "│   "), false);
            printTreeHelper(node.rightlink, indent + (isTail ? "    " : "│   "), true);
        }
    }


    public static void main(String[] args) {
        RedBlackBalancedTree tree = new RedBlackBalancedTree();

        // Insert values to form a base tree
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(18);
        /*
        our new tree looks like this

               10(B)
              /     \
           5(R)     15(R)
          /   \     /   \
       3(B)  7(B) 12(B) 18(B)

         */

        System.out.println("\nInitial Red-Black Tree:");
        tree.printTree();

        // Demonstrate Left Rotation around node 10
        /*
        Before

            10(B)
          /     \
       5(R)     15(R)
               /   \
           12(B)   18(B)

         */
        System.out.println("\nPerforming LEFT ROTATE around 10:");
        tree.leftRotate(tree.findNode(10));
        tree.printTree();

        /*
         After
                     15(R)
                    /     \
                 10(B)     18(B)
                /    \
             5(R)    12(B)
            /
          3(B)

         */
        /* Demonstrate Right Rotation around node 15
        before:
                     15(R)
                    /     \
                 10(B)     18(B)
                /    \
             5(R)    12(B)
            /
          3(B)

         */
        System.out.println("\nPerforming RIGHT ROTATE around 15:");
        tree.rotateRight(tree.findNode(15));
        tree.printTree();

        /*
                     10(B)
                    /     \
                 5(R)     15(R)
                /         /   \
             3(B)       12(B) 18(B)

         */


        System.out.println("\nInserting new node 8:");
        tree.insert(8);
        tree.printTree();

        /* treeafter insertion
                     10(B)
                    /     \
                 5(R)     15(R)
                /         /   \
             3(B)       12(B) 18(B)

         */

        // Demonstrate Deletion
        System.out.println("\nDeleting node 12:");
        tree.delete(12);
        tree.printTree();

        /*
        tree after deletion
                 10(B)
                /     \
             5(B)     15(R)
            /  \          \
         3(R)  8(R)       18(B)


         */


        System.out.println("\nFINAL TREE STATE:");
        tree.printTree();

    }

}
