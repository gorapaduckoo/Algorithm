import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9935_문자열폭발 {
	static String str, expStr;
	static int N, M;
	static Stack<Character> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		expStr = br.readLine();
		N = str.length();
		M = expStr.length();
		stack = new Stack<>();
		for (int i=0; i<N; i++) {
			stack.push(str.charAt(i));
			if(stack.peek()==expStr.charAt(M-1) && isBomb()) {
				for(int k=0; k<M; k++) {
					stack.pop();
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		if(sb.length()==0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb.reverse().toString());
		}
	}
	private static boolean isBomb() {
		if(stack.size()<M) return false;
		for(int i=0; i<M; i++) {
			if(stack.get(stack.size()-M+i) != expStr.charAt(i)) return false;
		}
		return true;
	}
}
