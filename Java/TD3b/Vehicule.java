public class Vehicule implements Avertir{
	protected int nbRoues;
	public void demarrer(){};
	public void rouler(){};
	public void arreter(){};
	public void klaxoner() {
		System.out.println("Pouet Pouet!");
	};
	public String categorie(){
	return "Je suis un vehicule";
	}
	//if categorie'll be private all transport will have categorie 'vehicule'
	public void description(){
	System.out.println(categorie() + " a " + nbRoues + " roues");
	}
}