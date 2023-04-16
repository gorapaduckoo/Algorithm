import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17271_리그오브레전설Small {
	static int N, M, NUM = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[N+1];
		dp[0] = 1;
		for (int i=1; i<=N; i++) {
			if(i>=M) {
				dp[i] = (dp[i-1]+dp[i-M])%NUM;
			} else {
				dp[i] = dp[i-1]%NUM;
			}
		}

		System.out.println(dp[N]);
	}
	
}
