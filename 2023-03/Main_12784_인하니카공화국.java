import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Edge {
	int next;
	int cost;
	public Edge(int next, int cost) {
		this.next = next;
		this.cost = cost;
	}
}

public class Main_12784_인하니카공화국 {
	static int T, N, M;
	static List<Edge>[] graph;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		while(T>0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N+1];
			for (int i=1; i<=N; i++) {
				graph[i] = new ArrayList<Edge>();
			}
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				graph[s].add(new Edge(e, cost));
				graph[e].add(new Edge(s, cost));
			}
			
			int ans = 0;
			for (Edge e : graph[1]) {
				ans += cutBridge(e.next, 1, e.cost);
			}
			System.out.println(ans);
			T--;
		}
	}
	
	static int cutBridge(int now, int parent, int parentCutCost) {
		
		int childCutCost = 0;
		for (Edge e : graph[now]) {
			if(e.next == parent) continue;
			childCutCost += cutBridge(e.next, now, e.cost);
		}
		
		if(childCutCost == 0) return parentCutCost;
		return Math.min(childCutCost, parentCutCost);
	}
}
