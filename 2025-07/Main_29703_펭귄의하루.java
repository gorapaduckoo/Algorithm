import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	int dist;
	
	public Point(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

public class Main_29703_펭귄의하루 {
	static int N, M, MAX = 1_000_001;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static int[][] startToFish, homeToFish;
	static char[][] town;
	static final char START = 'S', HOME = 'H', EMPTY = 'E', DANGER = 'D', FISH = 'F';
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		startToFish = new int[N][M];
		homeToFish = new int[N][M];
		town = new char[N][M];
		
		for (int i=0; i<N; i++) {
			Arrays.fill(startToFish[i], MAX);
			Arrays.fill(homeToFish[i], MAX);
		}
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				town[i][j] = str.charAt(j);
			}
		}
		
		calculateStartToFish();
		calculateHomeToFish();
		
		int answer = Integer.MAX_VALUE;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (town[i][j] == FISH) {
					answer = Math.min(answer, startToFish[i][j] + homeToFish[i][j]);
				}
			}
		}
		
		if (answer >= MAX) {
			answer = -1;
		}
		
		System.out.println(answer);
	}
	
	public static void calculateStartToFish() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (town[i][j] == START) {
					bfs(i, j, startToFish, FISH);
					break;
				}
			}
		}
	}
	
	public static void calculateHomeToFish() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (town[i][j] == HOME) {
					bfs(i, j, homeToFish, FISH);
					break;
				}
			}
		}
	}
	
	public static void bfs(int x, int y, int[][] distMap, char target) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		q.add(new Point(x, y, 0));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if (town[now.x][now.y] == target) {
				distMap[now.x][now.y] = now.dist;
				continue;
			}
			
			for (int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (nx<0 || ny<0 || nx>=N || ny>=M) continue;
				if (visited[nx][ny] || town[nx][ny] == DANGER) continue;
				
				q.add(new Point(nx, ny, now.dist+1));
				visited[nx][ny] = true;
			}
		}
	}
}
