import java.io.*;
import java.util.*;

public class Softeer_나무수확 {
	static int N;
    static int[][] seed;
    static int[][][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seed = new int[N+1][N+1];
        dp = new int[N+1][N+1][2];

        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                seed[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i][j-1][0])+seed[i][j];
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i][j-1][1])+seed[i][j];
                dp[i][j][1] = Math.max(dp[i-1][j][0] + seed[i][j]*2, dp[i][j][1]);
                dp[i][j][1] = Math.max(dp[i][j-1][0] + seed[i][j]*2, dp[i][j][1]);
            }
        }

        System.out.println(dp[N][N][1]);
    }
}
