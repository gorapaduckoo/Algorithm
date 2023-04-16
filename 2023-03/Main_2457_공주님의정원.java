import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Flower implements Comparable<Flower> {
	int startDate;
	int endDate;
	public Flower(int startDate, int endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public int compareTo(Flower o) {
		if(this.startDate == o.startDate) return o.endDate - this.endDate;
		return this.startDate - o.startDate;
	}
}

public class Main_2457_공주님의정원 {
	static int N, START_DATE, END_DATE;
	static List<Flower> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		START_DATE = changeDate(3, 1);
		END_DATE = changeDate(11, 30);
		list = new ArrayList<>();
		int ans = 0;
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startDate = changeDate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			int endDate = changeDate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			if(endDate<=START_DATE || startDate>END_DATE) continue;
			list.add(new Flower(startDate, endDate));
		}
		if(list.isEmpty()) {
			System.out.println(0);
			System.exit(0);
		}
		
		Collections.sort(list);
		Flower now = new Flower(0, START_DATE);
		int idx = 0;
		while(now.endDate <= END_DATE) {
			boolean flag = false;
			int maxDate = now.endDate;
			for(int i=idx; i<list.size(); i++) {
				Flower next = list.get(i);
				if(next.startDate > now.endDate) break;
				if(next.endDate > maxDate) {
					idx = i+1;
					maxDate = next.endDate;
					flag = true;
				}	
			}
			
			if(!flag) {
				System.out.println(0);
				System.exit(0);
			}
			now = list.get(idx-1);
			ans++;
		}
		
		if(now.endDate > END_DATE) System.out.println(ans);
		else System.out.println(0);
	}
	
	static int changeDate(int month, int day) {
		int date = 0;
		date += (month*100);
		date += day;
		return date;
	}
}
