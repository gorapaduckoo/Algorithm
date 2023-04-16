import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1256_사전 {
	static int N, M, K;
	static int MAX = 1000000001;
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new long[N+1][M+1];
		dp[0][0] = 0;
		for (int i=1; i<=N; i++) {
			dp[i][0] = 1;
		}
		for (int j=1; j<=M; j++) {
			dp[0][j] = 1;
		}
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
				if(dp[i][j]>=MAX) dp[i][j] = MAX;
			}
		}
		
		int i=N, j=M;
		
		if(dp[i][j] < K) {
			System.out.println("-1");
			System.exit(0);
		}
		
		StringBuilder sb = new StringBuilder();
		while(i>0 && j>0) {
			if(dp[i-1][j]>=K) {
				sb.append("a");
				i--;
			} else {
				K-=dp[i-1][j];
				sb.append("z");
				j--;
			}
		}
		
		while(i>0) {
			sb.append("a");
			i--;
		}
		while(j>0) {
			sb.append("z");
			j--;
		}
		System.out.println(sb.toString());
	}
}
