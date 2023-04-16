import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1520_내리막길 {
	static int N, M;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] map, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		
		for (int i=0; i<N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(0,0);
		System.out.println(dp[0][0]);
		
	}
	
	public static int move(int x, int y) {
		if(x==N-1 && y==M-1) return dp[x][y] = 1;
		if(dp[x][y]!=-1) return dp[x][y];
		dp[x][y] = 0;
		for (int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny]>=map[x][y]) continue;
			dp[x][y] += move(nx,ny);
		}
		return dp[x][y];
	}
}
