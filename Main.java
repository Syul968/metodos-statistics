import java.util.*;

public class Main{

    private static Scanner in = new Scanner(System.in);

    public static int readInputSize(){

        int n = -1;

        while(n <= 1){

            n = in.nextInt();
        }

        return n;
    }

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

    public static void main(String[] args) {

        int n = readInputSize();
        int[] data = readData(n);
		
		System.out.println(Statistics.tableAsString(data));
		System.out.printf("X- = %.4f\n", Statistics.mean(data));
		System.out.printf("X~ = %.4f\n", Statistics.median(data));
		System.out.println("X^ = " + (int)Statistics.mode(data));
		System.out.printf("s2 = %.4f\n", Statistics.variance(data));
		System.out.printf("s  = %.4f\n", Statistics.stdDev(data));
	}
}