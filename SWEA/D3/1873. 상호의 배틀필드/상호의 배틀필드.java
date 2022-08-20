import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class Solution {
    static StringBuilder sb;
    static int row, col, direction, posX, posY;
    static char[][] map;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
 
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 
        // 결과를 한 번에 출력하기 위한 StringBuilder
        // final int SIZE = 100;
        int N = Integer.parseInt(in.readLine());
        sb = new StringBuilder();
 
        for (int tc = 1; tc <= N; tc++) {
            // 입력부
            String[] inputString = in.readLine().split(" ");
            row = Integer.parseInt(inputString[0]);
            col = Integer.parseInt(inputString[1]);
 
            map = new char[row][col];
            for (int i = 0; i < row; ++i) {
                String line = in.readLine();
                for (int j = 0; j < col; ++j) {
                    map[i][j] = line.charAt(j);
                    switch (map[i][j]) {
                    case '^':
                        posX = i;
                        posY = j;
                        direction = 0;
                        break;
                    case 'v':
                        posX = i;
                        posY = j;
                        direction = 1;
                        break;
                    case '<':
                        posX = i;
                        posY = j;
                        direction = 2;
                        break;
                    case '>':
                        posX = i;
                        posY = j;
                        direction = 3;
                        break;
 
                    default:
                        break;
                    }
                }
            }
            int actionLength = Integer.parseInt(in.readLine());
            String actionString = in.readLine();
 
            // 로직
            for (int i = 0; i < actionLength; ++i) {
                switch (actionString.charAt(i)) {
                case 'U':
                    direction = 0;
                    move(actionString.charAt(i));
                    break;
                case 'D':
                    direction = 1;
                    move(actionString.charAt(i));
                    break;
                case 'L':
                    direction = 2;
                    move(actionString.charAt(i));
                    break;
                case 'R':
                    direction = 3;
                    move(actionString.charAt(i));
                    break;
 
                default:
                    shoot();
                    break;
                }
            }
 
            // 출력부
            sb.append("#" + tc + " ");
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
 
    public static void move(char c) {
        int nextX = posX + dx[direction];
        int nextY = posY + dy[direction];
        // 범위를 벗어나지 않으면서 다음칸이 움직일 수 있는경우
        if (0 <= nextX && nextX < row && 0 <= nextY && nextY < col && map[nextX][nextY] == '.') {
            map[posX][posY] = '.'; // 전차의 원래 위치는 평지이다.
            map[nextX][nextY] = getShape(direction);
            posX = nextX;
            posY = nextY;
        }       else {
            map[posX][posY] = getShape(direction); // 전차의 원래 위치는 평지이다.
        }
    }
 
    public static void shoot() {
        int nextX = posX + dx[direction];
        int nextY = posY + dy[direction];
        boolean used = false;
        while (true) {
            if (0 > nextX || nextX >= row || 0 > nextY || nextY >= col) {
                break;
            }
            if (map[nextX][nextY] == '#') {
                break;
            }
            if (used == true) {
                break;
            }
            if (map[nextX][nextY] == '*') {
                map[nextX][nextY] = '.';
                used = true;
            }
            nextX = nextX + dx[direction];
            nextY = nextY + dy[direction];
        }
    }
 
    public static char getShape(int n) {
        char res = '0';
        switch (n) {
        case 0:
            res = '^';
            break;
        case 1:
            res = 'v';
            break;
        case 2:
            res = '<';
            break;
        case 3:
            res = '>';
            break;
        default:
            break;
        }
        return res;
    }
}