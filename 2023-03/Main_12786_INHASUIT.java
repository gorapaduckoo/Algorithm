import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12786_INHASUIT {
	static int N, T, MAX = 20;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = Integer.parseInt(br.readLine());
		boolean[][] isHole = new boolean[N+1][MAX+1];
		int[][] dp = new int[N+1][MAX+1];
		isHole[0][1] = true;
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j=0; j<n; j++) {
				int holeHeight = Integer.parseInt(st.nextToken());
				isHole[i][holeHeight] = true;
			}
		}
		
		// dp[i][j]: i번째 나무 높이 j일 때 사용한 최소 T개
		Arrays.fill(dp[0], 0);
		for (int i=1; i<=N; i++) {
			Arrays.fill(dp[i], 51);
		}
		
		
		for (int i=0; i<N; i++) {
			int prevMin = Integer.MAX_VALUE;
			for (int j=1; j<=MAX; j++) {
				prevMin = Math.min(dp[i][j], prevMin);
			}
			
			for (int j=1; j<=MAX; j++) {
				if(isHole[i][j] && isHole[i+1][j]) {
					dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
				}
				if(j>1 && isHole[i][j] && isHole[i+1][j-1]) {
					dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]);
				}
				if(j<20 && isHole[i][j] && isHole[i+1][j+1]) {
					dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]);
				}
				if(j<=10 && isHole[i][j] && isHole[i+1][j*2]) {
					dp[i+1][j*2] = Math.min(dp[i+1][j]*2, dp[i][j]);
				} else if(j>10 && isHole[i][j] && isHole[i+1][MAX]){
					dp[i+1][MAX] = Math.min(dp[i+1][MAX], dp[i][j]);
				}
				if(isHole[i+1][j]) {
					dp[i+1][j] = Math.min(prevMin+1, dp[i+1][j]);
				}
			}
			
		}
		
		
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=MAX; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("////");
		int ans = Integer.MAX_VALUE;
		for (int i=1; i<=MAX; i++) {
			if(isHole[N][i]) ans = Math.min(dp[N][i], ans);
		}
		if(ans<=T) {
			System.out.println(ans);
		} else {
			System.out.println(-1);
		}
	}
}
