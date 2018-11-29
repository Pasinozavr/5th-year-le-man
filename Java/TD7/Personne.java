public class Personne implements Comparable<Personne>{
	protected String nom, prenom;
	protected Date naiss;
	public Personne(String n, String p, Date na)
	{
		nom=n;
		prenom=p;
		naiss=na;
	}
	public Personne()
	{
		nom=prenom="";
		naiss=new Date();
	}
	public String getnom()
	{
		return nom;
	}
	public String getprenom()
	{
		return prenom;
	}
	
	@Override
	public int compareTo(final Personne other) {
		return (this.nom+this.prenom).compareTo(other.getnom()+other.getprenom());
	}


}
