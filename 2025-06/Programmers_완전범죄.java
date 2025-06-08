import java.util.*;

public class Programmers_완전범죄 {
	int N, MAX = 121;
    int[][] dp;
    
    public int solution(int[][] info, int n, int m) {
        N = info.length;
        
        dp = new int[N][MAX];
        for (int i=0; i<N; i++) {
            Arrays.fill(dp[i], MAX);
        }
        
        dp[0][0] = info[0][0];
        dp[0][info[0][1]] = 0;
        
        for (int i=1; i<N; i++) {
            for (int j=0; j<m; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+info[i][0]);
                
                if (j < info[i][1]) continue;
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j-info[i][1]]);
                
            }
        }
        
        int answer = MAX;
        for (int i=0; i<m; i++) {
            answer = Math.min(answer, dp[N-1][i]);
        }
        
        if (answer >= n) {
            answer = -1;
        }
        
        return answer;
    }
}
