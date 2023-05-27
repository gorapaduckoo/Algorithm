import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_16724_피리부는사나이 {
	static int N, M;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static char[][] map;
	static boolean[][] visited;
	static int[][] regionNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		regionNum = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		int num = 1;
		int answer = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(regionNum[i][j]==0) {
					boolean isNewCycle = move(i, j, num);
					if(isNewCycle) answer++;
					num++;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static boolean move(int x, int y, int cnt) {
		Queue<Point> q = new ArrayDeque<>();
		
		
		q.add(new Point(x,y));
		visited[x][y] = true;
		regionNum[x][y] = cnt;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			int dir = findDirection(now.x, now.y);
			int nx = now.x + dx[dir];
			int ny = now.y + dy[dir];
			if(visited[nx][ny]) {
				if(regionNum[nx][ny]==cnt) return true;
				return false;
			}
			q.add(new Point(nx, ny));
			visited[nx][ny] = true;
			regionNum[nx][ny] = cnt;
		}
		return false;
	}
	
	static int findDirection(int x, int y) {
		if(map[x][y]=='U') return 0;
		else if(map[x][y]=='D') return 1;
		else if(map[x][y]=='L') return 2;
		else return 3;
	}
}
