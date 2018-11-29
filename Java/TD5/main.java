
public class main {

	public static void main(String[] args) {
		Agence myag=new Agence();
		myag.add(new Voiture("Timoleon", "A1", 2000, 150));
		myag.add(new Voiture("Timoleon", "A4", 1995, 80));
		myag.add(new Voiture("Graga", "B1", 2010, 250));
		myag.add(new Voiture("Graa", "A7", 2018, 60));
		myag.add(new Voiture("Timoleon", "A5", 2005, 98));
		myag.show();
		System.out.println("All cars with day prica <=100");
		
		for(Voiture v : myag.Selectionne(new CriterePrix(100)))
		{
			System.out.println(v.toString());
		}
		
		System.out.println("All Timoleon cars with day prica <=100");
		InterCritere mycr=new InterCritere();
		mycr.ajoute(new CriterePrix(100));
		mycr.ajoute(new CritereMarque("Timoleon"));
		
		for(Voiture v : myag.Selectionne(mycr))
		{
			System.out.println(v.toString());
		}
	}

}
