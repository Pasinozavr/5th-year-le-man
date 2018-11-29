public class Exercice2 {
	public class Mother
	{
	public void methode(Mother m){
		System.out.println("M");
	}
	}
	public class Daughter extends Mother
	{
	public void methode(Daughter d){
		System.out.println("D");
	}
	}
	public void smth()
	{
		Mother m1 = new Mother();
		Mother m2 = new Mother();
		Daughter d1 = new Daughter();
		Daughter d2 = new Daughter();
		m1.methode(m2);
		m1.methode(d1);
		d1.methode(m1);
		d1.methode(d2);
	}
}
