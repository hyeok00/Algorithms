import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedLine = in.readLine().split(" ");
        N = Integer.parseInt(splitedLine[0]);
        K = Integer.parseInt(splitedLine[1]);

        int[] names = new int[700000];
        for (int i = 0; i < N; ++i) {
            names[i] = in.readLine().length();
        }
        int arr[] = new int[21];

        long count = 0;
        for (int i = 0; i <= K; ++i)
            arr[names[i]]++;
        for (int i = 0; i < N - 1; ++i) {
            arr[names[i]]--;
            count += arr[names[i]];
            arr[names[i + K + 1]]++;
        }
        System.out.println(count);
    }
}