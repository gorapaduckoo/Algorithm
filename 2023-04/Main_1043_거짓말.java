import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1043_거짓말 {
	static int N, M, K;
	static List<Integer>[] party, joinParty;
	static Queue<Integer> q;
	static boolean[] isKnow, visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		isKnow = new boolean[N+1];
		K = Integer.parseInt(st.nextToken());
		for (int i=0; i<K; i++) {
			int idx = Integer.parseInt(st.nextToken());
			isKnow[idx] = true;
		}
		
		party = new ArrayList[M];
		joinParty = new ArrayList[N+1];
		visited = new boolean[M];
		for (int i=1; i<=N; i++) {
			joinParty[i] = new ArrayList<>();
		}
		for (int i=0; i<M; i++) {
			party[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j=0; j<n; j++) {
				int person = Integer.parseInt(st.nextToken());
				party[i].add(person);
				joinParty[person].add(i);
			}
		}
	
		for (int i=1; i<=N; i++) {
			if(isKnow[i]) bfs(i);
		}
		
		int ans = 0;
		for (int i=0; i<M; i++) {
			boolean flag = true;
			for (int p : party[i]) {
				if(isKnow[p]) {
					flag = false;
					break;
				}
			}
			if(flag) ans++;
		}
		System.out.println(ans);
	}
	
	static void bfs(int person) {
		q = new ArrayDeque<>();
		for (int party : joinParty[person]) {
			q.add(party);
			visited[party] = true;
		}
		
		while(!q.isEmpty()) {
			int nowParty = q.poll();
			for (int nextPerson : party[nowParty]) {
				if(isKnow[nextPerson]) continue;
				isKnow[nextPerson] = true;
				for (int nextParty : joinParty[nextPerson]) {
					if(visited[nextParty]) continue;
					q.add(nextParty);
					visited[nextParty] = true;
				}
			}
		}
	}
}
