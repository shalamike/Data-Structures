package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

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
        public Node (){}
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




    //if given number is less than the current node, it moves to the left, otherwise it moves to the right node
    // this will happen untill it reaches a null pointer
    public void insertNode(Node newnode, Node Root){
        if (Root == null){
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
    

}
