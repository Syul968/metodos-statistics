
/**
 * Class with utility mathematics functions.
 * 
 * @version 08/28/2018
 * @author Luis Francisco Flores Romero - A01328937
 * @author Salvador Orozco Villalever - A07104218
 */

public class Mathematics{

	/**
	 * Recursive method for calculating the 
	 * Greatest Common Divisor (GCD) of two
	 * integers.
	 *
	 * @param a the first integer
	 * @param b the second integer 
	 * @return the GCD of a and b
	 */ 
    public static long computeGCD(long a, long b){

        return b == 0 ? a : computeGCD(b, a%b);
    }

	/**
	 * Method for calculating the square root of
	 * a given real number based on Newton's 
	 * root iteration method.
	 *
	 * @param n the number whose square root will
	 * be calculated
	 */
    public static double sqrt(double n) {
		double root = n / 2.0;	//	Initial root guess
		
		if(n == 0.0)
			return 0.0;
            
		if(n < 0.0) {
			System.out.println("Negative numbers not allowed");
			return 0.0;
		}
		
		do {
			root = (root + n / root) / 2.0;	//	Newton's root iteration
		} while(root * root - n >= 0.0001);
		
		return root;
	}
	
	/**
		Multiply matrices
		Compute A x B product of given fraction matrices.
		@param	a	Matrix A.
		@param	b	Matrix B.
		@return		Matrices product.
	*/
	public static Fraction[][] multiplyMatrices(Fraction[][] a, Fraction[][] b) {
		if(a[0].length != b.length) {	// Verify matrices can be multiplied
			System.out.println("Matrices are not compatible");
			return null;
		}
		
		Fraction[][] r = new Fraction[a.length][b[0].length];	// Allocate space for matrices product
		
		for(int i = 0; i < b[0].length; i++) {
			for(int j = 0; j < a.length; j++) {
				Fraction acc = new Fraction(0, 1);	// Accumulates (row x col) sum
				for(int k = 0; k < a[0].length; k++) {
					acc = acc.add(a[j][k].multiply(b[k][i]));	//	Add individual values product to acc
				}
				r[j][i] = acc;	// Add to result matrix
			}
		}
		
		return r;
	}
	
	/**
		Equations matrix
		Compute the equations system of a matrix.
		The input matrix must be a Markov's chain transition
		matrix.
		@param	t	Matrix from which to find the system.
		@return		The equations system as matrix.
	*/
	public static Fraction[][] equationsMatrix(Fraction[][] t) {
		int states = t.length;
		int n = states - 1;
		Fraction[][] equations = new Fraction[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				equations[i][j] = t[j][i].substract(t[n][i]);
				
				if(i == j)
					equations[i][j] = equations[i][j].substract(new Fraction(1, 1));
			}
		}
		
		return equations;
	}
	
	/**
		Solutions vector
		Compute the solutions for the equations system of a matrix.
		The input matrix must be a Markov's chain transition
		matrix.
		@param	t	Matrix of which to get the solutions.
		@return		Solutions vector.
	*/
	public static Fraction[] solutionsVector(Fraction[][] t) {
		int states = t.length;
		int n = states - 1;
		Fraction[] solutions = new Fraction[n];
		
		for(int i = 0; i < n; i++) {
			solutions[i] = t[n][i].multiply(new Fraction(-1, 1));
		}
		
		return solutions;
	}
}