import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_1655_가운데를말해요 {
	static int N;
	static Queue<Integer> maxHeap, minHeap;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		minHeap = new PriorityQueue<>();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			if(i%2==0) {
				maxHeap.add(Integer.parseInt(br.readLine()));
			} else {
				minHeap.add(Integer.parseInt(br.readLine()));
			}
			
			if(!minHeap.isEmpty() && (maxHeap.peek()>minHeap.peek())) {
				int maxTop = maxHeap.poll();
				int minTop = minHeap.poll();
				maxHeap.add(minTop);
				minHeap.add(maxTop);
			}
			sb.append(maxHeap.peek());
			if(i<N-1) {
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}