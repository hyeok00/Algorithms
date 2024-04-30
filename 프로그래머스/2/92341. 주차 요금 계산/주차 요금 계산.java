import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	Map<String, Integer> parkingTime = new TreeMap<>();
	Map<String, Integer> parkingRecordMap = new HashMap<>();

	public int[] solution(int[] fees, String[] records) {
		for (String record : records) {
			String[] inputs = record.split(" ");
			int time = getTime(inputs[0]);
			if (inputs[2].equals("IN")) {
				parkingRecordMap.put(inputs[1], time);
			} else {
				int diff = time - parkingRecordMap.get(inputs[1]);
				parkingTime.put(inputs[1], parkingTime.getOrDefault(inputs[1], 0) + diff);
				parkingRecordMap.remove(inputs[1]);
			}
		}

        // 23:59에 강제 출차 처리
		int last = getTime("23:59");
		for (String key : parkingRecordMap.keySet()) {
			int diff = last - parkingRecordMap.get(key);
			parkingTime.put(key, parkingTime.getOrDefault(key, 0) + diff);
		}
        
		int[] answer = new int[parkingTime.size()];
		int idx = 0;
		for (String key : parkingTime.keySet()) {
			int time = parkingTime.get(key);
			answer[idx] += fees[1]; // 기본요금 부과

			if (time > fees[0]) { // 추가요금 부과
				if ((time - fees[0]) % fees[2] == 0)
					answer[idx] += ((time - fees[0]) / fees[2]) * fees[3];
				else
					answer[idx] += ((time - fees[0] + fees[2]) / fees[2]) * fees[3];
			}

			idx++;
		}
		return answer;
	}

	private int getTime(String input) {
		String[] split = input.split(":");
		return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
	}
}
