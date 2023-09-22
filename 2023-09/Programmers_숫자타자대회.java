import java.util.Arrays;

public class Programmers_숫자타자대회 {
    int[][] cost = { {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
                    {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
                    {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
                    {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
                    {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
                    {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
                    {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
                    {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
                    {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
                    {3, 6, 5, 4, 5, 3, 2, 4, 2, 1} };
    int N, MAX = 300_000;
    public int solution(String numbers) {
        int answer = MAX+1;
        N = numbers.length();
        int[][][] dp = new int[N+1][10][10];
        for (int i=0; i<=N; i++) {
            for (int j=0; j<10; j++) {
                Arrays.fill(dp[i][j], MAX+1);
            }
        }
        
        dp[0][4][6] = 0;
        for(int i=0; i<N; i++) {
            int next = numbers.charAt(i)-'0';
            for(int l=0; l<10; l++) {
                for (int r=0; r<10; r++) {
                    if(l==r) continue;
                    dp[i+1][next][r] = Math.min(dp[i+1][next][r], dp[i][l][r]+cost[l][next]);
                    dp[i+1][l][next] = Math.min(dp[i+1][l][next], dp[i][l][r]+cost[r][next]);
                }
            }
        }
                               
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                answer = Math.min(answer, dp[N][i][j]);
            }
        }
        return answer;
    }
}