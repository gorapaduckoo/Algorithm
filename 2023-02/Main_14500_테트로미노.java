import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_14500_테트로미노 {
	static int N, M, ans = 0;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] paper;
	static boolean[][] visited;
	static Queue<Point> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		paper = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());			
			for (int j=0; j<M; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				visited = new boolean[N][M];
				visited[i][j] = true;
				search(i,j,0,0);
				check_Tspin(i,j);
				visited[i][j] = false;
			}
		}
		
		System.out.println(ans);
	}
	
	static void search(int x, int y, int cnt, int sum) {
		if(cnt==4) {
			ans = Math.max(sum, ans);
			return;
		}
		for (int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny]) continue;
			visited[nx][ny] = true;
			search(nx, ny, cnt+1, sum+paper[x][y]);
			visited[nx][ny] = false;
		} 
	}
	static void check_Tspin(int x, int y) {
		for (int k=0; k<4; k++) {
			int sum = paper[x][y];
			for (int i=0; i<4; i++) {
				if(k==i) continue;
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M) break;
				sum += paper[nx][ny];
			}
			ans = Math.max(ans, sum);
		}
	}
}
