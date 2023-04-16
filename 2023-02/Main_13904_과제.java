import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Homework implements Comparable<Homework> {
	int deadline;
	int score;

	public Homework(int deadline, int score) {
		this.deadline = deadline;
		this.score = score;
	}
	
	public int compareTo(Homework o) {
		return o.score - this.score;
	}
}

public class Main_13904_과제 {
	static int N, ans = 0;
	static List<Homework> list;
	static int[] day;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		day = new int[1001];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			list.add(new Homework(deadline, score));
		}
		
		Collections.sort(list);
		for (int i=0; i<list.size(); i++) {
			Homework now = list.get(i);
			for (int j=now.deadline; j>0; j--) {
				if(day[j] < now.score) {
					day[j] = now.score;
					break;
				}
			}
		}
		
		for (int i=1; i<=1000; i++) {
			ans += day[i];
		}
		System.out.println(ans);
	}
}
