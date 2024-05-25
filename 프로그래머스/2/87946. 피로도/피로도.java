class Solution {
	int maxCount, maxDepth;
	int[][] dungeonsData;
	boolean[] isSelected;
	int[] permArr;

	public int solution(int k, int[][] dungeons) {
		dungeonsData = dungeons;
		maxDepth = dungeons.length;
		isSelected = new boolean[maxDepth];
		permArr = new int[maxDepth];
		permutaion(k, 0);
		return maxCount;
	}

	public void permutaion(int remain, int depth) {
		if (maxDepth == depth) {
			int count = 0;
			for (int index : permArr) {
				if (remain >= dungeonsData[index][0]) {
					remain -= dungeonsData[index][1];
					count++;
					continue;
				}
				break;
			}
			maxCount = maxCount > count ? maxCount : count;
			return;
		}
		for (int i = 0; i < maxDepth; ++i) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				permArr[depth] = i;
				permutaion(remain, depth + 1);
				isSelected[i] = false;
			}
		}
	}
}