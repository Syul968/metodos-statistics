public class MarkovChain{

	private Fraction[][] equationsMatrix;

	/**
	 * Constructor of the Markov Chain
	 * @param t
	 */
	public MarkovChain(Fraction[][] t){

		// Build the equations matrix
		equationsMatrix = computeEquationsMatrix(t);
		
		// Compute the reduced row echelon form of the matrix
		GaussElimination ge = new GaussElimination(equationsMatrix);
		
		// Update the equations matrix to its reduced row echelon form
		equationsMatrix = ge.getMatrix();
	}

    /**
		Equations matrix
		Compute the equations system of a matrix.
		The input matrix must be a Markov's chain transition
		matrix.
		@param	t	Matrix from which to find the system.
		@return		The equations system as matrix.
	*/
	public Fraction[][] computeEquationsMatrix(Fraction[][] t) {
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

	/**
	 * Getter for the equations matrix of the Markov Chain
	 * @return
	 */
	public Fraction[][] getEquationsMatrix(){

		return equationsMatrix;
	}
	
	/**
	 * Method that extracts the fixed-point vector from the of the Markov Chain 
	 * in its reduced row echelon form. 
	 * @return fixedPointVector an Fraction array corresponding to the fixed-point vector.
	 */
	public Fraction[] extractFixedPointVector(){

		Fraction[] fixedPointVector = new Fraction[equationsMatrix.length];
		
		for(int i = 0; i < equationsMatrix.length; i++){

			fixedPointVector[i] = equationsMatrix[i][i];
		}

		return fixedPointVector;
	}
}