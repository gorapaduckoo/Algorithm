import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Player {
	int now;
	int roll;
	public Player (int now, int roll) {
		this.now = now;
		this.roll = roll;
	}
}

public class Main_16928_뱀과사다리게임 {
	static int N, M, MAX = 100;
	static int[] minRoll, map;
	static Queue<Player> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minRoll = new int[MAX+1];
		map = new int[MAX+1];
		q = new ArrayDeque<>();
		
		for (int i=0; i<=MAX; i++) {
			map[i] = i;
		}
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			map[s] = e;
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			map[s] = e;
		}
		
		Arrays.fill(minRoll, Integer.MAX_VALUE);
		minRoll[1] = 0;
		q.add(new Player(1,0));
		while(!q.isEmpty()) {
			Player p = q.poll();
			int now = p.now;
			int roll = p.roll;
			if(now == MAX) break;
			if(map[now] != now) {
				int next = map[now];
				if(minRoll[next] > roll) {
					q.add(new Player(next, roll));
					minRoll[next] = roll;
				}
			}
			
			for (int i=0; i<=6; i++) {
				if(now+i>MAX) continue;
				int next = map[now+i];
				if(next>MAX || minRoll[next] <= roll+1) continue;
				q.add(new Player(next, roll+1));
				minRoll[next] = roll+1;
			}
		}
		
		for (int i=1; i<=100; i++) {
			System.out.print(minRoll[i] + " ");
			if(i%10==0) System.out.println();
		}
		
		System.out.println(minRoll[MAX]);
	}
}
