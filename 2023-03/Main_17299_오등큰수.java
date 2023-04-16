import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17299_오등큰수 {
	static int N;
	static int[] NGF, arr;
	static Map<Integer, Integer> map;
	static Stack<Integer> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		NGF = new int[N];
		arr = new int[N];
		map = new HashMap<>();
		stack = new Stack<>();
		
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			if(map.containsKey(num)) {
				map.put(num, map.get(num)+1);
			} else {
				map.put(num, 1);
			}
		}
	
		for (int i=N-1; i>=0; i--) {
			while(!stack.isEmpty()) {
				if(map.get(stack.peek()) > map.get(arr[i])) {
					NGF[i] = stack.peek();
					stack.add(arr[i]);
					break;
				}
				stack.pop();
			}
			if(stack.isEmpty()) {
				NGF[i] = -1;
				stack.add(arr[i]);
			}
	
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(NGF[i] + " ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
