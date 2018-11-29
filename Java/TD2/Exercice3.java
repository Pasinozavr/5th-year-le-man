	public class Exercice3 {
 class EtreHumain {
  private int age;
  public EtreHumain() {
   age = 0;
  }
  public int getAge() {
   return age;
  }
  public void vieillir() {
   age++;
  }
  public void vieillir(int n)
  {
	  age+=n;
  }
  public String toString()
  {
	  return "Se suis un etre humain age de? " + age + " annees";
  }
 }
 abstract class HommePolitique extends EtreHumain {
  public abstract String debattre();
  public String toString()
  {
	  return "Je suis un etre humain agé de? " + getAge() + " annees et je peux debattre " + debattre();
  }
 }
 abstract class Chanteur extends EtreHumain {
  public abstract String chanter();
  public String toString()
  {
  return "Je suis un etre humain agé de? " + getAge() + " annees et je peux chanter " + chanter();
  }
 }
 class Sarkozy extends HommePolitique {
  public String debattre() {
   return "Blablabla de droite";
  }
 }
 class Hollande extends HommePolitique {
  public String debattre() {
   return "Blablabla de gauche";
  }
 }
 class Sanseverino extends Chanteur {
  public String chanter() {
   return "La cigarette...";
  }
 }
 class Shakira extends Chanteur {
  public String chanter() {
   return "Beeeeee...";
  }
 }
public void smth()
{
	Sanseverino s1 = new Sanseverino();
	System.out.println(s1.chanter());
	s1.vieillir();
	
	//Shakira m1 = new EtreHumain();
	//There is class between Shakira and EtreHumain - Chanteur, it can be used
	//m1.vieillir() ;
	
	HommePolitique hp1 = new Sarkozy();
	//System.out.println(hp1.chanter());
	////chanter() isn't definied for objects of HommePolitique
	
	EtreHumain eh1 = new Sanseverino();
	eh1.vieillir(25);
	System.out.println(eh1.toString());
	//System.out.println(eh1.chanter());
	//chanter() isn't definied for objects of EtreHumain
	
	EtreHumain eh2 = new Sanseverino();
	System.out.println(((Chanteur)eh2).chanter());
	
	EtreHumain eh3;
	Chanteur c1;
	Shakira m2 = new Shakira();
	Sanseverino s2 = new Sanseverino();
	c1 = m2;
	System.out.println(c1.chanter());
	c1 = s2;
	System.out.println(c1.chanter());
	eh3 = c1;
	System.out.println(eh3.toString());
	//System.out.println(eh1.chanter());
	//chanter() isn't definied for objects of EtreHumain
}
}