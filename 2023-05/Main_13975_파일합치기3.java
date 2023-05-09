import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13975_파일합치기3 {
	static int T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T>0) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
			
			long ans = 0;
			while(pq.size()>=2) {
				long file1 = pq.poll();
				long file2 = pq.poll();
				
				long newFile = file1+file2;
				ans += newFile;
				pq.add(newFile);
			}
			sb.append(ans + "\n");
			T--;
		}
		System.out.println(sb.toString());
	}
}
