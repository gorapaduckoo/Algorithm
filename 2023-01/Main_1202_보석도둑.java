import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Jewel implements Comparable<Jewel>{
	int weight;
	int cost;
	public Jewel(int weight, int cost) {
		this.weight = weight;
		this.cost = cost;
	}
	@Override
	public int compareTo(Jewel o) {
		return this.weight - o.weight;
	}
}
public class Main_1202_보석도둑 {
	static int N, K;
	static long ans = 0;
	static Jewel[] jewel;
	static int[] bag;
	static PriorityQueue<Jewel> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		jewel = new Jewel[N];
		bag = new int[K];
		pq = new PriorityQueue<>((o1, o2) -> {
			return o2.cost - o1.cost;
		});
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			jewel[i] = new Jewel(w,c);
		}
		for (int i=0; i<K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(jewel);
		Arrays.sort(bag);
		
		int idx = 0;
		for (int i=0; i<K; i++) {
			while(idx<N && jewel[idx].weight <= bag[i]) {
				pq.add(jewel[idx]);
				idx++;
			}
			if(pq.isEmpty()) continue;
			System.out.println("bag: " + bag[i]);
			System.out.println(pq.peek().cost);
			ans += pq.poll().cost;
		}
		
		System.out.println(ans);
	}
}
