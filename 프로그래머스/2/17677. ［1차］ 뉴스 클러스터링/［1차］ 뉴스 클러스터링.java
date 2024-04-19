import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int solution(String str1, String str2) {
		List<String> group1 = getTokenList(str1.toLowerCase());
		List<String> group2 = getTokenList(str2.toLowerCase());

		List<String> intersection = new ArrayList<>();
		List<String> union = new ArrayList<>();

		for (String token : group1) {
			if (group2.contains(token)) {
				intersection.add(token);
				group2.remove(token);
			}
			union.add(token);
		}
		union.addAll(group2);
		
		if(union.size()== 0)
			return 65536;
		return (int)(65536 * intersection.size() / union.size());
	}

	private List<String> getTokenList(String str) {
		int len = str.length();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < len - 1; ++i) {
			if (isAlphabet(str.charAt(i)) && isAlphabet(str.charAt(i + 1)))
				list.add(str.substring(i, i + 2));
		}
		return list;
	}

	private boolean isAlphabet(char c) {
		if ('a' <= c && c <= 'z')
			return true;
		return false;
	}

}
