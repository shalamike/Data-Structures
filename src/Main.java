public class Main {

    public static void main(String[] args) {

        //creating a new empty linked list

        LinkedList list1 = new LinkedList();

        // adding Nodes starting with the head
        list1.head = new LinkedList.Node(1);
        LinkedList.Node second = new LinkedList.Node(2);
        LinkedList.Node third = new LinkedList.Node(3);

        /* the three nodes have been allocated dynamically,
        each one has a reference as head, (the head or front of the list), second and third as shown below

          list1.head        second              third
             |                |                  |
             |                |                  |
         +----+------+     +----+------+     +----+------+
         | 1  | null |     | 2  | null |     |  3 | null |
         +----+------+     +----+------+     +----+------+ */

        list1.head.next = second; // setting the link from the head node to the second node as shown below

         /*  Now next of the first Node refers to the second.  So they
            both are linked.

         llist.head        second              third
            |                |                  |
            |                |                  |
        +----+------+     +----+------+     +----+------+
        | 1  |  o-------->| 2  | null |     |  3 | null |
        +----+------+     +----+------+     +----+------+ */

        second.next = third; // setting the link from the second node to the head of the third node as shown below

         /*  Now next of the second Node refers to third.  So all three
            nodes are linked.

         llist.head        second              third
            |                |                  |
            |                |                  |
        +----+------+     +----+------+     +----+------+
        | 1  |  o-------->| 2  |  o-------->|  3 | null |
        +----+------+     +----+------+     +----+------+ */

         //list1.head.push(list1.head, 12);

        // creating a new list with the push method
        LinkedList list2 = new LinkedList();

        list2.push(2);// third index
        list2.push(3);// second index
        list2.push(4);//first index
        list2.push(5);//head


        System.out.println(list2.head.data);

        int index = 3;
        System.out.println( "Element at index " + index +  " is " +  list2.GetNthPosition(index));


    }
}