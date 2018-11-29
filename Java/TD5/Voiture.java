import java.util.Random;

public class Voiture {
	private String marque, modele;
	private int production, prix;
	public Voiture(String m1, String m2, int p1, int p2)
	{
		marque=m1;
		modele=m2;
		production=p1;
		prix=p2;
	}
	public String getmarque()
	{
		return marque;
	}
	public String getmodele()
	{
		return modele;
	}
	public int getproduction()
	{
		return production;
	}
	public int getprix()
	{
		return prix;
	}
	public String toString()
	{
		return ""+marque+" "+modele+" "+production+" "+prix;
	}
}
