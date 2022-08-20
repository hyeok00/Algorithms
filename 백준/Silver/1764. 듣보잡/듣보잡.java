import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

	public void solution() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedStr = input.readLine().split(" ");

		int n = Integer.parseInt(splitedStr[0]);
		int m = Integer.parseInt(splitedStr[1]);

		Map<String, Integer> myMap = new HashMap<>();
		for (int i = 0; i < n; ++i) {
			myMap.put(input.readLine(), 0);
		}
		ArrayList<String> arr = new ArrayList<>();
		for (int i = 0; i < m; ++i) {
			String str = input.readLine();
			if (myMap.get(str) != null) {
				arr.add(str);
			}
		}
		Collections.sort(arr);
		System.out.println(arr.size());
		for(String it : arr) {
			System.out.println(it);
		}
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}