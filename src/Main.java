public class Main {

    public static void main(String[] args) {

        //creating a new empty linked list

        LinkedList list1 = new LinkedList();

        // adding Nodes starting with the head
        //list1.head = new LinkedList.Node(1);
        //LinkedList.Node second = new LinkedList.Node(2);
        //LinkedList.Node third = new LinkedList.Node(3);
/*

         the three nodes have been allocated dynamically,
        each one has a reference as head, (the head or front of the list), second and third as shown below

          list1.head        second              third
             |                |                  |
             |                |                  |
         +----+------+     +----+------+     +----+------+
         | 1  | null |     | 2  | null |     |  3 | null |
         +----+------+     +----+------+     +----+------+
*/

        //list1.head.next = second; // setting the link from the head node to the second node as shown below
/*

           Now next of the first Node refers to the second.  So they
            both are linked.

         llist.head        second              third
            |                |                  |
            |                |                  |
        +----+------+     +----+------+     +----+------+
        | 1  |  o-------->| 2  | null |     |  3 | null |
        +----+------+     +----+------+     +----+------+
*/

        //second.next = third; // setting the link from the second node to the head of the third node as shown below
/*

           Now next of the second Node refers to third.  So all three
            nodes are linked.

         llist.head        second              third
            |                |                  |
            |                |                  |
        +----+------+     +----+------+     +----+------+
        | 1  |  o-------->| 2  |  o-------->|  3 | null |
        +----+------+     +----+------+     +----+------+
*/

         //list1.head.push(list1.head, 12);
        // creating a new list with the push method
        LinkedList list2 = new LinkedList();
/*
        list2.InsertFront(2);// third index
        list2.InsertFront(3);// second index
        list2.InsertFront(4);//first index
        list2.InsertFront(5);//head*/


 /*       int index = 3;
        System.out.println( "Element at index " + index +  " is " +  list2.GetNthPosition(index));
        // inserting a node 2 places after the head
        list2.insertAfter(list2.head.next.next, 8);
        System.out.println( "Element at index " + index +  " is " +  list2.GetNthPosition(index));
        list2.InsertAtEnd(9);
        int index2 = 8;
        System.out.println( "Element at the end is " +  list2.GetNthPosition(index2));

        list2.PrintList();
        list2.InsertAtIndex(3, 7);
        list2.PrintList();*/

        list2.InsertFront(1);
        list2.printList();
        list2.insertAfter(list2.head, 2);
        list2.printList();
        list2.InsertAtEnd(3);
        list2.printList();
        list2.InsertAfterIndex(1,9);
        list2.printList();

        CircularlLinkedList list3 = new CircularlLinkedList();
        list3.AddAtTail(1);
        list3.AddAtTail(2);
        list3.AddAtTail(3);
        list3.printList();


        /*creating a object of  the Binary Tree */

        BinaryTree tree1 = new BinaryTree();

        // creating the root of the node
        tree1.root = new BinaryTree.Node(1);
        System.out.println("initial tree with only one Node:");
        System.out.println(tree1.root.key);
        System.out.println();

        /*
        after creaiting our root our tree will appear as follows:

               1
            /     \
          null    null

         */

        //now we add in the left and right children of the nodes

        tree1.root.left = new BinaryTree.Node(2);
        tree1.root.right = new BinaryTree.Node(3);

        System.out.println("Displaying the left child of Node with key value 1");
        System.out.println(tree1.root.left.key);

        System.out.println();

        System.out.println("Displaying the right child of Node with key value 1");
        System.out.println(tree1.root.right.key);
        System.out.println();



        /*
        where 2 and three becomse the left and right child of the node respectively

                     1
                  /    \
                2        3
              /  \     /   \
            null null null null

         */
        // now to add a new node with value 4 as the left child of 2
        tree1.root.left.left = new BinaryTree.Node(4);

        /*
        Now that the Node with value 2 has a new child 4 on its left,
        the three will now look like this

                              1
                           /     \
                         2         3
                       /   \     /   \
                     4    null null null
                   /   \
                 null  null


         */

        System.out.println("displaying the left child of Node with value 2:");

        System.out.println(tree1.root.left.left.key);







    }
}