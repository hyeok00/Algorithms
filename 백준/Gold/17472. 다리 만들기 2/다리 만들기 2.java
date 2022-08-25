import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static int N, M, V, E;
	static int[][] visit, map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Pair>[] posList;
	static ArrayList<Edge> edgeList;
	final static int MAXLAND = 7;
	static int[] parents;

	static class Pair {
		int x;
		int y;

		public Pair(int a, int b) {
			x = a;
			y = b;
		}
	}

	static class Edge implements Comparable<Edge> {

		int from;
		int to;
		int weight;

		public Edge(int start, int end, int weight) {
			this.from = start;
			this.to = end;
			this.weight = weight;
		}

		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	private static void make(int n) { // 크기가 1인 서로소 집합 생성

		parents = new int[n];

		for (int i = 0; i < n; i++) { // 모든 노드가 자신을 부모로 하는(대표자) 집합으로 만듦
			parents[i] = i;
		}
	}

	private static int find(int a) { // a의 대표자 찾기

		if (parents[a] == a) {
			return a;
		}

		return parents[a] = find(parents[a]); // 우리의 대표자를 나의 부모로 .. : path compression
	}

	private static boolean union(int a, int b) { // 리턴 값 : true ==> union 성공
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return false;
		}

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);

		map = new int[N][M];
		visit = new int[N][M];
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}

		// 초기화
		edgeList = new ArrayList<>();
		posList = new ArrayList[MAXLAND];
		for (int i = 0; i < MAXLAND; ++i) {
			posList[i] = new ArrayList<>();
		}

		// 로직

		// 섬 개수 확인
		int numLand = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				// 지도 상에 땅으로 표시되며, 방문한 적이 없는경우.
				if (map[i][j] == 1 && visit[i][j] == 0) {
					numLand++;
					bfs(i, j, numLand);
				}
			}
		}

		// 섬 간 거리 계산 (가중치)
		for (int i = 1; i <= numLand; ++i) {
			search(i, numLand + 1);
		}

		make(numLand + 1);
		Collections.sort(edgeList);// 간선비용이 작은 순서대로 정렬

		int result = 0; // MST 비용
		int count = 0; // 연결 간선 수
		for (Edge edge : edgeList) {

			// 싸이클이 발생하지 않았으면
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				if (++count == numLand - 1) { // 연결 간선수가 (정점 수 - 1) 이면 최소 신장 트리 완성
					break;
				}
			}
		}

		// 출력부
		if (result == 0)
			System.out.println(-1);
		else if(count+1!=numLand)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	private static void search(int n, int numLand) {
		int[][] value = new int[numLand][numLand];
		for (int i = 1; i < numLand; ++i)
			for (int j = 1; j < numLand; ++j)
				value[i][j] = Integer.MAX_VALUE;

		// 섬의 모든 좌표에서 탐색을 시도한다.
		for (int i = 0; i < posList[n].size(); ++i) {
			// 4방향으로 탐색 시작
			for (int j = 0; j < 4; ++j) {
				int distance = 0;
				int curX = posList[n].get(i).x;
				int curY = posList[n].get(i).y;
				int nextX = curX;
				int nextY = curY;
				while (true) {
					nextX = nextX + dx[j];
					nextY = nextY + dy[j];
					// 범위 내부 만
					if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
						// 이동 했으나, 같은 섬이면 탐색 종료
						if (visit[nextX][nextY] == n)
							break;
						else if (visit[nextX][nextY] == 0) {
							// 다리를 놓을 수 있는경우, 다리 길이를 증가시키면서 테스트
							distance++;
						} else { // 다른 섬에 도달한 경우
							int destination = visit[nextX][nextY];
							if (distance == 1) // 길이가 1인 경우는 없다
								break;
							// 다리가 지어질 수 있는 지점에서 , 최단거리가 나오면 갱신한다.
							if (value[n][destination] > distance)
								value[n][destination] = distance;
							break;
						}
					} else {
						break;
					}
				}
			}
		}

		for (int i = 1; i < numLand; ++i) {
			for (int j = 1; j < numLand; ++j) {
				// 거리 값이 존재하는 경우
				if (value[i][j] != Integer.MAX_VALUE) {
					edgeList.add(new Edge(i, j, value[i][j]));
				}
			}
		}
	}

	private static void bfs(int x, int y, int numLand) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));
		visit[x][y] = numLand;

		while (!q.isEmpty()) {
			Pair p = q.peek();
			q.remove();

			posList[numLand].add(p);
			for (int i = 0; i < 4; ++i) {
				int nextX = p.x + dx[i];
				int nextY = p.y + dy[i];
				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
					if (map[nextX][nextY] == 1 && visit[nextX][nextY] == 0) {
						visit[nextX][nextY] = numLand;
						q.add(new Pair(nextX, nextY));
					}
				}
			}
		}
	}
}
