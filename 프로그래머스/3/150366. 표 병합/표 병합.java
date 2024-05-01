import java.util.ArrayList;
import java.util.List;

class Solution {
	String[] cellValue;
	int[] parent;
	final int MAX = 50;
	final int MAX_SIZE = MAX * MAX + 1;

	public int find(int n) {
		if (n == parent[n])
			return n;
		return parent[n] = find(parent[n]);
	}

	public void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return;

		if (cellValue[a].equals("") && !cellValue[b].equals("")) {
			parent[a] = b;
			cellValue[a] = "";
		} else {
			parent[b] = a;
			cellValue[b] = "";
		}
		for(int i = 0 ; i < MAX_SIZE; ++i)
			find(i);
	}

	public int getIndex(String r, String c) {
		return (Integer.parseInt(r) - 1) * MAX + Integer.parseInt(c) - 1;
	}

	public String[] solution(String[] commands) {
		cellValue = new String[MAX_SIZE];
		parent = new int[MAX_SIZE];
		List<String> answer = new ArrayList<>();

		for (int i = 0; i < MAX_SIZE; ++i) {
			cellValue[i] = "";
			parent[i] = i;
		}

		for (String command : commands) {
			String[] inputs = command.split(" ");
			if (inputs[0].equals("UPDATE")) {
				if (inputs.length == 4) {
					// Case 1. "UPDATE r c value"
					int idx = getIndex(inputs[1], inputs[2]);
					cellValue[find(idx)] = inputs[3];
				} else {
					// Case 2. "UPDATE value1 value2"
					for (int i = 0; i < MAX_SIZE; ++i)
						if (cellValue[i].equals(inputs[1]))
							cellValue[i] = inputs[2];
				}
			} else if (inputs[0].equals("MERGE")) {
				// Case 3. "MERGE r1 c1 r2 c2"
				int idx = getIndex(inputs[1], inputs[2]);
				int other = getIndex(inputs[3], inputs[4]);
				union(idx, other);
			} else if (inputs[0].equals("UNMERGE")) {
				// Case 4. "UNMERGE r c"
				int idx = getIndex(inputs[1], inputs[2]);
				int parentIdx = find(idx);
				String base = cellValue[parentIdx];
				for (int i = 0; i < MAX_SIZE; ++i) {
					if (parent[i] == parentIdx) {
						parent[i] = i;
						cellValue[i] = "";
					}
				}
				cellValue[idx] = base;
			} else { // PRINT
				// Case 5. "PRINT r c"
				int idx = getIndex(inputs[1], inputs[2]);
				int findIdx = find(idx);
				if (cellValue[findIdx].equals(""))
					answer.add("EMPTY");
				else
					answer.add(cellValue[findIdx]);
			}
		}
		return answer.toArray(new String[answer.size()]);
	}
}