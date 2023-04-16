import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_4195_친구네트워크 {
	static int T, F, N = 200002;
	static int[] parent, size;
	static Map<String, Integer> map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		while (T>0) {
			StringBuilder sb = new StringBuilder();
			F = Integer.parseInt(br.readLine());
			parent = new int[N];
			size = new int[N];
			map = new HashMap<String, Integer>();
			for (int i=0; i<N; i++) {
				parent[i] = i;
				size[i] = 1;
			}
			int idx = 1;
			for (int i=0; i<F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken();
				String name2 = st.nextToken();
				if(!map.containsKey(name1)) map.put(name1, idx++);
				if(!map.containsKey(name2)) map.put(name2, idx++);
				
				int idx1 = map.get(name1);
				int idx2 = map.get(name2);
				union(idx1, idx2);
				sb.append(Math.max(size[find(idx1)], size[find(idx2)]));
				if(i!=F-1) {
					sb.append("\n");
				}
			}
			System.out.println(sb.toString());
			T--;
		}
	}
	public static int find(int x) {
		if(parent[x] == x) return x;
		return find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		if(size[x] < size[y]) {
			size[y] += size[x];
			parent[x] = y;
			size[x] = 0;
		} else {
			size[x] += size[y];
			parent[y] = x;
			size[y] = 0;
		}
	}
}
