import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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

public class Main_16234_인구이동 {
	static int N, L, R, updatedCnt;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] arr;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(true) {
			updatedCnt = 0;
			visited = new boolean[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if(visited[i][j]) continue;
					open(i,j);
				}
			}
			
			if(updatedCnt==0) break;
			time++;
		}
		System.out.println(time);
		
	}
	
	static void open(int x, int y) {
		int sum = 0;
		List<Point> union = new ArrayList<>();
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(x,y));
		union.add(new Point(x,y));
		visited[x][y] = true;
		sum += arr[x][y];
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny]) continue;
				int diff = Math.abs(arr[now.x][now.y] - arr[nx][ny]);
				if(diff < L || diff > R) continue;
				q.add(new Point(nx,ny));
				union.add(new Point(nx, ny));
				visited[nx][ny] = true;
				sum += arr[nx][ny];
			}
		}
		if(union.size()==1) {
			return;
		}
		updatedCnt++;
		int population = sum / union.size();
		for (Point now : union) {
			arr[now.x][now.y] = population;
		}
		
	}
}
