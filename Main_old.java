import java.util.*;

/**
 * Main class which includes the main method.
 * 
 * @version 08/28/2018
 * @author Luis Francisco Flores Romero - A01328937
 * @author Salvador Orozco Villalever - A07104218
 */

public class Main{

    private static Scanner in = new Scanner(System.in);

    /**
     *  Method to read the size of the input. It
     *  validates that the size is greater or equal
     *  than 2.
     *  
     *  @return n the size of the input.
     */
    public static int readInputSize(){

        int n = -1;

        while(n <= 1){

            n = in.nextInt();
        }

        return n;
    }

    /**
     *  Method that reads the list of integers
     *  whose frequency table and statistic measures
     *  are to be computed.
     *  
     *  @param n the amount of integers to be read.
     *  @return data an array storing the integers.
     */
    public static int[] readData(int n){

        // Create an array in which the integers 
        // from the input will be stored
        int[] data = new int[n];
		
        // Read data
		for(int i = 0; i < n; i++) {

			data[i] = in.nextInt();
		}

        return data;
    }

    /**
     * Main method that triggers the execution.
     */
    public static void main(String[] args) {

        int n = readInputSize();
        int[] data = readData(n);
		
		System.out.println(Statistics.tableAsString(data));
		System.out.printf("X- = %.4f\n", Statistics.mean(data));
		System.out.printf("X~ = %.4f\n", Statistics.median(data));
        Statistics.displayMode(data);
		System.out.printf("s2 = %.4f\n", Statistics.variance(data));
		System.out.printf("s  = %.4f\n", Statistics.stdDev(data));
	}
}