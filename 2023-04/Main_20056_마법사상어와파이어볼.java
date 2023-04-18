import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Fireball {
	int weight;
	int speed;
	int dir;
	public Fireball(int weight, int speed, int dir) {
		this.weight = weight;
		this.speed = speed;
		this.dir = dir;
	}
}

public class Main_20056_마법사상어와파이어볼 {
	static int N, M, K;
	static int[] dx = {-1,-1,0,1,1,1,0,-1}, dy = {0,1,1,1,0,-1,-1,-1};
	static boolean[][] isUpdated;
	static Queue<Fireball>[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayDeque[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = new ArrayDeque<>();
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[x][y].add(new Fireball(m,s,d));
		}
		
		while(K>0) {
			move();
			merge();
			K--;
		}
		
		int ans = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				while(!map[i][j].isEmpty()) {
					Fireball now = map[i][j].poll();
					ans += now.weight;
				}
			}
		}
		System.out.println(ans);
		
	}
	
	static void move() {
		Queue[][] newMap = new ArrayDeque[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				newMap[i][j] = new ArrayDeque<>();
			}
		}
		for (int x=0; x<N; x++) {
			for (int y=0; y<N; y++) {
				while(!map[x][y].isEmpty()) {
					Fireball now = map[x][y].poll();
					int nx = x + (dx[now.dir]*(now.speed%N));
					int ny = y + (dy[now.dir]*(now.speed%N));
					while(nx<0) nx+=N;
					while(nx>=N) nx-=N;
					while(ny<0) ny+=N;
					while(ny>=N) ny-=N;
					newMap[nx][ny].add(now);
				}
			}
		}
		map = newMap;
	}
	static void merge() {
		for (int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				if(map[x][y].size()>1) {
					int size = map[x][y].size();
					int newWeight = 0, newSpeed = 0;
					boolean isOdd = false, isEven = false;  
					while(!map[x][y].isEmpty()) {
						Fireball now = map[x][y].poll();
						newWeight+=now.weight;
						newSpeed+=now.speed;
						if(now.dir%2==0) isEven = true;
						else isOdd = true;
					}
					newWeight/=5;
					if(newWeight==0) continue;
					newSpeed/=size;
					
					if(isOdd && isEven) {
						for (int i=1; i<8; i+=2) {
							map[x][y].add(new Fireball(newWeight, newSpeed, i));
						}
					} else {
						for (int i=0; i<8; i+=2) {
							map[x][y].add(new Fireball(newWeight, newSpeed, i));
						}
					}
				}
			}
		}
	}
}
