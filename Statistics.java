import java.util.*;
import java.text.DecimalFormat;

public class Statistics {
	
	public static int sum(int[] data) {
		int sum = 0;
		
		for(int i = 0; i < data.length; i++)
			sum += data[i];
		
		return sum;
	}
	
	public static HashMap<Integer, Integer> frequencies(int[] data) {
		HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < data.length; i++) {
			if(frequencies.containsKey(new Integer(data[i]))) {
				frequencies.put(data[i], new Integer(frequencies.get(data[i]) + 1));
			} else {
				frequencies.put(data[i], new Integer(1));
			}
		}
		
		return frequencies;
	}
	
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

		String table = "Frequency Table\n\n";
		table += String.format("%20s", "x_i");
		table += String.format("%20s", "n_i");
		table += String.format("%20s", "N_i");
		table += String.format("%20s", "f_i");
		table += String.format("%20s", "F_i");
		table += "\n\n";

		DecimalFormat formatter = new DecimalFormat("###,###");
		
		for(int i = 0; i < distinctValuesCount; i++){

			table += String.format("%20s", formatter.format(x_i[i]))
				+ String.format("%20s", formatter.format(n_i[i]))
				+ String.format("%20s", formatter.format(N_i[i]))
				+ String.format("%20s", f_i[i])
				+ String.format("%20s", F_i[i])
				+ "\n";
		}

		return table;
	}
	
	public static double mean(int[] data) {
		double mean = 0.0;
		
		mean = (double)sum(data) / data.length;
		return mean;
	}
	
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
	
	public static int mode(int[] data) {
		int mode = 0;
		
		HashMap<Integer, Integer> values = frequencies(data);
		Set mapSet = values.entrySet();
		Iterator iterator = mapSet.iterator();
		while(iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry)iterator.next();
			if((Integer)mapEntry.getValue() > mode)
				mode = (Integer)mapEntry.getKey();
		}
		
		return mode;
	}
	
	public static double variance(int[] data) {
		double variance = 0.0;
		int value;
		double mean = mean(data);
		
		HashMap<Integer, Integer> frequencies = frequencies(data);
		Set mapSet = frequencies.entrySet();
		Iterator iterator = mapSet.iterator();
		while(iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry)iterator.next();
			value = (Integer)mapEntry.getKey();
			variance += (Integer)mapEntry.getValue() * (value - mean) * (value - mean);
		}
		
		variance = variance / (data.length - 1);
		return variance;
	}
	
	public static double stdDev(int[] data) {
		double stdDev = 0.0;
		
		stdDev = Mathematics.sqrt(variance(data));
		
		return stdDev;
	}
}