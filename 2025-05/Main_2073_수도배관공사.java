import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2073_수도공사 {
	static int D, P;
	static int[] length, capacity;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		length = new int[P+1];
		capacity = new int[P+1];
		dp = new int[D+1];
		
		for (int i=1; i<=P; i++) {
			st = new StringTokenizer(br.readLine());
			length[i] = Integer.parseInt(st.nextToken());
			capacity[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = Integer.MAX_VALUE;
		
		for (int i=1; i<=P; i++) {
			for (int j=D; j>0; j--) {
				if (j < length[i]) break;
				dp[j] = Math.max(dp[j], Math.min(dp[j-length[i]], capacity[i]));
			}
		}
		
		System.out.println(dp[D]);
	}
}
