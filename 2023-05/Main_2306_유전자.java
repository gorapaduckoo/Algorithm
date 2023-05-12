import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2306_유전자 {
	static int N;
	static char[] dna;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dna = br.readLine().toCharArray();
		// 1. 삭제하거나 2. 삭제하지 않거나
		N = dna.length;
		int[][] dp = new int[N][N];
		
		for (int i=0; i<N-1; i++) {
			if(dna[i]=='a'&&dna[i+1]=='t') {
				dp[i][i+1] = 2;
				continue;
			}
			if(dna[i]=='g'&&dna[i+1]=='c') {
				dp[i][i+1] = 2;
				continue;
			}
		}
		
		for (int k=2; k<N; k++) {
			for (int i=0; i<N-k; i++) {
				if((dna[i]=='a'&&dna[i+k]=='t') || (dna[i]=='g'&&dna[i+k]=='c')) {
					dp[i][i+k] = dp[i+1][i+k-1]+2;
				}
				for(int j=i; j<=i+k; j++) {
					dp[i][i+k] = Math.max(dp[i][i+k], dp[i][j]+dp[j][i+k]);
				}
			}
		}
		
		System.out.println(dp[0][N-1]);
	}
}
