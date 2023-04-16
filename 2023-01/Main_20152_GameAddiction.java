import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20152_GameAddiction {
	static int H, N, MAX;
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H  =Integer.parseInt(st.nextToken());
		N  =Integer.parseInt(st.nextToken());
		
		if(H==N) {
			System.out.println(1);
			System.exit(0);
		}
		
		MAX = Math.abs(H-N)+1;
		dp = new long[MAX][MAX];
		for (int i=0; i<MAX; i++) {
			for (int j=0; j<MAX; j++) {
				if(j>i) break;
				if(i==0 && j==0) {
					dp[i][j] = 0;
				}else if(i==0 || j==0) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}
			}
		}
		
		System.out.println(dp[MAX-1][MAX-1]);
	}
}
