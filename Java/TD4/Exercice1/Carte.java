package Exercice1;
public class Carte  implements Comparable<Carte>{
	private Couleur mycoul;
	private Valeur myval;
	public Carte()
	{
		mycoul=new Couleur();
		myval=new Valeur();
	}
	
	@Override
	public int compareTo(final Carte other) {
	    return Integer.compare(this.myval.toInt(), other.myval.toInt());
	}
	public String toString()
	{
		return ""+myval.toInt()+" "+mycoul.toString();
	}
}
