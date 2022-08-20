import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	static StringBuilder sb;

	static class Point {
		public int x;
		public int y;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		int N = Integer.parseInt(in.readLine());
		List<Point> l = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			Point p = new Point();
			p.x = Integer.parseInt(splitedLine[0]);
			p.y = Integer.parseInt(splitedLine[1]);
			l.add(p);
		}
		
		// 로직
		Comparator<Point> comp = new Comparator<Main.Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x == o2.x) {
					return o1.y - o2.y;
				} else {
					return o1.x - o2.x;
				}
			}
		};
		Collections.sort(l, comp);
		for(int i=0; i<N; ++i) {
			Point p = new Point();
			p.x = l.get(i).x;
			p.y = l.get(i).y;
			sb.append(p.x + " " + p.y + "\n");
		}
		System.out.println(sb);
	}
}