package Exercice1;
import java.util.Random;

public class Valeur implements Comparable<Valeur>{
	private Random random = new Random();
	private int chiffre;
	public Valeur()
	{
		chiffre=random.nextInt(4)+1;
	}
	public int toInt()
	{
		return chiffre;
	}
	@Override
	public int compareTo(final Valeur other) {
	    return Integer.compare(this.chiffre, other.chiffre);
	}
}
