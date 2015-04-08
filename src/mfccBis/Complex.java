package mfccBis;


public class Complex 
{
	private final double re;
	private final double im;
	
	public Complex(double a, double b)
	{
		re = a;
		im = b;
	}
	
	public double re() {return re;}
	public double im() {return im;}
	
	public Complex plus(Complex z)
	{
		double reelle = this.re + z.re();
		double imaginaire = this.im + z.im();
		return new Complex(reelle, imaginaire);
	}
	
	public Complex moins(Complex z)
	{
		double reelle = this.re - z.re();
		double imaginaire = this.im - z.im();
		return new Complex(reelle, imaginaire);
	}
	
	public Complex fois(Complex z)
	{
		double reelle = (this.re)*(z.re()) - (this.im)*(z.im());
		double imaginaire = (this.re)*(z.im()) + (this.im)*(z.re());
		return new Complex(reelle, imaginaire);
	}
	 public Complex fois(double a)
	 {
		 return new Complex (a*re, a*im);
	 }
	 
	 public Complex conjugate()
	 {
		 return new Complex(re, -im);
	 }
	 
	 public double moduleCarre()
	 {
		 return re*re+im*im;
	 }
	 
	 public Complex inverse()
	 {
	        double module = re*re + im*im;
	        return new Complex(re/module, -im/module);
	 }
	
	 public Complex divide(Complex z)
	 {
		   return this.fois(z.inverse());
	 }
	 
	 public Complex divide(double a)
	 {
		 return this.fois(1/a);
	 }
	 
	 public double distancecar(Complex z)
	 {
		 double distance = (re-z.re())*(re-z.re()) + (im-z.im())*(im-z.im());
		 return distance;
	 }
	 
	 public Complex exp()
	 {
		 return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
	 }
}
