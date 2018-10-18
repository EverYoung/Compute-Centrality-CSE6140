/**
 * CSE 6140 Computational Algs & Engr Science
 * Assignment2
 * RecomputeMST.java (Update MST given a new edge)
 * Xiao Yang
 * 2018.9.24
 */

import java.util.ArrayList;
import javafx.util.Pair;

public class RecomputeMST {
    public static int recomputeMST(int u, int v, int weight, Graph g){
        int numVertice = g.getNumVertices();
        // MSTweight is the total weight of the current MST.
        int MSTweight = g.getMSTweight();

        // Case 1: The original graph already has an edge (u, v)
        if(g.findAdjacentVertex(u, v)){
            // Case 1 - Sub-case 1: the new edge added has a smaller weight than the original one
            // Update weights with the new one (smaller one) and also the MSTweight.
            if(g.getEdgeWeight(u, v) > weight){
                MSTweight += weight - g.getEdgeWeight(u, v);
                g.updateEdge(u, v, weight);
                g.setMSTweight(MSTweight);
                return MSTweight;
            }
            // Case 1 - Sub-case 2: the new edge added has a larger or equal weight.
            // The new edge added will not change the MST, so we do nothing.
            else return MSTweight;
        }

        // Case 2: The original graph does not contain an edge (u, v)
        // Add the new edge (its weight) into the graph and recompute the MST.
        else {
            //g.updateEdge(u, v, weight);

            //Find v from u, if successfully find, then the path forms a cycle with the new edge.
            //The cycle is found and built up using backtracking.
            boolean[] visited = new boolean[numVertice];
            ArrayList<Integer> cycle = new ArrayList<>();
            int maxWeight = 0;
            int maxIndex1 = 0;
            int maxIndex2 = 0;
            cycle.add(u);
            if(backtracking(u, v, g, visited, cycle)){
                //printCycle(cycle);
                //Find maxWeight
                for(int i = 0; i < cycle.size()-1; i++){
                    if(maxWeight <g.getEdgeWeight(cycle.get(i), cycle.get(i+1))){
                        maxWeight = g.getEdgeWeight(cycle.get(i), cycle.get(i+1));
                        maxIndex1 = cycle.get(i);
                        maxIndex2 = cycle.get(i + 1);
                    }
                }

                //System.out.print(maxWeight + " " + maxIndex1 + " " + maxIndex2);
                //System.out.print('\n');

                //If maxWeight in the cycle has a less weight, we don't change the MST
                if(maxWeight <= weight) {
                    return MSTweight;
                }
                // Else, we delete the edge with maxWeight and add in the new edge
                // Update the MST
                else{
                    MSTweight += weight - maxWeight;
                    g.getVertexEdge(u).add(new Pair<>(v, weight));
                    g.getVertexEdge(v).add(new Pair<>(u, weight));
                    ArrayList<Pair<Integer, Integer>> temp = g.getVertexEdge(maxIndex1);
                    for(int i = 0; i < temp.size(); i++){
                        if(temp.get(i).getKey() == maxIndex2){
                            g.removeEdge(maxIndex1, i);
                            break;
                        }
                    }
                    temp = g.getVertexEdge(maxIndex2);
                    for(int i = 0; i < temp.size(); i++){
                        if(temp.get(i).getKey() == maxIndex1){
                            g.removeEdge(maxIndex2, i);
                            break;
                        }
                    }

                }
            }

        }
        g.setMSTweight(MSTweight);
        return MSTweight;

    }

    private static boolean backtracking(int u, int v, Graph G, boolean[] visited, ArrayList<Integer> cycle){
        ArrayList<Pair<Integer, Integer>> temp = G.getVertexEdge(u);
        visited[u] = true;
        if(u == v) return true;
        for(int i = 0; i < temp.size(); i++){
            if(!visited[temp.get(i).getKey()]){
                cycle.add(temp.get(i).getKey());
                boolean res = backtracking(temp.get(i).getKey(), v, G, visited, cycle);
                if (res){
                    return true;
                }
                cycle.remove(temp.get(i).getKey());
            }
        }
        return false;
    }
}
