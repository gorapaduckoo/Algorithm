import java.util.Arrays;

class Programmers_코딩테스트공부 {
    int[][] dp;
    int MAX = 150;
    int maxAlp=0, maxCop=0;
    public int solution(int alp, int cop, int[][] problems) {
        for(int i=0; i<problems.length; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }
        if(alp>=maxAlp && cop>=maxCop) return 0;
        
        maxAlp = Math.max(maxAlp, alp);
        maxCop = Math.max(maxCop, cop);
        dp = new int[maxAlp+1][maxCop+1];
        
        for(int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i], MAX*2);
        }
        for(int i=0; i<=alp; i++) {
            for(int j=0; j<=cop; j++) {
                dp[i][j] = 0;
            }
        }
        
        for (int i=0; i<=maxAlp; i++) {
            for (int j=0; j<=maxCop; j++) {
                for(int k=0; k<problems.length; k++) {
                	
                    if(i>0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+1);
                    }
                    
                    if(j>0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+1);
                    }

                    if(i>=problems[k][0]&&j>=problems[k][1]) {
                        int nextAlp = Math.min(maxAlp, i+problems[k][2]);
                        int nextCop = Math.min(maxCop, j+problems[k][3]);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j]+problems[k][4]);
                    }
                }


            }
        }
            
        return dp[maxAlp][maxCop];
    }
}