import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110_공유기설치 {
	static int N, C;
	static int[] house;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		house = new int[N];
		
		for (int i=0; i<N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		int start = 1;
		int end = house[N-1]-house[0]+1;
		while(start<end) {
			int mid = (start+end)/2;
			if(isPossible(mid)) {
				start = mid+1;
			} else {
				end = mid;
			}
		}
		System.out.println(start-1);
	}
	
	
	public static boolean isPossible(int dist) {
		int cnt = 1;
		int lastWifi = house[0];
		for (int i=1; i<N; i++) {
			if(house[i]-lastWifi>=dist) {
				cnt++;
				lastWifi = house[i];
			}
		}
		if(cnt>=C) return true;
		return false;
		
	}
}
