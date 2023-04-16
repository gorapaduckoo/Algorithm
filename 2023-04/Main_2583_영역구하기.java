import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
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

public class Main_2583_영역구하기 {
	static int N, M, K;
	static int[] dx = {-1,1,0,0}, dy= {0,0,-1,1};
	static List<Integer> answer;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		answer = new ArrayList<>();
		visited = new boolean[N][M];
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			fill(x1, y1, x2, y2);			
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(!visited[i][j]) {
					answer.add(bfs(i,j));
				}
			}
		}
		
		
		Collections.sort(answer);
		System.out.println(answer.size());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<answer.size(); i++) {
			sb.append(answer.get(i) + " ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	static void fill(int x1, int y1, int x2, int y2) {
		for (int i=x1; i<x2; i++) {
			for (int j=y1; j<y2; j++) {
				visited[i][j] = true;
			}
		}
	}
	
	static int bfs(int x, int y) {
		int result = 0;
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(x, y));
		visited[x][y]=true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			visited[now.x][now.y]=true;
			result++;
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny]) continue;
				q.add(new Point(nx,ny));
				visited[nx][ny] = true;
			}
		}
		
		return result;
	}
}
