/**
 * Assignment2
 * ParseEdges (From a CSR format into a format that mutually store each edge for both of the endpoints)
 * Xiao Yang
 * 2018.9.24
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseEdges {
    public static Graph parseEdges(String graph_file) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(graph_file));
        String firstLine = br.readLine();
        int numVertices = Integer.parseInt(firstLine.split(" ")[0]);
        int numEdges = Integer.parseInt(firstLine.split(" ")[1]);
        Graph g = new Graph(numVertices, numEdges);

        String line = br.readLine();
        int src = Integer.parseInt(line.split(" ")[0]);
        int dst = Integer.parseInt(line.split(" ")[1]);
        int wgh = Integer.parseInt(line.split(" ")[2]);
        // Record last src, dst, wgh for comparision
        int lastSrc = src;
        int lastDst = dst;
        int lastWgh = wgh;
        while(line != null){
            src = Integer.parseInt(line.split(" ")[0]);
            dst = Integer.parseInt(line.split(" ")[1]);
            wgh = Integer.parseInt(line.split(" ")[2]);
            //If still at the same src
            if(src == lastSrc){
                // If for the same dst
                if(dst == lastDst){
                    // If current weight is smaller than before, store current weight.
                    if(wgh < lastWgh) lastWgh = wgh;
                }
                // If move on to the next dst, add edge for the last dst
                else{
                    g.setEdges(src, lastDst, lastWgh);
                    lastDst = dst;
                    lastWgh = wgh;
                }
            }
            // If move on to the next src, add the last edge for last src
            else{
                g.setEdges(lastSrc, lastDst, lastWgh);
                lastSrc = src;
                lastDst = dst;
                lastWgh = wgh;
            }
            line = br.readLine();
        }
        // Add the last edge in file
        g.setEdges(lastSrc, lastDst, lastWgh);

        br.close();
        return g;
    }
}
