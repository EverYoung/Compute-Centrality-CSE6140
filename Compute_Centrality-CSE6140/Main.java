/**
 * Main.java
 * @author GuoYuzhi
 * Georgia Institute of Technology, Fall 2018
 *
 * The main entry point for computing HC centrality
 */

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.err.println("Usage: <input-graph-file> <heist-nodes-file> <output-file>");
			System.exit(1);
		}

		String graphFilePath = args[0];
		String heistNodesFilePath = args[1];
		String outputFilePath = args[2];

		CheckVertices.checkVertices(graphFilePath);

		// Open the input and use it to create the graph
		Graph g = FileIo.readGraph(graphFilePath);
		//g.print();

		// Load the vertex set h
		VertexSet h = FileIo.readVertices(heistNodesFilePath);
		//h.print();

		// Compute the HC centrality for each vertex
        long startTime = System.nanoTime();
		HCC.compute(g, h);
		long endTime = System.nanoTime();

		System.out.print("Time:" + (endTime - startTime)/1000000 + "ms");

		// Write out the HC centrality to a file
		FileIo.writeVerticesData(g, outputFilePath);
	}
}
