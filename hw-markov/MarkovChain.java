public class MarkovChain{

	private Fraction[][] probabilitiesMatrix;
	private Fraction[][] equationsMatrix;
	private Fraction[] fixedPointVector;

	/**
	 * Constructor of the Markov Chain
	 * @param t
	 */
	public MarkovChain(Fraction[][] t){

		probabilitiesMatrix = copyFractionMatrix(t);

		// Build the equations matrix
		equationsMatrix = computeEquationsMatrix(t);
		
		// Compute the reduced row echelon form of the matrix
		GaussElimination ge = new GaussElimination(equationsMatrix);
		
		// Update the equations matrix to its reduced row echelon form
		equationsMatrix = copyFractionMatrix(ge.getMatrix());

		fixedPointVector = copyArray(ge.getSolutionVector());
	}

	public Fraction[][] copyFractionMatrix(Fraction[][] matrix){

		Fraction[][] newMatrix = new Fraction[matrix.length][matrix[0].length];

		for(int i = 0; i < matrix.length; i++)
			for(int j = 0; j < matrix[i].length; j++)
				newMatrix[i][j] = matrix[i][j];

		return newMatrix;
	}

	public Fraction[] copyArray(Fraction[] arr){

		Fraction[] newArr = new Fraction[arr.length];

		for(int i = 0; i < arr.length; i++)
			newArr[i] = arr[i];

		return newArr;
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
				equations[i][j] = t[j][i].subtract(t[n][i]);
				
				if(i == j)
					equations[i][j] = equations[i][j].subtract(new Fraction(1, 1));
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

	public Fraction[] getFixedPointVector(){

		return fixedPointVector;
	}

	public static Boolean fixedPointVectorIsCorrect(){

		return false;
	}
}