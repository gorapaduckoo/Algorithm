import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Bus {
	int line;
	int dist;
	
	public Bus(int line, int dist) {
		this.line = line;
		this.dist = dist;
	}
}

class Line {
	int startX, startY;
	int endX, endY;
	
	public Line(int x1, int y1, int x2, int y2) {
		this.startX = Math.min(x1, x2);
		this.startY = Math.min(y1, y2);
		this.endX = Math.max(x1, x2);
		this.endY = Math.max(y1, y2);
	}
	
	public boolean isHorizonal() {
		return startY == endY;
	}
	
	public boolean hasStation(int x, int y) {
		if (!isHorizonal()) {
			return startX == x && startY <= y && y <= endY;
		}
		return startY == y && startX <= x && x <= endX;
	}
	
	public boolean canTransfer(Line bus) {
		if (isHorizonal() && bus.isHorizonal() && startY == bus.startY) {
			return (startX <= bus.startX && bus.startX <= endX)
					|| (startX <= bus.endX && bus.endX <= endX);
		}
		if (isHorizonal() && !bus.isHorizonal()) {
			return (startX <= bus.startX && bus.startX <= endX)
					&& (bus.startY <= startY && startY <= bus.endY);
		}
		if (!isHorizonal() && bus.isHorizonal()) {
			return (bus.startX <= startX && startX <= bus.endX)
					&& (startY <= bus.startY && bus.startY <= endY);
		}
		if (!isHorizonal() && !bus.isHorizonal() && startX == bus.startX) {
			return (startY <= bus.startY && bus.startY <= endY)
					|| (startY <= bus.endY && bus.endY <= endY);
		}
		
		return false;
		
	}
}

public class Main_2536_버스갈아타기 {
	static int N, M, K;
	static int startX, startY, targetX, targetY;
	static Line[] lines;
	static boolean[] visited;
	static boolean[][] canTransfer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		lines = new Line[K];
		visited = new boolean[K];
		canTransfer = new boolean[K][K];
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			lines[idx] = new Line(x1, y1, x2, y2);
		}
		
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		targetX = Integer.parseInt(st.nextToken());
		targetY = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<K; i++) {
			for (int j=0; j<i; j++) {
				if (lines[i].canTransfer(lines[j])) {
					canTransfer[i][j] = true;
					canTransfer[j][i] = true;
				}
			}
		}
		
		Queue<Bus> q = new ArrayDeque<>();
		
		for (int i=0; i<K; i++) {
			if (lines[i].hasStation(startX, startY)) {
				q.add(new Bus(i, 1));
				visited[i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			Bus bus = q.poll();
			if (lines[bus.line].hasStation(targetX, targetY)) {
				System.out.println(bus.dist);
				break;
			}
			
			for (int nextLine = 0; nextLine < K; nextLine++) {
				if (visited[nextLine] || !canTransfer[bus.line][nextLine]) continue;
				q.add(new Bus(nextLine, bus.dist+1));
				visited[nextLine] = true;
			}
		}
	}
}

// (x1, y1) (x2, y2) / (x3, y3)