import java.lang.invoke.ConstantCallSite;

public class BinaryTree {
    // making the root of the binary tree
    Node root;

    // constructors
    BinaryTree(int value){
        this.root = new Node(value);
    }

    BinaryTree(){
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
    }

    /*
    lets take a look at the binary tree below

                1
               / \
              2   3
             / \
           4    5
     there are three main ways of traversing this binary tree;
     (a) Inorder (left,root,rigt) which outputs the following - 42513
     (b) Preorder (root, left, right) which outputs the following - 12343
     (C) PostOrder (left, right, root) which outputs the following - 45231
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
        System.out.println(pointer.info + " ");
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

}
