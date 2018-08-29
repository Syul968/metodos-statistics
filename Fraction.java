
/**
 * Class representing a fraction
 * 
 * @version 08/28/2018
 * @author Luis Francisco Flores Romero - A01328937
 * @author Salvador Orozco Villalever - A07104218
 */

public class Fraction{

    // The numerator of the fraction
    private int numerator;
    // The denominator of the fraction
    private int denominator;

    /**
     *  Constructor that takes as a parameter two ints
     *
     *  @param numerator the numerator of the fraction
     *  @param denominator the denominator of the fraction
     */
    public Fraction(int numerator, int denominator){

        assert denominator != 0: "The denominator of the fraction is zero";

        // To reduce the fraction to its lowest terms, compute the
        // greatest common divisor between the numerator and the 
        // denominator. Then divide each term by their gcd.
        int gcd = Mathematics.computeGCD(numerator, denominator);
        setNumerator(numerator/gcd);
        setDenominator(denominator/gcd);
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
    public void setNumerator(int numerator){

        this.numerator = numerator;
    }

    /**
     * Getter for the numerator
     *
     * @return numerator the numerator of the fraction
     */
    public int getNumerator(){

        return numerator;
    }

    /**
     * Setter for the denominator
     *
     * @param denominator the denominator of the fraction
     */
    public void setDenominator(int denominator){

        this.denominator = denominator;
    }

    /**
     * Getter for the denominator
     *
     * @return denominator the denominator of the fraction
     */
    public int getDenominator(){

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
            return Integer.toString(this.getNumerator());

        string str = this.getNumerator() + "/" + denominator;
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

        int num1 = this.getNumerator();
        int den1 = this.getDenominator();
        int num2 = f2.getNumerator();
        int den2 = f2.getDenominator();

        return new Fraction(num1*den2 + num2*den1, den1*den2);
    }
}