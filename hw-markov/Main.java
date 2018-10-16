import java.util.*;

/**
	Main
	This class prints Markov's Chain transition matrices
	for given steps of an initial transtition matrix.
	
	@author		A07104218	Salvador Orozco Villalever
	@author		A01328937	Luis Francisco Flores Romero
	@version	1.0
	@since		12.oct.2018
*/
public class Main {
	
	/**
		Print matrix
		Print given matrix of fractions.
		@param	m	The matrix to print.
		@return		Nothing.
	*/
	public static void printMatrix(Fraction[][] m) {
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + "   ");
			}
			System.out.println();
		}
	}

	/**
		Print fraction array
		Print given array of fractions.
		@param	arr	The array to print.
		@return		Nothing.
	*/
	public static void printFractionArray(Fraction[] arr) {
		
		for(int i = 0; i < arr.length; i++) {

			if(i > 0)
				System.out.print(", ");

			System.out.print(arr[i]);
		}
	}
	
	/**
		Main
		This method reads input to build a transition matrix.
		@param	args	Input from input stream.
		@return			Nothing.
	*/
	public static void main(String[] args) {
		
		Fraction[][] t, acc;
		int states;
		int steps;
		
		Scanner in = new Scanner(System.in);
		states = in.nextInt();
		
		t = new Fraction[states][states];
		acc = new Fraction[states][states];
		for(int i = 0; i < states; i++) {
			for(int j = 0; j < states; j++) {
				int num = in.nextInt();
				int den = in.nextInt();
				
				acc[i][j] = t[i][j] = new Fraction(num, den);
			}
		}
		
		steps = in.nextInt();
		System.out.println("P1");
		printMatrix(acc);
		for(int i = 1; i < steps; i++) {
			System.out.println("P" + (i + 1));
			acc = Mathematics.multiplyMatrices(acc, t);
			printMatrix(acc);
			System.out.println();
		}

		MarkovChain mc = new MarkovChain(t);
		System.out.println("Equations matrix:\n");
		printMatrix(mc.getEquationsMatrix());

		Fraction[] fixedPointVector = mc.extractFixedPointVector();
		System.out.println("Fixed point vector:\n");
		printFractionArray(fixedPointVector);
	}
}