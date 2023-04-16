package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	int dist;
	
	public Point(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

public class Main_2589_보물섬 {
	static int N, M, answer=0;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static char[][] map;
	static boolean[][] visitedOrigin;
	static Queue<Point> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visitedOrigin = new boolean[N][M];
		q = new ArrayDeque<Point>();
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'W') {
					visitedOrigin[i][j] = true;
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(map[i][j] == 'L') {
					answer = Math.max(answer, bfs(i,j, copyVisited()));
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static int bfs(int x, int y, boolean[][] visited) {
		visited[x][y] = true;
		q.add(new Point(x, y, 0));
		
		int result = 0;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			result = Math.max(result, now.dist);
			
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny]) continue;
				q.add(new Point(nx,ny,now.dist+1));
				visited[nx][ny] = true;
			}
		}
		
		return result;
		
	}
	
	static boolean[][] copyVisited() {
		boolean[][] result = new boolean[N][M];
		for (int i=0; i<N; i++) {
			result[i] = Arrays.copyOf(visitedOrigin[i], M);
		}
		return result;
	}
}
