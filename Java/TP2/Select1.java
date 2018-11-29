public class Select1 implements Selectionneur {
 private int age;
 public Select1() {
  age = 22;
 }
 public Select1(int a) {
  age = a;
 }
 public boolean isSatisfaitPar(Document d) {
  Date temp = new Date();
  return temp.compare(d.getauteur().getmort(), d.getauteur().getnaiss()) < age * 365;
 }
}