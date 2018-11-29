import java.util.LinkedList;

public class Agence {
	private LinkedList<Voiture> mylst;
	public Agence()
	{
		mylst=new LinkedList<Voiture>();
	}
	public LinkedList<Voiture> Selectionne(Critere c)
	{
		LinkedList<Voiture> temp=new LinkedList<Voiture>();
		for(Voiture e : mylst)
		{
			if(c.correspond(e))temp.add(e);
		}
		return temp;
	}
	public void add(Voiture e)
	{
		mylst.addLast(e);
	}
	public void show()
	{
		int count=1;
		for(Voiture v : mylst)
		{
			System.out.println(count+" "+v.toString());
			count++;
		}
	}
}
