import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int MAX = 87654321;
	static StringBuilder sb;
	static int V, E;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String[] splitedLine = in.readLine().split(" ");
		V = Integer.parseInt(splitedLine[0]);
		E = Integer.parseInt(splitedLine[1]);

		arr = new int[V + 1][V + 1];
		for (int i = 0; i < V + 1; ++i)
			for (int j = 0; j < V + 1; ++j)
				arr[i][j] = MAX;

		for (int i = 0; i < E; ++i) {
			splitedLine = in.readLine().split(" ");
			arr[Integer.parseInt(splitedLine[0])][Integer.parseInt(splitedLine[1])] = Integer.parseInt(splitedLine[2]);
		}

		for (int k = 1; k < V + 1; ++k)
			for (int i = 1; i < V + 1; ++i)
				for (int j = 1; j < V + 1; ++j)
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);

		// for (int i = 1; i < V + 1; ++i) {
		// 	for (int j = 1; j < V + 1; ++j) {
		// 		System.out.print(arr[i][j] + " ");
		// 	}
		// 	System.out.println();
		// }

		int result = MAX;
		for (int i = 1; i < V + 1; ++i) {
			if (arr[i][i] != MAX)
				result = Math.min(result, arr[i][i]);
		}
		if (result == MAX)
			System.out.println(-1);
		else
			System.out.println(result);
	}
}