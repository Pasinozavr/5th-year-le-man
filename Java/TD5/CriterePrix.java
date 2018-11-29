public class CriterePrix implements Critere{
	 private int i;
	 public CriterePrix(int annee) {
	  i = annee;
	 }
	 public CriterePrix() {
	  i=1999;
	 }
	public boolean correspond(Voiture v)
	{
		return v.getprix()<=i;
	}
}
