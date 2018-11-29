import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Glouton extends Glark {

	@Override
	public Case trouveDestination() {
		List<Case> list1= new ArrayList<Case>();
		if (caseCourante.top().blurf)list1.add(caseCourante.top());
		if (caseCourante.left().blurf)list1.add(caseCourante.left());
		if (caseCourante.right().blurf)list1.add(caseCourante.right());
		if (caseCourante.down().blurf)list1.add(caseCourante.down());
	
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
	public String toString()
	{
		return "G";
	}
	@Override
	public boolean estBorne()
	{
		return false;
	}
}
