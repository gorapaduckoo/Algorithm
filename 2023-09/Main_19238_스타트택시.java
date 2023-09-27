import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
	int x;
	int y;
	int dist;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, int dist) {
		this(x, y);
		this.dist = dist;
	}

	@Override
	public int compareTo(Point o) {
		if(this.x == o.x) return this.y - o.y;
		return this.x - o.x;
	}
}

public class Main_19238_스타트택시 {
	static int N, M, fuel, taxiX, taxiY, MAX_FUEL = 500_000;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static Point[] destination;
	static int[][] isWall, map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		destination = new Point[M+1];
		isWall = new int[N][N];
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				isWall[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxiX = Integer.parseInt(st.nextToken())-1;
		taxiY = Integer.parseInt(st.nextToken())-1;
		
		for (int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = i;
			
			destination[i] = new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		}
		
		int passengerCount = 0;
		while(passengerCount < M) {
			// 1. 가장 가까운 승객 찾기
			Point passenger = findPassenger();
			if(!canMove(passenger)) {
				fuel = -1;
				break;
			}
			// 2. 승객 태우러 이동
			move(passenger.x, passenger.y, passenger.dist);
			int idx = map[passenger.x][passenger.y];
			map[passenger.x][passenger.y] = 0;
			
			// 3. 목적지까지의 경로 찾기
			Point dest = findDest(destination[idx].x, destination[idx].y);
			if(!canMove(dest)) {
				fuel = -1;
				break;
			}
			
			// 4. 목적지까지 이동
			move(dest.x, dest.y, dest.dist);
			fuel += (dest.dist*2);
			passengerCount++;
		}
		
		System.out.println(fuel);
	}
	
	static Point findPassenger() {
		boolean[][] visited = new boolean[N][N];
		Queue<Point> q = new ArrayDeque<>();
		PriorityQueue<Point> pq = new PriorityQueue<>();
		q.add(new Point(taxiX, taxiY, 0));
		visited[taxiX][taxiY] = true;
		
		int minDist = N*N+1;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			if(map[now.x][now.y]!=0 && now.dist<=minDist) {
				pq.add(now);
				minDist = Math.min(now.dist, minDist);
			}
			
			if(now.dist > minDist) continue;
			
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				if(visited[nx][ny] || isWall[nx][ny]!=0) continue;
				q.add(new Point(nx, ny, now.dist+1));
				visited[nx][ny] = true;	
			}
		}
		
		if(!pq.isEmpty()) return pq.poll();
		return null;
	}
	
	static Point findDest(int destX, int destY) {
		boolean[][] visited = new boolean[N][N];
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(taxiX, taxiY, 0));
		visited[taxiX][taxiY] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			if(now.x==destX && now.y==destY) {
				return now;
			}
			
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				if(visited[nx][ny] || isWall[nx][ny]!=0) continue;
				q.add(new Point(nx, ny, now.dist+1));
				visited[nx][ny] = true;
			}
		}
		
		return null;
	}
	
	static boolean canMove(Point p) {
		if(p==null || fuel < p.dist) return false;
		return true;
	}
	
	static void move(int destX, int destY, int dist) {
		taxiX = destX;
		taxiY = destY;
		fuel -= dist;
	}
}
