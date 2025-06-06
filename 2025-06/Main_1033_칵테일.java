import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1033_칵테일 {
	
	static class Ingredient {
		long weight;
		List<Ratio> next;
		
		public Ingredient(long weight) {
			this.weight = weight;
			this.next = new ArrayList<Ratio>();
		}
	}
	
	static class Ratio {
		int idx;
		int prevRatio;
		int nextRatio;
		
		public Ratio(int idx, int prevRatio, int nextRatio) {
			this.idx = idx;
			this.prevRatio = prevRatio;
			this.nextRatio = nextRatio;
		}
	}
	
	static int N;
	static Ingredient[] ingredients;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		ingredients = new Ingredient[N];
		visited = new boolean[N];
		for (int i=0; i<N; i++) {
			ingredients[i] = new Ingredient(1);
		}
		
		long initWeight = 1;
		for (int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int gcd = (int) getGCD(p, q);
			p /= gcd;
			q /= gcd;
			
			ingredients[a].next.add(new Ratio(b, p, q));
			ingredients[b].next.add(new Ratio(a, q, p));
			initWeight *= p;
			initWeight *= q;
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		ingredients[0].weight = initWeight;
		q.add(0);
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for (Ratio next : ingredients[now].next) {
				if (visited[next.idx]) continue;
				
				ingredients[next.idx].weight = ingredients[now].weight / next.prevRatio * next.nextRatio;
				q.add(next.idx);
				visited[next.idx] = true;
			}
		}
		
		long gcd = ingredients[0].weight;
		for (int i=1; i<N; i++) {
			gcd = getGCD(gcd, ingredients[i].weight);
		}
		
		for (int i=0; i<N; i++) {
			ingredients[i].weight /= gcd;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(ingredients[i].weight + " ");
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
	
	static long getGCD(long a, long b) {
		BigInteger a1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		
		return a1.gcd(b2).longValue();
	}
}
