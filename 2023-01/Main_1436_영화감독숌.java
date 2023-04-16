import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1436_영화감독숌 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int ans = 666;
		int cnt = 1;
		while(cnt<N) {
			ans++;
			if(Integer.valueOf(ans
					).toString().contains("666")) {
				cnt++;
			}
		}
		System.out.println(ans);
	}
}

