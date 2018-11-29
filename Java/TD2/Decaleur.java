public class Decaleur {
private char [] L;
public Decaleur(int length)
{
	L=new char[length];
	for(int i=0; i<length;i++)
	{
		L[i]=' ';
	}
}
public Decaleur(String str)
{
	L=new char[str.length()];
	for(int i=0; i<L.length;i++)
	{
		L[i]=str.charAt(i);
	}
}
public int getWidth()
{
	return L.length;
}
public String raz()
{
	for(int i=0; i<L.length;i++)
	{
		L[i]=' ';
	}
	return this.toString();
}
public char decale()
{
	char temp=L[0];
	for(int y=0;y<L.length-1;y++)
	{
		L[y]=L[y+1];
	}
	L[L.length-1]=temp;
	return L[L.length-1];
}
public void ajouter(char ch)
{
	int y=0;
	for(;y<L.length-1;y++)
	{
		L[y]=L[y+1];
	}
	L[y]=ch;
}
public String toString()
{
	String str="";
	for(int i=0;i<L.length;i++)str+=L[i];
	return str;
} 

}
