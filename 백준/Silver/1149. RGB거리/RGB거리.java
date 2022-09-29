import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		int arr[][] = new int[N][3];

		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			for (int j = 0; j < 3; ++j) {
				arr[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}

		int red = arr[0][0];
		int green = arr[0][1];
		int blue = arr[0][2];

		for (int i = 1; i < N; ++i) {
			int prevRed = red;
			int prevGreen = green;
			int prevBlue = blue;
			
			red = prevGreen > prevBlue ? prevBlue + arr[i][0] : prevGreen + arr[i][0];
			green = prevRed > prevBlue ? prevBlue + arr[i][1] : prevRed + arr[i][1];
			blue = prevGreen > prevRed ? prevRed + arr[i][2] : prevGreen + arr[i][2];
		}

		int result = red < green ? red : green;
		result = result < blue ? result : blue;
		System.out.println(result);
	}
}