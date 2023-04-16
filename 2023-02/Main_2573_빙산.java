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
		this.x=x;
		this.y=y;
	}
}

public class Main_2573_빙산 {
	static int N, M, ans;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] ice;
	static boolean[][] visited, melted;
	static Queue<Point> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ice = new int[N][M];
		q = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
				if(ice[i][j]!=0) {
					q.add(new Point(i, j));
				}
			}
		}
		
		int year = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			melted = new boolean[N][M];
			while(size>0) {
				Point now = q.poll();
				int seaCnt = 0;
				for (int i=0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0 || ny<0 || nx>=N || ny>=M || ice[nx][ny]>0 || melted[nx][ny]) continue;
					seaCnt++;
				}
				melted[now.x][now.y] = true;
				if(ice[now.x][now.y]>seaCnt) {
					ice[now.x][now.y] -= seaCnt;
					q.add(now);
				} else {
					ice[now.x][now.y] = 0;
				}
				size--;
			}
			year++;
			if(countIce()>=2 && !q.isEmpty()) {
				ans = year;
				break;
			}
		}
		System.out.println(ans);
	}
	
	static public int countIce() {
		int cnt = 0;
		visited = new boolean[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(ice[i][j]>0 && !visited[i][j]) {
					visited[i][j] = true;
					bfs(i,j);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static public void bfs(int x, int y) {
		Queue<Point> iceQ = new ArrayDeque<>();
		iceQ.add(new Point(x,y));
		while(!iceQ.isEmpty()) {
			Point nowIce = iceQ.poll();
			for (int i=0; i<4; i++) {
				int nx = nowIce.x+dx[i];
				int ny = nowIce.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny] || ice[nx][ny]<=0) continue;
				visited[nx][ny] = true;
				iceQ.add(new Point(nx, ny));
			}
		}
		
	}
}
