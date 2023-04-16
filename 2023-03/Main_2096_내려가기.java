import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2096_내려가기 {
	static int N, M=3;
	static int MAX = 0, MIN = 1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][][] dp = new int[N][M][2];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				int n = Integer.parseInt(st.nextToken());
				dp[i][j][MAX] = n;
				dp[i][j][MIN] = n;
			}
		}
		
		for(int i=1; i<N; i++) {
			for (int j=0; j<M; j++) {
				int prevMAX = dp[i-1][j][MAX];
				int prevMIN = dp[i-1][j][MIN];
				if(j>0) {
					prevMAX = Math.max(prevMAX, dp[i-1][j-1][MAX]);
					prevMIN = Math.min(prevMIN, dp[i-1][j-1][MIN]);
				}
				if(j<M-1) {
					prevMAX = Math.max(prevMAX, dp[i-1][j+1][MAX]);
					prevMIN = Math.min(prevMIN, dp[i-1][j+1][MIN]);
				}
				dp[i][j][MAX] += prevMAX;
				dp[i][j][MIN] += prevMIN;
			}
		}
		
		int maxScore = Integer.MIN_VALUE;
		int minScore = Integer.MAX_VALUE;
		for (int j=0; j<M; j++) {
			maxScore = Math.max(maxScore, dp[N-1][j][MAX]);
			minScore = Math.min(minScore, dp[N-1][j][MIN]);
		}
		System.out.println(maxScore + " " + minScore);
		
	}
}
