import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;
	static int N, result;
	static int[] population, combArr;
	static boolean[] isVisit;
	static List<Integer>[] al;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = readInt(in);
		readLine(in);

		population = new int[N];
		for (int i = 0; i < N; ++i) {
			population[i] = Integer.parseInt(splitedLine[i]);
		}

		al = new ArrayList[N];
		for (int i = 0; i < N; ++i) {
			al[i] = new ArrayList<>();
			readLine(in);
			int size = Integer.parseInt(splitedLine[0]);
			for (int j = 0; j < size; ++j)
				al[i].add(Integer.parseInt(splitedLine[j + 1]) - 1);
		}

		result = Integer.MAX_VALUE;
		combArr = new int[N];
		if (N == 2) {
			System.out.println(Math.abs(population[1] - population[0]));
			return;
		}

		for (int i = 1; i < N - 1; ++i) {
			comb(0, 0, i);
		}

		if (result == Integer.MAX_VALUE)
			sb.append("-1");
		else
			sb.append(result);

		System.out.println(sb);
	}

	private static void comb(int start, int depth, int maxDepth) {
		if (depth == maxDepth) {
			boolean[] check = new boolean[N];
			List<Integer> group1 = new ArrayList<>();
			for (int i = 0; i < maxDepth; ++i) {
				check[combArr[i]] = true;
				group1.add(combArr[i]);
			}
			isVisit = new boolean[N];
			if (isConnected(group1)) {
				List<Integer> group2 = new ArrayList<>();
				for (int i = 0; i < N; ++i) {
					if (!check[i])
						group2.add(i);
				}
				if (isConnected(group2)) {
//					//
//					System.out.print("Group1 : ");
//					for (int i = 0; i < group1.size(); ++i) {
//						System.out.print((group1.get(i) + 1) + " ");
//					}
//					System.out.print("/ Group2 : ");
//					for (int i = 0; i < group2.size(); ++i) {
//						System.out.print((group2.get(i) + 1) + " ");
//					}
//					System.out.println();
//					//
					int sum1 = getSum(group1);
					int sum2 = getSum(group2);
					// System.out.println(sum1 + " " + sum2);
					if (Math.abs(sum1 - sum2) < result)
						result = Math.abs(sum1 - sum2);
				}
			}
			return;
		}
		for (int i = start; i < N; ++i) {
			combArr[depth] = i;
			comb(i + 1, depth + 1, maxDepth);
		}
	}

	private static int getSum(List<Integer> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); ++i) {
			sum += population[list.get(i)];
		}
		return sum;
	}

	private static boolean isConnected(List<Integer> list) {
		int count = 0;
		for (int i = 0; i < list.size(); ++i) {
			int start = list.get(i);
			if (isVisit[start] == false) {
				count++;

				Queue<Integer> q = new LinkedList<>();
				q.add(start);
				isVisit[start] = true;
				while (!q.isEmpty()) {
					int current = q.poll();
					for (int k = 0; k < al[current].size(); ++k) {
						int val = al[current].get(k);

						if (isVisit[val] == false && isMember(val, list)) {
							isVisit[val] = true;
							q.add(val);
						}
					}
				}
			}
		}
		if (count > 1)
			return false;
		return true;
	}

	private static boolean isMember(int val, List<Integer> list) {
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i) == val)
				return true;
		}
		return false;
	}

	static void readLine(BufferedReader in) throws Exception {
		splitedLine = in.readLine().split(" ");
	}

	static int readInt(BufferedReader in) throws Exception {
		return Integer.parseInt(in.readLine());
	}
}