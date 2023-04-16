import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15681_트리와쿼리 {
	static int N, R, Q;
	static int[] subtree;
	static List<Integer>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		subtree = new int[N+1];
		graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			graph[e].add(s);
		}
		
		subtree[R] = countNode(R, -1);
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<Q; i++) {
			int idx = Integer.parseInt(br.readLine());
			sb.append(subtree[idx]);
			sb.append('\n');
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}
	
	public static int countNode(int now, int parent) {
		if(subtree[now] != 0) return subtree[now];
		
		int result = 1;
		for(int nextNode : graph[now]) {
			if(nextNode==parent) continue;
			result += countNode(nextNode, now);
		}
		return subtree[now] = result;
	}
}
