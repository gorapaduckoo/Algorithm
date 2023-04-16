import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
	int x;
	int y;
	int z;
	int day;
	public Tomato(int x, int y, int z, int day) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.day = day;
	}
}

public class Main_7569_토마토 {
	static int N, M, H, ans = 0, cnt = 0;
	static int[] dx = {-1,1,0,0,0,0};
	static int[] dy = {0,0,-1,1,0,0};
	static int[] dz = {0,0,0,0,-1,1};
	static int[][][] tomato;
	static Queue<Tomato> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		tomato = new int[N][M][H];
		q = new ArrayDeque<Tomato>();
		
		for (int k=0; k<H; k++) {
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					tomato[i][j][k] = Integer.parseInt(st.nextToken());
					if(tomato[i][j][k] == 1) {
						q.add(new Tomato(i,j,k,0));
					} else if(tomato[i][j][k] == 0) {
						cnt++;
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			Tomato now = q.poll();
			ans = Math.max(ans, now.day);
			for (int i=0; i<6; i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				int nz = now.z+dz[i];
				if(nx<0 || ny<0 || nz<0 || nx>=N || ny>=M || nz>=H || tomato[nx][ny][nz]!=0) continue;
				tomato[nx][ny][nz] = 1;
				cnt--;
				q.add(new Tomato(nx,ny,nz, now.day+1));
			}
		}
		
		if(cnt>0) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
		
	}
}
