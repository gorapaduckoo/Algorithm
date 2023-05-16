import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int idx;
	int depth;
	Node(int idx, int depth) {
		this.idx = idx;
		this.depth = depth;
	}
}

public class Main_11437_LCA {
	static int N, M, root = 1;
	static int[] parent, depth;
	static List<Integer>[] graph;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		depth = new int[N+1];
		graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			parent[i] = i;
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			graph[e].add(s);
		}
		
		
		getDepths();
		
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int result = getCommonAncestor(x, y);
			sb.append(result +"\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void getDepths() {
		Queue<Node> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		q.add(new Node(root, 0));
		visited[root] = true;
		while(!q.isEmpty()) {
			Node now = q.poll();
			depth[now.idx] = now.depth;
			for (int next : graph[now.idx]) {
				if(visited[next]) continue;
				q.add(new Node(next, now.depth+1));
				parent[next] = now.idx;
				visited[next] = true;
			}
		}
	}
	
	static int getCommonAncestor(int x, int y) {
		int depthX = depth[x];
		int depthY = depth[y];
		while(depthX > depthY) {
			x = parent[x];
			depthX--;
		}
		while(depthX < depthY) {
			y = parent[y];
			depthY--;
		}
		
		while(x!=y) {
			x = parent[x];
			y = parent[y];
		}
		
		return x;
	}
}
