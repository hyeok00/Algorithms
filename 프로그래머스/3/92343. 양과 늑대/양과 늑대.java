import java.util.*;

class Solution {
    boolean[] visit;
	List<Integer>[] list;
	int[] infos;
	int answer = 0;
	int len;

	public int solution(int[] info, int[][] edges) {
		len = info.length;
		visit = new boolean[len];
		list = new List[len];
		infos = info;
		for (int i = 0; i < len; ++i)
			list[i] = new ArrayList<>();
		for (int[] edge : edges)
			list[edge[0]].add(edge[1]);

		for(int next : list[0])
			visit[next] = true;

		simulation(1, 0);
		return answer;
	}

	private void simulation(int sheep, int wolf) {
		answer = Math.max(answer, sheep);

		for (int i = 1; i < len; ++i) {
			// 방문할 수 있는 곳인지 체크(트리의 연결된 지점 중 하나인지)
			if (visit[i]) {
				visit[i] = false;
				// 양인 경우
				if (infos[i] == 0) {
					for (int next : list[i])
						visit[next] = true;
					simulation(sheep + 1, wolf);
					for (int next : list[i])
						visit[next] = false;

				} else { // 늑대
					if (sheep > wolf + 1) {
						for (int next : list[i])
							visit[next] = true;
						simulation(sheep, wolf + 1);
						for (int next : list[i])
							visit[next] = false;
					}
				}
				visit[i] = true;
			}
		}
	}
}