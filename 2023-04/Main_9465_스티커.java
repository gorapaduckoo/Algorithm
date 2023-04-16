import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9465_스티커 {
	static int T, N;
	static int[][] sticker, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		while (T>0) {
			N = Integer.parseInt(br.readLine());
			dp = new int[2][N];
			sticker = new int[2][N];
			for (int i=0; i<2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
					if(j==0) dp[i][j] = sticker[i][j];
				}
			}
			if(N>1) {
				dp[0][1] = dp[1][0]+sticker[0][1];
				dp[1][1] = dp[0][0]+sticker[1][1];
			}
			// 스티커(i,j)를 붙일때: dp[i-1][
			for (int j=2; j<N; j++) {
				for (int i=0; i<2; i++) {
					if(i==0) {
						dp[i][j] = Math.max(dp[1][j-1], Math.max(dp[0][j-2], dp[1][j-2]))+sticker[i][j];
					} else {
						dp[i][j] = Math.max(dp[0][j-1], Math.max(dp[1][j-2], dp[0][j-2]))+sticker[i][j];
					}
				}
			}
			
			System.out.println(Math.max(dp[0][N-1], dp[1][N-1]));
			T--;
		}
	}
}
