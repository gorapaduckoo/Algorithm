import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_12789_도키도키간식드리미 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int prev = 0;
		for (int i=0; i<N; i++) {
			int now = Integer.parseInt(st.nextToken());
			while(!stack.isEmpty() && stack.peek()==prev+1) {
				stack.pop();
				prev++;
			}
			if(now==prev+1) {
				prev++;
				continue;
			}
			stack.add(now);
		}
		
		boolean isPossible = true;
		while(!stack.isEmpty()) {
			if(stack.peek() == prev+1) {
				stack.pop();
				prev++;
			} else {
				isPossible = false;
				break;
			}
		}
		
		if(isPossible) {
			System.out.println("Nice");
		} else {
			System.out.println("Sad");
		}
		
	}
}
