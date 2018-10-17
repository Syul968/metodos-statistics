/**
	Utilities
	This class contains generic methods for handling data structures.
	
	@author		A07104218	Salvador Orozco Villalever
	@author		A01328937	Luis Francisco Flores Romero
	@version	1.0
	@since		12.oct.2018
*/

public class Utilities{

    /**
	 * Method that copies a fraction array.
	 * @param arr the array to copy
	 * @return newArr the copy of 'arr'
	 */
	public static Fraction[] copyFractionArray(Fraction[] arr){

		Fraction[] newArr = new Fraction[arr.length];

		for(int i = 0; i < arr.length; i++)
			newArr[i] = arr[i];

		return newArr;
    }

	/**
	 * Method that copies a fraction matrix.
	 * @param matrix the matrix to copy
	 * @return newMatrix the copy of 'matrix'
	 */
    public static Fraction[][] copyFractionMatrix(Fraction[][] matrix){

		Fraction[][] newMatrix = new Fraction[matrix.length][matrix[0].length];

		for(int i = 0; i < matrix.length; i++)
			for(int j = 0; j < matrix[i].length; j++)
				newMatrix[i][j] = matrix[i][j];

		return newMatrix;
	}
}