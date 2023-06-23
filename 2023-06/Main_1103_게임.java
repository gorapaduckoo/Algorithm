import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1103_게임 {
	static int N, M, DIR = 4;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static char[][] board;
	static int[][] step;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		step = new int[N][M];
		visited = new boolean[N][M];
		startAt(0,0);
		
		System.out.println(step[0][0]);
		
	}
	
	static int startAt(int x, int y) {
		if(visited[x][y]) {
			System.out.println(-1);
			System.exit(0);
		}
		if(step[x][y]!=0) return step[x][y];
		
		visited[x][y] = true;
		int maxStep = 1;
		for (int i=0; i<DIR; i++) {
			int nx = x + dx[i]*(board[x][y]-'0');
			int ny = y + dy[i]*(board[x][y]-'0');
			if(nx<0 || ny<0 || nx>=N || ny>=M) {
				continue;
			}
			if(board[nx][ny]=='H') {
				step[nx][ny] = 0;
				continue;
			}
			int val = startAt(nx, ny)+1;
			maxStep = Math.max(maxStep, val);
		}
		
		visited[x][y] = false;
		return step[x][y] = maxStep;
	}
}
