import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class Main_2668_숫자고르기 {
	static int N, cnt=0;
	static int[] graph;
	static boolean[] isCycle;
	static Queue<Integer> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1];
		isCycle = new boolean[N+1];
		q = new ArrayDeque<Integer>();
		for (int i=1; i<=N; i++) {
			graph[i] = Integer.parseInt(br.readLine());
			if(graph[i]==i) {
				isCycle[i] = true;
			}
		}
		
		for (int i=1; i<=N; i++) {
			if(isCycle[i]) continue;
			dfs(i,i);
		}
		
		for (int i=1; i<=N; i++) {
			if(isCycle[i]) {
				cnt++;
			}
		}
		System.out.println(cnt);
		for (int i=1; i<=N; i++) {
			if(isCycle[i]) {
			System.out.println(i);
			}
		}
	}
	
	private static void dfs(int now, int start) {
//		System.out.println("dfs(" + now +", " + start + ")");
		if(q.contains(now)) {
			while(!q.isEmpty()&&q.peek()!=now) {
				q.poll();
			}
			while(!q.isEmpty()) {
				isCycle[q.poll()] = true;
			}
			return;
		}
		q.add(now);
		dfs(graph[now], start);
		
	}
}
