public class RectangleSitue extends ObjetGeometrique2DSitue{
	private double a, b, X, Y;
	public RectangleSitue(double q, double w, double e, double r)
	{
		a=q;
		b=w;
		X=e;
		Y=r;
	}
	public void out()
	{
		System.out.println("a="+a+" b="+b+" X="+X+" Y="+Y);
	}
	public double perimetre()
	{
		return 2*a+2*b;
	}
	public double surface()
			{
		return a*b;
			}
	public void agrandir(double facteur)
	{
		try
		{
		if (facteur<=0) {
		    throw new IllegalZoomFactorException("The coef is unvalid", facteur);
		  } 
		}
		catch (IllegalZoomFactorException e) {
		   System.out.println("Coeff was unvalid so it changed to 1");
		   if (facteur<=0) facteur = 1;
		  }
		a*=facteur;
		b*=facteur;
	}
public void reduire(double facteur)
		{
	try
	{
	if (facteur<=0) {
	    throw new IllegalZoomFactorException("The coef is unvalid", facteur);
	  } 
	}
	catch (IllegalZoomFactorException e) {
	   System.out.println("Coeff was unvalid so it changed to 1");
	   if (facteur<=0) facteur = 1;
	  }
			a/=facteur;
			b/=facteur;
		}
public void deplacerHorizontalement(double dep)
{
	X+=dep;
}
public void deplacerVerticalement(double dep)
{
	Y+=dep;
}
}
