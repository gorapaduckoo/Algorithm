import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1912_연속합 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+1];
		int[] dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = Integer.MIN_VALUE;
		for (int i=1; i<=N; i++) {
			dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
			ans = Math.max(dp[i], ans);
		}
		
		System.out.println(ans);
	}
}
