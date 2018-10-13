
/**
 * Class representing a fraction
 * 
 * @version 10/12/2018
 * @author Luis Francisco Flores Romero - A01328937
 * @author Salvador Orozco Villalever - A07104218
 */

public class Fraction implements Comparable<Fraction> {

    // The numerator of the fraction
    private long numerator;
    // The denominator of the fraction
    private long denominator;

    /**
     *  Constructor that takes as a parameter two longs
     *
     *  @param numerator the numerator of the fraction
     *  @param denominator the denominator of the fraction
     */
    public Fraction(long numerator, long denominator){

        assert denominator != 0: "The denominator of the fraction is zero";
        
        // To reduce the fraction to its lowest terms, compute the
        // greatest common divisor between the numerator and the 
        // denominator. Then divide each term by their gcd.
        long gcd = Mathematics.computeGCD(numerator, denominator);
        setNumerator(numerator/gcd);
        setDenominator(denominator/gcd);
        
        if(this.denominator < 0) {
            this.denominator *= -1;
            this.numerator *= -1;
        }
    }

    /**
     *  Copy-constructor 
     *
     *  @param f the fraction to copy
     */
    public Fraction(Fraction f){

        setNumerator(f.getNumerator());
        setDenominator(f.getDenominator());
    }

    /**
     * Setter for the numerator
     *
     * @param numerator the numerator of the fraction
     */
    public void setNumerator(long numerator){

        this.numerator = numerator;
    }

    /**
     * Getter for the numerator
     *
     * @return numerator the numerator of the fraction
     */
    public long getNumerator(){

        return numerator;
    }

    /**
     * Setter for the denominator
     *
     * @param denominator the denominator of the fraction
     */
    public void setDenominator(long denominator){

        this.denominator = denominator;
    }

    /**
     * Getter for the denominator
     *
     * @return denominator the denominator of the fraction
     */
    public long getDenominator(){

        return denominator;
    }

    /**
     * Method to print the fraction as a string
     *
     * @return str a string representing the fraction
     */
    public String toString(){

        // If the fraction is greater or equal to 1
        // just return the numerator
        if(this.getDenominator() ==  1)
            return String.format("%4d     ", this.getNumerator());

        String str = String.format("%4d", this.getNumerator()) + "/" + String.format("%-4d", denominator);
        return str;
    }

    /**
     *  Method to add two fractions
     *  
     *  @param f2 the fraction to add to this fraction
     *  @return a new fraction corresponding to the sum
     *  of the two fractions
     */
    public Fraction add(Fraction f2){

        long num1 = this.getNumerator();
        long den1 = this.getDenominator();
        long num2 = f2.getNumerator();
        long den2 = f2.getDenominator();

        return new Fraction(num1*den2 + num2*den1, den1*den2);
    }
    
    /**
     *  Method to substract one fraction from another
     *  
     *  @param f2 the fraction to be substracted.
     *  @return a new fraction corresponding to the result
     *  of the substraction.
     */
    public Fraction substract(Fraction f2){

        long num1 = this.getNumerator();
        long den1 = this.getDenominator();
        long num2 = f2.getNumerator();
        long den2 = f2.getDenominator();

        return new Fraction(num1*den2 - num2*den1, den1*den2);
    }
    
    /**
     *  Method to multiply two fractions
     *  
     *  @param f2 the fraction to mutliply this fraction by
     *  @return a new fraction corresponding to the product
     *  of the two fractions
     */
    public Fraction multiply(Fraction f2) {
        long num = this.getNumerator() * f2.getNumerator();
        long den = this.getDenominator() * f2.getDenominator();
        
        return new Fraction(num, den);
    }
    
    /**
        Compare to
        Compare this fraction to given fraction.
        @param    f2    Fraction to compare this fraction to.
        @return         -1 if this fraction is smaller, 0 if fractions are equal, 1 if this fraction is greater.
    */
    public int compareTo(Fraction f2) {
        double leftProduct = this.getNumerator() * f2.getDenominator();
        double rightProduct = f2.getNumerator() * this.getDenominator();
        
        if(leftProduct < rightProduct)
            return -1;
        else if(leftProduct == rightProduct)
            return 0;
        
        return 1;
    }
}