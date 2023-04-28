import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2623_음악프로그램 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] in = new int[N+1];
		List<Integer>[] graph = new ArrayList[N+1];
		Queue<Integer> ans = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int prev = 0, now;
			for (int j=0; j<k; j++) {
				now = Integer.parseInt(st.nextToken());
				if(j>0) {
					graph[prev].add(now);
					in[now]++;
				}
				prev = now;

			}
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			if(in[i]==0) q.add(i);
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			ans.add(now);
			for (int next : graph[now]) {
				in[next]--;
				if(in[next]==0) {
					q.add(next);
				}
			}
		}
		
		if(ans.size()<N) {
			System.out.println(0);
		} else {
			StringBuilder sb = new StringBuilder();
			while(!ans.isEmpty()) {
				sb.append(ans.poll());
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
	}
}
