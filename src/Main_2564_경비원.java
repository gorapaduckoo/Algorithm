import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Store {
	int dir;
	int x, y;
	public Store (int dir, int x, int y) {
		this.dir = dir;
		this.x = x;
		this.y = y;
	}
}
public class Main_2564_경비원 {
	static int N, M, K, DIR, X, Y;
	static Store[] stores;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		stores = new Store[K];
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int location = Integer.parseInt(st.nextToken());
			int x, y;
			if(dir==1) {
				x=0; y=location;
			} else if (dir==2) {
				x=N; y=location;
			} else if(dir==3) {
				x=location; y=0;
			} else {
				x=location; y=M;
			}
			stores[i] = new Store(dir, x, y);
		}
		st = new StringTokenizer(br.readLine());
		DIR = Integer.parseInt(st.nextToken());
		int location = Integer.parseInt(st.nextToken());
		if(DIR==1) {
			X=0; Y=location;
		} else if (DIR==2) {
			X=N; Y=location;
		} else if(DIR==3) {
			X=location; Y=0;
		} else {
			X=location; Y=M;
		}
		
		int ans = 0;
		for (int i=0; i<K; i++) {
			if(DIR+stores[i].dir==3) {
				int right = (M-Y)+(M-stores[i].y);
				int left = Y+stores[i].y;
				ans += (N+Math.min(right, left));
			} else if (DIR+stores[i].dir==7) {
				int up = (N-X)+(N-stores[i].x);
				int down = X+stores[i].x;
				ans += (M+Math.min(up, down));
			} else {
				ans += (Math.abs(X-stores[i].x)+Math.abs(Y-stores[i].y));
			}
		}
		
		System.out.println(ans);
	}
}
