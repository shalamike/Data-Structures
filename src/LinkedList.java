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

    // creating a method that finds the nth position of a node in a list
    // takes the index of the node as an argument and returns the data at the given index
    public int GetNthPosition(int index){
        //setting the pointer at the head of the node
        Node current_node_pointer = head;
        // setting a counter for a while loop to traverse through the list untill we get to the Nth position
        int counter = 0; // index of Node we are currently looking at
        if (current_node_pointer == null) {
            DealWithUnderflow();
            //this will simply return a new list with one node and return it by its index
            return LinkedList.head.data;
        }
        else{
            // while the current node is not empty
            while (current_node_pointer != null)
            {
                //checking to see if the counter matches the searched index
                if (counter == index)
                    return current_node_pointer.data;
                else{
                    //incrememnting the counter till counter reaches index
                    counter++;
                    //moving the current node to the next node in the list
                    current_node_pointer = current_node_pointer.next;}
            }
        }

        /* if we get to this line, the caller was asking
        for a non-existent element so we assert fail, i.e the index would have been out of range */
        assert(false);
        return 0;
    }

    /* given a reference to the head of a list and an int,
    this new "push" method will insert a new node to the front of the list*/
    public void push(int new_data)
    {

        /* 1. alloc the Node and put data*/
        Node new_Node = new Node(new_data);

        /* 2. Make next of new Node as head */
        new_Node.next = head;

        /* 3. Move the head to point to new Node */
        head = new_Node;
    }
    /* given a reference to the head of a list and an int,
        this new "push" method will insert a new node to the front of the list*/
    //overridden method push with two inputs including head
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
          new head/Node       previous head/Node     second node + 1......  Nth node + 1
             |                |                       |                      |
             |                |                       |                      |
         +----+------+     +----+------+            +----+------+         +------+------+
         | 1  |  o-------->| 2  |  o--------------->|  3 |   o-------->   |  n+1 | null | -------> null
         +----+------+     +----+------+            +----+------+  .....  +------+------+
         */

    /*
    the following function will insert a new node after a given node
     */
    public void insertAfter(Node prev_node, int new_data){

        //checking if the given list is null i.e. dealWithUnderflow
        if (prev_node == null){
            DealWithUnderflow();
        }
        else {
            // allocating the node and adding the new data
            Node new_node = new Node(new_data);
            // making the next node of the the New node set as the previous node
            new_node.next = prev_node.next;
            // adding the node into the specified previous node
            prev_node.next = new_node;
        }
    }
    //this insert after method will add a new node as demonstrated below
        /*
                                            new node
                                               |
                                               |
                                             +----+------+
                                             |  n |   o  |
                                             +----+---|--+
                                               / \    |
                                                |     |
          new head/Node         node n          |     |        node + 1       Nth node + 1
             |                   |              |     |        |                      |
             |                   |              |     |        |                      |
         +----+------+  ...   +----+------+     |     |     +----+------+  ....   +------+------+
         | 1  |  o-------->   | n   |  o---------     |---->| n+2|   o-------->  |  n+1  | null | -------> null
         +----+------+  ...   +----+------+                 +----+------+  .....  +------+------+
         */

        /*
            the following funcion will add a new node at the end of the linked list
        */
        
}



