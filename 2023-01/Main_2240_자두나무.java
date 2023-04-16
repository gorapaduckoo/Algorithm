import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2240_자두나무 {
	static int T, W, ans=0;
	static int[] plum;
	static int[][][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		plum = new int[T+1];
		dp = new int[W+1][T+1][3];
		
		for (int i=1; i<=T; i++) {
			plum[i] = Integer.parseInt(br.readLine());
		}
		
		// 움직이지 않는 경우 초기화
		for (int j=1; j<=T; j++) {
			if(plum[j]==1) {
				dp[0][j][1] = dp[0][j-1][1]+1;
			} else {
				dp[0][j][1] = dp[0][j-1][1];
			}
		}
		
		for (int i=1; i<=W; i++) {
			for(int j=1; j<=T; j++) {
				if(plum[j]==1) {
					dp[i][j][1] = Math.max(dp[i][j-1][1], dp[i-1][j-1][2])+1;
					dp[i][j][2] = Math.max(dp[i-1][j-1][1], dp[i][j-1][2]); 
				} else {
					dp[i][j][1] = Math.max(dp[i][j-1][1], dp[i-1][j-1][2]);
					dp[i][j][2] = Math.max(dp[i-1][j-1][1], dp[i][j-1][2])+1;
				}
			}
		}
		
		System.out.println(Math.max(dp[W][T][1], dp[W][T][2]));
	}
}
