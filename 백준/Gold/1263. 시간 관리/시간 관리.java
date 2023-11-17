import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static class Work implements Comparable<Work> {
		int processTime;
		int end;

		Work(String[] str) {
			processTime = stoi(str[0]);
			end = stoi(str[1]);
		}

		@Override
		public int compareTo(Work o) {
			return o.end - this.end;
		}
	}

	static int N;
	static Work[] works;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		works = new Work[N];
		for (int i = 0; i < N; ++i)
			works[i] = new Work(in.readLine().split(" "));

		Arrays.sort(works);
		int time = works[0].end; // Si의 최댓값
		for (int i = 0; i < N; ++i) {
			time = Math.min(works[i].end, time) - works[i].processTime;
			if (time < 0) { // 0시에 시작해도 끝낼 수 없는 경우
				time = -1;
				break;
			}
		}
		System.out.println(time);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}