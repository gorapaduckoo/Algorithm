import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6086_최대유량 {
	static int N, MAX = 52, START = 0, END = 'Z'-'A';
	static int[][] pipe = new int[MAX][MAX];
	static int[] prev;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = charToInt(st.nextToken().charAt(0));
			int b = charToInt(st.nextToken().charAt(0));
			int width = Integer.parseInt(st.nextToken());
			pipe[a][b] += width;
			pipe[b][a] += width;
		}
		
		prev = new int[MAX];
		int answer = 0;
		while(true) {
			Arrays.fill(prev, -1);
			
			bfs(START,END);
			if(prev[END]==-1) break;
			answer += calculateFlow(START,END);
		}
		
		System.out.println(answer);
	}
	
	static void bfs(int start, int end) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		prev[start] = start;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			if(now==end) break;
			for (int next=0; next<pipe[now].length; next++) {
				if(pipe[now][next]>0 && prev[next]==-1) {
					q.add(next);
					prev[next] = now;
				}
			}
		}
	}
	
	static int calculateFlow(int start, int end) {
		int water = Integer.MAX_VALUE;
		
		int now = end;
		while(now!=start) {
			water = Math.min(pipe[prev[now]][now], water);
			now = prev[now];
		}
		now = end;
		while(now!=start) {
			pipe[prev[now]][now]-=water;
			pipe[now][prev[now]]+=water;
			now = prev[now];
		}
		return water;
	}
	
	static int charToInt(char c) {
		if('A'<=c && c<='Z') return c-'A';
		if('a'<=c && c<='z') return c-'a'+26;
		return -1;
	}
}
