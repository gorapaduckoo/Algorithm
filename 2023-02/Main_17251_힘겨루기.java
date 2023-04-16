import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17251_힘겨루기 {
	static int N, maxPower = 0;
	static int[] power, sum;
	static Queue<Integer> red, blue;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		power = new int[N];
		red = new ArrayDeque<>();
		blue = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			power[i] = Integer.parseInt(st.nextToken());
			maxPower = Math.max(maxPower, power[i]);
		}
		
		if(power[0]==maxPower) red.add(0);
		for (int i=1; i<N; i++) {
			if(power[i]==maxPower) {
				blue.add(i);
			}
		}
		
		
		int R=0, B=0;
		for (int i=1; i<N; i++) {
			if(!red.isEmpty()&&blue.isEmpty()) {
				R++;
			} else if(red.isEmpty()&&!blue.isEmpty()) {
				B++;
			}
			
			if(!blue.isEmpty() && blue.peek()==i) red.add(blue.poll());
		}
		
		
		if(R==B) {
			System.out.println("X");
		} else if (R>B) {
			System.out.println("R");
		} else {
			System.out.println("B");
		}
	}
}
