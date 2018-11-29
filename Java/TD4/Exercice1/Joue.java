package Exercice1;
import java.util.LinkedList;

public class Joue {
	public static LinkedList<Carte> cards=new LinkedList<Carte>();
	public static int turns=0, numcards=5;
	public static void turn(Tas t1, Tas t2)
	{
		boolean finished=false;
		do
		{
		turns++;
		Carte c1=t1.take(), c2=t2.take();
		System.out.println("turn "+turns+" "+t1.length()+"+"+t2.length()+"="+numcards*2+" == "+(+t1.length()+t2.length()));
		System.out.println("Players showed "+c1.toString()+" and "+c2.toString());
		int compres=c1.compareTo(c2);
		cards.add(c1);
		cards.add(c2);
		if(compres>0)
			{
			for(Carte c : cards)
			{
				t1.add(c);
			}
			cards.clear();
			System.out.println("first>second");
			finished=true;
			}
		else if(compres<0)
		{
			for(Carte c : cards)
			{
				t2.add(c);
			}
			cards.clear();
			finished=true;
			System.out.println("first<second");
		}
		else
		{
			System.out.println("first=second -> new turn");
		}
		
		if(t1.length()==0)
			{
			System.out.println("Player 1 won at the turn "+turns);
			break;
			}
		else if(t2.length()==0)
			{
			System.out.println("Player 2 won at the turn "+turns);
			break;
			}
		
		}while(!finished && t1.length()>0 && t2.length()>0);
	}
	public static void main(String[] args) {
		Tas t1=new Tas(numcards), t2=new Tas(numcards);
		int res;
		do
		{
			turn(t1,t2);
		}while(t1.length()>0 && t2.length()>0);

	}

}
