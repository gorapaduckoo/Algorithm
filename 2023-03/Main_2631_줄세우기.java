import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2631_줄세우기 {
	static int N;
	static int[] arr, dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int ans = 0;
		Arrays.fill(dp, 1);
		for (int i=1; i<N; i++) {
			for (int j=0; j<i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[j]+1, dp[i]);
				}
			}
			ans = Math.max(dp[i], ans);
		}
		
		System.out.println(N-ans);
	}
}
