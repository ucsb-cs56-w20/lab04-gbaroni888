/** A class to represent a rational number
    with a numerator and denominator

    @author P. Conrad for CS56 F16

    */
package edu.ucsb.cs56.ratcalc.model;


public class Rational {

    private int num;
    private int denom;

    /** 
	greatest common divisor of a and b
	@param a first number
	@param b second number
	@return gcd of a and b
    */
    public static int gcd(int a, int b) {
	if (a==0)
	    return b;
	else if (b==0)
	    return a;
	else
	    return gcd(b%a, a);
    }
    
    public Rational() {
	this.num = 1;
	this.denom = 1;
    }

    public Rational(int num, int denom) {
	if (denom== 0) {
	    throw new IllegalArgumentException("denominator may not be zero");
	}
	this.num = num;
	this.denom = denom;
	if (num != 0) {
	    int gcd = Rational.gcd(num,denom);
	    this.num /= gcd;
	    this.denom /= gcd;
	}
    }

    public String toString() {
	if (denom == 1 || num == 0)
	    return "" + num;
	if (denom < 0){
		denom=-1*denom;
		num=-1*num;
	}
	return num + "/" + denom;
    }

    public int getNumerator() { return this.num; }
    public int getDenominator() { return this.denom; }

    public Rational times(Rational r) {
	return new Rational(this.num * r.num,
			    this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
	return new Rational(a.num * b.num,
			    a.denom * b.denom);
    }

    public static int lcm(int a, int b) {
	if((a<0&&b>0)||(a>0&&b<0))
		return -(a*b)/gcd(a,b);
	return (a*b)/gcd(a,b);
    }

    public Rational plus(Rational r){
    	if(r.num == 0){
		return this;
	}
	else if(this.num == 0){
		return r;
	}
    	r.num = r.num*this.denom;
	int denom = r.denom*this.denom;
	this.num = this.num*r.denom;
	int num = this.num +r.num;
	int g = gcd(num,denom);
	if(g!=1){
		num=num/g;
		denom=denom/g;
	}
	Rational r2 = new Rational(num, denom);
	return r2;
    }
    public static Rational sum(Rational a, Rational b){
	return a.plus(b);
    }

    public Rational minus(Rational r){
    	Rational temp = new Rational(-r.num, r.denom);
	return sum(this, temp);
    }
    public static Rational difference(Rational a, Rational b){
    	return a.minus(b);
    }

    public Rational reciprocalOf(){
	    if(this.num==0){
		    throw new ArithmeticException("numerator may not be zero");
	    }
	    Rational temp=new Rational(this.denom,this.num);
	    return temp;
    }

    public Rational dividedBy(Rational r){
    	Rational temp = r.reciprocalOf();
	temp = product(this, temp);
	return temp;
    }

    public static Rational quotient(Rational a, Rational b){
	    return a.dividedBy(b);
    }
    
    /** 
	For testing getters.  
	@param args unused
     */

    public static void main (String [] args) {
	Rational r = new Rational(5,7);
	System.out.println("r.getNumerator()=" + r.getNumerator());
	System.out.println("r.getDenominator()=" + r.getDenominator());
    }

    
}
