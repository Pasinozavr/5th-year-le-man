public class Afficheur{
private Decaleur myText;
private String ontop, copy;
private int fridge, now;
public Afficheur(String message)
{
	now=0;
	fridge=5;
	myText=new Decaleur(fridge);
	ontop=message;
	copy=ontop;
}
public void top()
{	
	now++;
	if(now==fridge)
	{
		now=0;
	}
	if(ontop.length()==0)ontop=copy;
	myText.ajouter(ontop.charAt(0));
	String temp="";
	for(int i=1;i<ontop.length();i++)temp+=ontop.charAt(i);
	ontop=temp;
	
}
public String toString()
{
	return myText.toString();
}
public void setMessage(String message)
{
	now=0;
	fridge=5;
	myText=new Decaleur(fridge);
	ontop=message;
	copy=ontop;
}
}
