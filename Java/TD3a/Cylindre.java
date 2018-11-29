public class Cylindre extends ObjetGeometrique3D{
	private double r, h;
public double surface()
{
	return 2*3.1415*r*h;
}
public double volume()
{
	return 3.1415*r*r*h;
}
public void agrandir(double facteur)
{
	r*=facteur;
	h*=facteur;
}
public void reduire(double facteur)
{
	r/=facteur;
	h/=facteur;
}
}
