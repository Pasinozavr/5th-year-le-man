public class Cercle extends ObjetGeometrique2D{
	private double r;
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
		r*=facteur;
	}
public void reduire(double facteur)
		{
			r/=facteur;
		}	
}

