import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Log {
	int idx;
	int start;
	int end;
	int regionNum;
	Log(int idx, int start, int end) {
		this.idx = idx;
		this.start = start;
		this.end = end;
	}
}

public class Main_17619_개구리점프 {
	static int N, Q;
	static Log[] logs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		logs = new Log[N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			st.nextToken();
			logs[i] = new Log(i+1, s,e);
		}
		Arrays.sort(logs, new Comparator<Log>() {
			public int compare(Log o1, Log o2) {
				return o1.start - o2.start;
			}
		});
		
		int lastEnd = logs[0].start;
		int idx = 1;
		
		for (int i=0; i<N; i++) {
			if(lastEnd < logs[i].start) idx++;
			lastEnd = Math.max(lastEnd, logs[i].end);
			logs[i].regionNum = idx;
		}
		
		Arrays.sort(logs, new Comparator<Log>() {
			public int compare(Log o1, Log o2) {
				return o1.idx - o2.idx;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if(logs[j-1].regionNum == logs[k-1].regionNum) sb.append(1 +"\n");
			else sb.append(0+"\n");
		}
		
		System.out.println(sb.toString());
		
		
	}
}
