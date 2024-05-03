import java.util.*;

class Solution {
	List<Integer>[] list;
	boolean[] canVisit;
	Map<Integer, Integer> map = new HashMap<>();

	public boolean solution(int n, int[][] path, int[][] order) {
		list = new List[n];
		canVisit = new boolean[n];
		Arrays.fill(canVisit, true);
		for (int data[] : order) {
			map.put(data[0], data[1]);
			canVisit[data[1]] = false;
		}
		for (int i = 0; i < n; ++i)
			list[i] = new ArrayList<>();

		for (int data[] : path) {
			list[data[0]].add(data[1]);
			list[data[1]].add(data[0]);
		}

		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[n];
		visit[0] = true;
		q.add(0);
		int count = 1;

		while (!q.isEmpty()) {
			int size = q.size();
			boolean hasNew = false;
			while (size-- > 0) {
				int cur = q.poll();

				for (int next : list[cur]) {
					// 갈 수 있으며, 방문한 적이 없다면 진행
					if (canVisit[next] && !visit[next]) {
						hasNew = true;
						visit[next] = true;
						q.add(next);
						count++;
					}
				}

				// 현재 방을 방문함으로써, 새롭게 열리는 방이 있는 경우
				if (map.containsKey(cur)) {
					int next = map.get(cur);
					canVisit[next] = true;
					for(int check : list[next]){
						if(visit[check]){
							hasNew = true;
							visit[next] = true;
							q.add(next);
							count++;
							break;
						}
					}
				}
			}
			if (!hasNew)
				break;
		}
		return count == n ? true : false;
	}
}