public class GaussElimination{

	private Fraction[][] matrix;
	private Fraction[] solutionVector;
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


		// computeReducedRowEchelonForm();
		performGaussianEliminationWithPartialPivoting();
	}

	static class Coordinate {

		int row;
		int col;

		Coordinate(int r, int c){

			row = r;
			col = c;
		}

		public String toString(){

			return "(" + row + ", " + col + ")";
		}
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

	public void multiplyAndAdd(Coordinate to, Coordinate from, Fraction scalar){

		for(int i = 0; i < colCount; i++){

			matrix[to.row][i].add(matrix[from.row][from.col].multiply(scalar));
		}
	}

	public void scaleRow(int row, Fraction scalar){

		for(int i = 0; i < colCount; i++)
			matrix[row][i].multiply(scalar);
	}

	public void performGaussianEliminationWithPartialPivoting(){

		int n = rowCount;
		int m = colCount;

		for(int p = 0; p < n; p++){

			int maxPivotRowIndex = p;

			for(int i = p + 1; i < n ; i++){

				long num1 = matrix[i][p].getNumerator();
				long den1 = matrix[i][p].getDenominator();
				long num2 = matrix[maxPivotRowIndex][p].getNumerator();
				long den2 = matrix[maxPivotRowIndex][p].getDenominator();

				if(Math.abs(num1 * den2) > Math.abs(num2 * den1)){

					maxPivotRowIndex = i; 
				}
			}	

			// Swap rows
			Fraction[] temp = matrix[p];
			matrix[p] = matrix[maxPivotRowIndex];
			matrix[maxPivotRowIndex] = temp;

			// Pivot
			for(int i = p + 1; i < n; i++){

				Fraction alpha = matrix[i][p].divide(matrix[p][p]);
				matrix[i][colCount - 1] = matrix[i][colCount - 1].subtract(alpha.multiply(matrix[p][colCount - 1]));

				for(int j = p; j < n; j++){

					matrix[i][j] = matrix[i][j].subtract(alpha.multiply(matrix[p][j]));
				}
			}
		}

		solutionVector = new Fraction[n];

		for(int i = 0; i < n; i++)
			solutionVector[i] = new Fraction(0, 1);

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

	public int computeValueKInColumn(int col, int kValue){
		
		int count = 0;

		for(int i = 0; i < rowCount; i++){

			if(matrix[i][col].compareTo(new Fraction(0, 1)) != 0)
				count++;
		}

		return count;
	}

	public Coordinate findPivot(Coordinate c){

		int firstRow = c.row;
		Coordinate finalPivot = new Coordinate(c.row, c.col);
		Coordinate currPivot = new Coordinate(c.row, c.col);

		for(int i = c.row; i < (rowCount - firstRow); i++){

			currPivot.row = i;

			if(matrix[currPivot.row][currPivot.col].compareTo(new Fraction(1, 1)) != 0){

				swapRows(currPivot.row, c.row);
			}
		}

		currPivot.row = c.row;

		for(int i = currPivot.row; i < (rowCount - firstRow); i++){

			currPivot.row = i;

			if(matrix[currPivot.row][currPivot.col].compareTo(new Fraction(0, 1)) != 0){

				finalPivot.row = i;
				break;
			}
		}

		return finalPivot;
	}

	/**
	 * Getter for the matrix
	 * @return
	 */
	public Fraction[][] getMatrix(){

		return matrix;
	}

	public Fraction[] getSolutionVector(){

		return solutionVector;
	}
}