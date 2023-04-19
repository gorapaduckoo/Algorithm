import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17144_미세먼지 {
	static int N, M, T;
	static int CLEANER_UP=-1, CLEANER_DOWN=-1;
	static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0}, clock = {3,1,2,0}, anticlock = {2,1,3,0};
	static int[][] room;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] == -1) {
					if(CLEANER_UP == -1) {
						CLEANER_UP = i;
					} else {
						CLEANER_DOWN = i;
					}
				}
			}
		}
		
		while(T>0) {
			spread();
			clockCirculate();
			anticlockCirculate();
			T--;
		}
		
		int ans = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				ans += room[i][j];
			}
		}
		System.out.println(ans);
		
	}
	
	static void spread() {
		int[][] newRoom = new int[N][M];
		
		for (int x=0; x<N; x++) {
			for (int y=0; y<M; y++) {
				if(room[x][y] > 0) {
					int num = 0;
					int dust = room[x][y]/5;
					for (int i=0; i<4; i++) {
						int nx = x+dx[i];
						int ny = y+dy[i];
						if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
						if((nx==CLEANER_UP||nx==CLEANER_DOWN)&&ny==0) continue;
						num++;
						newRoom[nx][ny] += dust;
					}
					newRoom[x][y] += (room[x][y]- dust*num);
				}
			}
		}
		room = newRoom;
	}
	
	static void clockCirculate() {
		int x = CLEANER_DOWN+1, y=0;
		int idx = 0;
		do {
			int i = clock[idx];
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<CLEANER_DOWN || ny<0 || nx>=N || ny>=M) {
				idx++;
				continue;
			}
			room[x][y] = room[nx][ny];
			x = nx;
			y = ny;
		}while (idx<4);
		
		room[CLEANER_DOWN][0] = 0;
	}
	
	static void anticlockCirculate() {
		int x = CLEANER_UP-1, y=0;
		int idx = 0;
		do {
			int i = anticlock[idx];
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0 || ny<0 || nx>CLEANER_UP || ny>=M) {
				idx++;
				continue;
			}
			room[x][y] = room[nx][ny];
			x = nx;
			y = ny;
		}while (idx<4);
		
		room[CLEANER_UP][0] = 0;
	}
}
