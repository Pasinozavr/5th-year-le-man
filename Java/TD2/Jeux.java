public class Jeux {
	private Joueur [] joueurs = new Joueur[5];
	private java.util.Random rnd = new java.util.Random();
	private int nombreDeJoueurs;
	public void affecteUneStrategieAleatoire (Joueur joueur) {
			joueur.setstrategie(1+rnd.nextInt(3));
	}
	public Jeux (int nombreJoueurs) {
		this.nombreDeJoueurs = nombreJoueurs;
		for (int i = 0; i < 50000; i++)
		rnd.nextDouble();
		
		joueurs[1] = new Joueur(1, rnd);
		joueurs[2] = new Joueur(2, rnd);
		joueurs[3] = new Joueur(3, rnd);
		joueurs[4] = new Joueur(4, rnd);

		for (int joueurNumero = 1; joueurNumero <= nombreJoueurs; joueurNumero++)
		affecteUneStrategieAleatoire(joueurs[joueurNumero]);
		}
	
		public void joue () {
		int joueurNumero = 0;
		do {
		joueurNumero++;
		if (joueurNumero > nombreDeJoueurs)	joueurNumero = 1;
		if (joueurs[joueurNumero].dureeStrategie() == 0)affecteUneStrategieAleatoire(joueurs[joueurNumero]);
		joueurs[joueurNumero].setanotherposition(joueurs[1].position(),joueurs[4].position());
		joueurs[joueurNumero].deplace(1 + rnd.nextInt(6));
		System.out.println ("Joueur " + joueurNumero +	" position: " + joueurs[joueurNumero].position() + " points: " +joueurs[joueurNumero].points());
		} while (!joueurs[joueurNumero].gagnant());
		System.out.println("\n\tJoueur " + joueurNumero +" gagne le jeu." );
		
}
}