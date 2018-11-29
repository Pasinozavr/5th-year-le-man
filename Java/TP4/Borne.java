import java.util.*;
public class Borne extends Glark {
	@Override
	public boolean estBorne()
	{
		return true;
	}
	@Override
	public void devoreGlark(Glark g)
	{
		System.out.println(this.toString()+" met "+g.toString());
		if(!g.estBorne())
			{
			energie+=10;g.isEaten();}
		Jeu.listeDesGlarks.stream().filter(v -> !v.alive).forEach(Jeu.listeDesGlarks::remove);
	}
	@Override
	public Case trouveDestination() {
		if (!caseCourante.top().obstacle)return caseCourante.top();
		else if (!caseCourante.left().obstacle)return caseCourante.left();
		else if (!caseCourante.right().obstacle)return caseCourante.right();
		else if (!caseCourante.down().obstacle)return caseCourante.down();
		else return caseCourante;
	}
	@Override
	public String toString()
	{
		return "B";
	}
}
