import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10775_공항 {
	static int G, P; 
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		parent = new int[G+1];
		for (int i=1; i<=G; i++) {
			parent[i] = i;
		}
		
		int cnt = 0;
		for (int i=0; i<P; i++) {
			int now = Integer.parseInt(br.readLine());
			now = find(now);
			if(now==0) break;
			
			union(now-1, now);
			cnt++;
		}
		System.out.println(cnt);
	}
	
	static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return;
		parent[y] = x;
	}
	
}
