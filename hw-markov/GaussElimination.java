public class GaussElimination{

    /**
		Swap rows
		Swap two rows of a matrix by their indices (base 0).
		@param	t	The matrix to perform the operation with.
		@param	a	One of the rows to be swaped.
		@param	b	The other row to be swaped.
	*/
	public static void swapRows(Fraction[][] t, int a, int b) {
		Fraction[] temp = t[a];
		t[a] = t[b];
		t[b] = temp;
    }
    
    /**
		Multiply row
		Multiply the row of a matrix by given fraction.
		@param	t	The matrix to perform the operation with.
		@param	i	The index of the row to be swaped.
		@param	f	The fraction to multiply the row by.
	*/
	public static void multiplyRow(Fraction[][] t, int i, Fraction f) {
		assert (f.getNumerator() != 0): "Should not multiply row by 0";
		
		for(int k = 0; k < t[i].length; k++)
			t[i][k] = t[i][k].multiply(f);
    }
    
    /**
		Add row multiple
		Add the multiple of a row to another row in a matrix.
		@param	t	The matrix to perform the operation with.
		@param	f	The multiplying factor.
		@param	a	The index of row to be multiplied and added.
		@param	b	The index of to row to add the multiplied row to.
	*/
	public static void addRowMultiple(Fraction[][] t, Fraction f, int a, int b) {
		assert (f.getNumerator() != 0): "That operation doesn't make any sense...";
		
		Fraction[] mulRow = new Fraction[t[a].length];
		for(int i = 0; i < t[a].length; i++)
			t[b][i] = t[b][i].add(t[a][i].multiply(f));
	}
}