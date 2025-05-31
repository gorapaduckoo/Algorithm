import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3257_발코딩 {
	static String word1, word2, str;
	static int N, M, K;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		word1 = br.readLine();
		word2 = br.readLine();
		str = br.readLine();
		
		N = word1.length();
		M = word2.length();
		K = str.length();
		
		dp = new int[N+1][M+1];
		dp[0][0] = 1;
		
		for (int i=0; i<=N; i++) {
			for (int j=0; j<=M; j++) {
				if (i < N && dp[i][j] != 0 && word1.charAt(i) == str.charAt(i+j)) {
					dp[i+1][j] = 1;
				}
				if (j < M && dp[i][j] != 0 && word2.charAt(j) == str.charAt(i+j)) {
					dp[i][j+1] = 2;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int i = N, j = M;
		while (i>0 || j>0) {
			if (dp[i][j] == 1) {
				sb.append('1');
				i--;
			}else if (dp[i][j] == 2) {
				sb.append('2');
				j--;
			}
		}
		
		System.out.println(sb.reverse().toString());
	}
	
}
