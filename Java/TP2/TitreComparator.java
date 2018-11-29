import java.util.Comparator;

public class TitreComparator implements Comparator {
 public TitreComparator() {

 }
 public int compare(Object o1, Object o2) {
  return compare((String) o1, (String) o2);
 }
 private int compare(String s1, String s2) {
  for (int c = 0; s1.charAt(c) != '\0' && s2.charAt(c) != '\0'; c++) {
   if (s1.charAt(c) == '\0' && s2.charAt(c) == '\0') {} else if (s1.charAt(c) == '\0') return -1;
   else if (s2.charAt(c) == '\0') return 1;
   else if ((int) s1.charAt(c) > (int) s2.charAt(c)) {
    return 1;
   } else if ((int) s1.charAt(c) < (int) s2.charAt(c)) {
    return -1;
   }
  }
  return 0;
 }
}