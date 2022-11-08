import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		char[] oldArr = new char[N];
		char[] oldArr2 = new char[N];
		char[] newArr = new char[N];

		String str = in.readLine();
		oldArr = str.toCharArray();
		oldArr2 = str.toCharArray();
		newArr = in.readLine().toCharArray();

		oldArr2[0] = toggle(oldArr2[0]);
		oldArr2[1] = toggle(oldArr2[1]);
		int count = 0;
		int count2 = 1;
		for (int i = 1; i < N; ++i) {
			if (oldArr[i - 1] == newArr[i - 1]) {
			} else {
				count++;
				doFuntcion(oldArr, i - 1);
			}
			if (oldArr2[i - 1] == newArr[i - 1]) {
			} else {
				count2++;
				doFuntcion(oldArr2, i - 1);
			}
		}

		if (isSame(oldArr, newArr))
			sb.append(count);
		else if (isSame(oldArr2, newArr)) {
			sb.append(count2);
		} else
			sb.append("-1");
		System.out.println(sb);
	}

	private static boolean isSame(char[] src, char[] des) {
		int len = src.length;
		for (int i = 0; i < len; ++i) {
			if (src[i] != des[i])
				return false;
		}
		return true;
	}

	private static void doFuntcion(char[] oldArr, int index) {
		for (int i = index; i <= index + 2; ++i) {
			if (0 <= i && i < oldArr.length)
				oldArr[i] = toggle(oldArr[i]);
		}
	}

	private static char toggle(char c) {
		if (c == '0')
			return '1';
		else
			return '0';
	}
}