public class CircularlLinkedList {
     /*
     A circular linked list is like a linked list except for the fact that its end node points towarrds its head node
     as depicted in the diagram below

                   +----+------+     +----+------+     +----+------+
             ----->| 1  |  o-------->| 2  |  o-------->|  3 | null |-----
            |      +----+------+     +----+------+     +----+------+     |
            |                                                            |
             ------------------------------------------------------------

     */
     // declaring the current head and tail pointers as null where the tail is the last element of the list
     static Node head,tail;
     //creating the Node
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
    CircularlLinkedList DealWithUnderflow(){
        // returning a new linked list with a Node
        CircularlLinkedList list = new CircularlLinkedList();
        return list;
    }
    //Creating a method DealWithUnderflow which simply will result in the process being abandoned as the list by returning null
    CircularlLinkedList DealWithOverflow() {
        System.out.println("List is full");
        return null;
    }
     // adding a node at the end of the list
    public void AddAtTail(int data){
         //creatnig the node to be added
        Node newNode = new Node(data);
        if (head == null){
            //if the list is empty both the head and tail will point to the new node
            head = newNode;
            tail = newNode;
            newNode.next = head;
        }
        else{
            // the tail will point to a new node
            tail.next = newNode;
            // setting the tail node to the new node
            tail = newNode;
            // since its a circular linked list, the tail will point to the head
            tail.next = head;
        }
    }

    public void printList() {
        // settiing the pointer to the head
         Node pointer = head;
        if(head == null) {
            System.out.println("List is empty");
        }
        else {
            // setting the pointer to the next element
            System.out.println("Nodes of the circular linked list: ");
            //creating an initially empty string for the list that will have each element added
            String listToBePrinted = "";
            while(pointer!= tail){
                //adding the pointer data to the string after each loop untill it reaches the head again
                listToBePrinted += pointer.data + " ";
                pointer = pointer.next;
            }
            // adding the tail value to string
            listToBePrinted += pointer.data;
            // printing list
            System.out.println(listToBePrinted);
        }
    }
}

