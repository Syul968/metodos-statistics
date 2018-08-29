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