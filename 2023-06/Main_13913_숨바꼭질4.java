import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Hop{
	int idx;
	int time;
	
	Hop(int idx, int time) {
		this.idx = idx;
		this.time = time;
	}
}

public class Main_13913_숨바꼭질4 {
	static int[] prev;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		prev = new int[Math.max(N, K)*2+1];
		Arrays.fill(prev, -1);
		
		int answer = bfs(N, K);
		Stack<Integer> route = trackRoute(N, K);
		
		StringBuilder sb = new StringBuilder();
		sb.append(answer +"\n");
		while(!route.isEmpty()) {
			sb.append(route.pop() + " ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}
	
	static int bfs(int start, int end) {
		Queue<Hop> q = new ArrayDeque<>();
		q.add(new Hop(start, 0));
		prev[start] = start;
		
		while(!q.isEmpty()) {
			Hop now = q.poll();
			if(now.idx==end) return now.time;
			int[] nextIdx = {now.idx*2, now.idx+1, now.idx-1}; 
			
			for (int i=0; i<3; i++) {
				int next = nextIdx[i];
				if(next<0 || next>=prev.length || prev[next]!=-1) continue;
				prev[next] = now.idx;
				q.add(new Hop(next, now.time+1));
			}
		}
		
		return -1;
	}
	
	static Stack<Integer> trackRoute(int start, int end) {
		Stack<Integer> result = new Stack<>();
		int now = end;
		
		while(now!=start) {
			result.add(now);
			now = prev[now];
		}
		result.add(now);
		
		return result;
		
	}
}
