import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17298_오큰수 {
	static int N;
	static int[] arr, ans;
	static Stack<Integer> s;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		ans = new int[N];
		Arrays.fill(ans, -1);
		s = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			while(!s.isEmpty() && (arr[s.peek()]<arr[i]) ) {
				ans[s.pop()] = arr[i];
			}
			s.add(i);
		}
		
		
		for (int i=0; i<N-1; i++) {
			sb.append(ans[i] + " ");
		}
		sb.append(ans[N-1]);
		System.out.println(sb.toString());
	}
}
