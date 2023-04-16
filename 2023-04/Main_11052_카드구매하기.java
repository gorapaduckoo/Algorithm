import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11052_카드구매하기 {
	static int N;
	static int[] price;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		price = new int[N+1];
		dp = new int[N+1][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}

		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if(j-i<0) dp[i][j] = dp[i-1][j];
				else dp[i][j] = Math.max(dp[i][j-i]+price[i], dp[i-1][j]);
			}
		}
		
		System.out.println(dp[N][N]);

	}
}
