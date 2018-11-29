public class Enseignant extends Personne{
private String statut;
public Enseignant(String n, String p, Date na, String lvl)
{
	nom=n;
	prenom=p;
	naiss=na;
	statut=lvl;
}
public String toString()
	{
		return ""+nom+" "+prenom+" "+naiss.toString()+" "+statut;
	}
}
