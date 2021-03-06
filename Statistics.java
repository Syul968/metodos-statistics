import java.util.*;
import java.text.DecimalFormat;
import java.util.stream.*;

/**
 * Statistics
 * This class builds the frequency table for a data set and
 * finds the basic statistical measures: mean, median,
 * mode, variance and standard deviation.
 * 
 * @version 08/28/2018
 * @author Salvador Orozco Villalever - A07104218
 * @author Luis Francisco Flores Romero - A01328937
 */

public class Statistics {
	/**
	 *  Counts absolute frequency for
	 *	each distinct data value.
	 *  
	 *	@param	data	Data set.
	 *  @return			Absolute frequency table as HashMap.
	 */
	public static HashMap<Integer, Integer> frequencies(int[] data) {
		//	Map data values to frequencies count
		HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < data.length; i++) {
			if(frequencies.containsKey(new Integer(data[i]))) {
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
	 *  Builds a string representation of the frequencies
	 *	table (x_i, n_i, N_i, f_i, F_i) for provided
	 *	data set.
	 *  
	 *	@param	data	Data set.
	 *  @return			The frequencies table as a String.
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
		for(HashMap.Entry<Integer, Integer> entry : frequencies.entrySet()) {
			
			x_i[currIndex] = entry.getKey();
			n_i[currIndex] = entry.getValue();
			N_i[currIndex] = n_i[currIndex] + (currIndex > 0 ? N_i[currIndex - 1] : 0);
			f_i[currIndex] = new Fraction(n_i[currIndex], data.length);
			F_i[currIndex] = new Fraction(f_i[currIndex]);

			if(currIndex > 0){
				
				F_i[currIndex] = F_i[currIndex].add(F_i[currIndex - 1]);
			}

			currIndex++;
		}
		
		//	Define table format and content
		int[] lengths = {20, 20, 20, 20, 20};
		String[] headers = {"x_i", "n_i", "N_i", "f_i", "F_i"};
		String[] rowTags = new String[5];
		
		String table = "Frequency Table\n\n";
		table += TablePrinter.printRow(lengths, headers, TablePrinter.ALIGN_CENTER, true);

		DecimalFormat formatter = new DecimalFormat("###,###");
		
		for(int i = 0; i < distinctValuesCount; i++){
			
			//	Set row tags
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
	 *	Computes data set mean.
	 *
	 *	@param	data	Data set.
	 *	@return			Mean value.
	 */
	public static double mean(int[] data) {
		return (double)IntStream.of(data).sum() / data.length;
	}
	
	/**
	 *	Computes median value in data set.
	 *
	 *	@param	data	Data set.
	 *	@return			Median value.
	 */
	public static double median(int[] data) {
		double median = 0.0;
		
		Arrays.sort(data);
		int middle = data.length / 2;
		
		median += data[middle];
		if(data.length % 2 == 0) {
			median += data[middle - 1];
			median = median / 2.0;
		}
		
		return median;
	}
	
	/**
	 *	Finds first mode in data set.
	 *
	 *	@param	data	Data set.
	 *	@return			Smallest mode value.
	 */
	public static int mode(int[] data) {
		int mode = data[0];
		int modeValue = 0;
		
		for(HashMap.Entry<Integer, Integer> entry : frequencies(data).entrySet()) {
			if(entry.getValue() > modeValue) {
				mode = entry.getKey();
				modeValue = entry.getValue();
			}
		}
		
		return mode;
	}
	
	/**
	 *	Computes variance for sample population.
	 *	Please note min data set size is 2.
	 *
	 *	@param	data	Data set, min size 2.
	 *	@return			Variance value.
	 */
	public static double variance(int[] data) {
		double variance = 0.0;
		int value;
		double mean = mean(data);
		
		for(HashMap.Entry<Integer, Integer> entry : frequencies(data).entrySet()) {
			value = entry.getKey();
			variance += entry.getValue() * (value - mean) * (value - mean);
		}
		
		variance = variance / (data.length - 1);
		return variance;
	}
	
	/**
	 *	Computes standard deviation as variance square root.
	 *
	 *	@param	data	Data set, min size 2.
	 *	@return			Standard deviation value.
	 */
	public static double stdDev(int[] data) {
		return Mathematics.sqrt(variance(data));
	}
}