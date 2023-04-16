import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25511_값이k인트리노드의깊이 {
	static int N, K;
	static int[] parent, value;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		parent = new int[N];
		value = new int[N];
		for (int i=0; i<N; i++) {
			parent[i] = i;
		}
		
		for (int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			parent[node2] = node1;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int v = Integer.parseInt(st.nextToken());
			value[v] = i;
		}
		
		int now = value[K];
		int depth = 0;
		while(now != parent[now]) {
			now = parent[now];
			depth++;
		}
		System.out.println(depth);
	}
}
