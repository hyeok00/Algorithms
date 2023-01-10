import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N =Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; ++i) {
			int arr[] = new int[10];
			String[] sp = in.readLine().split(" ");
			for(int j=0; j<10; ++j){
				arr[j] = Integer.parseInt(sp[j]);
			}
			Arrays.sort(arr);
			sb.append(arr[7]);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}