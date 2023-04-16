import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_27212_미팅 {
	static int N, M, C;
	static int[] A, B;
	static int[][] W;
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		A = new int[N+1];
		B = new int[M+1];
		W = new int[C+1][C+1];
		dp = new long[N+1][M+1];
		
		for (int i=1; i<=C; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=C; j++ ) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				dp[i][j] = Math.max(Math.max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]+W[A[i]][B[j]]);
			}
		}
		System.out.println(dp[N][M]);
		
	}
}
