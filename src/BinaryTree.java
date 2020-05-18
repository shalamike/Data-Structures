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
        int key;
        Node left, right;

        public Node (int value){
            this.key = value;
            this.left = this.right = null;
        }
    }
}
