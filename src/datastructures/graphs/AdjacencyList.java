package datastructures.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AdjacencyList {
    HashMap<Integer, LinkedList<Integer>> adjacencyList ;



    public AdjacencyList(){
        adjacencyList = new HashMap<>();
    }

    public void addEdge(int from, int to){
        adjacencyList.get(from).add(to);
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


    public static void main(String args[]){

        AdjacencyList graph = new AdjacencyList();
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addNode();


        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(3,3);

        graph.displayAdjacencies();

    }


}
