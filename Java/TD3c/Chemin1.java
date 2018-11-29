/*
 class Erreur extends Exception {
		protected int num;
		public Erreur(int n){
		num = n;
		}
		public String toString(){
		return "Exception Erreur " + num;
		}
		}
class Erreur_d extends Erreur {
		private int code;
		public Erreur_d(int n, int c){
		super(n);
		code = c;
		}
public String toString(){
		return "Exception Erreur_d " + num + " " + code;
		}
		}

public class Chemin1 {

	public static void main(String[] args) {
		try{
			//A isn't declared
			A a = new A(1);
			System.out.println("Apres la creation de a(1)");
			}catch(Erreur e){
			System.out.println(e);
			}
			System.out.println("Suite du main");
			try{
			A b = new A(1);
			System.out.println("Apres la creation de b(1)");
			}catch(Erreur_d e){
			System.out.println(e);
			}catch(Erreur e){
			System.out.println(e);
			}

	}

}
*/
