import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1915_가장큰정사각형 {
	static int N, M, ans=0;
	static int[][] arr, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		for (int i=1; i<=N; i++) {
			String str = br.readLine();
			for (int j=1; j<=M; j++) {
				arr[i][j] = str.charAt(j-1)-'0';
			}
		}
		
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				if(arr[i][j]==1) {
					dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
					ans = Math.max(dp[i][j], ans);
				}
			}
		}
		
		System.out.println(ans*ans);
	}
}
