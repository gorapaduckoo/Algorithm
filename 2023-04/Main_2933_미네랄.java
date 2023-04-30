import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int compareTo(Point o) {
		return o.x - this.x;
	}
}

public class Main_2933_미네랄 {
	static int R, C;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static boolean[][] visited, floated;
	static char[][] cave;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cave = new char[R][C];
		
		for (int i=0; i<R; i++) {
			String str = br.readLine();
			for (int j=0; j<C; j++) {
				cave[i][j] = str.charAt(j);
			}
		}
		
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int x = 0;
		
		for (int k=0; k<N; k++) {
			x = R - Integer.parseInt(st.nextToken());
			if(k%2==0) destroyLeft(x);
			else destroyRight(x);
			
			visited = new boolean[R][C];
			floated = new boolean[R][C];
			boolean isMoved = false;
			for (int i=0; i<R; i++) {
				if(isMoved) break;
				for (int j=0; j<C; j++) {
					if(isMoved) break;
					if(cave[i][j]=='.' || visited[i][j]) continue;
					if(isFloat(i,j)) {
						List<Point> floats = checkFloats(i,j);
						Collections.sort(floats);
						moveDown(floats);
						isMoved = true;
					}
				}
			}
			
		}
		
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(cave[i][j]);
			}
			System.out.println();
		}
	}
	
	static void destroyLeft(int x) {
		int y=0;
		while(y<C) {
			if(cave[x][y]=='x') {
				cave[x][y]='.';
				break;
			}
			y++;
		}
	}
	
	static void destroyRight(int x) {
		int y=C-1;
		while(y>=0) {
			if(cave[x][y]=='x') {
				cave[x][y]='.';
				break;
			}
			y--;
		}
	}
	
	static boolean isFloat(int x, int y) {
		boolean result = true;
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(x,y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Point now = q.poll();
			if(now.x==R-1) {
				result = false;
			}
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=R || ny>=C || cave[nx][ny]=='.' || visited[nx][ny]) continue;
				q.add(new Point(nx,ny));
				visited[nx][ny] = true;
			}
		}
		return result;
	}
	
	static List<Point> checkFloats(int x, int y) {
		List<Point> list = new ArrayList<>();
		boolean[][] check = new boolean[R][C];
		Queue<Point> q = new ArrayDeque<>();
		
		list.add(new Point (x, y));
		q.add(new Point(x, y));
		check[x][y] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=R || ny>=C || cave[nx][ny]=='.' || check[nx][ny]) continue;
				q.add(new Point(nx, ny));
				list.add(new Point(nx, ny));
				check[nx][ny] = true;
			}
		}
		
		for (int i=0; i<list.size(); i++) {
			Point now = list.get(i);
			floated[now.x][now.y] = true;
		}
		return list;
	}
	
	static void moveDown(List<Point> list) {
		boolean flag = true;
		int moveDist = 0;
		
		char[][] newCave = new char[R][C];
		
		while(flag) {
			moveDist++;
			for (int i=0; i<list.size(); i++) {
				Point now = list.get(i);
				int nx = now.x+moveDist;
				int ny = now.y;
				if(nx>=R || (cave[nx][ny]=='x'&&!floated[nx][ny])) {
					flag = false;
					break;
				}
			}
		}
		
		moveDist--;
		for (int i=0; i<list.size(); i++) {
			Point now = list.get(i);
			int nx = now.x+moveDist;
			cave[now.x][now.y] = '.';
			cave[nx][now.y] = 'x';
		}
	}
}
