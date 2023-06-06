import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

class Subject {
	int idx;
	int credit;
	
	Subject(int idx, int credit) {
		this.idx = idx;
		this.credit = credit;
	}
}

public class Main_28087_고인물의졸업계획 {
	static int N, M;
	static Queue<Integer> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Subject[] subject = new Subject[M];
		for (int i=0; i<M; i++) {
			subject[i] = new Subject(i+1, Integer.parseInt(br.readLine()));
		}
		
		sortSubject(subject);
		selectSubject(subject);
		
		StringBuilder sb = new StringBuilder();
		sb.append(q.size()+"\n");
		while(!q.isEmpty()) {
			sb.append(q.poll()+"\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void sortSubject(Subject[] subject) {
		Arrays.sort(subject, new Comparator<Subject>() {
			public int compare(Subject o1, Subject o2) {
				return o2.credit - o1.credit;
			}
			
		});		
	}
	
	static void selectSubject(Subject[] subject) {
		q = new ArrayDeque<>();
		int sum = 0;
		
		for (int i=0; i<M; i++) {
			if(subject[i].credit>2*N) continue;
			if(sum<N) {
				q.add(subject[i].idx);
				sum+=subject[i].credit;
			}
			if(sum>2*N) {
				int idx = q.poll();
				sum-=subject[idx].credit;
			}
			if(N<=sum && sum<=2*N) return;
		}
	}
	
}
