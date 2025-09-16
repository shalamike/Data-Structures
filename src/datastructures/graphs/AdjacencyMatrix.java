package datastructures.graphs;

import java.util.ArrayList;

public class AdjacencyMatrix {
    /*
    An adjacency matrix uses a 2 dimensional array to represent the connections between the nodes of the graph.
    The values stored within a graph will contain the edges that connect each node.
     */

    int[][] matrixArray;

    int numOfNodes;

    ArrayList<ArrayList<Integer>>matrixArrayList;

    int currentNumberOfNodes;

    boolean isDirected;

    public AdjacencyMatrix(int numOfNodes, boolean isDirected) {
        this.numOfNodes = numOfNodes;
        this.isDirected = isDirected;
        matrixArray = new int[numOfNodes][numOfNodes];
    }

    public AdjacencyMatrix(){
        matrixArrayList = new ArrayList<>();
        currentNumberOfNodes = 0;
    }

    // adding edge to our matrix

    public void matrixArrayAddEdge (int fromNode, int toNode){
        matrixArray[fromNode][toNode] = 1;
        if (!isDirected)
            matrixArray[toNode][fromNode] = 1;
    }

    public void matrixArrayAddEdge(int fromNode, int toNode, int weight){
        matrixArray[fromNode][toNode] = weight;
        if (!isDirected)
            matrixArray[toNode][fromNode] = weight;
    }

    public void matrixArrayListCreateNewNode(){
        matrixArrayList.add(new ArrayList<>());
        currentNumberOfNodes++;
        //adding default values to edges to ensure the matrix is consistent in structure with array

        for (ArrayList<Integer> integers : matrixArrayList) {
            if (integers.size() < currentNumberOfNodes)
                for( int currentRowPointer= integers.size(); integers.size()< currentNumberOfNodes; currentRowPointer++){
                    integers.add(0);
                }
        }
    }

    public void matrixArrayListCreatEdge(int to, int from, int weight){
        if (!isDirected)
            matrixArrayList.get(to).set(from, weight);
        else {
            matrixArrayList.get(to).set(from, weight);
            matrixArrayList.get(from).set(to, weight);
        }
    }


    public void printMatrix(){
        StringBuilder header = new StringBuilder();
        for (int currentNode =0; currentNode < numOfNodes; currentNode++){
            header.append("  ").append(currentNode);
        }
        System.out.println(header);
        for (int rowPointer =0; rowPointer < numOfNodes; rowPointer++){
            StringBuilder row = new StringBuilder();
            row.append(rowPointer);
            row.append("[");
            for (int edgePointer = 0; edgePointer < numOfNodes; edgePointer++){
                if (edgePointer < numOfNodes - 1){
                    row.append(matrixArray[rowPointer][edgePointer]);
                    row.append(", ");
                }
                else {
                    row.append(matrixArray[rowPointer][edgePointer]);
                }
            }
            row.append("]");
            System.out.println(row);
        }
    }

    public void printMatrix2(){
        StringBuilder header = new StringBuilder();
        for (int currentNode =0; currentNode < currentNumberOfNodes; currentNode++){
            header.append("  ").append(currentNode);
        }
        System.out.println(header);
        for (int rowPointer =0; rowPointer < currentNumberOfNodes; rowPointer++){
            StringBuilder row = new StringBuilder();
            row.append(rowPointer);
            row.append("[");
            for (int edgePointer = 0; edgePointer < currentNumberOfNodes; edgePointer++){
                if (edgePointer < currentNumberOfNodes - 1){
                    row.append(matrixArrayList.get(rowPointer).get(edgePointer));
                    row.append(", ");
                }
                else {
                    row.append(matrixArrayList.get(rowPointer).get(edgePointer));
                }
            }
            row.append("]");
            System.out.println(row);
        }
    }

    public boolean hasEdge(int from, int to){
        return matrixArray[from][to] >= 1;
    }

    public static void main (String args[]){

        AdjacencyMatrix graph = new AdjacencyMatrix(4, true);

        graph.matrixArrayAddEdge(0,1);
        graph.matrixArrayAddEdge(0,2);
        graph.matrixArrayAddEdge(1,2);
        graph.matrixArrayAddEdge(2,0);
        graph.matrixArrayAddEdge(2,3);
        graph.matrixArrayAddEdge(3,3);


        graph.printMatrix();
        System.out.println();
        graph = new AdjacencyMatrix(4, true);
        graph.matrixArrayAddEdge(0,1,1);
        graph.matrixArrayAddEdge(0,2,1);
        graph.matrixArrayAddEdge(1,2,2);
        graph.matrixArrayAddEdge(2,0,3);
        graph.matrixArrayAddEdge(2,3,2);
        graph.matrixArrayAddEdge(3,3,1);

        graph.printMatrix();

        AdjacencyMatrix graph2 = new AdjacencyMatrix();

        graph2.matrixArrayListCreateNewNode();
        graph2.matrixArrayListCreateNewNode();
        graph2.matrixArrayListCreateNewNode();
        graph2.matrixArrayListCreateNewNode();

        graph2.matrixArrayListCreatEdge(0,1, 1);
        graph2.matrixArrayListCreatEdge(0,2, 1);
        graph2.matrixArrayListCreatEdge(1,2, 2);
        graph2.matrixArrayListCreatEdge(2,0, 3);
        graph2.matrixArrayListCreatEdge(2,3, 2);
        graph2.matrixArrayListCreatEdge(3,3, 1);


        System.out.println();
        graph2.printMatrix2();


    }
}
