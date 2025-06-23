import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2718_타일채우기 {
	static int T, N;
	static int[] dp, arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		int[] arr = new int[T];
		
		for (int i=0; i<T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			N = Math.max(N, arr[i]);
		}
		
		dp = new int[N+1];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 5;
		for (int i=3; i<=N; i++) {
			dp[i] = dp[i-1] + 4*dp[i-2];
			
			for (int j=3; j<=i; j++) {
				if (j%2 == 0) {
					dp[i] += (3*dp[i-j]);
				} else {
					dp[i] += (2*dp[i-j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<T; i++) {
			sb.append(dp[arr[i]]);
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
