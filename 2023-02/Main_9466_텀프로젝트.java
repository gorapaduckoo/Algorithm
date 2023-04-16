import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9466_텀프로젝트 {
	static int T, N;
	static int[] select;
	static boolean[] visited,checked,isTeam;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		while(T>0) {
			N = Integer.parseInt(br.readLine());
			select = new int[N+1];
			visited = new boolean[N+1];
			checked = new boolean[N+1];
			isTeam = new boolean[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) {
				select[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i=1; i<=N; i++) {
				if(!visited[i]) {
					dfs(i);
				}
			}
			
			int ans = 0;
			for (int i=1; i<=N; i++) {
				if(!isTeam[i]) ans++;
			}
			
			System.out.println(ans);
			T--;
		}
	}
	
	static public void dfs(int now) {
		visited[now] = true;
		int next = select[now];
		if(!visited[next]) {
			dfs(next);
		} else if(!checked[next]) {
			int idx = now;
			do {
				isTeam[idx] = true;
				idx = select[idx];
			} while(idx!=now);
		}
		checked[now] = true;
	}
}
