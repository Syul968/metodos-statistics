/**
	GaussElimination
	This class performs the Gaussian elimination on a given
	matrix and computes the corresponding solution vector.
	
	@author		A07104218	Salvador Orozco Villalever
	@author		A01328937	Luis Francisco Flores Romero
	@version	1.0
	@since		12.oct.2018
*/

public class GaussElimination{

	// Matrix to be solved
	private Fraction[][] matrix;

	// Array containing the solution vector
	private Fraction[] solutionVector;

	// Amount of rows
	int rowCount;

	// Amount of columns
	int colCount;

	/**
	 * Constructor for the Gauss elimination
	 * @param t the matrix to be solved
	 */
	public GaussElimination(Fraction[][] t){

		rowCount = t.length;
		colCount = t[0].length;

		matrix = Utilities.copyFractionMatrix(t);

		// computeReducedRowEchelonForm();
		performGaussianEliminationWithPartialPivoting();
	}

    /**
	 *	Swap rows
	 *	Swap two rows of a matrix by their indices (base 0).
	 *	@param	rowIndex1	The index of one of the rows to be swapped.
	 *	@param	rowIndex2	The index of the other row to be swapped.
	 */
	public void swapRows(int rowIndex1, int rowIndex2) {
		
		Fraction[] temp = matrix[rowIndex1];
		matrix[rowIndex1] = matrix[rowIndex2];
		matrix[rowIndex2] = temp;
    }
    
	/**
	 * Method that performs the Gaussian elimination with partial
	 * pivoting. It modifies the 'matrix' attribute of this class
	 * and assigns the solutionVector attribute of this class.
	 */
	public void performGaussianEliminationWithPartialPivoting(){

		int n = rowCount;

		for(int p = 0; p < n; p++){

			// Initialize with a pivot row index candidate.
			int maxPivotRowIndex = p;

			for(int i = p + 1; i < n ; i++){

				// Update the pivot row index candidate if necessary.
				if(matrix[i][p].compareToAbsValue(matrix[maxPivotRowIndex][p]) > 0){

					maxPivotRowIndex = i; 
				}
			}	

			// Swap the rows p and maxPivotRowIndex.
			swapRows(p, maxPivotRowIndex);

			// Pivot.
			for(int i = p + 1; i < n; i++){

				Fraction alpha = matrix[i][p].divide(matrix[p][p]);
				matrix[i][colCount - 1] = matrix[i][colCount - 1].subtract(alpha.multiply(matrix[p][colCount - 1]));

				for(int j = p; j < n; j++){

					matrix[i][j] = matrix[i][j].subtract(alpha.multiply(matrix[p][j]));
				}
			}
		}

		// Assign the solution vector.
		solutionVector = new Fraction[n];

		// Initialize fractions as 0/1.
		for(int i = 0; i < n; i++)
			solutionVector[i] = new Fraction(0, 1);

		// Do back substitution.
		for(int i = n - 1; i >= 0; i--){

			Fraction sum = new Fraction(0, 1);
			
			for(int j = i + 1; j < n; j++){

				sum = sum.add(matrix[i][j].multiply(solutionVector[j]));
			}

			Fraction tmp = matrix[i][colCount - 1].subtract(sum);
			tmp = tmp.divide(matrix[i][i]);
			solutionVector[i] = tmp;
		}
	}

	/**
	 * Getter for the matrix
	 * @return matrix the matrix of the Gauss elimination
	 */
	public Fraction[][] getMatrix(){

		return matrix;
	}

	/**
	 * Getter for the solution vector
	 * @return solutionVector the solution vector of the Gauss elimination
	 */
	public Fraction[] getSolutionVector(){

		return solutionVector;
	}
}