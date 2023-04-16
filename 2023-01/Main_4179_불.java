import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	int time;
	
	public Point(int x, int y, int time) {
		this.x=x;
		this.y=y;
		this.time=time;
	}
}

public class Main_4179_불 {
	static int R, C, nowTime = 0;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static char[][] maze;
	static boolean[][] visited;
	static Queue<Point> q = new ArrayDeque<Point>(), fireQueue = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maze = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for (int j=0; j<C; j++) {
				maze[i][j] = str.charAt(j);
				if (maze[i][j] == 'F') {
					fireQueue.add(new Point(i, j, 0));
				}
				else if(maze[i][j] == 'J') {
					q.add(new Point(i,j,0));
					visited[i][j] = true;
				}
			}
		}
		
		while(true) {
			// 불 확산
			fire();
			// 지훈이 이동
			if(move()) {
				System.out.println(nowTime+1);
				break;
			}
			
			nowTime++;
			if(q.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				break;
			}
		}
		
	}
	
	private static void fire() {
			int qSize = fireQueue.size();
			for (int k=0; k<qSize; k++) {
				Point nowFire = fireQueue.poll();
				int x = nowFire.x;
				int y = nowFire.y;
				int time = nowFire.time;
				
				for (int i=0; i<4; i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					if(nx<0 || ny<0 || nx>=R || ny>=C || maze[nx][ny] == 'F' || maze[nx][ny] =='#') continue;
					fireQueue.add(new Point(nx,ny,time+1));
					maze[nx][ny] = 'F';
				}
			}
	}
	
	private static boolean move() {
			int qSize = q.size();
				for (int k=0; k<qSize; k++) {
				Point now = q.poll();
				int x = now.x;
				int y = now.y;
				int time = now.time;
				
//				System.out.println("now: " + x + ", " + y + "," + nowTime);
				
				if(x==0 || y==0 || x==R-1 || y==C-1 ) return true;
				
				for (int i=0; i<4; i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
//					System.out.println("next: " + nx + ", " + ny + ", " + visited[nx][ny]);
					if (nx<0 || ny<0 || nx>=R || ny>=C || maze[nx][ny]!='.' || visited[nx][ny] ) continue;
					q.add(new Point(nx,ny, time+1));
					visited[nx][ny] = true;
				}
			}
		return false;
	}
}
