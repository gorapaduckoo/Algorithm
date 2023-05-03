import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2805_나무자르기 {
	static int N, M;
	static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int s=0, e=0;
		
		tree = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			e = Math.max(e, tree[i]);
		}
		
		while(s<e) {
			int mid = (s+e)/2;
			if(cuttingTree(mid)<M) {
				e = mid;
			} else {
				s = mid+1;
			}
		}
		System.out.println(s-1);
	}
	
	static long cuttingTree(int height) {
		long sum = 0;
		for (int i=0; i<N; i++) {
			if(tree[i]>height) sum += (tree[i]-height);
		}
		
		return sum;
	}
}
