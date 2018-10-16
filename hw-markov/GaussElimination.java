public class GaussElimination{

	private Fraction[][] matrix;
	int rowCount;
	int colCount;

	public GaussElimination(Fraction[][] t){

		rowCount = t.length;
		colCount = t[0].length;

		// Copy the matrix
		matrix = new Fraction[rowCount][colCount];

		for(int i = 0; i < t.length; i++)
			for(int j = 0; j < t[i].length; j++)
				matrix[i][j] = t[i][j];

		computeReducedRowEchelonForm();
	}

    /**
		Swap rows
		Swap two rows of a matrix by their indices (base 0).
		@param	rowIndex1	The index of one of the rows to be swapped.
		@param	rowIndex2	The index of the other row to be swapped.
	*/
	public void swapRows(int rowIndex1, int rowIndex2) {
		
		Fraction[] temp = matrix[rowIndex1];
		matrix[rowIndex1] = matrix[rowIndex2];
		matrix[rowIndex2] = temp;
    }
    
    /**
		Multiply row
		Multiply the row of a matrix by given fraction.
		@param	i	The index of the row to be swaped.
		@param	f	The fraction to multiply the row by.
	*/
	public void multiplyRow(int i, Fraction f) {
		
		assert (f.getNumerator() != 0): "Should not multiply row by 0";
		
		for(int k = 0; k < matrix[i].length; k++)
			matrix[i][k] = matrix[i][k].multiply(f);
    }
    
    /**
		Add row multiple
		Add the multiple of a row to another row in a matrix.
		@param	f	The multiplying factor.
		@param	a	The index of row to be multiplied and added.
		@param	b	The index of to row to add the multiplied row to.
	*/
	public void addRowMultiple(Fraction f, int a, int b) {
		
		assert (f.getNumerator() != 0): "That operation doesn't make any sense...";
		
		for(int i = 0; i < matrix[a].length; i++)
			matrix[b][i] = matrix[b][i].add(matrix[a][i].multiply(f));
	}

	public void computeReducedRowEchelonForm(){


	}

	/**
	 * Getter for the matrix
	 * @return
	 */
	public Fraction[][] getMatrix(){

		return matrix;
	}
}