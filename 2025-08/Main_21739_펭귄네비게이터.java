import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_21739_펭귄네비게이터 {
	static int N, K = 1_000_000_007;
	static long[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new long[N+1];
		arr[0] = 1;
		
		for (int i=1; i<=N; i++) {
			for (int j=0; j<i; j++) {
				arr[i] += (arr[j] * arr[i-j-1]);
				arr[i] %= K;
			}
		}
		
		System.out.println(arr[N]);
	}
}
