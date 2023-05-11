import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	int time;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_17141_연구소2 {
	static int N, M, ans = Integer.MAX_VALUE;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] lab;
	static boolean[][] pick;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][N];
		pick = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(lab[i][j]==2) pick(i,j,1);
			}
		}
		
		if(ans==Integer.MAX_VALUE)
			System.out.println(-1);
		else System.out.println(ans);
	}
	
	static void pick(int x, int y, int cnt) {
		pick[x][y] = true;

		if(cnt==M) {
			ans = Math.min(ans, spreadVirus());
			pick[x][y] = false;
			return;
		}
		
		for (int i=x; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(lab[i][j]==2&&!pick[i][j]) pick(i,j, cnt+1);
			}
		}
		pick[x][y] = false;
	}
	
	static int spreadVirus() {
		int time = -1;
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(pick[i][j]) {
					q.add(new Point(i,j));
					visited[i][j] = true;
				}
			}
		}
		
		while(!q.isEmpty()) {
			time++;
			int size = q.size();
			for (int i=0; i<size; i++) {
				Point now = q.poll();
				for (int k=0; k<4; k++) {
					int nx = now.x+dx[k];
					int ny = now.y+dy[k];
					if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
					if(visited[nx][ny] || lab[nx][ny]==1) continue;
					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
		
		if(spreadAll(visited)) 
			return time;
		else 
			return Integer.MAX_VALUE;
	}
	
	static boolean spreadAll(boolean[][] visited) {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(!visited[i][j] && lab[i][j]!=1) return false;
			}
		}
		return true;
	}
}
