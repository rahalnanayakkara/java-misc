package objects;

import number.Factors;
import number.ZeroValueException;
import io.Keyboard;
import io.Screen;

public class Fraction 
{
	private int n, d;
	
	public Fraction(int n,int d)
	{
		this.n=n;
		if (d==0) throw new ZeroValueException();
		this.d=d;
	}
	
	public Fraction()
	{
		this(1,1);
	}
	
	public Fraction(int a)
	{
		this(a,1);
	}
	
	public Fraction(String prompt)
	{
		input(prompt);
	}
	
	public void input(String prompt)
	{
		loop : while(true)
		{
			try
			{
				String f = Keyboard.readString(prompt);
				int div = f.indexOf('/');
				n = Integer.parseInt(f.substring(0,div));
				int d = Integer.parseInt(f.substring(div+1));
				if (d==0) throw new ZeroValueException();
				this.d=d;
				break loop;
			}
			catch(Exception e)
			{
				Screen.show("Enter Fraction in the form", "numerator / denominator");
			}
		}
	}
	
	public void set(int n, int d)
	{
		this.n=n;
		this.d=d;
	}
	
	public String toString()
	{
		return n+"/"+d;
	}
	
	public void show(String message)
	{
		Screen.show(message, toString());
	}
	
	public int getDenominator()
	{
		return d;
	}
	
	public int getNumerator()
	{
		return n;
	}
	
	public void setNumerator(int n)
	{
		this.n=n;
	}
	
	public void setDenominator(int d)
	{
		if (d!=0) this.d=d;
		else throw new ZeroValueException();
	}
	
	public void simplify()
	{
		int gcd = Factors.gcd(n,d);
		set(n/gcd,d/gcd);
	}
	
	public void simplify(int n, int d)
	{
		int gcd = Factors.gcd(n,d);
		set(n/gcd,d/gcd);
	}
	
	public int intPart()
	{
		return n/d;
	}
	
	public Fraction fractionPart()
	{
		return new Fraction(n%d,d);
	}
	
	public double value()
	{
		return n*1.0/d;
	}
	
	////////////////////////////////////////ADDITION/////////////////////////////////////////////////////
	
	public void add(Fraction a, Fraction b)
	{
		int an = a.getNumerator(), bn = b.getNumerator(), ad=a.getDenominator(), bd = b.getDenominator();
		simplify(an*bd+bn*ad,bd*ad);
	}
	
	public void add(Fraction a)
	{
		add(a,this);
	}
	
	public void add(Fraction a, int n)
	{
		add(a, new Fraction(n));
	}
	
	public void add(int n)
	{
		add(this,n);
	}
	
	///////////////////////////////////MULTIPLY//////////////////////////////////////////////////////////
	
	public void multiply(Fraction a, Fraction b)
	{
		int an = a.getNumerator(), bn = b.getNumerator(), ad=a.getDenominator(), bd = b.getDenominator();
		simplify(an*bn,ad*bd);
	}
	
	public void multiply(Fraction a)
	{
		multiply(a,this);
	}
	
	public void multiply(Fraction a, int n)
	{
		multiply(a, new Fraction(n));
	}
	
	public void multiply(int n)
	{
		multiply(this,n);
	}
	
	/////////////////////////////////////////SUBSTRACT////////////////////////////////////////////////////
	
	public void subtract(Fraction a, Fraction b)
	{ 
		b.multiply(-1);
		add(a,b);
	}
}
