import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2133_타일채우기 {
	static int N;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[31];
		if(N%2!=0) {
			System.out.println(0);
			System.exit(0);
		}
		dp[2] = 3;
		
		
		for(int i=4; i<=N; i+=2) {
			for (int j=2; j<i; j+=2) {
				if(j==2) dp[i] += dp[i-j]*3;
				else dp[i] += dp[i-j]*2;
			}
			dp[i] += 2;
		}
		
		System.out.println(dp[N]);
		
	}
}
