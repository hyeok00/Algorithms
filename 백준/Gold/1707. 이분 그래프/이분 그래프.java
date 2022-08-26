import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static ArrayList<Integer>[] al;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			String[] splitedLine = in.readLine().split(" ");
			int V = Integer.parseInt(splitedLine[0]);
			int E = Integer.parseInt(splitedLine[1]);

			al = new ArrayList[V + 1];
			for (int i = 0; i < V + 1; ++i)
				al[i] = new ArrayList<>();

			for (int i = 0; i < E; ++i) {
				splitedLine = in.readLine().split(" ");
				int first = Integer.parseInt(splitedLine[0]);
				int second = Integer.parseInt(splitedLine[1]);
				al[first].add(second);
				al[second].add(first);
			}

			int[] visit = new int[V + 1];

			boolean type = true;
			for (int t = 1; t <= V; ++t) {
				if(visit[t]!=0)
					continue;
				Queue<Integer> q = new LinkedList();
				q.add(t);
				int flag = 1;
				visit[t] = flag;
				A: while (!q.isEmpty()) {
					int qSize = q.size();
					while (--qSize >= 0) {
						int cur = q.poll();
						for (int i = 0; i < al[cur].size(); ++i) {
							int val = al[cur].get(i);
							if (visit[val] == 0) {
								visit[val] = visit[cur] * -1;
								q.add(val);
							} else {
								if (visit[val] == visit[cur]) {
									type = false;
									break A;
								}
							}
						}
					}
				}
			}
			if (type)
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		System.out.println(sb);
	}
}