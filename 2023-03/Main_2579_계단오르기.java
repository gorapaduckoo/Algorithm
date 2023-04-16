import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2579_계단오르기 {
	static int N;
	static int[] stair, dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stair = new int[N+1];
		dp = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i=1; i<=N; i++) {
			if(i<=2) {
				dp[i] = dp[i-1]+stair[i];
			} else {
				dp[i] = Math.max(dp[i-2]+stair[i], dp[i-3]+stair[i-1]+stair[i]);
				
			}
		}
		System.out.println(dp[N]);
		
	}
}
