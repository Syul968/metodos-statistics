public class TablePrinter {
	public static final int ALIGN_LEFT = -1;
	public static final int ALIGN_CENTER = 0;
	public static final int ALIGN_RIGHT = 1;
	
	public static void printBorder(int cellLength) {
		System.out.print("+");
		for(int i = 0; i <= cellLength; i++) {
			System.out.print("-");
		}
		System.out.print("+");
	}
	
	public static void extendBorder(int cellLength) {
		for(int i = 0; i <= cellLength; i++) {
			System.out.print("-");
		}
		System.out.print("+");
	}
	
	public static void printRow(int[] lengths, String[] tags, int alignment) {
		printBorder(lengths[0]);
		for(int i = 1; i < lengths.length; i++) {
			extendBorder(lengths[i]);
		}
		System.out.print("\n");
		
		System.out.print("|");
		for(int i = 0; i < lengths.length; i++) {
			switch(alignment) {
				case ALIGN_LEFT:
					System.out.print(tags[i]);
					for(int j = 0; j <= lengths[i] - tags[i].length(); j++) {
						System.out.print(" ");
					}
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
					for(int j = 0; j <= lengths[i] - tags[i].length(); j++) {
						System.out.print(" ");
					}
					System.out.print(tags[i]);
					break;
			}
			System.out.print("|");
		}
		System.out.print("\n");
		
		printBorder(lengths[0]);
		for(int i = 1; i < lengths.length; i++) {
			extendBorder(lengths[i]);
		}
		System.out.print("\n");
	}
	
	public static void addRow(int[] lengths, String[] tags, int alignment) {
		System.out.print("|");
		for(int i = 0; i < lengths.length; i++) {
			switch(alignment) {
				case ALIGN_LEFT:
					System.out.print(tags[i]);
					for(int j = 0; j <= lengths[i] - tags[i].length(); j++) {
						System.out.print(" ");
					}
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
					for(int j = 0; j <= lengths[i] - tags[i].length(); j++) {
						System.out.print(" ");
					}
					System.out.print(tags[i]);
					break;
			}
			System.out.print("|");
		}
		System.out.print("\n");
		
		printBorder(lengths[0]);
		for(int i = 1; i < lengths.length; i++) {
			extendBorder(lengths[i]);
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		int[] cellLengths = {10, 10, 10, 10, 10};
		String[] tags = {"xa", "n", "N", "f", "F"};
		printRow(cellLengths, tags, TablePrinter.ALIGN_CENTER);
		addRow(cellLengths, tags, TablePrinter.ALIGN_RIGHT);
	}
}