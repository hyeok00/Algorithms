import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long N =Long.parseLong(in.readLine());
		N = N*2;
		long result = 1;
		while(true){
			if (result * (result+1)>N){
				System.out.println(result-1);
				return;
			}
			result++;
		}
	}
}