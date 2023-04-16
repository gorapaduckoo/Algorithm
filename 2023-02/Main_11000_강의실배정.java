import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lesson implements Comparable<Lesson>{
	int start;
	int end;
	public Lesson(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Lesson o) {
		return this.start - o.start;
	}
}

public class Main_11000_강의실배정 {
	static int N, ans=0;
	static Lesson[] lesson;
	static PriorityQueue<Integer> pq;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		lesson = new Lesson[N];
		pq = new PriorityQueue<>();
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			lesson[i] = new Lesson(s,e);
		}
		Arrays.sort(lesson);
		
		for (int i=0; i<N; i++) {
			Lesson now = lesson[i];
			while(!pq.isEmpty() && pq.peek() <= now.start) {
				pq.poll();
			}
			pq.add(now.end);
			ans = Math.max(pq.size(), ans);
		}
		System.out.println(ans);
	}
}
