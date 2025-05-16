
public class Programmers_거스름돈 {
	int N, K;
    int[][] dp;
    public int solution(int n, int[] money) {
        
        init(n, money);
        
        for (int i=0; i<K; i++) {
            for (int j=1; j<=N; j++) {
                if (i == 0) {
                    if (j % money[i] == 0) {
                        dp[i][j] = 1;
                    }
                    continue;
                }
                
                if (j < money[i]) {
                    dp[i][j] += dp[i-1][j];
                    continue;
                }
                
                dp[i][j] += dp[i-1][j];
                dp[i][j] += dp[i][j-money[i]];
                dp[i][j] %= 1_000_000_007;
            }
        }
        
        return dp[K-1][N];
    }
    
    void init(int n, int[] money) {
        N = n;
        K = money.length;
        dp = new int[K][N+1];
        
        for (int i=0; i<K; i++) {
            dp[i][0] = 1;
        }
    }
}
