import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2169_로봇조종하기 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		int[][][] dp = new int[N][M][3];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 0: 위에서 오는 경우
		// 1: 왼쪽에서 오는 경우
		// 2: 오른쪽에서 오는 경우
		Arrays.fill(dp[0][0], arr[0][0]);
		for (int j=1; j<M; j++) {
			Arrays.fill(dp[0][j], dp[0][j-1][0]+arr[0][j]);
		}
		
		for (int i=1; i<N; i++) {
			for (int j=0; j<M; j++) {
				dp[i][j][0] = Math.max(dp[i-1][j][0], Math.max(dp[i-1][j][1], dp[i-1][j][2])) + arr[i][j];
				if(j==0) {
					dp[i][j][1] = dp[i][j][0];
					continue;
				}
				dp[i][j][1] = Math.max(dp[i][j-1][0], dp[i][j-1][1]) + arr[i][j];
			}
			for (int j=M-1; j>=0; j--) {
				if(j==M-1) {
					dp[i][j][2] = dp[i][j][0];
					continue;
				}
				dp[i][j][2] = Math.max(dp[i][j+1][0], dp[i][j+1][2]) + arr[i][j];
			}
		}

		int ans = Integer.MIN_VALUE;
		for (int i=0; i<3; i++) {
			ans = Math.max(dp[N-1][M-1][i], ans);
		}
		System.out.println(ans);
		
	}
}