public class Fraction{

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator){

        assert denominator != 0: "The denominator of the fraction is zero";

        int gcd = Mathematics.computeGCD(numerator, denominator);
        setNumerator(numerator/gcd);
        setDenominator(denominator/gcd);
    }

    public Fraction(Fraction f){

        setNumerator(f.getNumerator());
        setDenominator(f.getDenominator());
    }

    public void setNumerator(int numerator){

        this.numerator = numerator;
    }

    public int getNumerator(){

        return numerator;
    }

    public void setDenominator(int denominator){

        this.denominator = denominator;
    }

    public int getDenominator(){

        return denominator;
    }

    public String toString(){

        if(this.getNumerator() == this.getDenominator())
            return Integer.toString(this.getNumerator());

        return this.getNumerator() + "/" + denominator;
    }

    public Fraction add(Fraction f2){

        int num1 = this.getNumerator();
        int den1 = this.getDenominator();
        int num2 = f2.getNumerator();
        int den2 = f2.getDenominator();

        return new Fraction(num1*den2 + num2*den1, den1*den2);
    }
}