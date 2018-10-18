/**
 * CSE 6140 Computational Algs & Engr Science
 * Assignment2
 * ComputeMST.java (Generate MST from a given graph)
 * Prim's Algorithm
 * Xiao Yang
 * 2018.9.24
*/
import java.util.*;
import javafx.util.Pair;

public class ComputeMST {
    public static int computeMST(Graph g) {
        int numVertice = g.getNumVertices();
        // minWeight stores the minimum-weight edge of each vertex at each step.
        int minWeight[] = new int[numVertice];
        // MSTparents stroes MST vertices' adjacency (used for generate MST adjacency ArrayList)
        int MSTparents[] = new int[numVertice];
        // MSTweight is MST's total weight
        int MSTweight = 0;

        // Create a set to record which vertex is already in MST
        Set<Integer> s = new HashSet<>();
        // Define a new Comparator for PriorityQueue to compare weight, use minimum weight as priority
        Comparator<Pair<Integer, Integer>> comparator = new Comparator<>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                if(o1.getValue() <= o2.getValue()) return -1;
                else if(o1.getValue() > o2.getValue()) return 1;
                return 0;
            }
        };
        //Create a PriorityQueue to always pop out the edge with minimum weight
        PriorityQueue<Pair<Integer, Integer>> PQ = new PriorityQueue<>(numVertice, comparator);

        //Initialization: push all vertex with MAX_VALUE weight into PriorityQueue,
        //except for source vertex that has 0 minimum weight
        for(int i = 0; i < numVertice; i++){
            if(i == 0) {
                minWeight[i] = 0;
            }
            else minWeight[i] = Integer.MAX_VALUE;
            PQ.add(new Pair<>(i, minWeight[i]));
        }


        // Prim's Alg start!
        while (!PQ.isEmpty()) {
            Pair<Integer, Integer> u = PQ.poll();
            int uIndex = u.getKey();
            s.add(uIndex);
            // Calculate MSTweight at each step;
            MSTweight += u.getValue();
            // For each vertex u, consider all its adjacent vertices v and update their current minimum weights
            ArrayList<Pair<Integer, Integer>> uEdges = g.getVertexEdge(uIndex);
            for (int j = 0; j < uEdges.size(); j++) {
                int vIndex = uEdges.get(j).getKey();
                int vWeight = uEdges.get(j).getValue();
                if (!s.contains(vIndex) && vWeight < minWeight[vIndex]) {
                    Pair<Integer, Integer> oldV = new Pair<>(vIndex, minWeight[vIndex]);
                    Pair<Integer, Integer> newV = new Pair<>(vIndex, vWeight);
                    PQ.remove(oldV);
                    PQ.add(newV);
                    minWeight[vIndex] = vWeight;
                    // Record their possible parent
                    MSTparents[vIndex] = uIndex;
                }
            }
        }

        g.setMSTweight(MSTweight);
        g.setMSparents(MSTparents);

        return MSTweight;
    }
}
