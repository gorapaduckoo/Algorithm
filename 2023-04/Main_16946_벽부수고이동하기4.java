import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class Main_16946_벽부수고이동하기4 {
	static int N, M;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static boolean[][] visited;
	static int[][] map, mem, regionNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];
		map = new int[N][M];
		mem = new int[N][M];
		regionNum = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		int num = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(map[i][j]==0 && !visited[i][j]) {
					bfs(i,j, num);
					num++;
				}
			}
		}
		
		System.out.println();
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(map[i][j]==0) {
					sb.append(0);
					continue;
				}
				
				int sum = 1;
				Set<Integer> set = new HashSet<>();
				for (int k=0; k<4; k++) {
					int nx = i+dx[k];
					int ny = j+dy[k];
					if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
					if(set.contains(regionNum[nx][ny])) continue;
					sum+=mem[nx][ny];
					set.add(regionNum[nx][ny]);
				}
				sb.append(sum%10);
			}
			sb.append('\n');
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	static void bfs(int x, int y, int num) {
		int result = 0;
		Queue<Point> q = new ArrayDeque<>();
		Queue<Point> room = new ArrayDeque<>();
		
		q.add(new Point(x, y));
		room.add(new Point(x, y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Point now = q.poll();
			result++;
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny] || map[nx][ny]==1) continue;
				q.add(new Point(nx, ny));
				room.add(new Point(nx, ny));
				visited[nx][ny]=true;
			}
		}
		
		while(!room.isEmpty()) {
			Point now = room.poll();
			mem[now.x][now.y] = result;
			regionNum[now.x][now.y] = num;
		}
	}
}
