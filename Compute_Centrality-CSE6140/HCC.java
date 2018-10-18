import java.util.LinkedList;
import java.util.Queue;

/**
 * HCC.java
 * @author <your name here>
 * Georgia Institute of Technology, Fall 2018
 *
 * Heist-Closeness Centrality computation implementation
 * 
 * NOTE: You should change this file to add in your implementation.
 * Feel free to create as many local functions as you want.
 */

public class HCC {
	public static void compute(Graph g, VertexSet h) {
		// <Implement HCC here>
		LinkedList<Integer> Q = new LinkedList<>();
		int n = g.getNumVertices();
		int offsets[] = g.getRawOffsets();
		int edges[] = g.getRawEdges();


		for(int s=0; s<n; s++){
            double farness = 0;
			boolean visited[] = new boolean[n];
            int d[] = new int[n];
            d[s] = 0;
			Q.add(s);

			while(Q.size()!=0){
				int v = Q.poll();
				int entry = offsets[v];
				for(int j = entry; j < entry + g.degree(v); j++){
					int w = edges[j];
					if(visited[w] == false) {
						Q.add(w);
						visited[w] = true;
						d[w] = d[v] + 1;
					}
				}
				if(h.searchVertex(v)){
					farness += d[v];
				}
			}
			farness = 1/farness;
			g.setVertexData(s, farness);
		}
	}
}

