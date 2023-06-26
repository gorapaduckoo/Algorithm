import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_1744_수묶기 {
	static int N, zeroCnt = 0;
	static PriorityQueue<Integer> plusQ, minusQ;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		plusQ = new PriorityQueue<>(Comparator.reverseOrder());
		minusQ = new PriorityQueue<>();
		N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n>0) plusQ.add(n);
			else if (n==0) zeroCnt++;
			else minusQ.add(n);
		}
		
		int sum = 0;
		sum += combinePlusNumber();
		sum += combineMinusNumber();
		sum += combinePlusMinusNumber();
		
		System.out.println(sum);
	}
	
	static int combinePlusNumber() {
		int result = 0;
		while(plusQ.size()>=2) {
			int a = plusQ.poll();
			int b = plusQ.poll();
			if(a==1 || b==1) result += (a+b);
			else result += (a*b);
		}
		return result;
	}
	
	static int combineMinusNumber() {
		int result = 0;
		while(minusQ.size()>=2) {
			int a = minusQ.poll();
			int b = minusQ.poll();
			result += (a*b);
		}
		return result;
	}
	
	static int combinePlusMinusNumber() {
		int result = 0;
		if(!plusQ.isEmpty()) {
			int num = plusQ.poll();
			result += num;
		}
		if(!minusQ.isEmpty()) {
			int num = minusQ.poll();
			if(zeroCnt>0) zeroCnt--;
			else result += num;
		}
		
		return result;
	}
}
