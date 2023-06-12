import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N;
	static boolean[] arr;
	static List<Pair> list;
	static String[] splitedLine;

	static class Pair implements Comparable<Pair> {
		int first;
		int second;

		Pair() {
		}

		Pair(int a, int b) {
			first = a;
			second = b;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.second == o.second) {
				return this.first - o.second;
			} else {
				return o.second - this.second;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		arr = new boolean[1001];
		list = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			list.add(new Pair(Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1])));
		}
		Collections.sort(list);

		int res = 0;
		for (int i = 0; i < list.size(); ++i) {
			Pair current = list.get(i);
			for (int j = current.first; j > 0; j--) {
				if (arr[j] == false) {
					arr[j] = true;
					res += current.second;
					break;
				}
			}
		}
		System.out.println(res);
	}
}