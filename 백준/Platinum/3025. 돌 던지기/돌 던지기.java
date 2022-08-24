import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	final static char BLANK = '.';
	final static char WALL = 'X';
	final static char ASH = 'O';
	static int R, C;
	static char[][] map;
	static Quick[] q = new Quick[31];

	static class Quick {
		int col[]; // col[r] 하면 r번째행은 몇번째 열에 있는가 반환
		int r; // 장애물의 위치

		public Quick() {
			super();
			this.col = new int[30000];
			this.r = 1;
		}

		public void insert() {
			map[r - 1][col[r - 1]] = ASH;
		}

		public void trim() {
			while (true) {
				// 장애물 바로위를 가리킨다.
				int cur = col[r - 1];

				// 만약 그지점이 빈공간이 아니라면 insert한 직후
				// 위로 거슬러 올라가면서 빠른접근하도록 갱신한다.
				if (r > 1 && map[r - 1][cur] != '.') {
					r--;
				}
				// 타겟 위치가 맨밑이라는건 갱신할때까지 갓다는것
				else if (r == R)
					break;
				// X도 갱신할만큼 갱신한것.
				else if (map[r][cur] == WALL)
					break;
				else if (map[r][cur] == BLANK)
					col[r++] = cur;
				else {
					if (cur > 0 && map[r][cur - 1] == BLANK && map[r - 1][cur - 1] == BLANK)// 좌
						col[r++] = cur - 1;
					else if (cur + 1 < C && map[r][cur + 1] == BLANK && map[r - 1][cur + 1] == BLANK)// 우
						col[r++] = cur + 1;
					else// 둘다 아닐때
						break;

				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("sample.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		R = Integer.parseInt(splitedLine[0]); // R
		C = Integer.parseInt(splitedLine[1]); // C
		map = new char[30000][31]; // 전체 map

		for (int i = 0; i < R; ++i) {
			String inputLine = in.readLine();
			for (int j = 0; j < C; ++j) {
				map[i][j] = inputLine.charAt(j); // map을 채운다
			}
		}

		for (int j = 0; j < C; j++) {
			// 배열을 만들고 j의 위치에서 타겟위치 r의 위치를 갱신해준다.
			q[j] = new Quick();
			q[j].col[0] = j;
			q[j].trim();
		}

		int N = Integer.parseInt(in.readLine()); // N
		for (int i = 0; i < N; ++i) {
			// 로직
			int pos = Integer.parseInt(in.readLine()); // 화산탄이 떨어지는 위치
			q[pos-1].insert();
			for (int j = 0; j < C; ++j)
				q[j].trim();
		}

		// 출력부
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
//	public static void simulation(char[][] map, int pos, int r, int c) {
//		int x = 0;
//		int y = pos;
//		while (true) {
//			if (x == r) { // 가장 아랫줄 인경우
//				map[x][pos] = ASH;
//				break;
//			}
//			if (x + 1 <= r) { // 아랫칸이 존재하는 경우 (배열 인덱스 내부)
//				if (map[x + 1][pos] == WALL) {
//					// 아래가 장애물일 경우 그 자리에서 굳는다.
//					map[x][pos] = ASH;
//					break;
//				} else if (map[x + 1][pos] == BLANK) {
//					x += 1; // 비어 있다면 아래로 이동한다.
//				} else { // 아래가 화산탄인 경우
//					if (pos - 1 >= 0 && map[x][pos - 1] == BLANK && map[x + 1][pos - 1] == BLANK) {
//						// 왼쪽열이 존재하는경우 + 왼쪽과 왼쪽아래가 비어있을 경우
//						pos = pos - 1;
//						//x = x + 1;
//					} else if (pos + 1 <= c && map[x][pos + 1] == BLANK && map[x + 1][pos + 1] == BLANK) {
//						// 오른쪽열이 존재하는경우
//						pos = pos + 1;
//						//x = x + 1;
//					} else { // 위 두 케이스가 아니라면 그자리에서 굳는다.
//						map[x][pos] = ASH;
//						break;
//					}
//				}
//			}
//		}
//	}
}
