public class Sphere extends ObjetGeometrique3D{
	private double r;
public double surface()
{
	return 4*3.1415*r*r;
}
public double volume()
{
	return (4*3.1415*r*r*r)/3.;
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
