import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();

		Set<String> strSet = new HashSet();
		int size = s.length();
		for (int i = 0; i < size; ++i) {
			for (int j = i; j <= size; ++j) {
				String temp = s.substring(i, j);
				if (!temp.equals(""))
					strSet.add(s.substring(i, j));
			}
		}
		System.out.println(strSet.size());
	}
}