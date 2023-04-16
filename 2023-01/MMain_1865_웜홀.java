import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge {
	int next;
	int weight;
	public Edge(int next, int weight) {
		this.next = next;
		this.weight = weight;
	}
}

public class Main_1865_웜홀 {
	static int TC, N, M, W;
	static int MAX = 987654321;
	static int[] dist;
	static ArrayList<Edge>[] graph;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		
		while(TC>0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			dist = new int[N+1];
			Arrays.fill(dist, MAX);
			graph = new ArrayList[N+1];
			for (int i=1; i<=N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				graph[s].add(new Edge(e,w));
				graph[e].add(new Edge(s,w));
			}
			for (int i=0; i<W; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				graph[s].add(new Edge(e,-w));
			}
			
			if(bellmanFord()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			TC--;
		}
		
	}
	
	private static boolean bellmanFord() {
		dist[1] = 0;
		
		for (int i=1; i<N; i++) {
			for (int j=1; j<=N; j++) {
				for(Edge e : graph[j]) {
//					System.out.println("from: " + i + ", to: " + e.next + ", w: " + e.weight);
					if(dist[e.next] > dist[j]+e.weight) {
						dist[e.next] = dist[j]+e.weight;
					}
				}
			}
			
		}
		for (int i=1; i<=N; i++) {
			for (Edge e : graph[i]) {
				if(dist[e.next] > dist[i]+e.weight) {
					return true;
				}
			}
		}
		return false;
	}
}
