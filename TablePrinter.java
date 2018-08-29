/**
 * Table Printer
 * This class prints formatted rows with string tags.
 * 
 * @version 08/28/2018
 * @author Salvador Orozco Villalever - A07104218
 * @author Luis Francisco Flores Romero - A01328937
 */
public class TablePrinter {
	public static final int ALIGN_LEFT = -1;
	public static final int ALIGN_CENTER = 0;
	public static final int ALIGN_RIGHT = 1;
	
	/**
	 *	Builds a String representation of a border based
	 *	on "+" and "-" chars.
	 *
	 *	@param	cellLength	The length of the cell to which this border belongs.
	 *	@param	starts		Whether this border belongs to a first column.
	 *	@return				String representation of the border.
	 */
	public static String printBorder(int cellLength, boolean starts) {
		String border = "";
		if(starts)
			border += "+";
			
		for(int i = 0; i <= cellLength; i++) {
			border += "-";
		}
		border += "+";
		
		return border;
	}
	
	/**
	 *	Builds a String representation of a row with given tags
	 *	and lengths. Makes use of printBorder method.
	 *
	 *	@param	lengths		Array of row cells' lenghts.
	 *	@param	tags		Array of row cells' tags.
	 *	@param	alignment	How to align tags in the row. Either ALIGN_LEFT, ALIGN_CENTER or ALIGN_RIGHT.
	 *	@param	top			Whether this is the first row in a table.
	 *	@return				String representation of the row.
	 */
	public static String printRow(int[] lengths, String[] tags, int alignment, boolean top) {
		String row = "";
		
		if(top) {
			row += printBorder(lengths[0], true);
			for(int i = 1; i < lengths.length; i++) {
				row += printBorder(lengths[i], false);
			}
			row += "\n";
		}
		
		row += "|";
		for(int i = 0; i < lengths.length; i++) {
			switch(alignment) {
				case ALIGN_LEFT:
					row += String.format("%-" + lengths[i] + "s ", tags[i]);
					break;
				case ALIGN_CENTER:
					int offset = (lengths[i] - tags[i].length()) / 2;
					for(int j = 0; j <= offset; j++) {
						row += " ";
					}
					row += tags[i];
					for(int j = 0; j < lengths[i] - tags[i].length() - offset; j++) {
						row += " ";
					}
					break;
				case ALIGN_RIGHT:
				default:
					row += String.format(" %" + lengths[i] + "s", tags[i]);
					break;
			}
			row += "|";
		}
		row += "\n";
		
		row += printBorder(lengths[0], true);
		for(int i = 1; i < lengths.length; i++) {
			row += printBorder(lengths[i], false);
		}
		row += "\n";
		
		return row;
	}
}