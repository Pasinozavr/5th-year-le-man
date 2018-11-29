public class Latence{
	private Decaleur myText;
	private String ontop, copy;
	private int fridge, now, decay, decaynow;
	public Latence(int frid, int dec)
	{
		fridge=frid;
		myText=new Decaleur(fridge);
		decay=dec;
		decaynow=0;
	}
	public void top()
	{	
		now++;
		if(now==fridge)
		{
			now=0;
		}
		if(ontop.length()==0)
			{
			ontop=copy;
			if(decaynow!=decay) { myText.ajouter(' '); decaynow++; }
			else decaynow=0;
			}
		
		myText.ajouter(ontop.charAt(0));
		String temp="";
		for(int i=1;i<ontop.length();i++)temp+=ontop.charAt(i);
		ontop=temp;
		
		//ontop=temp;
	}
	public String toString()
	{
		return myText.toString();
	}
	public void setMessage(String message)
	{
		now=0;
		myText=new Decaleur(fridge);
		ontop=message;
		copy=ontop;
	}

}
