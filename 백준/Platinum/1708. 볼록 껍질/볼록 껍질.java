import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Main {

	static final Point start = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

	private static class Point implements Comparable<Point> {
		public long x;
		public long y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point p) {
			// ccw 메서드의 리턴 값이 양수: 반시계 방향이므로 자리바꿈이 일어나지 않게 -1 곱한다.
			long ccw = ccw(start, this, p) * -1;

			// 점 A(start), B 기준으로 C가 평행하게 위치하고 있다면
			if (ccw == 0) {
				// 점 A(start)와 가까운 점을 우선으로 정렬
				return distance(start, this) - distance(start, p) < 0 ? -1 : 1;
			}

			return ccw < 0 ? -1 : 1;
		}
	}

	private static long ccw(Point A, Point B, Point C) {
		return (B.x - A.x) * (C.y - A.y) - (C.x - A.x) * (B.y - A.y);
	}

	private static long distance(Point A, Point B) {
		return Math.abs(A.x - B.x) + Math.abs(A.y - B.y);
	}

	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		List<Point> points = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);

			points.add(new Point(x, y));
		}

		for (Point p : points) {
			if (p.y < start.y) {
				start.x = p.x;
				start.y = p.y;
			} else if (p.y == start.y) {
				if (p.x < start.x) {
					start.x = p.x;
					start.y = p.y;
				}
			}
		}
		Collections.sort(points);

		Stack<Point> stack = new Stack<>();
		stack.add(start);

		for (int i = 1; i < points.size(); i++) {
			while (stack.size() >= 2
					&& ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) {
				stack.pop();
			}
			stack.add(points.get(i));
		}
		sb.append(stack.size());
		System.out.println(sb);
	}

}