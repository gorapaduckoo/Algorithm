import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2294_동전2 {
	static int N, K;
	static int[] coins;
	static int[][] dp;
	static int MAX = 10001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1][K+1];
		coins = new int[N+1];
		
		Arrays.fill(dp[0], MAX);
		for(int i=1; i<=N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(coins);
		
		
		for (int i=1; i<=N; i++) {
			int coin = coins[i];
			for (int j=1; j<=K; j++) {
				if(j-coin < 0) {
					dp[i][j] = dp[i-1][j];
					continue;
				}
				int tmp = Math.min(dp[i-1][j-coin]+1, dp[i][j-coin]+1);
				tmp = Math.min(tmp, dp[i-1][j]);
				dp[i][j] = tmp;
				
			}
		}
		
//		for (int i=0; i<=N; i++) {
//			for (int j=0; j<=K; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		if(dp[N][K]==MAX) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N][K]);
		}
	}
}
