package datastructures;

public class ThreadedBinarySearchTree {
    public static Node root;
    public static boolean rightThread;
    public static boolean leftThread;

    private class Node{
        int info;
        int leftTag;
        int rightTag;
        Node leftLink;
        Node rightLink;

        public Node(int data){
            this.info = data;
        }

    }
}
