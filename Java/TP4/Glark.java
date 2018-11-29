import java.util.*;
public abstract class Glark {
protected int energie=100;
protected Case caseCourante;
public boolean alive;
public Glark()
{
	caseCourante=null;
	energie=100;
	alive=true;
}
public void vasY()
{
	energie-=5;
}

public void devoreBluf()
{
	energie+=100;
}
public void devoreGlark(Glark g)
{
	System.out.println(this.toString()+" met "+g.toString());
}
public void isEaten()
{
	energie=0;
	System.out.println(this.toString()+" is eaten.");
}
public Case trouveDestination() {
	return caseCourante.top();
}
public boolean estBorne()
{
	return false;
}
public String toString()
{
	return "Ã";
}
public void move(Case from, Case to)
{
	if(energie>0)
	{
	if(to.glark)this.devoreGlark(to.g);
	if(to.blurf)
		{
		this.devoreBluf();
		to.blurf=false;
		}
	from.glark=false;
	to.glark=true;
	to.g=this;
	this.caseCourante=to;
	this.vasY();
	}
	else
	{
		alive=false;
	}
}
}
