public class main {

	public static void main(String[] args) {
		RectangleSitue myrect=new RectangleSitue(10,20,50,50);
		myrect.out();
		myrect.agrandir(3);
		myrect.deplacerHorizontalement(100);
		myrect.out();
		myrect.reduire(-2);
		myrect.out();
		System.out.println("perimetre=" +myrect.perimetre()+" surface="+myrect.surface());
		
		CercleSitue mycerc=new CercleSitue(5, 50, 50);
		mycerc.out();
		mycerc.reduire(2);
		mycerc.deplacerVerticalement(100);
		mycerc.out();
		mycerc.agrandir(-2);
		mycerc.out();
		System.out.println("perimetre=" +myrect.perimetre()+" surface="+myrect.surface());
	}

}
