import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int RESULT;
	static String inputStr;
	static String[] splitedLine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		inputStr = in.readLine();

		// 로직
		int len = inputStr.length();
		int value = 0;
		boolean flag = false;
		boolean isFirstNum = true;
		String temp = "";
		for (int i = 0; i < len; ++i) {
			if (inputStr.charAt(i) == '+' || inputStr.charAt(i) == '-') {
				value = Integer.parseInt(temp);
				if (isFirstNum) {
					RESULT += value;
					isFirstNum = false;
					temp = "";
					if (inputStr.charAt(i) == '-')
						flag = true;
					continue;
				}
				if (flag) {
					RESULT -= value;
				} else {
					RESULT += value;
				}
				if (inputStr.charAt(i) == '-')
					flag = true;

				temp = "";
			} else { // 숫자인 경우
				temp += inputStr.charAt(i);
			}
		}
		value = Integer.parseInt(temp);
		if (flag) {
			RESULT -= value;
		} else {
			RESULT += value;
		}
		sb.append(RESULT);

		// 출력부
		System.out.println(sb);
	}
}
