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

        //Creating a methd DealWithUnderflow which simply will result in the process being abandoned as the list would be full
        LinkedList DealWithOverflow() {
            return null;
        }

    }
}
