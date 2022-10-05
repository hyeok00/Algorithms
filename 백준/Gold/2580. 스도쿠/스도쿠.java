import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	final static int SIZE = 9;
	static int[][] map;
	static ArrayList<Pair> data;

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		map = new int[SIZE][SIZE];
		data = new ArrayList<>();
		for (int i = 0; i < SIZE; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			for (int j = 0; j < SIZE; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
				if (map[i][j] == 0) {
					data.add(new Pair(i, j));
				}
			}
		}

		int depth = 0;
		simulation(map, depth);
	}

	private static void simulation(int[][] testMap, int depth) {
		Pair temp = data.get(depth);

		for (int i = 1; i <= 9; ++i) {
			boolean isOkay = check(testMap, temp, i);
			if (isOkay) {
				testMap[temp.first][temp.second] = i;
				if (depth == data.size() - 1) {
					StringBuilder sb = new StringBuilder();
					for (int x = 0; x < SIZE; ++x) {
						for (int y = 0; y < SIZE; ++y) {
							sb.append(testMap[x][y]+" ");
						}
						sb.append("\n");
					}
					System.out.println(sb);
                    System.exit(0);
				} else {
					int[][] nextMap = new int[SIZE][SIZE];
					copyArr(testMap, nextMap);
					simulation(nextMap, depth + 1);
				}
			}
		}
	}

	private static boolean check(int[][] testMap, Pair temp, int value) {
		for (int i = 0; i < 9; ++i) {
			if (testMap[i][temp.second] == value) {
				return false;
			}
			if (testMap[temp.first][i] == value) {
				return false;
			}
		}

		if (0 <= temp.first && temp.first < 3) {
			if (0 <= temp.second && temp.second < 3) {
				for (int i = 0; i < 3; ++i) {
					for (int j = 0; j < 3; ++j) {
						if (testMap[i][j] == value) {
							return false;
						}
					}
				}
			} else if (3 <= temp.second && temp.second < 6) {
				for (int i = 0; i < 3; ++i) {
					for (int j = 3; j < 6; ++j) {
						if (testMap[i][j] == value) {
							return false;
						}
					}
				}
			} else if (6 <= temp.second && temp.second < 9) {
				for (int i = 0; i < 3; ++i) {
					for (int j = 6; j < 9; ++j) {
						if (testMap[i][j] == value) {
							return false;
						}
					}
				}
			}
		} else if (3 <= temp.first && temp.first < 6) {
			if (0 <= temp.second && temp.second < 3) {
				for (int i = 3; i < 6; ++i) {
					for (int j = 0; j < 3; ++j) {
						if (testMap[i][j] == value) {
							return false;
						}
					}
				}
			} else if (3 <= temp.second && temp.second < 6) {
				for (int i = 3; i < 6; ++i) {
					for (int j = 3; j < 6; ++j) {
						if (testMap[i][j] == value) {
							return false;
						}
					}
				}
			} else if (6 <= temp.second && temp.second < 9) {
				for (int i = 3; i < 6; ++i) {
					for (int j = 6; j < 9; ++j) {
						if (testMap[i][j] == value) {
							return false;
						}
					}
				}
			}
		} else if (3 <= temp.first && temp.first < 6) {
			if (0 <= temp.second && temp.second < 3) {
				for (int i = 6; i < 9; ++i) {
					for (int j = 0; j < 3; ++j) {
						if (testMap[i][j] == value) {
							return false;
						}
					}
				}
			} else if (3 <= temp.second && temp.second < 6) {
				for (int i = 6; i < 9; ++i) {
					for (int j = 3; j < 6; ++j) {
						if (testMap[i][j] == value) {
							return false;
						}
					}
				}
			} else if (6 <= temp.second && temp.second < 9) {
				for (int i = 6; i < 9; ++i) {
					for (int j = 6; j < 9; ++j) {
						if (testMap[i][j] == value) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public static void copyArr(int[][] src, int[][] des) {
		int len = src.length;
		int innerLen = src[0].length;

		for (int i = 0; i < len; ++i) {
			for (int j = 0; j < innerLen; ++j) {
				des[i][j] = src[i][j];
			}
		}
	}
}