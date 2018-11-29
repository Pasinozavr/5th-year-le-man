package Exercice1;
import java.util.LinkedList;

public class Tas {
	private LinkedList<Carte> mylst=new LinkedList<Carte>();
	public Tas(int num)
	{
		for(int i=0;i<=num;i++)mylst.add(new Carte());
	}
	public Carte take()
	{
		return mylst.removeFirst();
	}
	public int length()
	{
		int c=0;
		for(Carte ca : mylst)c++;
		return c;
	}
	public void add(Carte nw)
	{
		mylst.addLast(nw);
	}
}
