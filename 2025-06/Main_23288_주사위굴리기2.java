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

class Dice extends Point {
	int[] face = {0, 1, 2, 3, 4, 5, 6};
	int dir;
	
	public Dice(int x, int y, int dir) {
		super(x, y);
		this.dir = 0;
	}
}

public class Main_23288_주사위굴리기2 {

	static int N, M, K, score = 0;
	static int EAST = 0, SOUTH = 1, WEST = 2, NORTH = 3;
	static Dice dice = new Dice(0, 0, 0);
	
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	static boolean[][] visited;
	static int[][] map, movableCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		map = new int[N][M];
		movableCnt = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(!visited[i][j]) {
					calculateMovableCnt(i, j, map[i][j]);
				}
			}
		}
		
		for (int i=0; i<K; i++) {
			move();
			score += calculateScore();
			changeDir();
		}
		
		System.out.println(score);
	}
	
	static void calculateMovableCnt(int x, int y, int num) {
		Queue<Point> q = new ArrayDeque<Point>();
		List<Point> points = new ArrayList<>();
		int cnt = 0;
		
		q.add(new Point(x, y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			points.add(now);
			cnt++;
			
			for (int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny]) continue;
				if (map[nx][ny] != num) continue;
				
				q.add(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
		
		for (Point point : points) {
			movableCnt[point.x][point.y] = cnt;
		}
	}
	
	static void move() {
		int[] tmp = new int[7];
		for (int i=1; i<=6; i++) {
			tmp[i] = dice.face[i];
		}
		
		int nx = dice.x + dx[dice.dir];
		int ny = dice.y + dy[dice.dir];
		if (nx<0 || ny<0 || nx>=N || ny>=M) {
			dice.dir = (dice.dir + 2) % 4;
		}
		
		if (dice.dir == EAST) {
			moveEast(tmp);
		} else if (dice.dir == WEST) {
			moveWest(tmp);
		} else if (dice.dir == SOUTH) {
			moveSouth(tmp);
		} else {
			moveNorth(tmp);
		}
		
		dice.x += dx[dice.dir];
		dice.y += dy[dice.dir];
	}
	
	static void moveEast(int[] tmp) {
		dice.face[1] = tmp[4];
		dice.face[3] = tmp[1];
		dice.face[4] = tmp[6];
		dice.face[6] = tmp[3];
	}
	
	static void moveWest(int[] tmp) {
		dice.face[1] = tmp[3];
		dice.face[3] = tmp[6];
		dice.face[4] = tmp[1];
		dice.face[6] = tmp[4];
	}
	
	static void moveSouth(int[] tmp) {
		dice.face[1] = tmp[2];
		dice.face[2] = tmp[6];
		dice.face[5] = tmp[1];
		dice.face[6] = tmp[5];
	}
	
	static void moveNorth(int[] tmp) {
		dice.face[1] = tmp[5];
		dice.face[2] = tmp[1];
		dice.face[5] = tmp[6];
		dice.face[6] = tmp[2];
	}
	
	static int calculateScore() {
		return map[dice.x][dice.y] * movableCnt[dice.x][dice.y];
	}
	
	static void changeDir() {
		if (dice.face[6] > map[dice.x][dice.y]) {
			dice.dir++;
		} else if (dice.face[6] < map[dice.x][dice.y]){
			dice.dir--;
		}
		
		dice.dir += 4;
		dice.dir %= 4;
	}
}
