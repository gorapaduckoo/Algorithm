import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Coin {
	int[] x = new int[2];
	int[] y = new int[2];
	int cnt;
	public Coin(int x1, int y1, int x2, int y2, int cnt) {
		this.x[0] = x1;
		this.x[1] = x2;
		this.y[0] = y1;
		this.y[1] = y2;
		this.cnt = cnt;
	}
}

public class Main_16197_두동전 {
	static int N, M, answer = -1;
	static Queue<Coin> q;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] board;
	static boolean[][][][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		q = new ArrayDeque<Coin>();
		board = new int[N][M];
		visited = new boolean[N][M][N][M];
		
		Coin coin = new Coin(-1,-1,-1,-1,0);
		int coinCnt = 0;
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				board[i][j] = str.charAt(j);
				if(board[i][j]=='o') {
					coin.x[coinCnt] = i;
					coin.y[coinCnt] = j;
					coinCnt++;
				}
			}
		}
		
		q.add(coin);
		visited[coin.x[0]][coin.y[0]][coin.x[1]][coin.y[1]] = true;
		
		while(!q.isEmpty() && answer==-1) {
			Coin now = q.poll();
			if(now.cnt==10) continue;
			
			for (int i=0; i<4; i++) {
				int nx1 = now.x[0] + dx[i];
				int nx2 = now.x[1] + dx[i];
				int ny1 = now.y[0] + dy[i];
				int ny2 = now.y[1] + dy[i];
				
				if(isClear(nx1, ny1, nx2, ny2)) {
					answer = now.cnt+1;
					break;
				}
				
				if(isFall(nx1,ny1)&&isFall(nx2,ny2)) continue;
				if(isWall(nx1,ny1)) {
					nx1-=dx[i];
					ny1-=dy[i];
				}
				if(isWall(nx2,ny2)) {
					nx2-=dx[i];
					ny2-=dy[i];
				}
				if(visited[nx1][ny1][nx2][ny2]) continue;
				q.add(new Coin(nx1,ny1,nx2,ny2, now.cnt+1));
				visited[nx1][ny1][nx2][ny2] = true;
			}
		}
		
		System.out.println(answer);
		
	}
	
	static private boolean isClear(int x1, int y1, int x2, int y2) {
		if(isFall(x1,y1)^isFall(x2,y2)) return true;
		return false;
	}
	
	static private boolean isFall(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=M) return true;
		return false;
	}
	
	static private boolean isWall(int x, int y) {
		if(board[x][y] == '#') return true;
		return false;
	}
}
