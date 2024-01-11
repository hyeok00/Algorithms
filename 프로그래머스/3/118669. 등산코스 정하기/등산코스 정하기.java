import java.util.*;

class Node implements Comparable<Node>{
	int from;
	int to;
	int weight;
	Node(int a, int b, int c){
		from = a;
		to = b;
		weight = c;
	}

	public int compareTo(Node o){
		if(this.weight == o.weight)
			return this.to - o.to;
		return this.weight - o.weight;
	}
}
class Solution {
	List<Node>[] graph;
	Set<Integer> start, end;
	int[] dist;
	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		graph = new List[n + 1];
		dist = new int[n + 1];
		for(int i = 0; i <= n; ++i)
			graph[i] = new ArrayList();
		start = new HashSet(); // 출발지
		end = new HashSet(); // 도착지

		for(int i : gates)
			start.add(i);
		for(int i : summits)
			end.add(i);

		for(int[] data : paths){
			if(!start.contains(data[1]) && !end.contains(data[0]))
				graph[data[0]].add(new Node(data[0], data[1], data[2]));
			if(!start.contains(data[0]) && !end.contains(data[1]))
				graph[data[1]].add(new Node(data[1], data[0], data[2]));
		}


		int[] answer = new int[2];
		search(answer);

		return answer;
	}

	public void search(int[] answer){
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue();
		for(int i : start){
			for(Node node : graph[i])
				pq.add(node);
		}

        answer[0] = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;
		while(!pq.isEmpty()){
			Node cur = pq.poll();
            if(cur.weight > max)
                break;
			dist[cur.to] = cur.weight;

			if(end.contains(cur.to)){
				// 정상에 도착함. 최대값을 찾자.
				for(int value : dist){
					if(value>max && value != Integer.MAX_VALUE || max == Integer.MAX_VALUE)
						max = value;
				}
				answer[0] = Math.min(answer[0],cur.to);
				answer[1] = max;
			}

			for(int i = 0; i < graph[cur.to].size(); ++i){
				Node next = graph[cur.to].get(i);

				// 다음지점으로 가본적이 없는 경우.
				if(dist[next.to] == Integer.MAX_VALUE) {
					// dist[next.to] = next.weight;
					pq.add(next);
				}
			}
		}
	}
}