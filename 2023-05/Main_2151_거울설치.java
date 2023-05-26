import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Light{
	int x;
	int y;
	int dir;
	int mirrorCnt;
	
	public Light(int x, int y, int dir, int mirrorCnt) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.mirrorCnt = mirrorCnt;
	}

}

public class Main_2151_거울설치 {
	static int N;
	// 상 하 좌 우
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static char[][] house;
	static int[][][] visited;
	static Queue<Light> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		house = new char[N][N];
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				house[i][j] = str.charAt(j);
			}
		}
		
		q = new ArrayDeque<>();
		visited = new int[N][N][4];
		init();
		int answer = installMirror();
		System.out.println(answer);
		
	}
	
	static int installMirror() {
		int result = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Light now = q.poll();
			if(house[now.x][now.y] == '#') {
				result = Math.min(result, now.mirrorCnt);
			}
			if(house[now.x][now.y] == '!') {
				changeDirection(now);
			}
			int nx = now.x+dx[now.dir];
			int ny = now.y+dy[now.dir];
			if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
			if(house[nx][ny]=='*' || visited[nx][ny][now.dir]<=now.mirrorCnt) continue;
			q.add(new Light(nx, ny, now.dir, now.mirrorCnt));
			visited[nx][ny][now.dir] = now.mirrorCnt;
		}
		return result;
	}
	
	static void init() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				for (int k=0; k<4; k++) {
					visited[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {	
				if(house[i][j] == '#') {
					for (int k=0; k<4; k++) {
						visited[i][j][k] = 0;
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
						if(house[nx][ny]=='*') continue;
						q.add(new Light(nx, ny, k, 0));
						visited[nx][ny][k] = 0;
					}
					return;
				}
			}
		}
	}
	
	static void changeDirection(Light now) {
		if(now.dir<2) {
			for (int i=2; i<4; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				if(house[nx][ny]=='*' || visited[nx][ny][i]<=now.mirrorCnt+1) continue;
				q.add(new Light(nx, ny, i, now.mirrorCnt+1));
				visited[nx][ny][i] = now.mirrorCnt+1;
			}
		} else {
			for (int i=0; i<2; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				if(house[nx][ny]=='*' || visited[nx][ny][i]<=now.mirrorCnt+1) continue;
				q.add(new Light(nx, ny, i, now.mirrorCnt+1));
				visited[nx][ny][i] = now.mirrorCnt+1;
			}
		}
	}
}
