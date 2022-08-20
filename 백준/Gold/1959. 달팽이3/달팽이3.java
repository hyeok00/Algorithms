import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		long M = Long.parseLong(splitedLine[0]);
		long N = Long.parseLong(splitedLine[1]);

		// 로직
		long count = 0;
		boolean m_isOdd = false;
		boolean n_isOdd = false;

		long centerX = 0, centerY = 0;

		if (M % 2 == 1) {
			m_isOdd = true;
		}
		if (N % 2 == 1) {
			n_isOdd = true;
		}

		if (M > N) { // 세로가 더 긴 경우
			count = (N - 1) * 2 + 1;
			if (n_isOdd) {
				centerX = (N / 2) + 1 + (M - N);
				centerY = (N / 2) + 1;
			} else {
				centerX = (N / 2) + 1;
				centerY = (N / 2);
			}
		} else if (M == N) { // 동일한 경우
			count = (M - 1) * 2;
			if (m_isOdd) {
				centerX = (M / 2) + 1;
				centerY = (M / 2) + 1;
			} else {
				centerX = (M / 2) + 1;
				centerY = (M / 2);
			}

		} else {// M<N , 가로가 더 긴 경우
			count = (M - 1) * 2;
			if (m_isOdd) {
				centerX = (M / 2) + 1;
				centerY = (N - M) + (M / 2) + 1;
			} else {
				centerX = (M / 2) + 1;
				centerY = (M / 2);
			}
		}

		sb.append(count + "\n");
		sb.append(centerX + " " + centerY);

		// 출력부
		System.out.println(sb);
	}
}