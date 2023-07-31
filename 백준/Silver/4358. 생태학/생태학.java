import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String s;
		int total = 0;
		Map<String, Integer> map = new TreeMap<>();
		while ((s = in.readLine()) != null) {
			total++;
			map.put(s, map.getOrDefault(s, 0) + 1);
		}

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Integer> it : map.entrySet())
			sb.append(it.getKey() + " " + String.format("%.4f", (double)it.getValue()*100 / (double)total) + "\n");

		System.out.print(sb);
	}

}