public class CercleSitue extends ObjetGeometrique2DSitue{
private double r, X, Y;
public CercleSitue(double w, double e, double r)
{
	this.r=w;
	X=e;
	Y=r;
}
public void out()
{
	System.out.println("r="+r+" X="+X+" Y="+Y);
}
public double perimetre()
{
	return 3.1415*2*r;
}
public double surface()
		{
	return 3.1415*r*r;
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
	r*=facteur;
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
		r/=facteur;
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
