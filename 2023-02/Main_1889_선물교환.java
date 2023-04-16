import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int idx;
	int input;
	public Node(int idx, int input) {
		this.idx = idx;
		this.input = input;
	}
	@Override
	public int compareTo(Node o) {
		return this.input - o.input;
	}
}

public class Main_1889_선물교환 {
	static int N, ans=0;
	static int[][] node;
	static int[] inCnt;
	static boolean[] removed;
	static PriorityQueue<Node> pq;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		node = new int[N+1][2];
		inCnt = new int[N+1];
		pq = new PriorityQueue<>();
		removed = new boolean[N+1];
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			node[i][0] = Integer.parseInt(st.nextToken()); 
			node[i][1] = Integer.parseInt(st.nextToken()); 
			inCnt[node[i][0]]++;
			inCnt[node[i][1]]++;
		}
		
		for (int i=1; i<=N; i++) {
			if(inCnt[i]<2) {
				pq.add(new Node(i, inCnt[i]));
				removed[i] = true;
			}
		}
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int idx = now.idx;
			int next1 = node[idx][0];
			int next2 = node[idx][1];
			
			if(--inCnt[next1]<2 && !removed[next1]) {
				pq.add(new Node(next1, inCnt[next1]));
				removed[next1] = true;
			}
			if(--inCnt[next2]<2 && !removed[next2]) {
				pq.add(new Node(next2, inCnt[next2]));
				removed[next2] =true;
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			if(inCnt[i]==0) continue;
			if(inCnt[i]==2) {
				ans++;
				sb.append(i + " ");
			}
		}
		if(ans==0) {
			System.out.println(ans);
		} else {
			sb.setLength(sb.length()-1);
			System.out.println(ans);
			System.out.println(sb.toString());
		}
	}
}
