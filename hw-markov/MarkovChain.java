public class MarkovChain{

    /**
		Equations matrix
		Compute the equations system of a matrix.
		The input matrix must be a Markov's chain transition
		matrix.
		@param	t	Matrix from which to find the system.
		@return		The equations system as matrix.
	*/
	public static Fraction[][] computeEquationsMatrix(Fraction[][] t) {
		int states = t.length;
		int n = states - 1;

		// Add one extra column for the right sides of the equations
		Fraction[][] equations = new Fraction[n][n + 1]; 
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				equations[i][j] = t[j][i].substract(t[n][i]);
				
				if(i == j)
					equations[i][j] = equations[i][j].substract(new Fraction(1, 1));
			}
		}

		// Add the right sides of the equations to the matrix
		for(int i = 0; i < n; i++) {
			
			equations[i][n] = t[n][i].multiply(new Fraction(-1, 1));
		}
		
		return equations;
    }
}