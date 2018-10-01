import java.util.*;

/**
	Main
	This class makes use of pseudorandom numbers set
	to display the central tendency and spread measures
	of a LCG.
	
	@author		A07104218	Salvador Orozco Villalever
	@author		A01328937	Luis Francisco Flores Romero
	@version	1.0
	@since		25.sep.2018
*/
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int seed = in.nextInt();
		int a = in.nextInt();
		int c = in.nextInt();
		int mod = in.nextInt();
		int n = in.nextInt();
		
		RandomSet set = new RandomSet(seed, a, c, mod);
		System.out.println("TAIL " + "(" + set.getTailLength() + ")");
		int[] tail = set.getTail();
		for(int num : tail)
			System.out.print(num + " ");
		System.out.println();
			
		System.out.println("PERIOD " + "(" + set.getPeriodLength() + ")");
		int[] period = set.getPeriod();
		for(int num : period)
			System.out.print(num + " ");
		System.out.println();
		
		System.out.println("CYCLE " + "(" + set.getCycleLength() + ")");
		int[] cycle = set.getCycle();
		for(int num : cycle)
			System.out.print(num + " ");
		System.out.println();
		
		System.out.println("Numbers (" + n + ")");
		int[] nums = set.generate(n);
		for(int num : nums)
			System.out.print(num + " ");
		System.out.println();
		
		Statistics.displayCentralTendencyAndSpreadMeasures(nums);
		double[] rangesArr = {0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
		Statistics.displayRangeCountPercentage(rangesArr, Statistics.computeNormalizedSequence(nums, set.genMod()));
	}
}