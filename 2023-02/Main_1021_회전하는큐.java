import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_1021_회전하는큐 {
	static int N, M;
	static LinkedList<Integer> deque;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		deque = new LinkedList<>();
		for (int i=1; i<=N; i++) {
			deque.add(i);
		}
		
		int cnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(deque.peek()==num) {
				deque.poll();
				continue;
			}
			
			int idx = deque.indexOf(num);
			if(idx <= (deque.size()/2)) {
				while(deque.peek()!=num) {
					deque.add(deque.pollFirst());
					cnt++;
				}
			} else {
				while(deque.peek()!=num) {
					deque.addFirst(deque.pollLast());
					cnt++;
				}
			}
			deque.poll();
		}
		
		System.out.println(cnt);
	}
}
