import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int idx;
	int dist;
	public Node (int idx, int dist) {
		this.idx = idx;
		this.dist = dist;
	}
}

public class Main_1167_트리의지름 {
	static int N, ans = 0;
	static int maxDist = 0, maxNode = 1;
	static boolean[] visited;
	static List<Node>[] graph;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			graph[idx] = new ArrayList<Node>();
			int n;
			while((n=Integer.parseInt(st.nextToken())) != -1) {
				graph[idx].add(new Node(n, Integer.parseInt(st.nextToken())));
			}
		}
		
		findMaxNode(1, 0);
		Arrays.fill(visited, false);
		findDiameter(maxNode, 0);
		System.out.println(ans);
	}
	
	private static void findMaxNode(int now, int dist) {
		visited[now] = true;
		if(dist > maxDist) {
			maxDist = dist;
			maxNode = now;
		}
		for (Node next : graph[now]) {
			if(visited[next.idx]) continue;
			findMaxNode(next.idx, dist+next.dist);
		}
	}
	private static void findDiameter(int now, int dist) {
		visited[now] = true;
		ans = Math.max(dist, ans);
		for (Node next : graph[now]) {
			if(visited[next.idx]) continue;
			findDiameter(next.idx, dist+next.dist);
		}
	}
}
