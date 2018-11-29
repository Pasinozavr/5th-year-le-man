import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class BaseDocuments {
 public Iterator selectionne(Selectionneur s) {
  Map < String, Auteur > temp = new HashMap < String, Auteur > ();
  for (String st: base.keySet()) {
   Document d = new Document(base.get(st), st);
   if (s.isSatisfaitPar(d))
    temp.put(d.gettitre(), d.getauteur());
  }
  return temp.entrySet().iterator();
 }
 public Iterator selectionne(SelectionneurComposite s) {
  Map < String, Auteur > temp = new HashMap < String, Auteur > ();
  for (String st: base.keySet()) {
   Document d = new Document(base.get(st), st);
   if (s.isSatisfaitPar(d))
    temp.put(d.gettitre(), d.getauteur());
  }
  return temp.entrySet().iterator();
 }
 private Map < String, Auteur > base;
 public BaseDocuments() {
  base = new HashMap < String, Auteur > ();
 }
 public Map < String, Auteur > getbase() {
  return base;
 }
 public void ajoute(Document doc) {
  base.put(doc.gettitre(), doc.getauteur());
  System.out.println(doc.toString() + " added to base");
 }
 public Document supprime(Document doc) {
  if (!base.containsKey(doc.gettitre()) || !base.containsValue(doc.getauteur())) {
   System.out.println("Document haven't found");
   return null;
  } else {
   Document temp = new Document(doc.getauteur(), doc.gettitre());
   base.remove(doc.gettitre(), doc.getauteur());
   System.out.println(doc.toString() + " deleted from base");
   return temp;
  }
 }
 public void affiche() {
  int num = 1;
  System.out.println("AFFICHE");
  for (String s: base.keySet()) {
   System.out.print(num + ". " + base.get(s).toString());
   System.out.println(" '" + s + "'");
   num++;
  }
 }
 public boolean estDisponible(Document doc) {
  try {
   if (!base.containsKey(doc.gettitre()) || !base.containsValue(doc.getauteur())) {
    throw new DocumentException("Document doesn't exist", doc.getauteur(), doc.gettitre());
   } else {
    return doc.getatbibl();
   }
  } catch (DocumentException e) {
   System.out.println("Document doesn't exist");
  }
  return false;
 }
 public void emprunte(Document doc) {
  try {
   if (!base.containsKey(doc.gettitre()) || !base.containsValue(doc.getauteur())) {
    throw new DocumentException("Document doesn't exist", doc.getauteur(), doc.gettitre());
   } else {
    if (estDisponible(doc)) {
     doc.changeplace();
     System.out.println(doc.toString() + " taked out");
    } else {
     System.out.println(doc.toString() + " currently isn't avaible to take out");
    }
   }
  } catch (DocumentException e) {
   System.out.println("Document doesn't exist");
  }
 }
 public void rend(Document doc) {
  try {
   if (!base.containsKey(doc.gettitre()) || !base.containsValue(doc.getauteur())) {
    throw new DocumentException("Document doesn't exist", doc.getauteur(), doc.gettitre());
   } else {
    if (!estDisponible(doc)) {
     doc.changeplace();
     System.out.println(doc.toString() + " returned back");
    } else {
     System.out.println(doc.toString() + " is currently in base Oo and can't be returned back");
    }
   }
  } catch (DocumentException e) {
   System.out.println("Document doesn't exist");
  }
 }
 public Iterator listeTriee(Iterator i, Comparator c) {
  List list = new ArrayList();
  while (i.hasNext()) {
   list.add(i.next().toString());
  }
  Collections.sort(list, c);
  return list.iterator();
 }

}

class DocumentException extends Exception {
 static final long serialVersionUID = 1L;
 private Auteur aut;
 private String titre;
 public Auteur getAut() {
  return aut;
 }
 public String getTitre() {
  return titre;
 }
 public DocumentException(String message, Auteur a, String t) {
  super(message);
  titre = t;
  aut = a;
 }
}