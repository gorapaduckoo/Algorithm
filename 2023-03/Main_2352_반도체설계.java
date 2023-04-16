import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2352_반도체설계 {
	static List<Integer> list;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			find(Integer.parseInt(st.nextToken()));;
		}
		
		System.out.println(list.size());
	}
	
	static void find(int n) {
		if(list.isEmpty()) {
			list.add(n);
			return;
		}
		
		int s=0, e=list.size();
		while(s<e) {
			int mid = (s+e)/2;
			if(list.get(mid) > n) {
				e = mid;
			} else {
				s = mid+1;
			}
		}
		
		if(s >= list.size()) {
			list.add(n);
			return;
		}
		list.set(s, n);
	}
}
