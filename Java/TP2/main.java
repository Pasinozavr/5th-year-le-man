import java.util.Iterator;

public class main {

 public static void main(String[] args) throws Exception {
  System.out.println("Working");
  Auteur aut1 = new Auteur("Pasha", new Date(2, 3, 1991), null),
   aut2 = new Auteur("Masha", new Date(2, 2, 1977), new Date(3, 5, 2015)),
   badaut = new Auteur("Bad", new Date(2, 2, 1955), new Date(2, 2, 1999));
  Document book1 = new Document(aut1, "Book1"),
   book2 = new Document(aut1, "Book2"),
   book3 = new Document(aut2, "Book3"),
   polym1 = new Document(aut1, "polymorphisme1"),
   polym2 = new Document(badaut, "polymorphisme2"),
   polym3 = new Document(badaut, "ABCD polymorphisme"),
   polym4 = new Document(badaut, "zWy polymorphisme");
  BaseDocuments base = new BaseDocuments();
  base.ajoute(book1);
  base.ajoute(book2);
  base.ajoute(book3);
  base.affiche();
  base.supprime(book3);
  base.affiche();
  base.emprunte(book2);
  base.emprunte(book2);
  base.rend(book2);
  base.rend(book2);
  base.emprunte(book3);

  Select1 sel1 = new Select1();
  Select2 sel2 = new Select2(2016);
  Select3 sel3 = new Select3("Book");
  Iterator it1 = base.selectionne(sel1),
   it2 = base.selectionne(sel2),
   it3 = base.selectionne(sel3);
  System.out.println("Autors who live over 22 years:");
  while (it1.hasNext()) {
   System.out.println(it1.next());
  }
  System.out.println("Autors who was alive at 2016:");
  while (it2.hasNext()) {
   System.out.println(it2.next());
  }
  System.out.println("Books with word Book in titre");
  while (it3.hasNext()) {
   System.out.println(it3.next());
  }
  base.ajoute(polym1);
  base.ajoute(polym2);

  SelectionneurComposite selcom = new SelectionneurComposite();
  selcom.ajoute(new Select2(2015));
  selcom.ajoute(new Select3("polymorphisme"));
  Iterator it4 = base.selectionne(selcom);
  System.out.println("Books with word polymorphisme in titre and which autor was alive at 2015");
  while (it4.hasNext()) {
   System.out.println(it4.next());
  }

  base.ajoute(polym3);
  base.ajoute(polym4);
  System.out.println("By alphabet:");
  Iterator it5 = base.listeTriee(base.selectionne(new Select3("polymorphisme")), new TitreComparator());
  while (it5.hasNext()) {
   System.out.println(it5.next());
  }
 }

}