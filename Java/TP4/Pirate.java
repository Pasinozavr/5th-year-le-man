import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Pirate extends Glark{
	@Override
	public String toString()
{
	return "P";
}
	@Override
	public void devoreBluf()
	{

	}
	@Override
	public void devoreGlark(Glark g)
	{
		System.out.println(this.toString()+" met "+g.toString());
		energie+=10;
		g.isEaten();
		Jeu.listeDesGlarks.stream().filter(v -> !v.alive).forEach(Jeu.listeDesGlarks::remove);
	}
	@Override
	public Case trouveDestination() {
		List<Case> list1= new ArrayList<Case>();
		
		if (caseCourante.top().glark)list1.add(caseCourante.top());
		if (caseCourante.left().glark)list1.add(caseCourante.left());
		if (caseCourante.right().glark)list1.add(caseCourante.right());
		if (caseCourante.down().glark)list1.add(caseCourante.down());
	
		if(!list1.isEmpty())return list1.get(new Random().nextInt(list1.size()));
		
		if(list1.isEmpty())
		{
				if (!caseCourante.top().obstacle)list1.add(caseCourante.top());
				if (!caseCourante.left().obstacle)list1.add(caseCourante.left());
				if (!caseCourante.right().obstacle)list1.add(caseCourante.right());
				if (!caseCourante.down().obstacle)list1.add(caseCourante.down());
		}
		if(!list1.isEmpty())return list1.get(new Random().nextInt(list1.size()));
		else return caseCourante;
	}
	@Override
	public boolean estBorne()
	{
		return false;
	}
}
