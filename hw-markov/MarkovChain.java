/**
	MarkovChain
	This class computes the linear equation matrix of a given
	Markov Chain and calls the computation of its fixed-point 
	vector.
	
	@author		A07104218	Salvador Orozco Villalever
	@author		A01328937	Luis Francisco Flores Romero
	@version	1.0
	@since		12.oct.2018
*/

public class MarkovChain{

	private Fraction[][] probabilitiesMatrix;
	private Fraction[][] equationsMatrix;
	private Fraction[] fixedPointVector;

	/**
	 * Constructor of the Markov Chain
	 * @param t the probabilities matrix of the Markov Chain
	 */
	public MarkovChain(Fraction[][] t){

		// Assign the probabilities matrix.
		probabilitiesMatrix = Utilities.copyFractionMatrix(t);

		// Build the equations matrix.
		equationsMatrix = computeEquationsMatrix(t);
		
		// Use Gaussian elimination to solve the matrix.
		GaussElimination ge = new GaussElimination(equationsMatrix);
		
		// Copy the fraction matrix that resulted from the Gaussian elimination.
		equationsMatrix = Utilities.copyFractionMatrix(ge.getMatrix());

		// Get the fixed-point vector.
		fixedPointVector = computeFixedPointVector(ge.getSolutionVector());

		// Assert that the computation of the fixed-point vector was correct.
		if(!fixedPointVectorIsCorrect())
			System.out.println("The fixed-point vector is incorrect!");
	}

    /**
	 *	Equations matrix
	 *	Compute the equations system of a matrix.
	 *	The input matrix must be a Markov's chain transition
	 *	matrix.
	 *	@param	t	Matrix from which to find the system.
	 *	@return		The equations system as matrix.
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
	 * Method that computes the fixed-point vector of the Markov chain
	 * given the solution vector of the linear equations matrix.
	 * @param matrixSolutionVector the solution vector of the linear equations matrix
	 * obtained by performing Gaussian elimination.
	 * @return the fixed-point vector of the Markov chain.
	 */
	public Fraction[] computeFixedPointVector(Fraction[] matrixSolutionVector){

		// The matrix solution vector only contains n - 1 entries of the Markov Chain
		// solution vector. The last entry of the Markov Chain solution vector is equal 
		// to 1 - the sum of the entries in the matrix solution vector.
		Fraction[] solutionVector = new Fraction[matrixSolutionVector.length + 1];

		Fraction matrixSolutionVectorSum = new Fraction(0, 1);

		for(int i = 0; i < matrixSolutionVector.length; i++){

			solutionVector[i] = new Fraction(matrixSolutionVector[i]);
			matrixSolutionVectorSum = matrixSolutionVectorSum.add(matrixSolutionVector[i]);
		}

		solutionVector[solutionVector.length - 1] = new Fraction(1, 1).subtract(matrixSolutionVectorSum);

		return solutionVector;
	}

	/**
	 * Getter for the equations matrix of the Markov Chain.
	 * @return equationsMatrix the linear equations matrix of
	 * this Markov Chain.
	 */
	public Fraction[][] getEquationsMatrix(){

		return equationsMatrix;
	}	

	/**
	 * Getter for the fixed-point vector.
	 * @return fixedPointVector the fixed-point vector of this Markov Chain.
	 */
	public Fraction[] getFixedPointVector(){

		return fixedPointVector;
	}

	/**
	 * Method that tests whether the fixed-point vector is correct.
	 * @return True, if the fixed-point vector is correct. Else, false.
	 */
	public Boolean fixedPointVectorIsCorrect(){

		Fraction[][] fixedPointVectorMatrix = new Fraction[1][fixedPointVector.length];

		// Turn the fixed-point vector into a matrix with one row.
		for(int i = 0; i < fixedPointVector.length; i++)
			fixedPointVectorMatrix[0][i] = fixedPointVector[i];

		return Mathematics.compareFractionMatrices(Mathematics.multiplyMatrices(fixedPointVectorMatrix, probabilitiesMatrix), fixedPointVectorMatrix);
	}
}