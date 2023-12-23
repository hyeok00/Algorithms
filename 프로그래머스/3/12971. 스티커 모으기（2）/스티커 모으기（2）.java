import java.util.*;
class Solution {
	public int solution(int sticker[]) {
		int len = sticker.length;

        if(len == 1)
            return sticker[0];
        if(len == 2)
            return Math.max(sticker[0], sticker[1]);
        
		int[][] arr = new int[2][len + 1];

		arr[0][0] = sticker[0];
		arr[0][1] = Math.max(sticker[0], sticker[1]);

		for (int i = 2; i < len - 1; ++i) {
			if (arr[0][i - 2] > arr[0][i - 1]) {
				arr[0][i] = arr[0][i - 2] + sticker[i];
			} else {
				if (arr[0][i - 1] > arr[0][i - 2] + sticker[i]) {
					arr[0][i] = arr[0][i - 1];
				} else {
					arr[0][i] = arr[0][i - 2] + sticker[i];
				}
			}
		}

		arr[1][1] = sticker[1];
		arr[1][2] = Math.max(sticker[1], sticker[2]);

		for (int i = 3; i < len; ++i) {
			if (arr[1][i - 2] > arr[1][i - 1]) {
				arr[1][i] = arr[1][i - 2] + sticker[i];
			} else {
				if (arr[1][i - 1] > arr[1][i - 2] + sticker[i]) {
					arr[1][i] = arr[1][i - 1];
				} else {
					arr[1][i] = arr[1][i - 2] + sticker[i];
				}
			}
		}

		return Arrays.stream(arr).flatMapToInt(Arrays::stream).max().getAsInt();
	}
}