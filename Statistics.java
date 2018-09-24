import java.util.*;
import java.text.DecimalFormat;
import java.util.stream.*;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 * Statistics This class builds the frequency table for a data set and finds the
 * basic statistical measures: mean, median, mode, variance and standard
 * deviation.
 * 
 * @version 08/28/2018
 * @author Salvador Orozco Villalever - A07104218
 * @author Luis Francisco Flores Romero - A01328937
 */

public class Statistics {
	/**
	 * Counts absolute frequency for each distinct data value.
	 * 
	 * @param data Data set.
	 * @return Absolute frequency table as HashMap.
	 */
	public static HashMap<Integer, Integer> frequencies(int[] data) {
		// Map data values to frequencies count
		HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();

		for (int i = 0; i < data.length; i++) {
			if (frequencies.containsKey(new Integer(data[i]))) {
				// if value is in table, increment count
				frequencies.put(data[i], new Integer(frequencies.get(data[i]) + 1));
			} else {
				// add value to table with count 1
				frequencies.put(data[i], new Integer(1));
			}
		}

		return frequencies;
	}

	/**
	 * Builds a string representation of the frequencies table (x_i, n_i, N_i, f_i,
	 * F_i) for provided data set.
	 * 
	 * @param data Data set.
	 * @return The frequencies table as a String.
	 */
	public static String tableAsString(int[] data) {

		HashMap<Integer, Integer> frequencies = frequencies(data);
		int distinctValuesCount = frequencies.size();

		int[] x_i = new int[distinctValuesCount];
		int[] n_i = new int[distinctValuesCount];
		int[] N_i = new int[distinctValuesCount];
		Fraction[] f_i = new Fraction[distinctValuesCount];
		Fraction[] F_i = new Fraction[distinctValuesCount];

		int currIndex = 0;
		for (HashMap.Entry<Integer, Integer> entry : frequencies.entrySet()) {

			x_i[currIndex] = entry.getKey();
			n_i[currIndex] = entry.getValue();
			N_i[currIndex] = n_i[currIndex] + (currIndex > 0 ? N_i[currIndex - 1] : 0);
			f_i[currIndex] = new Fraction(n_i[currIndex], data.length);
			F_i[currIndex] = new Fraction(f_i[currIndex]);

			if (currIndex > 0) {

				F_i[currIndex] = F_i[currIndex].add(F_i[currIndex - 1]);
			}

			currIndex++;
		}

		// Define table format and content
		int[] lengths = { 20, 20, 20, 20, 20 };
		String[] headers = { "x_i", "n_i", "N_i", "f_i", "F_i" };
		String[] rowTags = new String[5];

		String table = "Frequency Table\n\n";
		table += TablePrinter.printRow(lengths, headers, TablePrinter.ALIGN_CENTER, true);

		DecimalFormat formatter = new DecimalFormat("###,###");

		for (int i = 0; i < distinctValuesCount; i++) {

			// Set row tags
			rowTags[0] = formatter.format(x_i[i]);
			rowTags[1] = formatter.format(n_i[i]);
			rowTags[2] = formatter.format(N_i[i]);
			rowTags[3] = f_i[i].toString();
			rowTags[4] = F_i[i].toString();
			table += TablePrinter.printRow(lengths, rowTags, TablePrinter.ALIGN_RIGHT, false);

		}

		return table;
	}

	/**
	 * Computes data set mean.
	 *
	 * @param data Data set.
	 * @return Mean value.
	 */
	public static double mean(int[] data) {
		return (double) IntStream.of(data).sum() / data.length;
	}

	/**
	 * Computes median value in data set.
	 *
	 * @param data Data set.
	 * @return Median value.
	 */
	public static double median(int[] data) {
		double median = 0.0;

		Arrays.sort(data);
		int middle = data.length / 2;

		median += data[middle];
		if (data.length % 2 == 0) {
			median += data[middle - 1];
			median = median / 2.0;
		}

		return median;
	}

	/**
	 * Method that computes the count of the mode in a given array of integers
	 * 
	 * @param data the array of integers whose mode count is to be computed
	 * @return the count of the mode in the given array, i.e. the amount of 
	 * occurrences of the (or one of the) value(s) with the greatest occurrences 
	 * in the array.
	 */
	public static int getModeCount(int[] data) {

		int modeCount = 0;

		for (HashMap.Entry<Integer, Integer> entry : frequencies(data).entrySet()) {
			if (entry.getValue() > modeCount) {
				modeCount = entry.getValue();
			}
		}

		return modeCount;
	}

	/**
	 * Method that returns an ArrayList containing the
	 * mode values of a given array of integers. This method
	 * assumes that more than one mode value can exist.
	 *
	 * @param data Data set.
	 * @return an ArrayList containing the
	 * mode values of a given array of integers. 
	 */
	public static ArrayList<Integer> mode(int[] data) {

		ArrayList<Integer> modesList = new ArrayList<>();

		// First get the count of the mode values
		int modeCount = getModeCount(data);

		for (HashMap.Entry<Integer, Integer> entry : frequencies(data).entrySet()) {
			
			// If a given element has the same mode count,
			if (entry.getValue() == modeCount) {

				// add it to the mode values array list
				modesList.add(entry.getKey());
			}
		}

		return modesList;
	}

	/**
	 * Method that displays the mode of an array of integers. It takes into account that
	 * more than one mode can exist.
	 * 
	 * @param data The array of integers whose mode(s) is/are to be displayed.
	 */
	public static void displayMode(int[] data) {

		ArrayList<Integer> modeList = Statistics.mode(data);

		System.out.println();
		System.out.println("There are " + Integer.toString(modeList.size())
				+ " elements in the mode. The elements are the following:\n");

		for (Integer x : modeList) {

			System.out.println(x);
		}

		System.out.println();
	}

