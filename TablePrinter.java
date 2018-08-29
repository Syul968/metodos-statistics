public class TablePrinter {
	public static final int ALIGN_LEFT = -1;
	public static final int ALIGN_CENTER = 0;
	public static final int ALIGN_RIGHT = 1;
	
	public static void printBorder(int cellLength, boolean starts) {
		if(starts)
			System.out.print("+");
			
		for(int i = 0; i <= cellLength; i++) {
			System.out.print("-");
		}
		System.out.print("+");
	}
	
	public static void printRow(int[] lengths, String[] tags, int alignment, boolean top) {
		if(top) {
			printBorder(lengths[0], true);
			for(int i = 1; i < lengths.length; i++) {
				printBorder(lengths[i], false);
			}
			System.out.print("\n");
		}
		
		System.out.print("|");
		for(int i = 0; i < lengths.length; i++) {
			switch(alignment) {
				case ALIGN_LEFT:
					System.out.printf("%-" + lengths[i] + "s ", tags[i]);
					break;
				case ALIGN_CENTER:
					int offset = (lengths[i] - tags[i].length()) / 2;
					for(int j = 0; j <= offset; j++) {
						System.out.print(" ");
					}
					System.out.print(tags[i]);
					for(int j = 0; j < lengths[i] - tags[i].length() - offset; j++) {
						System.out.print(" ");
					}
					break;
				case ALIGN_RIGHT:
				default:
					System.out.printf(" %" + lengths[i] + "s", tags[i]);
					break;
			}
			System.out.print("|");
		}
		System.out.print("\n");
		
		printBorder(lengths[0], true);
		for(int i = 1; i < lengths.length; i++) {
			printBorder(lengths[i], false);
		}
		System.out.print("\n");
	}
}