import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckVertices {
    public static void checkVertices(String graphFile) throws IOException {
        // Open the file and read the number of vertices/edges
        BufferedReader br = new BufferedReader(new FileReader(graphFile));
        String firstLine = br.readLine();
        int numVertices = Integer.parseInt(firstLine.split(" ")[0]);
        int numEdges = Integer.parseInt(firstLine.split(" ")[1]);
        String line = br.readLine();
        int prev = 0;
        int count = 0;
        while(line != null){
            int cur = Integer.parseInt(line.split(" ")[0]);
            if (cur - prev >= 2){
                int diff = cur - prev - 1;
                while(diff != 0){
                    System.out.print(cur - diff);
                    System.out.print('\n');
                    diff--;
                    count++;
                }
            }
            prev = cur;
            line = br.readLine();
        }
        System.out.print("Number of missing vertices:");
        System.out.print(count);
        System.out.print('\n');
    }
}
