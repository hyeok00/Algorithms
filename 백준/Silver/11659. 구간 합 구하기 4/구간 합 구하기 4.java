import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputString = in.readLine().split(" ");
		int N = Integer.parseInt(inputString[0]);
		int M = Integer.parseInt(inputString[1]);
		sb = new StringBuilder();

		int[] arr = new int[N+1];
		String[] splitedLine = in.readLine().split(" ");
		
		int sum = 0;
		for (int i = 0; i < N; ++i) {
			int num = Integer.parseInt(splitedLine[i]);
			sum+=num;
			arr[i+1] = sum;
		}

		// 출력부
		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int first = Integer.parseInt(splitedLine[0]);
			int second = Integer.parseInt(splitedLine[1]);
			
			sb.append(arr[second]-arr[first-1] + "\n");
		}
		System.out.println(sb);
	}
}