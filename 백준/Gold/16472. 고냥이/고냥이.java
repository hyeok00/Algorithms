import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = stoi(in.readLine());
		String s = in.readLine();

		int max = 0;
		int check[] = new int['z' - 'a' + 1];

		int count = 0;
		int left = 0;
		for(int right = 0; right < s.length(); ++right){
			char c = s.charAt(right);
			
			if (check[c - 'a'] == 0)
				count++;
			check[c - 'a']++;
			
			while (count > N && left < right) {
				char remove = s.charAt(left);
				left++;
				
				check[remove - 'a']--;
				if (check[remove - 'a'] == 0)
					count--;
			}
			
			max = Math.max(max, right - left + 1);
		}
		System.out.println(max);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}