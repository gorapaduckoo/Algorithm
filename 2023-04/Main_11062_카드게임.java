import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11062_카드게임 {
	static int T, N;
	static int[] card;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		while(T>0) {
			N = Integer.parseInt(br.readLine());
			card = new int[N];
			boolean isMyTurn = false;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				card[i] = Integer.parseInt(st.nextToken());
			}
			
			dp = new int[N][N];
			if(N%2==1) {
				for (int i=0; i<N; i++) {
					dp[i][i] = card[i];
				}
			} else {
				isMyTurn = true;
			}
			
			
			for(int k=1; k<N; k++) {
				for (int i=0; i<N-k; i++) {
					int j=i+k;
					if(isMyTurn) {
						dp[i][j] = Math.max(dp[i][j-1]+card[j], dp[i+1][j]+card[i]); 
					} else {
						dp[i][j] = Math.min(dp[i][j-1], dp[i+1][j]);
					}
				}
				isMyTurn = !isMyTurn;
			}
			
			System.out.println(dp[0][N-1]);
			
			T--;
		}
	}
}
