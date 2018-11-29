public class main {

	public static void main(String[] args) {
		Vehicule v;
		v = new Vehicule();
		v.description();
		v = new Voiture();
		v.description();
		v.klaxoner();
		v = new Bus();
		v.description();
		v.klaxoner();
		v = new Fiat();
		v.description();

	}

}
