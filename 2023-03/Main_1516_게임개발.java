import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Building implements Comparable<Building>{
	int idx;
	int time;
	public Building(int idx, int time) {
		this.idx = idx;
		this.time = time;
	}
	@Override
	public int compareTo(Building o) {
		return this.time - o.time;
	}
}
public class Main_1516_게임개발 {
	static int N;
	static int[] time, in, ans;
	static List<Integer>[] graph;
	static PriorityQueue<Building> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		time = new int[N+1];
		in = new int[N+1];
		ans = new int[N+1];
		graph = new ArrayList[N+1];
		q = new PriorityQueue<>();
		
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			int num;
			while((num=Integer.parseInt(st.nextToken())) != -1) {
				graph[num].add(i);
				in[i]++;
			}
		}
		
		for (int i=1; i<=N; i++) {
			if(in[i]==0) {
				q.add(new Building(i, time[i]));
			}
		}
		
		
		while(!q.isEmpty()) {
			Building now = q.poll();
			ans[now.idx] = now.time;
			for (int next : graph[now.idx]) {
				in[next]--;
				if(in[next]==0) q.add(new Building(next, now.time+time[next]));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			sb.append(ans[i]);
			sb.append('\n');
		}
		sb.setLength(sb.length()-1);
		System.out.print(sb.toString());
	}
}