	/**
	 * Computes variance for sample population. Please note min data set size is 2.
	 *
	 * @param data Data set, min size 2.
	 * @return Variance value.
	 */
	public static double variance(int[] data) {
		double variance = 0.0;
		int value;
		double mean = mean(data);

		for (HashMap.Entry<Integer, Integer> entry : frequencies(data).entrySet()) {
			value = entry.getKey();
			variance += entry.getValue() * (value - mean) * (value - mean);
		}

		variance = variance / (data.length - 1);
		return variance;
	}

	/**
	 * Computes standard deviation as variance square root.
	 *
	 * @param data Data set, min size 2.
	 * @return Standard deviation value.
	 */
	public static double stdDev(int[] data) {
		return Mathematics.sqrt(variance(data));
	}

	/**
	 * Method that prints three central tendency measures (mean, median, mode)
	 * and two spread measures (variance and standard deviation).
	 * 
	 * @param data The array of integers whose central tendency and spread measures
	 * are to be displayed. 
	 */
	public static void displayCentralTendencyAndSpreadMeasures(int[] data) {

		System.out.println("\n#########################################\n");
		System.out.println("The measures of central tendency are the following:");
		System.out.printf("X- = %.4f\n", Statistics.mean(data));
		System.out.printf("X~ = %.4f\n", Statistics.median(data));
		Statistics.displayMode(data);
		System.out.printf("s2 = %.4f\n", Statistics.variance(data));
		System.out.printf("s  = %.4f\n", Statistics.stdDev(data));
		System.out.println("\n#########################################\n");
	}

	/**
	 * Method that counts the number of elements in an array of doubles that
	 * are in the range (minVal, maxVal], i.e. greater than minVal but lower
	 * than or equal to maxVal.
	 * 
	 * @param valuesArr The array of doubles whose count of numbers 
	 * in a given range we're looking for.
	 * @param minVal The minimum value in the desired range
	 * @param maxVal The maximum value in the desired range
	 * @return the count of the numbers in that range
	 */
	public static int computeCountInRange(double[] valuesArr, double minVal, double maxVal) {

		int count = 0;

		for (int i = 0; i < valuesArr.length; i++) {

			if (minVal < valuesArr[i] && valuesArr[i] <= maxVal)
				count++;
		}

		return count;
	}

	/**
	 * Method that returns a map that stores the count of elements in the valuesArr 
	 * that are within a given set of ranges. The map has the following schema:
	 *  
	 * 		<Pair<Double, Double>, Integer> -> <Pair<rangeMinValue, rangeMaxValue>, CountInRange>
	 * 
	 * @param rangesArr an array that stores boundary values. The set of ranges are pairs of 
	 * consecutive boundary values in this array.
	 * @param valuesArr The array of doubles in which we are looking for numbers in given ranges.
	 * @return The map storing the count of elements per given range.
	 */
	public static HashMap<Pair<Double, Double>, Integer> computeRangeCountMap(double[] rangesArr, double[] valuesArr) {

		HashMap<Pair<Double, Double>, Integer> rangeCountMap = new HashMap<>();

		for (int i = 0; i < rangesArr.length - 1; i++) {

			rangeCountMap.put(new Pair(rangesArr[i], rangesArr[i + 1]),
				computeCountInRange(valuesArr, rangesArr[i], rangesArr[i + 1]));
		}

		return rangeCountMap;
	}

	/**
	 * Method that normalizes an array of integers by replacing each element 0 <= x_i < mod
	 * by the quotient x_i/mod.
	 * 
	 * @param data The array of integers 0 <= x_i < mod
	 * @param mod The value used for the modulo operation to get the array of integers
	 * @return An array of doubles with the normalized values.
	 */
	public static double[] computeNormalizedSequence(int[] data, int mod){

		double[] normalizedData = new double[data.length];

		for(int i = 0; i < data.length; i++)
			normalizedData[i] = (1.0 * data[i])/mod;

		return normalizedData;
	}

	/**
	 * Method that displays the range count percentage of an array of doubles
	 * 
	 * @param rangesArr an array that stores boundary values. The set of ranges are pairs of 
	 * consecutive boundary values in this array.
	 * @param data The array of doubles from which the range count percentages are to be found.
	 */
	public static void displayRangeCountPercentage(double[] rangesArr, double[] data) {

		HashMap<Pair<Double, Double>, Integer> rangeCountMap = computeRangeCountMap(rangesArr, data);
		int sequenceLength = data.length;

		System.out.println("Percentage of normalized values in a given range:\n");

		// Iterate until the next-to-last element in the rangesArr array. That iteration
		// will use the next-to-last and the last elements in the rangesArr array.
		for (int i = 0; i < rangesArr.length - 1; i++) {

			// The left value of the interval is the current value in the rangesArr array.
			double intervalLeftValue = rangesArr[i];

			// The right value of the interval is the next value in the rangesArr array.
			double intervalRightValue = rangesArr[i + 1];
			
			// Retrieve from the map the count for the given range.
			int rangeCount = rangeCountMap.get(new Pair(rangesArr[i], rangesArr[i + 1]));

			double rangeCountPercentage = (100.0 * rangeCount) / sequenceLength;
			System.out.printf("x in (%.2f, %.2f] -> %.4f%%\n", intervalLeftValue, intervalRightValue,
					rangeCountPercentage);
		}
	}
}