
public class main {

	public static void main(String[] args) {
		ArbreBinaireRecherche<Integer> myarb=new ArbreBinaireRecherche<Integer>(7, 
				new ArbreBinaireRecherche<Integer>(3, 
						new ArbreBinaireRecherche<Integer>(1), 
						new ArbreBinaireRecherche<Integer>(6,
								new ArbreBinaireRecherche<Integer>(4), null)), 
				new ArbreBinaireRecherche<Integer>(10,null,
						new ArbreBinaireRecherche<Integer>(14, 
								new ArbreBinaireRecherche<Integer>(13), null)));
		myarb.addel(25);
		myarb.showtree();
		ArbreBinaireRecherche<String> myarb2=new ArbreBinaireRecherche<String>("lol");
		myarb2.addel("wow");
		myarb2.addel("greatings");
		myarb2.addel("tree");
		myarb2.showtree();
		
		ArbreBinaireRecherche<Personne> arblast=new ArbreBinaireRecherche<Personne>(new Enseignant("Ens", "#1", new Date(2,2,1985), "Prof"));
		arblast.addel(new Enseignant("Ens", "#2", new Date(2,2,1975), "Prof2"));
		arblast.addel(new Etudiant("Et", "#1", new Date(2,2,1995), "Middle2"));
		arblast.addel(new Etudiant("Et", "#2", new Date(2,2,2005), "Nouveau"));
		arblast.showtree();
	}
	
	
}
