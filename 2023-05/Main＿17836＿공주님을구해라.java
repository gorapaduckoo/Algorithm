import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Soldier {
	int x;
	int y;
	int time;
	int getGram;
	Soldier(int x, int y, int time, int getGram) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.getGram = getGram;
	}
}

public class Main＿17836＿공주님을구해라 {
	static int N, M, T;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] castle;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T= Integer.parseInt(st.nextToken());
		
		castle = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = bfs(0,0);
		if(answer>T) System.out.println("Fail");
		else System.out.println(answer);
		
	}
	
	static int bfs(int x, int y) {
		boolean[][][] visited = new boolean[N][M][2];
		Queue<Soldier> q = new ArrayDeque<>();
		
		int result = Integer.MAX_VALUE;
		
		visited[0][0][0] = true;
		q.add(new Soldier(0, 0, 0, 0));
		while(!q.isEmpty()) {
			Soldier now = q.poll();
			if(now.x==N-1 && now.y==M-1) {
				result = Math.min(result, now.time);
			}
			if(castle[now.x][now.y]==2) {
				now.getGram = 1;
			}
	
			for (int i=0; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny][now.getGram]) continue;
				if(now.getGram==0 && castle[nx][ny]==1) continue;
				q.add(new Soldier(nx, ny, now.time+1, now.getGram));
				visited[nx][ny][now.getGram] = true;
			}
			
		}
		
		return result;
	}
}
