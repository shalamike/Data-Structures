//this class creates a singly linked list
public class LinkedList {
    static Node head; //the head of the linked list
    static class Node{
        // creating the Node of the linked list
        int data;
        Node next;

        // the constructor creates a new node
        // by default next is initiallised as null in the constructor
        Node (int d){
            data = d;
            next = null;
        }

        // creating a method DealWithUnderflow which simply creates a new list with a new node at index 1
        LinkedList DealWithUnderflow(){
            // returning a new linked list with a Node
            LinkedList list = new LinkedList();
            list.head = new LinkedList.Node(1);
            return list;
        }

        //Creating a method DealWithUnderflow which simply will result in the process being abandoned as the list by returning null
        LinkedList DealWithOverflow() {
            System.out.println("List is full");
            return null;
        }

        /* given a reference to the head of a list and an int,
        this new "push" method will insert a new node to the front of the list*/
        public void push(Node head, int new_data)
        {

            /* 1. alloc the Node and put data*/
            Node new_Node = new Node(new_data);

            /* 2. Make next of new Node as head */
            new_Node.next = head;

            /* 3. Move the head to point to new Node */
            head = new_Node;
        }

        //this push method will add a new node as demonstrated below
        /*

          new head/Node       previous head/Node     second node  ......    Nth node + 1
             |                |                       |                      |
             |                |                       |                      |
         +----+------+     +----+------+            +----+------+         +------+------+
         | 1  | null |     | 2  | null |            |  3 | null |         |  n+1 | null |
         +----+------+     +----+------+            +----+------+  .....  +------+------+

         */


    }
}
