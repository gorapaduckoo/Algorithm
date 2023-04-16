import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_12015_가장긴증가하는부분수열2 {
	static int N;
	static int[] arr;
	static List<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		list = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			find(Integer.parseInt(st.nextToken()));
		}
		System.out.println(list.size());
		
	}
	static void find(int n) {
		if(list.isEmpty()) {
			list.add(n);
			return;
		}
		int s=0;
		int e=list.size();
		while(s < e) {
			int mid = (s+e)/2;
			if(list.get(mid)>=n) {
				e = mid;
			} else {
				s = mid+1;
			}
		}
		if(s>=list.size()) {
			list.add(n);
		} else {
			list.set(s, n);
		}
	}
}
