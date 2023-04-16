import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Problem implements Comparable<Problem> {
	int deadLine;
	int cupRamen;
	public Problem(int deadLine, int cupRamen) {
		this.deadLine = deadLine;
		this.cupRamen = cupRamen;
	}
	@Override
	public int compareTo(Problem o) {
		if(this.deadLine == o.deadLine) {
			return o.cupRamen - this.cupRamen;
		}
		return this.deadLine - o.deadLine;
	}
}

public class Main_1781_컵라면 {
	static int N;
	static Problem[] problem;
	static PriorityQueue<Integer> pq;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		problem = new Problem[N];
		pq = new PriorityQueue<>();
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadLine = Integer.parseInt(st.nextToken());
			int cupRamen = Integer.parseInt(st.nextToken());
			problem[i] = new Problem(deadLine, cupRamen);
		}
		
		Arrays.sort(problem);
		
		for (int i=0; i<N; i++) {
			if(pq.size() < problem[i].deadLine) {
				pq.add(problem[i].cupRamen);
			} else if(pq.peek() < problem[i].cupRamen) {
				pq.poll();
				pq.add(problem[i].cupRamen);
			}
		}
		
		int ans = 0;
		while(!pq.isEmpty()) {
			ans += pq.poll();
		}
		System.out.println(ans);
		
	}
}
