public class Etudiant extends Personne{
 private String niveau;
 public Etudiant(String n, String p, Date na, String lvl)
 {
	 nom=n;
	prenom=p;
	naiss=na;
	 niveau=lvl;
 }
 public String toString()
	{
		return ""+nom+" "+prenom+" "+naiss.toString()+" "+niveau;
	}
}
