import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2616_소형기관차 {
	static int N, K;
	static int[] train, sum;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		train = new int[N+1];
		sum = new int[N+1];
		dp = new int[4][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			train[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1]+train[i];
		}
		K = Integer.parseInt(br.readLine());
		
		for (int i=1; i<=3; i++) {
			for (int j=1; j<=N; j++) {
				if(j<K) dp[i][j] = sum[j];
				else dp[i][j] = Math.max(dp[i-1][j-K]+(sum[j]-sum[j-K]), dp[i][j-1]);
			}
		}

		System.out.println(dp[3][N]);

	}
}
