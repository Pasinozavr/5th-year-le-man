package Exercice1;
import java.util.Random;

public class Couleur {
	private Random random = new Random();
	private boolean coeur=false, carreau=false, trefle=false, pique=false;
	int toInt()
	{
		if(coeur)return 1;
		else if(carreau)return 2;
		else if(trefle)return 3;
		else return 4;
	}
	public Couleur()
	{
		int des=random.nextInt(4);
		if(des==1)coeur=true;
		else if(des==2)carreau=true;
		else if(des==3)trefle=true;
		else pique=true;
	}
	public String toString()
	{
		if(coeur)return "coeur";
		else if(carreau)return "carreau";
		else if(trefle)return "trefle";
		else return "pique";
	}
}
