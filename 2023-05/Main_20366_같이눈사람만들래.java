import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Snowman implements Comparable<Snowman>{
	int idx1;
	int idx2;
	long size;
	
	public Snowman(int idx1, int idx2, long size) {
		this.idx1 = idx1;
		this.idx2 = idx2;
		this.size = size;
	}
	
	public int compareTo(Snowman o) {
		if(this.size < o.size) return -1;
		else if (this.size == o.size) return 0;
		else return 1;
	}
}

public class Main_20366_같이눈사람만들래 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] snowBall = new int[N];
		List<Snowman> snowman = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			snowBall[i] = Integer.parseInt(st.nextToken());
		}
		for (int i=0; i<N; i++) {
			for (int j=i+1; j<N; j++) {
				snowman.add(new Snowman(i, j, (long)snowBall[i]+(long)snowBall[j]));
			}
		}
		Collections.sort(snowman);
		
		long ans = Long.MAX_VALUE;
		for (int i=0; i<snowman.size()-1; i++) {
			if(!isDuplicated(snowman.get(i), snowman.get(i+1))) {
				ans = Math.min(ans, snowman.get(i+1).size - snowman.get(i).size);
			}
		}
		System.out.println(ans);
	}
	
	static boolean isDuplicated(Snowman snowman1, Snowman snowman2) {
		if(snowman1.idx1 == snowman2.idx1) return true;
		if(snowman1.idx1 == snowman2.idx2) return true;
		if(snowman1.idx2 == snowman2.idx1) return true;
		if(snowman1.idx2 == snowman2.idx2) return true;
		return false;
	}
}
