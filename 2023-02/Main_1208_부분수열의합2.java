import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1208_부분수열의합2 {
	static int N, S;
	static long ans=0;
	static int[] arr;
	static List<Integer> left, right;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		left = new ArrayList<>();
		right = new ArrayList<>();
		
		select(N/2, 0, 0, left);
		select(N, N/2, 0, right);
		
		Collections.sort(left);
		Collections.sort(right, Collections.reverseOrder());
		
		
		int p1 = 0, p2=0;
		while(p1<left.size() && p2<right.size()) {
			int leftValue = left.get(p1);
			int rightValue = right.get(p2);
			int sum = leftValue + rightValue;
			if(sum==S) {
				long cnt1 = 0, cnt2 = 0;
				while(p1<left.size() && leftValue==left.get(p1)) {
					cnt1++;
					p1++;
				}
				while(p2<right.size() && rightValue==right.get(p2)) {
					cnt2++;
					p2++;
				}
				ans += (cnt1*cnt2);
			}
			if(sum>S) {
				p2++;
			} else if(sum<S) {
				p1++;
			}
		}
		
		if(S==0) {
			ans--;
		}
		
		System.out.println(ans);
		
	}
	
	public static void select(int end, int now, int sum, List<Integer> list) {
		if(now>=end) {
			list.add(sum);
			return;
		}
		select(end, now+1, sum+arr[now], list);
		select(end, now+1, sum, list);
	}
}
