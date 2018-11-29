public class CritereMarque implements Critere{
	 private String m;
	 public CritereMarque(String word) {
	  m = word;
	 }
	 public CritereMarque() {
	  m = "Lada";
	 }
	public boolean correspond(Voiture v)
	{
		return v.getmarque()==m;
	}
}
