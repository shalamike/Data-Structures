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

        Node head;
        // constructor to define the new node
        // by default next and previous nodes are set to null
        Node(int data){
            info = data;
            next = prev;
            Node next;
        }

        //inserting in the front of the list
        public void pushFront(int data){
            //first we allocate data for the node and then we put data in the node
            Node newNode = new Node(data);

            //make the next node of the new node as head and the previous node as null
            newNode.next = head;
            newNode.prev = null;

            //change the previous of head as new node if the head is empty

            if(head != null){
                head.prev = newNode;
            }
        }

        public void pushAfterGivenNode(Node previousNode, int newData){
            //first we check if the previous node is empty
            if (previousNode == null){
                System.out.println("previous node cannot be null");
            }

            // now we allocate the psace for the node
            Node NewNode = new Node(newData);

            //make the next node as the previous node

            NewNode.next = previousNode.next;

            // make the next of the previous node as the new node
            previousNode.next = NewNode;

            //change the previous of newNodes next node

            if (NewNode.next != null ){
                NewNode.next.prev = NewNode;
            }
        }
    }



}
