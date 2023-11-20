import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static List<Integer> cranes, boxes;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		cranes = new ArrayList<>();
		boxes = new ArrayList<>();
		int N = stoi(in.readLine());
		String[] splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i)
			cranes.add(stoi(splitedLine[i]));

		int M = stoi(in.readLine());
		splitedLine = in.readLine().split(" ");
		for (int i = 0; i < M; ++i)
			boxes.add(stoi(splitedLine[i]));

		Collections.sort(cranes, Collections.reverseOrder());
		Collections.sort(boxes, Collections.reverseOrder());

		// 크레인이 들 수 있는 무게보다 무거운 박스가 있는 경우 옮길 수 없다.
		if (boxes.get(0) > cranes.get(0)) {
			System.out.println(-1);
		} else {
			int time = 0;
			while (!boxes.isEmpty()) {
				time++;
				for (int i = 0; i < N; ++i) {
					for (int j = 0; j < boxes.size(); ++j) {
						if (cranes.get(i) >= boxes.get(j)) {
							boxes.remove(j);
							break;
						}
					}
				}
			}
			System.out.println(time);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}