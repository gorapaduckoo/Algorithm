import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17404_RGB거리2 {
	static int N, MAX = 1000001, ans = Integer.MAX_VALUE;
	static int[][] cost, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N+1][3];
		dp = new int[N+1][3];
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<3; i++) {
			pickFirstColor(i);
		}
		
		System.out.println(ans);
	}
	
	static void pickFirstColor(int n) {
		for (int i=0; i<3; i++) {
			if(i==n) dp[1][i] = cost[1][i];
			else dp[1][i] = MAX;
		}
		
		for (int i=2; i<=N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
		}
		
		for (int i=0; i<3; i++) {
			if(i==n) continue;
			ans = Math.min(ans, dp[N][i]);
		}
	}
}
