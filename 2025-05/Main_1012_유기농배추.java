import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1012_유기농배추 {
	
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int T, N, M, K;
	
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static boolean[][] isKimchi;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int answer = 0;
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			isKimchi = new boolean[N][M];
			visited = new boolean[N][M];
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				isKimchi[x][y] = true;
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (isKimchi[i][j] && !visited[i][j]) {
						searchKimchi(i, j);
						answer++;
					}
				}
			}
			
			sb.append(answer + "\n");
			T--;
		}
		
		System.out.print(sb.toString());
	}
	
	public static void searchKimchi(int x, int y) {
		Queue<Point> q = new ArrayDeque<Point>();
		
		q.add(new Point(x, y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Point point = q.poll();
			
			for (int i=0; i<4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];

				if (nx<0 || ny<0 || nx>=N || ny>=M || !isKimchi[nx][ny]) continue;
				if (visited[nx][ny]) continue;
				
				q.add(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
}
