import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Road {
	int end;
	int cost;
	public Road(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
}

public class Main_1219_오민식의고민 {
	static int N, M, S, E;
	static int INF = -Integer.MAX_VALUE;
	static int[] profit;
	static long[] money;
	static boolean[] visited;
	static List< List<Road> > graph;
	static Queue<Integer> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		money = new long[N];
		visited = new boolean[N];
		profit = new int[N];
		graph = new ArrayList<>();
		for (int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		q = new ArrayDeque<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Road(e,c));
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			profit[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(money, INF);
		money[S] = profit[S];
		for (int i=0; i<N; i++) {
			for (int start=0; start<N; start++) {
				for (Road r : graph.get(start)) {
					if(money[start]==INF || money[r.end] >= money[start]-r.cost+profit[r.end]) continue;
					if(i==N-1) {
						q.add(start);
						visited[start] = true;
						continue;
					}
					money[r.end] = money[start]-r.cost+profit[r.end];
	//				for (int k=0; k<N; k++) {
	//					System.out.print(money[k] + " ");
	//				}
	//				System.out.println();
				}
			}
		}
		
//		for (int i=0; i<N; i++) {
//			System.out.print(money[i] + " ");
//		}
//		System.out.println();
		
		
		if(money[E] == INF) {
			System.out.println("gg");
		} else if (BFS()) {
			System.out.println("Gee");
		} else {
			System.out.println(money[E]);
		}
		
	}
	
	static private boolean BFS() {
		while(!q.isEmpty()) {
			int now = q.poll();
			if(now==E) return true;
			for(Road r : graph.get(now)) {
				if(visited[r.end]) continue;
				visited[r.end] = true;
				q.add(r.end);
			}
		}
		return false;
	}
}
