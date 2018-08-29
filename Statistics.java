import java.util.*;

public class Statistics {
	public static int sum(int[] data) {
		int sum = 0;
		
		for(int i = 0; i < data.length; i++)
			sum += data[i];
		
		return sum;
	}
	
	public static double sqrt(double n) {
		double root = n / 2.0;	//	Initial root guess
		
		if(n == 0.0)
			return 0.0;
		if(n < 0.0) {
			System.out.println("Negative numbers not allowed");
			return 0.0;
		}
		
		do {
			root = (root + n / root) / 2.0;	//	Newton's root iteration
		} while(root * root - n >= 0.0001);
		
		return root;
	}
	
	public static HashMap<Integer, Integer> frequencies(int[] data) {
		HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < data.length; i++) {
			if(frequencies.containsKey(new Integer(data[i]))) {
//				System.out.println(data[i] + " already in collection");
				frequencies.put(data[i], new Integer(frequencies.get(data[i]) + 1));
			} else {
//				System.out.println(data[i] + " not in collection");
				frequencies.put(data[i], new Integer(1));
			}
		}
		
		//	Print map entries
//		Set mapSet = frequencies.entrySet();
//		Iterator iterator = mapSet.iterator();
//		while(iterator.hasNext()) {
//			Map.Entry mapEntry = (Map.Entry)iterator.next();
//			System.out.println(mapEntry.getKey() + ": " + mapEntry.getValue());
//		}
		
		return frequencies;
	}
	
	public static String tableAsString(int[] data) {
		String table = "";
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
		
		stdDev = sqrt(variance(data));
		
		return stdDev;
	}
}