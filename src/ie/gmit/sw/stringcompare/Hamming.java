package ie.gmit.sw.stringcompare;

public class Hamming implements StringComparable {
	public String distance(String s, String t) {
		if (s.length() != t.length()) return "-1"; //Similar length strings only
		int counter = 0;
		
		for (int i = 0; i < s.length(); ++i){
			if (s.charAt(i) != t.charAt(i)) counter++;
		}
		return  Integer.toString(counter);
	}
}
