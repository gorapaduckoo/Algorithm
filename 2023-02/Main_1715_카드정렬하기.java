import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1715_카드정렬하기 {
	static int N;
	static long ans = 0;
	static PriorityQueue<Long> pq;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		for (int i=0; i<N; i++) {
			pq.add(Long.parseLong(br.readLine()));
		}
		
		while(pq.size()>=2) {
			long tmp = pq.poll() + pq.poll();
			ans += tmp;
			pq.add(tmp);
		}
		
		System.out.println(ans);
	}
}
