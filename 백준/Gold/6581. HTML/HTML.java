import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String hrLine = "--------------------------------------------------------------------------------";
	static int index = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = "";
		while ((input = in.readLine()) != null) {
			String[] splitedLine = input.split(" |	");
			for (int i = 0; i < splitedLine.length; ++i) {
				if (splitedLine[i].equals("<br>")) {
					sb.append("\n");
					index = 0;
				} else if (splitedLine[i].equals("<hr>")) {
					if (index != 0) {
						sb.append("\n");
					}
					sb.append(hrLine).append("\n");
					index = 0;
				} else if (splitedLine[i].equals("")) {
					// nothing
				} else {
					int size = splitedLine[i].length();
					int weight = index == 0 ? 0 : 1;
					if (index + size + weight > 80) {
						sb.append("\n");
						index = 0;
					}
					if (index != 0)
						sb.append(' ');
					sb.append(splitedLine[i]);
					index += size + weight;
				}
			}
		}
		sb.append("\n");
		System.out.println(sb);
	}
}