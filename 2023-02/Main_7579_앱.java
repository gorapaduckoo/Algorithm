import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7579_앱 {
	static int N, M;
	static int[] memory, cost;
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		memory = new int[N+1];
		cost = new int[N+1];
		dp = new long[N+1][10001];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=1; i<=N; i++) {
			for (int j=0; j<=10000; j++) {
				if(j<cost[i]) {
					dp[i][j] = dp[i-1][j];
					continue;
				}
				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+memory[i]);
			}
		}
		
		
		for(int j=0; j<=10000; j++) {
			if(dp[N][j] >= M) {
				System.out.println(j);
				break;
			}
		}
		
	}
}
