package datastructures.graphs;

import java.lang.reflect.Array;
import java.util.*;

public class AdjacencyList {
    HashMap<Integer, LinkedList<Integer>> adjacencyList ;

    boolean isDirected;



    public AdjacencyList(){
        this.adjacencyList = new HashMap<>();
        this.isDirected = false;
    }

    public AdjacencyList(boolean isDirected){
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(int from, int to){
        adjacencyList.get(from).add(to);
        if (isDirected)
            adjacencyList.get(to).add(from);
    }

    public void addNode(){
        if (!adjacencyList.containsKey(adjacencyList.size())){
            int currentKey = adjacencyList.size();
            adjacencyList.put(currentKey, new LinkedList<>());
        }
    }

    public void displayAdjacencies(){
        int numberOfNodes = adjacencyList.size();
        String header = "Node : Connections";
        System.out.println(header);
        for(int rowPointer = 0; rowPointer < numberOfNodes; rowPointer++){
            StringBuilder row = new StringBuilder();
            row.append(rowPointer).append("    : ");
            LinkedList currentRow = adjacencyList.get(rowPointer);
            for (int currentEdge = 0; currentEdge < currentRow.size(); currentEdge++){
                row.append("[").append(currentRow.get(currentEdge)).append("]");
            }
            System.out.println(row);
        }
    }

    public ArrayList<Integer> dfsIterative(int startNode){

        // total number of nodes in the graph represented by an Adjacency List
        int numberOfNodes = adjacencyList.size();

        //A list of booleans where the index represents the node and the boolean value depicts whether the node has been visited
        boolean[] visitedNodes = new boolean[numberOfNodes];

        //An array list to store the order of visited nodes
        ArrayList<Integer> visitedNodeOrder = new ArrayList<>();

        // A stack to keep track of the nodes that need to be visited
        Stack<Integer> toBeVisitedStack = new Stack<>();

        //starting off the stack with our starting node
        toBeVisitedStack.push(startNode);

        //DFS traversal begins and will only end when there are no more items in our stack
        while(!toBeVisitedStack.isEmpty()){

            //removing our current value from our stack (or the startnode assuming its only just started the dfs)
            int currentNode = toBeVisitedStack.pop();

            // checking to see if our current node has not been visited yet
            if (!visitedNodes[currentNode]){
                //as we are visiting it, we change its visited boolean to true
                visitedNodes[currentNode] = true;
                System.out.println("visiting Node: " + currentNode);
                // adding our visited node to our visitedNodeOrder array
                visitedNodeOrder.add(currentNode);
                //getting the list of all our current visited nodes neighbours.
                LinkedList<Integer> neighbouringNodes = adjacencyList.get(currentNode);

                //to add the smaller nodes first, we will add the largest numbers first
                for (int i = neighbouringNodes.size()-1 ; i >= 0; i--){
                    //getting the current neighbour from our current nodes list of neighbours
                    int currentNeighbour = neighbouringNodes.get(i);

                    //if the current neigbouring node has not been visited, we add that neigbour to our to be visited stack
                    if (!visitedNodes[currentNeighbour]){
                        toBeVisitedStack.push(currentNeighbour);
                    }
                }
            }
        }

        //finally we return our list of nodes in its visited order.
        return visitedNodeOrder;
    }

    public ArrayList<Integer> bfsIterative(int startNode){
        ArrayList<Integer> visitedNodeOrder = new ArrayList<>();

        int numOfNodes = adjacencyList.size();

        Queue<Integer> toBeVisitedQueue = new LinkedList<>();

        toBeVisitedQueue.add(startNode);

        while (!toBeVisitedQueue.isEmpty()){
            int currentNode = toBeVisitedQueue.remove();
            visitedNodeOrder.add(currentNode);
            for (int neighbouringNode : adjacencyList.get(currentNode)){
                if (!toBeVisitedQueue.contains(neighbouringNode)){
                    toBeVisitedQueue.add(neighbouringNode);
                }
            }
        }

        return visitedNodeOrder;
    }

    public static void main(String args[]){

//        AdjacencyList graph = new AdjacencyList();
//        graph.addNode();
//        graph.addNode();
//        graph.addNode();
//        graph.addNode();
//
//
//        graph.addEdge(0,1);
//        graph.addEdge(0,2);
//        graph.addEdge(1,2);
//        graph.addEdge(2,0);
//        graph.addEdge(2,3);
//        graph.addEdge(3,3);
//
//        graph.displayAdjacencies();
//
//
//        StringBuilder nodeOrder = new StringBuilder();
//        for (int node : graph.dfsIterative(2)){
//            nodeOrder.append(node).append(" ");
//        }
//
//        System.out.println(nodeOrder);

        AdjacencyList graph2 = new AdjacencyList();

        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();

        graph2.addEdge(0,1);
        graph2.addEdge(0,2);
        graph2.addEdge(1,2);
        graph2.addEdge(1,3);
        graph2.addEdge(1,4);
        graph2.addEdge(3,5);
        graph2.addEdge(5,6);
        graph2.addEdge(5,7);
        graph2.addEdge(5,8);
        graph2.addEdge(7,8);
        graph2.addEdge(8,9);

        StringBuilder nodeOrderDfs = new StringBuilder();
        StringBuilder nodeOrderBfs = new StringBuilder();

        for (int node : graph2.dfsIterative(0)){
            nodeOrderDfs.append(node).append(" ");
        };

        System.out.println("dfs order traversal: " + nodeOrderDfs);

        for (int node : graph2.bfsIterative(0)){
            nodeOrderBfs.append(node).append(" ");
        }

        System.out.println("bfs order traversal: " + nodeOrderBfs);

    }



}
