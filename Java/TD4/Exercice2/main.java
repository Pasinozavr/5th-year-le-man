package Exercice2;

public class main {

	public static void main(String[] args) {
		Texte mytext=new Texte();
		Texte example=new Texte("ahaha,easy;ez");
		mytext.showOccurrences(mytext.calculOccurrences(example));
		mytext.showWordOccurrences(mytext.calcilWordOccurences(example));
	}
}
