public class Case {
protected int i, j;
public boolean blurf=false;
public boolean glark=false;
public boolean obstacle=false;
public boolean noth=false;
public Glark g;
protected Damier monDamier;

public Case right()
{
	return monDamier.donneTaCase(i+1, j);
}
public Case left()
{
	return monDamier.donneTaCase(i-1, j);
}
public Case top()
{
	return monDamier.donneTaCase(i, j+1);
}
public Case down()
{
	return monDamier.donneTaCase(i, j-1);
}

public String toString()
{
	if (blurf) return "F";
	if(obstacle) return "#";
	if(glark) return g.toString();
	return "-";
}
public void isEaten()
{
	blurf=false;
}

public Case(int i, int j, Damier d)
{
	noth=true;
	this.i=i;
	this.j=j;
	monDamier=d;
}
public Case(int i, int j, char c, Damier d)
{
	if(c=='b')blurf=true;noth=false;
	if(c=='o')obstacle=true;noth=false;
	this.i=i;
	this.j=j;
	monDamier=d;
}
public Case(int i, int j, Glark g, Damier d)
{
	this.i=i;
	this.j=j;
	glark=true;noth=false;
	this.g=g;
	monDamier=d;
}

}
