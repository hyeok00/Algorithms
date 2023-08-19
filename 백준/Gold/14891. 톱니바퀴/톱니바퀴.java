import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Main {
    static StringBuilder sb;
 
    static class Magnet {
        int pos;
        int[] data;
 
        public Magnet() {
            pos = 0;
            data = new int[SIZE];
        }
 
        public int getTop() {
            return data[(8 - pos) % 8];
        }
 
        public int getLeft() {
            if (pos != 7) {
                return data[6 - pos];
            }
            return data[7];
        }
 
        public int getRight() {
            if (pos <= 2)
                return data[2 - pos];
            return data[10 - pos];
        }
    }
 
    final static int SIZE = 8;
    final static int NUM_MAGNET = 4;
    final static int magnetN = 1;
    final static int magnetS = 1;
 
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
 
        // 입력부
 
            // 초기화
            Magnet[] ml = new Magnet[NUM_MAGNET];
            String[] splitedLine;
            for (int i = 0; i < NUM_MAGNET; ++i) {
                String line = in.readLine();
                ml[i] = new Magnet();
                for (int j = 0; j < SIZE; ++j) {
                    ml[i].data[j] = line.charAt(j)-'0';
                }
            }
 
            
            int K = Integer.parseInt(in.readLine());
            // 로직
            boolean[] leftValueArr = new boolean[NUM_MAGNET];
            boolean[] rightValueArr = new boolean[NUM_MAGNET];
            for (int numRotate = 0; numRotate < K; ++numRotate) {
                splitedLine = in.readLine().split(" ");
                int target = Integer.parseInt(splitedLine[0]) - 1; // 입력 1번이 index 0 을 의미
                int turnDirection = Integer.parseInt(splitedLine[1]);
                if (turnDirection == -1)
                    turnDirection++;
                // 현재 상태 확인
                for (int i = 0; i < NUM_MAGNET; ++i) {
                    leftValueArr[i] = ml[i].getLeft() == 1;
                    rightValueArr[i] = ml[i].getRight() == 1;
                }
 
                // 현재 상태 변환
                if (turnDirection == 1) { // 시계방향 회전
                    if (ml[target].pos == SIZE - 1) {
                        ml[target].pos = 0;
                    } else {
                        ml[target].pos = ml[target].pos + 1;
                    }
                } else { // 반시계방향 회전
                    if (ml[target].pos == 0) {
                        ml[target].pos = SIZE - 1;
                    } else {
                        ml[target].pos = ml[target].pos - 1;
                    }
                }
 
                // 왼쪽으로 확인
                int cur = target;
                int nextDirection = (turnDirection + 1) % 2;
                int left = target - 1;
                while (left >= 0) {
                    if (leftValueArr[cur] == rightValueArr[left])
                        break; // 같으면 회전하지 않는다
 
                    if (nextDirection == 1) { // 시계방향 회전
                        if (ml[left].pos == SIZE - 1) {
                            ml[left].pos = 0;
                        } else {
                            ml[left].pos = ml[left].pos + 1;
                        }
                    } else { // 반시계방향 회전
                        if (ml[left].pos == 0) {
                            ml[left].pos = SIZE - 1;
                        } else {
                            ml[left].pos = ml[left].pos - 1;
                        }
                    }
                    cur = left;
                    nextDirection = (nextDirection + 1) % 2;
 
                    left -= 1;
                }
 
                // 오른쪽으로 확인
                cur = target;
                nextDirection = (turnDirection + 1) % 2;
                int right = target + 1;
                while (right < NUM_MAGNET) {
                    if (rightValueArr[cur] == leftValueArr[right])
                        break; // 같으면 회전하지 않는다
 
                    if (nextDirection == 1) { // 시계방향 회전
                        if (ml[right].pos == SIZE - 1) {
                            ml[right].pos = 0;
                        } else {
                            ml[right].pos = ml[right].pos + 1;
                        }
                    } else { // 반시계방향 회전
                        if (ml[right].pos == 0) {
                            ml[right].pos = SIZE - 1;
                        } else {
                            ml[right].pos = ml[right].pos - 1;
                        }
                    }
                    cur = right;
                    nextDirection = (nextDirection + 1) % 2;
 
                    right += 1;
                }
            }
 
            // 점수 계산
            int score = 0; // 최종 점수 기록
            int weight = 1;
            for (int i = 0; i < NUM_MAGNET; ++i) {
                if (ml[i].getTop() == magnetS)
                    score += ml[i].getTop() * weight;
                weight = weight << 1;
            }
            sb.append(score);
        
        // 출력부
 
        System.out.println(sb);
    }
}