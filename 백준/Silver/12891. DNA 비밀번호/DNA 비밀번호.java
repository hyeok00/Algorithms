import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	final static int A = 0;
	final static int C = 1;
	final static int G = 2;
	final static int T = 3;
	static int[] needCount;
	static int[] curCount;
	static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int M = Integer.parseInt(splitedLine[1]);
		String Line = in.readLine();

		splitedLine = in.readLine().split(" ");

		// 로직
		needCount = new int[4];
		int len = Line.length();

		needCount[A] = Integer.parseInt(splitedLine[0]);
		needCount[C] = Integer.parseInt(splitedLine[1]);
		needCount[G] = Integer.parseInt(splitedLine[2]);
		needCount[T] = Integer.parseInt(splitedLine[3]);

		res = 0;

		curCount = new int[4];
		for (int i = 0; i < M; ++i) {
			switch (Line.charAt(i)) {
			case 'A':
				++curCount[A];
				break;
			case 'C':
				++curCount[C];
				break;
			case 'G':
				++curCount[G];
				break;
			case 'T':
				++curCount[T];
				break;
			default:
				break;
			}
		}
		check();

		for (int i = M; i < len; ++i) {
			// 이전 문자열 제거
			switch (Line.charAt(i - M)) {
			case 'A':
				--curCount[A];
				break;
			case 'C':
				--curCount[C];
				break;
			case 'G':
				--curCount[G];
				break;
			case 'T':
				--curCount[T];
				break;
			default:
				break;
			}

			// 새로운 문자열 체크
			switch (Line.charAt(i)) {
			case 'A':
				++curCount[A];
				break;
			case 'C':
				++curCount[C];
				break;
			case 'G':
				++curCount[G];
				break;
			case 'T':
				++curCount[T];
				break;
			default:
				break;
			}
			check();
		}

		sb.append(res);

		// 출력
		System.out.println(sb);
	}

	static void check() {
		boolean flag = true;
		for (int i = 0; i < 4; ++i) {
			if (needCount[i] > curCount[i]) {
				flag = false;
				break;
			}
		}
		if (true == flag)
			res++;
	}
}