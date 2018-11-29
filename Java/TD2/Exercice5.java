public class Exercice5 {
	public Exercice5()
	{
		
	}
	public void smth()
	{
		Afficheur test=new Afficheur("Something");
		for (int i = 0; i < 20; i++) {
			 test.top();
			 System.out.println("<<" + test + ">>");
			 }
		Latence lat=new Latence(10,3);
		lat.setMessage("ABC");
		for (int i = 0; i < 10; i++) {
			 lat.top();
			 System.out.println("<<" + lat + ">>");
			 }
		Vitesse vest=new Vitesse(10,3,2);
		vest.setMessage("Mamba mamba");
		for (int i = 0; i < 10; i++) {
			 vest.top();
			 System.out.println("<<" + vest + ">>");
			 }
	}
}
