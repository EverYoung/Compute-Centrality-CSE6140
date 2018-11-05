import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RunExperiment {
    public static void main(String[] args) throws IOException {
        //There are two required input argument, the first one is input filename
        //and the second is output filename
        if(args.length < 2){
            System.err.println("Unexpected number of command line arguments");
            System.exit(1);
        }
        String inputFile = args[0];
        String outputFile = args[1];

        //Read file
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String firstline = br.readLine();
        //Acquire the problem size n and k
        int n = Integer.parseInt(firstline.split(",")[0]);
        int k = Integer.parseInt(firstline.split(",")[1]);
        PrintWriter output = new PrintWriter(outputFile);

        //Loop for each instance
        while(k > 0) {
            //Read each instance and store it in a double array
            String[] line = br.readLine().split(",");
            double data[] = new double[n];
            for(int i = 0; i < n; ++i){
                data[i] = Double.parseDouble(line[i]);
            }

            //Where the algorithmss kick in
            double startTime = System.nanoTime();

            //Choose one algorithm
            //double[] ans = divideAndConquer(data, 0, n - 1);
            double[] ans = dynamicProgramming(data);
            //double[] ans = simpleTraversal(data);

            double finishTime = System.nanoTime();

            //Keep two decimal digits
            double totalTime = (finishTime - startTime) / 1000000;
            totalTime = (double)(Math.round(totalTime * 100))/100;

            //Write file
            output.println(ans[0] + "," + (int)ans[1] + "," + (int)ans[2] + "," + totalTime);

            //Counter decrease
            --k;
        }

        //Close IO stream
        br.close();
        output.close();
    }

    private static double[] divideAndConquer(double[] data, int front, int back){
        //Return a double array with the first element as maximum sum, second element as starting index
        //and the third element as ending index

        //Return when size equals 1 (Base case)
        if(front == back){
            return new double[]{data[front], front + 1, back + 1};
        }

        //When size larger than 1
        int mid = front + (back - front) / 2;
        //Recursively compute left half
        double[] max1 = divideAndConquer(data, front, mid);
        //Recursively compute right half
        double[] max2 = divideAndConquer(data, mid + 1, back);
        //Compute the subsequence cross the middle
        double[] max3 = new double[3];
        double maxProfit = 0, sumLeft = 0, sumRight = 0, maxLeft = 0, maxRight = 0;
        //Starting index and ending index initialization
        int begin = 0, end = mid;
        //Scan towards the front
        for(int i = mid; i >= front; --i){
            sumLeft += data[i];
            //Update starting index and partial maximum sum
            if(maxLeft < sumLeft){
                begin = i;
                maxLeft = sumLeft;
            }
        }
        //Scan towards the back
        for(int i = mid+1; i <= back; ++i) {
            sumRight += data[i];
            //Update ending index and partial maximum sum
            if (maxRight <= sumRight) {
                end = i;
                maxRight = sumRight;
            }
        }
        //Keep two decimal digits
        maxProfit = maxRight + maxLeft;
        maxProfit = (double)(Math.round(maxProfit * 100))/100;
        //Construct ans
        max3[0] = maxProfit;
        max3[1] = begin + 1;
        max3[2] = end + 1;

        //Compare three candidate maximum sum, take the largest one
        if(max1[0] >= max2[0] && max1[0] >= maxProfit) return max1;
        else if(max2[0] >= max1[0] && max2[0] >= maxProfit) return max2;
        else return max3;
    }

    private static double[] dynamicProgramming(double[] data){
        //Return a double array with the first element as maximum sum, second element as starting index
        //and the third element as ending index

        //Apply extra memory
        double dp[] = new double[data.length + 1];
        //Base case initialization
        dp[0] = 0;
        //Compute each value in dp[]
        int begin = 0, end = 0, tempBegin = 0;
        double maxProfit = 0;
        for(int i = 1; i <= data.length; ++i){
            dp[i] = Math.max(dp[i-1] + data[i-1], 0);
            //Update starting index
            if(dp[i] > 0 && dp[i-1] == 0) tempBegin = i;
            //Update ending index and maximum sum
            if(dp[i] > maxProfit){
                maxProfit = dp[i];
                begin = tempBegin;
                end = i;
            }
        }

        //Keep two decimal digits
        maxProfit = (double)(Math.round(maxProfit * 100))/100;
        return new double[]{maxProfit, begin, end};
    }

    private static double[] simpleTraversal(double[] data){
        //Simplified dynamic programming solution
        //Use single variable instead of an array
        int begin = 0, end = 0, tempBegin = 0;
        double maxProfit = 0, sum = 0;
        for(int i = 0; i < data.length; ++i){
            //Update starting index
            if(sum == 0 && sum + data[i] > 0) tempBegin = i;
            //Compute sum, if negative then set to 0, otherwise add number
            sum = Math.max(sum + data[i], 0);
            //Update maximum sum and ending index, when sum gets larger than maximum
            if(sum > maxProfit){
                maxProfit = sum;
                begin = tempBegin;
                end = i;
            }
        }

        //Keep two decimal digits
        maxProfit = (double)(Math.round(maxProfit * 100))/100;
        return new double[]{maxProfit, begin + 1, end + 1};
    }
}
