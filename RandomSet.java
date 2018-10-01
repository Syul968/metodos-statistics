
/**
	Random Set
	This class defines a set of pseudorandom numbers
	from a Linear Congruential IntegerSequenceGenerator. The set is
	limited to the first pseudorandom numbers which
	constitute the period.
	
	@author		A07104218	Salvador Orozco Villalever
	@author		A01328937	Luis Francisco Flores Romero
	@version	1.0
	@since		09/21/2018
*/
import java.util.*;
import javafx.util.Pair;

public class RandomSet {
	private static IntegerSequenceGenerator gen;

	private int cycleStart;
	private int periodLength;
	private int cycleLength;
	private int tailLength;
	private ArrayList<Integer> numbers;

	private int xCurr;
	private int xIndex;

	/**
	 * Constructor This method initializes a Random Set to be filled with
	 * pseudorandom numbers from the generator. The set is filled with all
	 * pseudorandom numbers in the recurrence relation period for given parameters.
	 * 
	 * @param seed       Seed for the generator.
	 * @param multiplier Multiplier for the generator.
	 * @param increment  Increment for the generator.
	 * @param mod        Modulo for the generator.
	 */
	public RandomSet(int seed, int multiplier, int increment, int mod) {
		this.xIndex = 0;
		this.xCurr = seed;
		this.cycleStart = -1;
		this.periodLength = -1;
		this.cycleLength = -1;
		this.tailLength = -1;

		this.gen = new IntegerSequenceGenerator(seed, multiplier, increment, mod);

		this.numbers = new ArrayList<Integer>();
		save(); // Set first number in the set

		generatePeriod();
	}

	/**
	 * Get period length Returns the period length of this set.
	 * 
	 * @return Period length.
	 */
	public int getPeriodLength() {
		return this.periodLength;
	}

	/**
	 * Get cycle length Returns the cycle length of this set.
	 * 
	 * @return Cycle length.
	 */
	public int getCycleLength() {
		return this.cycleLength;
	}

	/**
	 * Get tail length Returns the tail length of this set.
	 * 
	 * @oaram Tail length.
	 */
	public int getTailLength() {
		return this.tailLength;
	}

	/**
	 * Save Pushes the current pseudorandom number into the set.
	 * 
	 * @return Nothing.
	 */
	private void save() {
		this.numbers.add(xIndex++, xCurr); // Keep index for next item updated
	}

	/**
	 * Generate period Generates all pseudorandom numbers in the set period and
	 * saves them.
	 * 
	 * @return Nothing.
	 */
	private void generatePeriod() {
		// Generate and push numbers into the set until one repeats
		while (!numbers.contains(this.gen.viewNext())) {
			xCurr = this.gen.next();
			save();
		}
		// Update lengths
		cycleStart = numbers.indexOf(this.gen.viewNext());
		periodLength = xIndex;
		cycleLength = periodLength - cycleStart;
		tailLength = periodLength - cycleLength;
	}

	/**
	 * Get period Returns the whole set as array.
	 * 
	 * @return Period as array.
	 */
	public int[] getPeriod() {
		return generate(xIndex);
	}

	/**
	 * Get cycle Returns the set cycle as array.
	 * 
	 * @return Cycle as array.
	 */
	public int[] getCycle() {
		int[] cycle = new int[cycleLength];
		for (int i = cycleStart; i < periodLength; i++) {
			cycle[i - cycleStart] = numbers.get(i);
		}

		return cycle;
	}

	/**
	 * Get tail Returns the set tail as array.
	 * 
	 * @return Tail as array.
	 */
	public int[] getTail() {
		int[] tail = new int[tailLength];
		for (int i = 0; i < tailLength; i++) {
			tail[i] = numbers.get(i);
		}

		return tail;
	}
	
	/**
		Generator modulo
		Returns the modulo used for generation of this set.
		@return		Modulo.
	*/
	public int genMod() {
		return this.gen.getMod();
	}
	
	/**
	 * To string Builds a string representation of the set.
	 * 
	 * @return Set as String.
	 */
	public String toString() {
		String res = "";

		res += "SEED: " + this.gen.getSeed() + "\n";
		res += "a: " + this.gen.getMultiplier() + "\n";
		res += "c: " + this.gen.getIncrement() + "\n";
		res += "mod: " + this.gen.getMod() + "\n";

		res += "\n";

		res += "Period length: " + periodLength + "\n";
		res += "Cycle length: " + cycleLength + "\n";
		res += "Tail length: " + tailLength + "\n";
		res += "Cycle starts at [" + cycleStart + ": " + numbers.get(cycleStart) + "]\n";

		res += "\n";
		res += "PERIOD\n";
		int[] period = getPeriod();
		for (Integer number : period) {
			res += number + " ";
		}

		res += "\n";
		res += "TAIL\n";
		int[] tail = getTail();
		for (Integer number : tail) {
			res += number + " ";
		}

		res += "\n";
		res += "CYCLE\n";
		int[] cycle = getCycle();
		for (Integer number : cycle) {
			res += number + " ";
		}

		return res;
	}

	/**
	 * Generate Generates first n elements of the recurrence relation.
	 * 
	 * @param n Numbers to be generated.
	 * @return First n pseudorandom numbers as array.
	 */
	public int[] generate(int n) {
		int[] res = new int[n];
		int top = n;

		if (n > periodLength) {
			top = periodLength;
		}

		// Fill with period first
		for (int i = 0; i < top; i++) {
			res[i] = numbers.get(i);
		}

		// Fill remaining places with numbers in the cycle
		// This avoids computation and altering generator's sate
		// by accessing elements already in memory
		for (int i = top; i < n; i++) {
			res[i] = numbers.get(((i - cycleStart) % cycleLength) + cycleStart);
		}

		return res;
	}

	/**
	 * Method that compares two sequences: one from the IntegerSequenceGenerator and another one from
	 * the RandomSet
	 * @param generatorSequence the integer sequence generated by the IntegerSequenceGenerator
	 * @param randomSetSequence the integer sequence generated by the RandomSet
	 */
	public static void compareSequences(int[] generatorSequence, int[] randomSetSequence) {

		// Traverse and look for mismatches between the arrays.
		for (int i = 0; i < generatorSequence.length; i++) {
			if (generatorSequence[i] != randomSetSequence[i]) {
				System.out.println("Failed at " + i + ": [" + generatorSequence[i] + ", " + randomSetSequence[i] + "]");
			}
		}

		System.out.println(Arrays.equals(generatorSequence, randomSetSequence));
		System.out.println();
	}

	/**
	 * Main For testing purposes.
	 * 
	 * @param args Input from input stream.
	 * @return Nothing.
	 */
	public static void main(String[] args) {

		RandomSet set = new RandomSet(7642, 867, 7727, 9419);
		System.out.println(set.toString());

		int[] generatorSequence = new IntegerSequenceGenerator(7642, 867, 7727, 9419).generate(8192);
		int[] randomSetSequence = set.generate(8192);

		compareSequences(generatorSequence, randomSetSequence);
		Statistics.displayCentralTendencyAndSpreadMeasures(randomSetSequence);
		double[] rangesArr = {0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
		Statistics.displayRangeCountPercentage(rangesArr, Statistics.computeNormalizedSequence(randomSetSequence, gen.getMod()));
	}
}