
public class Programmers_산모양타일링 {
	int[][] dp;
    int K = 10007;
    
    public int solution(int n, int[] tops) {
        int answer = 0;
        
        dp = new int[n][2];
        
        if (tops[0] == 1) {
            dp[0][0] = 1;
            dp[0][1] = 3;
        } else {
            dp[0][0] = 1;
            dp[0][1] = 2;
        }
        
        for (int i=1; i<n; i++) {
            if (tops[i] == 1) {
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % K;
                dp[i][1] = (dp[i-1][0]*2 + dp[i-1][1]*3) % K; 
            } else {
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % K;
                dp[i][1] = (dp[i-1][0] + dp[i-1][1]*2) % K;
            }
        }

        return (dp[n-1][0] + dp[n-1][1]) % K;
    }
}
