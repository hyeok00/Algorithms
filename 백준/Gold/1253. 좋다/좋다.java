import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

	static int N;
	static int[] arr;
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		arr = new int[N];
		map = new HashMap<>();
		String[] splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			int num = stoi(splitedLine[i]);
			arr[i] = num;
			map.merge(num, 1, (oldValue, newValue) -> oldValue + 1);
		}

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N - 1; ++i) {
			int first = arr[i];
			map.put(first, map.get(first) - 1); // 해당 숫자의 카운트를 내린다.
			for (int j = i + 1; j < N; ++j) {
				int second = arr[j];
				map.put(second, map.get(second) - 1); // 해당 숫자의 카운트를 내린다.
				if (map.getOrDefault(first + second, 0) > 0)// 더한 숫자가 입력에 있는경우
					set.add(first + second);

				map.put(second, map.get(second) + 1); // 원상복구
			}
			map.put(first, map.get(first) + 1); // 원상복구
		}
		int result = 0;
		for(int i : set){
			result += map.get(i);
		}
		System.out.println(result);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}