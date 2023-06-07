import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_2812_크게만들기 {
	static int N, K, SIZE;
	static Deque<Integer> dq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		SIZE = N-K;
		dq = new ArrayDeque<>();
		
		String number = br.readLine();
		for (int i=0; i<number.length(); i++) {
			int num = number.charAt(i)-'0';
			
			while(K>0 && !dq.isEmpty() && dq.peekLast() < num) {
				dq.pollLast();
				K--;
			}
			dq.add(num);
		}
		while(dq.size()>SIZE) {
			dq.pollLast();
		}
		
		StringBuilder sb = new StringBuilder();
		while(!dq.isEmpty()) {
			sb.append(dq.pollFirst());
		}
		System.out.println(sb.toString());
	}
}
