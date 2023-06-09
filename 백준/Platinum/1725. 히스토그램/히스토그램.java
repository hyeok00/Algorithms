import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		System.out.println(getArea(N));
	}

	public static int getArea(int len) {
		Stack<Integer> s = new Stack();

		int maxArea = 0;
		for (int i = 0; i < len; i++) {
			while ((!s.isEmpty()) && arr[s.peek()] >= arr[i]) {
				int height = arr[s.pop()];
				int width;
				if (s.isEmpty())
					width = i;
				else
					width = i - s.peek() - 1;
				maxArea = Math.max(maxArea, height * width);
			}
			s.push(i);

		}

		while (!s.isEmpty()) {
			int height = arr[s.pop()];
			int width;
			if (s.isEmpty())
				width = len;
			else
				width = len - s.peek() - 1;
			maxArea = Math.max(maxArea, width * height);
		}

		return maxArea;
	}
}