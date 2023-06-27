import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String base = in.readLine();
		String target = in.readLine();

		int count = 0;
		int size = target.length();
		int index = 0;
		while (true) {
			if (index > base.length() - size)
				break;
			String substr = base.substring(index, index + size);
			if (substr.equals(target)) {
				count++;
				index += size;
			} else {
				index++;
			}

		}
		System.out.println(count);
	}
}