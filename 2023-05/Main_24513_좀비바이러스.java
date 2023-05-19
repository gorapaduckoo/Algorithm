import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
	int x;
	int y;
	int virusType;
	public Virus(int x, int y, int virusType) {
		this.x = x;
		this.y = y;
		this.virusType = virusType;
	}
}

public class Main_24513_좀비바이러스 {
	static int N, M;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M][2];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		
		spreadVirus();
		int[] answer = countVirusTown();
		for (int i=0; i<answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}
	
	static void spreadVirus() {
		Queue<Virus> q = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(map[i][j][0]>0) q.add(new Virus(i,j,map[i][j][0]));
			}
		}
		
		int time = 1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i=0; i<size; i++) {
				Virus now = q.poll();
				if(map[now.x][now.y][0]==3) continue;
				for (int k=0; k<4; k++) {
					int nx = now.x+dx[k];
					int ny = now.y+dy[k];
					if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
					if(map[nx][ny][0]>0 && map[nx][ny][1]!=time) continue;
					if(map[nx][ny][0]<0) continue;
					if(map[nx][ny][0]!=now.virusType && map[nx][ny][0]<3 && map[nx][ny][1]==time) {
						map[nx][ny][0] += now.virusType;
					}
					if(map[nx][ny][0]==0) {
						map[nx][ny][0] += now.virusType;
						map[nx][ny][1] = time;
						q.add(new Virus(nx, ny, now.virusType));
					}
				}	
			}
			time++;
		}
	}
	
	static int[] countVirusTown() {
		int[] result = new int[3];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(map[i][j][0]>0) result[map[i][j][0]-1]++;
			}
		}
		return result;
	}
}
