import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

public class Main {
	static int N;

	static class Point {
		int x;
		int y;

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		List<Point> list = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			String[] inputs = in.readLine().split(" ");
			list.add(new Point(stoi(inputs[0]), stoi(inputs[1])));
		}

		int size = list.size() - 1;
		double res1 = (double)list.get(size).x * (double)list.get(0).y;
		double res2 = (double)list.get(0).x * (double)list.get(size).y;
		for (int i = 0; i < size; ++i) {
			res1 += (double)list.get(i).x * (double)list.get(i + 1).y;
			res2 += (double)list.get(i + 1).x * (double)list.get(i).y;
		}

		double result = Math.abs(res1 - res2) * 0.5;
		result *= 10;
		Math.round(result);
		result *= 0.1;

		System.out.printf("%.1f", result);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}