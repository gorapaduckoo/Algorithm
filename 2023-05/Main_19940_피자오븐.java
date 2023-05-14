import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_19940_피자오븐 {
	static int MAX = 60;
	static int[] button = {60, 10, -10, 1, -1};
	static int[][] minPush = new int[MAX*2][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		bfs();
		while(T>0) {
			int dest = Integer.parseInt(br.readLine());
			int[] answer = minPush[dest%60].clone();
			answer[0]+=(dest/60);
			
			for (int i=0; i<5; i++) {
				sb.append(answer[i] + " ");
			}
			sb.append("\n");
			T--;
		}
		System.out.println(sb.toString());
	}
	
	static void bfs() {		
		boolean[] visited = new boolean[MAX*2];
		Queue<Integer> q = new ArrayDeque<>();

		q.add(0);
		visited[0] = true;
		minPush[0] = new int[5];
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for (int i=4; i>=0; i--) {
				int next = now+button[i];
				if(next<0 || next>=MAX*2 || visited[next]) continue;
				q.add(next);
				visited[next] = true;
				minPush[next] = minPush[now].clone();
				minPush[next][i]++;
			}
		}
		
	}
}
