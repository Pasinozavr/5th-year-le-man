import java.util.Random;
public class Joueur {
private int points, strategie, position, dureeStrategie, anotherposition[]=new int[5];
private Random rnd;
public void setstrategie(int strat)
{
	System.out.println("Strategie for player#"+position+" changed from "+strategie+" to "+strat);
	strategie=strat;
}
public Joueur(int pos, Random rnd)
{
	this.rnd=rnd;
	points=0;
	strategie=0;
	position=pos;
	dureeStrategie=0;
}
public int dureeStrategie()
{
	if(dureeStrategie==0)dureeStrategie=2+rnd.nextInt(3);
	dureeStrategie--;
	return dureeStrategie;
}
public boolean gagnant()
{
	return points>=500;
}
public int position()
{
	return position;
}
public int points()
{
	return points;
}
public int getstrategie()
{
	return strategie;
}
public void deplace(int what)
{
	int temp=0;
	switch(strategie)
	{
	case 1:
		temp=new Random().nextInt(6)+(anotherposition[1]-position)/2;
		if(temp==1 && temp==2)temp*=-1;
	break;
	case 2:
		temp=new Random().nextInt(6);
		if(temp%2==0)temp*=3;
	case 3:
		temp=new Random().nextInt(6)+(anotherposition[4]-position)/2;
		if(temp==1 && temp==2)temp*=-1;
	}
	points+=temp;
}
public void setanotherposition(int i, int j)
{
	anotherposition[1]=i;
	anotherposition[4]=j;
}
public void turn(int anotherposition)
{
	
}
}