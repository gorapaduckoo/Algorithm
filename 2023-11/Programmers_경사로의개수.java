import java.util.Arrays;

public class Programmers_경사로의개수 {
	long[][][][][] route;
    int[][][][][] dp;
    long[][] answer;
    int N, M, K=1_000_000_007;
    int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    public int solution(int[][] grid, int[] d, int k) {
        
        N = grid.length;
        M = grid[0].length;
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(k));
        String str = sb.reverse().toString();
        
        route = new long[N][M][N][M][str.length()];
        dp = new int[N][M][N][M][d.length];
        answer = new long[N][M];
        
        for (int i=0; i<N; i++) {
            Arrays.fill(answer[i], 1);
        }
        
        for (int hop=0; hop<d.length; hop++) {
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    findRoute(i, j, hop, grid, d);
                }
            }
        }
        
        for (int startX=0; startX<N; startX++) {
            for (int startY=0; startY<M; startY++) {
                for (int endX=0; endX<N; endX++) {
                    for (int endY=0; endY<M; endY++) {
                        route[startX][startY][endX][endY][0] = dp[startX][startY][endX][endY][d.length-1];
                    }
                }
            }
        }
        
        for (int repeat = 1; repeat<str.length(); repeat++) {
            for (int start=0; start<N*M; start++) {
                int startX = start/M;
                int startY = start%M;
                
                for (int end=0; end<N*M; end++) {
                    int endX = end/M;
                    int endY = end%M;
                    
                    for (int stopOver=0; stopOver<N*M; stopOver++) {
                        int stopOverX = stopOver/M;
                        int stopOverY = stopOver%M;
                        
                        route[startX][startY][endX][endY][repeat] += (route[startX][startY][stopOverX][stopOverY][repeat-1]%K * route[stopOverX][stopOverY][endX][endY][repeat-1]%K)%K;
                        route[startX][startY][endX][endY][repeat]%=K;
                    }
                }
            }
        }
        
        countRoute(str);
        
        long ans = 0;
        for (int endX=0; endX<N; endX++) {
            for (int endY=0; endY<M; endY++) {
                ans += (answer[endX][endY]%K);
                ans %= K;
            }
        }
        return (int) ans;
    }
    
    void findRoute(int x, int y, int hop, int[][] grid, int[] d) {
        for (int k=0; k<4; k++) {
            int nx = x+dx[k];
            int ny = y+dy[k];
            
            if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
            if(grid[nx][ny]-grid[x][y] != d[hop]) continue;
            if(hop==0) {
                dp[x][y][nx][ny][hop] = 1;
            } else {
                for (int i=0; i<N; i++) {
                    for (int j=0; j<M; j++) {
                        dp[i][j][nx][ny][hop] += dp[i][j][x][y][hop-1];
                        dp[i][j][nx][ny][hop] %= K;
                    }
                }
            }
        }
    }
    
    void countRoute(String str) {
        for (int idx=0; idx<str.length(); idx++) {
            if(str.charAt(idx)=='0') continue;
            
            long[][] newAnswer = new long[N][M];
            
            for (int start=0; start<N*M; start++) {
                int startX = start/M;
                int startY = start%M;
                
                for (int end=0; end<N*M; end++) {
                    int endX = end/M;
                    int endY = end%M;
                        
                    newAnswer[endX][endY] += (answer[startX][startY]%K) * (route[startX][startY][endX][endY][idx]%K);
                    newAnswer[endX][endY]%=K;
                }
            }
            
            answer = newAnswer;
        }
        
    }
}
