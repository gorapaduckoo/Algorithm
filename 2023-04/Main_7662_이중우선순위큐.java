import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Number {
	int n;
	int idx;
	public Number(int n, int idx) {
		this.n = n;
		this.idx = idx;
	}
}

public class Main_7662_이중우선순위큐 {
	static int T, N;
	static Map<Integer, Integer> map;
	static PriorityQueue<Number> minHeap, maxHeap;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(T>0) {
			N = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			
			// 테스트케이스 중 Integer.MAX_VALUE, Integer.MIN_VALUE가 존재
			minHeap = new PriorityQueue<>((o1, o2) -> {
				if(o1.n == o2.n) return 0;
				else if(o1.n < o2.n) return -1;
				else return 1;
			});
			maxHeap = new PriorityQueue<>((o1,o2)-> {
				if(o1.n == o2.n) return 0;
				else if (o2.n < o1.n) return -1;
				else return 1;
			});
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				int n = Integer.parseInt(st.nextToken());
				if(cmd.equals("I")) {
					add(n, i);
					continue;
				} 
				if(cmd.equals("D")) {
					if(n==-1) removeMin();
					else removeMax();
				}
			}
			if(!removeDummy()) sb.append("EMPTY\n");
			else {
				sb.append(maxHeap.poll().n + " " + minHeap.poll().n + '\n');
			}
			T--;
		}
		System.out.print(sb.toString());
	}
	
	static void add(int n, int idx) {
		map.put(idx, 2);
		minHeap.add(new Number(n, idx));
		maxHeap.add(new Number(n, idx));
	}
	
	static void removeMax() {
		if(map.isEmpty()) return;
		while(!maxHeap.isEmpty() && map.get(maxHeap.peek().idx)==1) {
			map.remove(maxHeap.poll().idx);
		}
		if(maxHeap.isEmpty()) return;
		int idx = maxHeap.peek().idx;
		map.put(idx, map.get(idx)-1);
		maxHeap.poll();
	}
	
	static void removeMin() {
		if(map.isEmpty()) return;
		while(!minHeap.isEmpty() && map.get(minHeap.peek().idx)==1) {
			map.remove(minHeap.poll().idx);
		}
		if(minHeap.isEmpty()) return;
		int idx = minHeap.peek().idx;
		map.put(idx, map.get(idx)-1);
		minHeap.poll();
	}
	
	static boolean removeDummy() {
		if(map.isEmpty()) return false;
		while(!maxHeap.isEmpty() && map.get(maxHeap.peek().idx)==1) {
			map.remove(maxHeap.poll().idx);
		}
		if(maxHeap.isEmpty()) return false;
		
		while(!minHeap.isEmpty() && map.get(minHeap.peek().idx)==1) {
			map.remove(minHeap.poll().idx);
		}
		if(minHeap.isEmpty()) return false;
		
		return true;
	}
}
