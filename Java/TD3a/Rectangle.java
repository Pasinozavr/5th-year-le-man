public class Rectangle extends ObjetGeometrique2D{
	private double a, b;
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
		a*=facteur;
		b*=facteur;
	}
public void reduire(double facteur)
		{
			a/=facteur;
			b/=facteur;
		}
}
