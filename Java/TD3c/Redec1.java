class Erreur extends Exception {}
class Erreur1 extends Erreur {}
class Erreur2 extends Erreur {}
class A {
public A(int n) throws Erreur {
try {
if(n==1) throw new Erreur1();
if(n==2) throw new Erreur2();
if(n==3) throw new Erreur();
}
catch(Erreur1 e) {
System.out.println("** Exception Erreur1 dans constructeur A");
}
catch(Erreur e) {
System.out.println("** Exception Erreur dans constructeur A");
throw(e);
}
}
}
public class Redec1 {

	public static void main(String args[]) {
		int n;
		for(n=1;n<=3;n++) {
		try {
		A a = new A(n);
		}
		catch(Erreur1 e) {
		System.out.println("** Exception Erreur1 dans main");
		}
		catch(Erreur2 e) {
		System.out.println("** Exception Erreur2 dans main");
		}
		catch(Erreur e) {
		System.out.println("** Exception Erreur dans main");
		}
		System.out.println("--------------------------");
		}
		System.out.println("Fin main");
		}

}
