/**
 * Graph.java for weighted graph
 * Xiao Yang
 * Georgia Institute of Technology
 *
 * Use 2D list to store pairs of <dst, weight>
 */

import javafx.util.Pair;
import java.util.ArrayList;

public class Graph {
    // Keep track of the graph size
    private int numVertices;
    private int numEdges;
    private int MSTweight;
    private int[] MSTparents;
    private ArrayList<ArrayList<Pair<Integer, Integer>>> edges;

    public Graph(int numVertices, int numEdges) {
        this.numVertices = numVertices;
        this.numEdges = numEdges;
        this.MSTweight = 0;
        this.MSTparents = new int[numVertices];
        this.edges = new ArrayList<>(numVertices);
        for(int i = 0; i < numVertices; i++){
           edges.add(new ArrayList<>());
        }
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    //MSTparent operations
    public void setMSparents(int[] MSTparents){
        this.MSTparents = MSTparents;
    }

    //MSTweight operations
    public void setMSTweight(int MSTweight){
        this.MSTweight = MSTweight;
    }

    public int getMSTweight(){
        return this.MSTweight;
    }

    //edges operations
    public void setEdges(int src, int dst, int weight){
        this.edges.get(src).add(new Pair<>(dst, weight));
        this.edges.get(dst).add(new Pair<>(src, weight));
    }

    public void updateEdge(int u, int v, int weight){
        boolean exist = false;
        for(int i = 0; i < this.edges.get(u).size(); i++){
            if(this.edges.get(u).get(i).getKey() == v){
                this.edges.get(u).set(i, new Pair<>(v, weight));
                exist = true;
                break;
            }
        }
        if(exist){
            for(int i = 0; i < this.edges.get(v).size(); i++){
                if(this.edges.get(v).get(i).getKey() == u) {
                    this.edges.get(v).set(i, new Pair<>(u, weight));
                    break;
                }
            }
        }
        else{
            setEdges(u, v, weight);
        }
    }

    public void removeEdge(int u, int index){
        this.edges.get(u).remove(index);
    }

    public int getEdgeWeight(int u, int v){
        ArrayList<Pair<Integer, Integer>> temp = this.edges.get(u);
        for(int i = 0; i < temp.size(); i++){
            if(temp.get(i).getKey() == v){
                return temp.get(i).getValue();
            }
        }
        System.out.print("No such edge exists");
        return 0;
    }

    public ArrayList<Pair<Integer, Integer>> getVertexEdge(int u){
        return this.edges.get(u);
    }

    public boolean findAdjacentVertex(int src, int target){
        for(int i = 0; i < this.edges.get(src).size(); i++){
            if(this.edges.get(src).get(i).getKey() == target){
                return true;
            }
        }
        return false;
    }

    //MSTadj.operations
    /**
    public ArrayList<ArrayList<Integer>> generateMSTAdj(){
        for(int i = 0; i < numVertices; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = 0; j < numVertices; j++){
                if(j == i){
                    if(i != this.MSTparents[j]) {
                        temp.add(this.MSTparents[j]);
                    }
                }
                else{
                    if(i == this.MSTparents[j]){
                        temp.add(j);
                    }
                }
            }
            this.MSTadj.add(temp);
        }
        return this.MSTadj;
    }
     */

    // Graph shrink: shrink the original graph into MST
    public void graphShrink(){
        for(int i = 0; i < numVertices; i++){
            for(int j = i; j <numVertices; j++){
                if(this.MSTparents[j] != i && MSTparents[i] != j){
                    for(int k = 0; k < this.edges.get(i).size();){
                        int tempWeight;
                        if(this.edges.get(i).get(k).getKey() == j){
                            tempWeight = this.edges.get(i).get(k).getValue();
                            this.edges.get(i).remove(k);
                            this.edges.get(j).remove(new Pair<Integer, Integer>(i, tempWeight));
                            continue;
                        }
                        k++;
                    }
                }
            }
        }
    }

    //print
    public void print(){
        for(int i = 0; i < numVertices; i++){
            ArrayList<Pair<Integer, Integer>> temp = this.edges.get(i);
            System.out.print(i + "->");
            for(int j = 0; j <temp.size(); j++){
                System.out.print(temp.get(j) + " ");
            }
            System.out.print("\n");
        }
    }

    public void printParents(){
        for(int i = 0; i < numVertices; i++){
            System.out.print(this.MSTparents[i] + " ");
        }
    }
}