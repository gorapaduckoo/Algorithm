import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int idx;
	int dist;
	public Node (int next, int dist) {
		this.idx = next;
		this.dist = dist;
	}
	
	public int compareTo(Node o) {
		return this.dist - o.dist;
	}
}

public class Main_14621_나만안되는연애 {
	static int N, M;
	static boolean[] isMale, visited;
	static List<Node>[] graph;
	static PriorityQueue<Node> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isMale = new boolean[N+1];
		visited = new boolean[N+1];
		graph = new List[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
			if(st.nextToken().equals("M")) {
				isMale[i] = true;
			}
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(isMale[u]==isMale[v]) continue;
			graph[u].add(new Node(v, d));
			graph[v].add(new Node(u, d));
		}
		
		pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		int sum = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(visited[now.idx]) continue;
			visited[now.idx] = true;
			sum += now.dist;
			
			for (Node next : graph[now.idx]) {
				if(visited[next.idx]) continue;
				pq.add(next);
			}
		}
		for (int i=1; i<=N; i++) {
			if(!visited[i]) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		System.out.println(sum);
		
	}
}
