package Exercice2;
import java.util.TreeMap;
import java.util.Map;

public class Texte{
	private String text;
	public String toString() {return text;}
	public Map calculOccurrences(Texte txt)
	{
		char[] char_array = txt.toString().toCharArray();
        System.out.println("The Given String is : " + txt);
        Map<Character, Integer> charCounter = new TreeMap<Character, Integer>();
        for (char i : char_array) {
        	charCounter.put(i,charCounter.get(i) == null ? 1 : charCounter.get(i) + 1);
        }
       return charCounter;
	}
	public Map calcilWordOccurences(Texte text)
	{
		Map<String, Integer> wordToCount = new TreeMap<>();
		for (String word : text.toString().split("\\W")) 
		{
		    wordToCount.put(word, wordToCount.getOrDefault(word, 0) + 1);
		}
		return wordToCount;
	}
	public void showWordOccurrences(Map<String, Integer> wordtoCount)
	{
		System.out.println(wordtoCount);
	}
	public void showOccurrences(Map<Character, Integer> charCounter)
	{
        for (Character key : charCounter.keySet()) {
        	System.out.println("occurrence of '" + key + "' is  "+ charCounter.get(key));
        }
	}
	public Texte(String str)
	{
		text=str;
	}
	public Texte()
	{
		text="";
	}
}
