import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_2638_치즈 {
	static int N, M;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static boolean[][] cheese, isOutside;
	static Queue<Point> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new boolean[N][M];
		isOutside = new boolean[N][M];
		q = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n==1) cheese[i][j] = true;
			}
		}
		
		bfs(0,0);
		bfs(N-1,0);
		bfs(0,M-1);
		bfs(N-1,M-1);
		
		checkOutside();
		
		int time = 0;
		while(!q.isEmpty()) {
			int n = q.size();
			for(int i=0; i<n; i++) {
				Point now = q.poll();
				bfs(now.x, now.y);
				cheese[now.x][now.y] = false;
				isOutside[now.x][now.y] = true;
			}
			checkOutside();
			time++;
		}
		System.out.println(time);
		
		
	}
	
	static void checkOutside() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(cheese[i][j]) {
					int cnt = 0;
					for (int k=0; k<4; k++) {
						int x = i+dx[k];
						int y = j+dy[k];
						if(x<0 || y<0 || x>=N || y>=M || !isOutside[x][y]) continue;
						cnt++;
					}
					if(cnt>=2) q.add(new Point(i,j));
				}
			}
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Point> airQ = new ArrayDeque<>();
		airQ.add(new Point(x, y));
		isOutside[x][y] = true;
		while(!airQ.isEmpty()) {
			Point now = airQ.poll();
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || isOutside[nx][ny] || cheese[nx][ny]) continue;
				airQ.add(new Point(nx, ny));
				isOutside[nx][ny] = true;
			}
		}
	}
}
