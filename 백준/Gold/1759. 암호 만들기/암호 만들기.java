import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;
	static int L, C;
	static char[] permArr, data;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		splitedLine = in.readLine().split(" ");
		L = Integer.parseInt(splitedLine[0]);
		C = Integer.parseInt(splitedLine[1]);
		data = new char[C];
		splitedLine = in.readLine().split(" ");
		for (int i = 0; i < C; ++i) {
			data[i] = splitedLine[i].charAt(0);
		}

		// 로직
		Arrays.sort(data);
		permArr = new char[L];

		perm(0);
		// 출력부
		System.out.println(sb);
	}

	public static void perm(int depth) {
		if (L == depth) {
			int count = 0;
			for (int i = 0; i < L; ++i) {
				if (isVowel(permArr[i]))
					count++;
			}
			if (1 <= count && count <= L - 2) {
				for (int i = 0; i < L; ++i) {
					sb.append(permArr[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i = 0; i < C; ++i) {
			boolean isVisit = false;
			for (int j = 0; j < depth; ++j) {
				if (permArr[j] >= data[i]) {
					isVisit = true;
					break;
				}
			}

			if (isVisit)
				continue;

			permArr[depth] = data[i];
			perm(depth + 1);
		}
	}

	public static boolean isVowel(char c) {
		boolean res = false;
		switch (c) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			res = true;
			break;

		default:
			break;
		}
		return res;
	}
}
