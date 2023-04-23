import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_11057_오르막수 {
	static int N, K = 10007;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][10];
		Arrays.fill(dp[1], 1);
		
		for (int i=2; i<=N; i++) {
			for (int j=0; j<10; j++) {
				for (int k=0; k<=j; k++) {
					dp[i][j] += dp[i-1][k];
					dp[i][j]%=K;
				}
				
			}
		}
		 
		int ans = 0;
		for (int i=0; i<10; i++) {
			ans+=dp[N][i];
			ans%=K;
		}
		System.out.println(ans);
	}
}
