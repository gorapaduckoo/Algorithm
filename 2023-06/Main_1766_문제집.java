import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1766_문제집 {
	static int N, M;
	static List<Integer>[] graph;
	static int[] input;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			input[b]++;
			graph[a].add(b);
		}
		
		StringBuilder sb = new StringBuilder();
		Queue<Integer> order = searchGraph();
		while(!order.isEmpty()) {
			sb.append(order.poll() +" ");
		}
		System.out.println(sb.toString());
	}
	
	static void init() {
		graph = new ArrayList[N+1];
		input = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
	}
	
	static Queue<Integer> searchGraph() {
		Queue<Integer> result = new ArrayDeque<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i=1; i<=N; i++) {
			if(input[i]==0) pq.add(i);
		}
		
		while(!pq.isEmpty()) {
			int now = pq.poll();
			result.add(now);
			
			for (int next : graph[now]) {
				input[next]--;
				if(input[next]==0) pq.add(next);
			}
		}
		
		return result;
	}
}
