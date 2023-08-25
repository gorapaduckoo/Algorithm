import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10942_팰린드롬 {
	static int N, M;
	static int[] num;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		dp = new int[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			dp[i][i] = 1;
			if(i<N-1 && num[i]==num[i+1]) {
				dp[i][i+1] = 1;
			}
		}
		
		for (int k=2; k<N; k++) {
			for (int i=0; i<N-k; i++) {
				int j = i+k;
				if(dp[i+1][j-1]==1 && num[i]==num[j]) {
					dp[i][j] = 1;
				}
			}
		}
		
		
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			sb.append(dp[s][e] + "\n");
		}
		System.out.println(sb.toString());
	}
}
