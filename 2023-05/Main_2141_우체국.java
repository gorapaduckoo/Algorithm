import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Town implements Comparable<Town>{
	int location;
	int population;
	public Town(int location, int popularity) {
		this.location = location;
		this.population = popularity;
	}
	
	public int compareTo(Town o) {
		return this.location - o.location;
	}
}

public class Main_2141_우체국 {
	static int N;
	static long totalPopulation;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Town[] town = new Town[N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());	
			int loc = Integer.parseInt(st.nextToken());
			int pop = Integer.parseInt(st.nextToken());
			town[i] = new Town(loc, pop);
			totalPopulation += pop;
		}
		
		Arrays.sort(town);
		long mid = totalPopulation/2;
		if(totalPopulation%2==1) mid++;
		
		long sum = 0;
		for (int i=0; i<N; i++) {
			sum+=town[i].population;
			if(sum>=mid) {
				System.out.println(town[i].location);
				break;
			}
		}
	}
}
