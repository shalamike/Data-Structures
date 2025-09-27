package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class BinarySearchTree {
    // making the root of the binary tree
    Node root;
    // constructors
    BinarySearchTree(int value){
        this.root = new Node(value);
    }
    BinarySearchTree(){
        this.root = null;
    }
    /* class containing the left and right
    child of the current Node and the contents the Node stores */
    public static class Node {
        int info;
        Node leftlink, rightlink;

        public Node (int value){
            this.info = value;
            this.leftlink = this.rightlink = null;
        }
        public Node (){}
    }

    /*
    lets take a look at the binary search tree below

                5
               / \
              3   7
             / \
            2  4
     there are three main ways of traversing this binary tree;
     (a) Inorder (left,root,rigt) which outputs the following - 23457
     (b) Preorder (root, left, right) which outputs the following - 53247
     (C) PostOrder (left, right, root) which outputs the following - 24375
     */

    // creating a recursive function to traverse display nodes on the tree inorder
    public void printInorder(Node pointer){
        //checking to see if the node is currently empty
        if (pointer == null)
            return;
        //setting the pointer to the left child
        printInorder(pointer.leftlink);
        //visiting the root and printing the data of the key
        System.out.println(pointer.info + " ");
        // now setting the pointer to the right child
        printInorder(pointer.rightlink);
    }



    public void printPreorder(Node pointer){
        //checking to see if the node is empty
        if (pointer == null)
            return;
        //first we visit the root and print the value of the key
        System.out.println(pointer.info + " "); // visit node
        //then set the pointer to the left child
        printPreorder(pointer.leftlink);
        //then set the pointer to the right child
        printPreorder(pointer.rightlink);
    }

    public void printPostorder(Node pointer){
        //checking to see if the node is empty
        if (pointer == null)
            return;
        // first visit the node on the left child
        printPostorder(pointer.leftlink);
        // then visit the node on the right child
        printPostorder(pointer.rightlink);
        //visiting the root and printing the data of the key
        System.out.println(pointer.info + " ");
    }

    public void preOrderNonRecursive(Node pointer){
        pointer = root;
        Stack<Node> PointerStack = new Stack<Node>();
        if (pointer == null){
            System.out.println("tree is empty");
        }
        else{
            while (true){
                if (pointer != null){
                    System.out.println(pointer.info);// Visit P
                    PointerStack.push(pointer);
                    pointer = pointer.leftlink;
                }
                else{
                    if (PointerStack.empty()){
                        return;
                    }
                    else{
                        pointer = PointerStack.peek();
                        PointerStack.pop();
                        pointer = pointer.rightlink;
                    }
                }
            }
        }
    }




    /*
    Below is an algorythm to insert a new node into the binary tree. To do this, it traverses the tip of the tree and
    moves down depending on the value of the node to be added. If the value of the new node is less than the current
    node's stored value, it will move to the left child. if it's greater than or equal to our current nodes value, it
    will move to the right child.

    To demonstrate, lets construct a tree using this method below:

    starting at 8 we will call


    to add a new node with value 6,

     */
    public void insertNode(Node newnode, Node BinaryTreeRoot){
        if (BinaryTreeRoot == null){
            root = newnode;
        }
        else{
            Node pointer = root;
            Node prev = pointer;
            while (pointer != null){
                prev = pointer;
                if (newnode.info < pointer.info){
                    pointer = pointer.leftlink;
                }
                else {
                    pointer = pointer.rightlink;
                }
            }
            if (newnode.info < prev.info){
                prev.leftlink = newnode;
            }
            else {
                prev.rightlink = newnode;
            }
        }
    }

    public void insertNode2(Node newnode){
        if (root == null){
            root = newnode;
        }
        else{
            Node pointer = root;
            Node prev = pointer;
            while (pointer != null){
                prev = pointer;
                if (newnode.info < pointer.info){
                    pointer = pointer.leftlink;
                }
                else {
                    pointer = pointer.rightlink;
                }
            }
            if (newnode.info < prev.info){
                prev.leftlink = newnode;
            }
            else {
                prev.rightlink = newnode;
            }
        }
    }

    // Recursive insert method

    private void insertRecursive(int value){
        // Base case: if the tree has no nodes, we create a new node here.
        if (root == null) {
            this.root = new Node(value);
        }
        /*
        After the check, we initiate the recursive call to add the new node to its correct place.
        this will call regardless if we had to add a new node or not as our current tree will have a root value.
        A value we either added in on line 183 or was allready present.
         */
        else {
            insertRecursiveHelper(value, this.root);
        }
    }

    private void insertRecursiveHelper(int value, Node currentNode) {

        if (value < currentNode.info){
            if (currentNode.leftlink == null){
                currentNode.leftlink = new Node(value);
            }
            else {
                insertRecursiveHelper(value, currentNode.leftlink);
            }
        }
        else if (value > currentNode.info) {
            if (currentNode.rightlink == null){
                currentNode.rightlink = new Node(value);
            }
            else {
                insertRecursiveHelper(value, currentNode.rightlink);
            }
        }
    }


    //checking to see if there is a path to a given node in the binary tree
    // it will also populate an array pathArray with the given path in reverse (excluding the root)
    public static boolean findPath(Node pointer,ArrayList<String> pathArray, int findInt){
        //checking to see if there is a tree
        if (pointer == null){
            return false;
        }
        else{
            //checking to see if the root contains the value
            if (pointer.info == findInt){
                return true;
            }
            // recursively calling the findPath method to check if right link of its current node
            if (findPath(pointer.rightlink,pathArray, findInt)){
                pathArray.add("right" );
                return true;
            }
            // recursively calling the findPath method to check the left link of its current node
            if (findPath(pointer.leftlink, pathArray, findInt)){
                pathArray.add("left" );
                return true;
            }
        }
        return false;
    }

    public void printPath(ArrayList<String> pathArray, Node root){
        Node pointer = root;
        String path = "";
        pathArray.add("root");
        Collections.reverse(pathArray);
        if (pathArray.isEmpty()){
            path = "Path array not found";
        }
        else{
            for(String direction : pathArray){
                if(direction == "root"){
                    path += "root ["+ pointer.info + "]";
                }
                else if (direction == "right"){
                    path += "--> Right Link [" + pointer.rightlink.info + "]";
                    pointer = pointer.rightlink;
                }
                else{
                    path += "--> Left Link [" + pointer.leftlink.info + "]";
                    pointer = pointer.leftlink;
                }
            }
        }
        System.out.println(path);
    }

    public void DeleteNode(int dataValue, Node root){
        Node pointer = root;
        while (pointer != null && pointer.info != dataValue){
            Node pointerParent = pointer;
            if (pointer.info < dataValue){
                pointer = pointer.leftlink;
            }
            else {
                pointer = pointer.rightlink;
            }

        }
        if (pointer == null){
            System.out.println("Node doesnt exist");
        }
        else {
            //as we have a pointer pointing to the node to be deleted and its parent we need a
        }
    }



    public static void displayTree(BinarySearchTree tree) {
        if (tree.root == null) {
            System.out.println("(empty tree)");
            return;
        }

        // Do a BFS traversal, keeping track of levels
        Queue<Node> queue = new LinkedList<>();
        queue.add(tree.root);

        int height = getHeight(tree.root);
        int maxWidth = (int) Math.pow(2, height) - 1; // max nodes at bottom level

        int level = 0;
        while (!queue.isEmpty() && level < height) {
            int size = queue.size();
            int spacesBetween = (int) Math.pow(2, height - level) - 1;
            int spacesBefore = (spacesBetween / 2);

            // Print spacing before first node
            printSpaces(spacesBefore);

            for (int i = 0; i < size; i++) {
                BinarySearchTree.Node node = queue.poll();

                if (node != null) {
                    System.out.print(node.info);
                    queue.add(node.leftlink);
                    queue.add(node.rightlink);
                } else {
                    System.out.print(" ");
                    queue.add(null);
                    queue.add(null);
                }

                // Print spacing after each node
                printSpaces(spacesBetween);
            }
            System.out.println();
            level++;
        }
    }

    private static int getHeight(BinarySearchTree.Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.leftlink), getHeight(node.rightlink));
    }

    private static void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("  "); // adjust spacing for readability
        }
    }

    public static void main(String[] args){

        /*creating a object of  the Binary Search Tree */

        BinarySearchTree tree1 = new BinarySearchTree();

        // creating the root of the node
        tree1.insertNode2(new Node(5));
        System.out.println("initial tree with only one Node: " + tree1.root.info);
        System.out.println();

        /*
        after creaiting our root our tree will appear as follows:

               5
              / \
            /     \
          null    null

         */

        //now we add in the left and right children of the nodes
        tree1.insertNode2(new Node(3));
        tree1.insertNode2(new Node(7));
        System.out.println("displaying structure of tree after adding in new nodes");

        /*
        The reason this tree is a Binary Search tree is because when we inserted our two nodes, 3 and 7.
        The insertion process added the smaller node to the left child and the larger node to the right.
         */

        displayTree(tree1);

//
        System.out.println("left child of Node with key value: " + tree1.root.leftlink.info );
//
        System.out.println();
//
        System.out.println("right child of Node with key value: " + tree1.root.rightlink.info );
        System.out.println();


        /*

        After adding our two new nodes, the tree will look like this

               5
             /   \
            /     \
           3       7

           where 3 is the left child of 5 because it is smaller than 5 while 7 is the right child of 5 because it is larger

         */




        // now to add a new node with value 2

        tree1.insertNode2(new Node(2));

        displayTree(tree1);




         /*

         As 2 is less than 5 it will go to the left child of the tree wich is occupied by node of value 3. As 2 is still
         less than 3, it will then move to the left child of 3 as shown below

                       5
                     /   \
                    /     \
                   3       7
                  /
                 /
                2


                lets add Nodes 4 and 6 to see how those would be added


         */


        tree1.insertNode2(new Node(4));
        tree1.insertNode2(new Node(6));

        displayTree(tree1);

        /*


                       5
                    /    \
                   /      \
                 3          7
                / \        /
               /   \      /
              2     4    6

         */


        /*
                 we can traverse this binary tree using three different recursive methods:
         1. Inorder
            i. we visit the current nodes left subtree untill there are no subtrees left i.e. its null
            ii. then we visit the root, i.e. access the data of the node we are on
            iii. after visiting the root, we move onto its right child
         2. Preorder
            i. first we visit the root of the tree i.e. access the data of the node we are on
            ii. after visiting its root, we move to the left child
            iii. if there is no left child, we move to the right child
         3. Postorder
            i. we visit the current nodes left subtree untill there are no subtrees left i.e. its null
            ii. then we move to the right subtree of our current node and only keep moving to the right
                if there are no more left subtrees on the current root
            iii. then we visit the root. i.e. access the data of the node we are on

        by running this program with the three different recursive functions we can see the different out
         */

        System.out.println("displaying the Inorder traversal of the tree");
        tree1.printInorder(tree1.root);

        System.out.println("displaying Preorder traversal of the tree");
        tree1.printPreorder(tree1.root);


        System.out.println("displaying PostOrder traversal");
        tree1.printPostorder(tree1.root);



//        tree1.insertNode(new BinaryTree.Node(12), tree1.root);




//
//         System.out.println("displaying Postorder traversal of the tree");
//         tree1.printPostorder(tree1.root);

        /**********************************************************************************************************/
        System.out.println();
         System.out.println("Creating a new tree with different values");
         BinarySearchTree tree2 = new BinarySearchTree();
//         tree2.insertNode(new BinarySearchTree.Node(10), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(6), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(4), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(7), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(3), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(9), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(1), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(18), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(13), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(11), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(17), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(15), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(2), tree2.root);
//         tree2.insertNode(new BinarySearchTree.Node(19), tree2.root);

        tree2.insertRecursive(  10);
        tree2.insertRecursive(6);
        tree2.insertRecursive(4);
        tree2.insertRecursive(7);
        tree2.insertRecursive(3);
        tree2.insertRecursive(9);
        tree2.insertRecursive(1);
        tree2.insertRecursive(18);
        tree2.insertRecursive(13);
        tree2.insertRecursive(11);
        tree2.insertRecursive(17);
        tree2.insertRecursive(15);
        tree2.insertRecursive(2);
        tree2.insertRecursive(19);





        //tree2.printPostorder(tree2.root);
        ArrayList<String> pathArray1 = new ArrayList<>();
        ArrayList<String> pathArray2 = new ArrayList<>();
        ArrayList<String> pathArray3 = new ArrayList<>();
        tree2.findPath(tree2.root,pathArray1, 9);
        tree2.findPath(tree2.root,pathArray2, 2);
        tree2.findPath(tree2.root,pathArray3, 17);
        tree2.printPath(pathArray1,tree2.root);
        tree2.printPath(pathArray2,tree2.root);
        tree2.printPath(pathArray3,tree2.root);

        System.out.println();

        displayTree(tree2);

    }

}
