public class DoublyLinkedList {
    /*
                    llist.head        second              third
                         |                |                  |
                         |                |                  |
                    +----+------+-----+     +----+------+-----+     +----+------+------+
                    |    |      |o--------> |    |      | o-------->|    |      |      |
                    |    |    1 |     |     |    |      |     |     |    | null |      |
                    |    |      |     |<--------o|      |     |<--------o|      |      |
                    +----+------+-----+     +----+------+-----+          +------+------+
     */

    static class Node{
        int info;
        Node next;
        Node prev;
        // constructor to define the new node
        // by default next and previous nodes are set to null
        Node(int d){
            info = d;
            next = prev = null;
        }
    }
}
