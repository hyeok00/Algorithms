import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	static String[] splitedLine;
	static StringBuilder sb;
	static int[] fee, plan;
	static int result;

	final static int DAY = 0;
	final static int MONTH = 1;
	final static int THREE_MONTH = 2;
	final static int YEAR = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			fee = new int[4]; // 각 사용권의 가격
			plan = new int[12 + 1]; // 각 달의 이용계획
			splitedLine = in.readLine().split(" ");
			for (int i = 0; i < 4; ++i) {
				fee[i] = Integer.parseInt(splitedLine[i]);
			}

			splitedLine = in.readLine().split(" ");
			for (int i = 1; i <= 12; ++i) {
				plan[i] = Integer.parseInt(splitedLine[i - 1]);
			}

			result = Integer.MAX_VALUE;
			simulation(1, 0);
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
	}

	private static void simulation(int startMonth, int accSum) {
		if (startMonth > 12) {
			if (accSum < result)
				result = accSum;
			return;
		}

		int sum = 0;

		// Step1. i월에 1년 결제
		sum = 0;
		sum += fee[YEAR];
		if (accSum + sum < result)
			simulation(startMonth + 12, accSum + sum);

		// Step2. i월에 3달 결제
		sum = 0;
		sum += fee[THREE_MONTH];
		if (accSum + sum < result)
			simulation(startMonth + 3, accSum + sum);

		// Step3. i월에 1달 결제
		sum = 0;
		sum = fee[MONTH];
		if (accSum + sum < result)
			simulation(startMonth + 1, accSum + sum);

		// Step4. i월에 1일 결제
		sum = plan[startMonth] * fee[DAY];
		if (accSum + sum < result)
			simulation(startMonth + 1, accSum + sum);
	}
}