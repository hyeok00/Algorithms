import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);

		List<Integer> negList = new ArrayList<>();
		List<Integer> posList = new ArrayList<>();
		splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			int value = Integer.parseInt(splitedLine[i]);
			if (value < 0)
				negList.add(value * -1);
			else
				posList.add(value);
		}
		Collections.sort(negList);
		Collections.sort(posList);

		int nSize = negList.size() - 1;
		int pSize = posList.size() - 1;
		int res = 0;
		if (nSize >= 0) {
			for (int i = nSize; i >= 0; i -= M) {
				res += negList.get(i) * 2;
			}
		}
		if (pSize >= 0) {
			for (int i = pSize; i >= 0; i -= M) {
				res += posList.get(i) * 2;
			}
		}
		if (pSize == -1 && nSize != -1) {
			res -= negList.get(nSize);
		} else if (pSize != -1 && nSize == -1) {
			res -= posList.get(pSize);
		} else {
			res -= Math.max(posList.get(pSize), negList.get(nSize));
		}

		System.out.println(res);
	}
}