import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12785_토쟁이의등굣길 {
	static int w, h, x, y, N = 1000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[h+1][w+1];
		Arrays.fill(dp[1], 1);
		for (int i=1; i<=h; i++) {
			dp[i][1] = 1;
		}
		
		for (int i=2; i<=h; i++) {
			for (int j=2; j<=w; j++) {
				dp[i][j] = (dp[i-1][j]+dp[i][j-1])%N;
			}
		}
		
		System.out.println(dp[y][x]*dp[h-y+1][w-x+1]%N);
		
		
	}
}
