import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static StringBuilder sb;
	static int N, M, minDistance;
	static int[] combArr;
	static ArrayList<Pair> housePos, chickenPos;
	final static int HOUSE = 1;
	final static int CHICKENSTORE = 2;

	static class Pair {
		int x, y;

		public Pair() {
		}

		public Pair(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);

		combArr = new int[M];
		housePos = new ArrayList<>(2 * N);
		chickenPos = new ArrayList<>(13);
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				int value = Integer.parseInt(splitedLine[j]);
				if (value == HOUSE) {
					housePos.add(new Pair(i + 1, j + 1));
				} else if (value == CHICKENSTORE) {
					chickenPos.add(new Pair(i + 1, j + 1));
				} else {
					// none
				}
			}
		}

		// 로직
		// i~M개 를 고르는 조합을 찾는다. (참고. i>=1)
		minDistance = Integer.MAX_VALUE;
		for (int i = 1; i <= M; ++i) {
			comb(i, 0);
		}

		sb.append(minDistance);
		// 출력부
		System.out.println(sb);
	}

	static void comb(int r, int depth) {
		if (r == depth) {
			int total = 0;
			for (int i = 0; i < housePos.size(); ++i) {
				int minValue = Integer.MAX_VALUE;
				Pair hp = housePos.get(i);
				for (int j = 0; j < depth; ++j) {
					Pair cp = chickenPos.get(combArr[j]);
					int distance = Math.abs(hp.x - cp.x) + Math.abs(hp.y - cp.y);
					if (distance < minValue) {
						minValue = distance;
					}
				}
				total += minValue;
			}
			if(total <= minDistance) {
				minDistance = total;
			}
//			for (int i = 0; i < depth; ++i) {
//				System.out.print(combArr[i] + " ");
//			}
//			System.out.println();
			return;
		}
		for (int i = 0; i < chickenPos.size(); ++i) {
			boolean isVisit = false;
			for (int j = 0; j < depth; ++j) {
				if (combArr[j] >= i) {
					isVisit = true;
					break;
				}
			}

			if (isVisit)
				continue;
			combArr[depth] = i;
			comb(r, depth + 1);
		}
	}
}
