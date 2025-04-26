import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_16193_차이를최대로2 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		Deque<Integer> num = new ArrayDeque<>();
 		Deque<Integer> dq = new ArrayDeque<>();
 		
		for (int i=0; i<arr.length; i++) {
			num.addLast(arr[i]);
		}
		
		dq.add(num.pollFirst());

 		while(!num.isEmpty()) {
 			int maxOnFirst = Math.abs(dq.peekFirst() - num.peekLast());
 			int maxOnLast = Math.abs(dq.peekLast() - num.peekLast());
 			int minOnFirst = Math.abs(dq.peekFirst() - num.peekFirst());
 			int minOnLast = Math.abs(dq.peekLast() - num.peekFirst());
 			
 			int maxValue = Math.max(Math.max(maxOnFirst, maxOnLast), Math.max(minOnFirst, minOnLast));
 			
 			if (maxValue == maxOnFirst) {
 				dq.addFirst(num.pollLast());
 			} else if (maxValue == maxOnLast) {
 				dq.addLast(num.pollLast());
 			} else if (maxValue == minOnFirst) {
 				dq.addFirst(num.pollFirst());
 			} else {
 				dq.addLast(num.pollFirst());
 			}
 		}
		
 		Integer[] arrangedArr = dq.toArray(new Integer[0]);
 		long answer = 0;
 		for (int i=0; i<arrangedArr.length-1 ; i++) {
 			answer += Math.abs(arrangedArr[i] - arrangedArr[i+1]);
 		}
 		
 		System.out.println(answer);
	}
}