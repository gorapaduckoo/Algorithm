import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	int time;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

public class Main_17142_연구소3 {
	static int N, M, ans = Integer.MAX_VALUE;
	static List<Point> location;
	static Point[] activeVirus;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		location = new ArrayList<>();
		activeVirus = new Point[M];
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) location.add(new Point(i,j));
			}
		}
		
		pick(0,0);
		
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static void pick(int cnt, int now) {
		if(cnt==M) {
			spread();
			return;
		}
		for (int i=now; i<location.size(); i++) {
			activeVirus[cnt] = location.get(i);
			pick(cnt+1,i+1);
		}
	}
	
	static void spread() {
		boolean[][] visited = new boolean[N][N];
		int[][] spreadTime = new int[N][N];
		for (int i=0; i<N; i++) {
			Arrays.fill(spreadTime[i], -1);
		}
		Queue<Point> q = new ArrayDeque<>();
		
		for (int i=0; i<M; i++) {
			Point now = activeVirus[i];
			q.add(new Point(now.x, now.y, 0));
			visited[now.x][now.y] = true;
			spreadTime[now.x][now.y] = 0;
		}
		while(!q.isEmpty()) {
			Point now = q.poll();
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny] || map[nx][ny]==1) continue;
				
				visited[nx][ny]=true;
				spreadTime[nx][ny]=now.time+1;
				q.add(new Point(nx, ny, now.time+1));
			}
		}
		
		int maxTime = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(map[i][j]!=0) continue;
				if(spreadTime[i][j]==-1) return;
				maxTime = Math.max(maxTime, spreadTime[i][j]);
			}
		}
		
		ans = Math.min(ans, maxTime);
	}
}
