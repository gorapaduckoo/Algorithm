import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class PostIt implements Comparable<PostIt> {
	int tableNum;
	int time;
	public PostIt(int num, int time) {
		this.tableNum = num;
		this.time = time;
	}
	
	public int compareTo(PostIt o) {
		if(this.time == o.time) {
			return this.tableNum - o.tableNum;
		}
		return this.time - o.time;
	}
}

public class Main_14612_김식당 {
	static int N, M;
	static List<PostIt> orderList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		orderList = new LinkedList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			if(order.equals("order")) {
				int n = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				orderList.add(new PostIt(n,t));
			} else if(order.equals("sort")) {
				Collections.sort(orderList);
			} else if(order.equals("complete")){
				int n = Integer.parseInt(st.nextToken());
				completeOrder(n);
			}
			
			printOrderList();
		}
	}
	
	static void completeOrder(int tableNum) {
		for (int i=0; i<orderList.size(); i++) {
			if(orderList.get(i).tableNum == tableNum) {
				orderList.remove(i);
				break;
			}
		}
	}
	
	static void printOrderList() {
		if(orderList.isEmpty()) {
			System.out.println("sleep");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (PostIt p : orderList) {
			sb.append(p.tableNum + " ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		return;
	}
}
