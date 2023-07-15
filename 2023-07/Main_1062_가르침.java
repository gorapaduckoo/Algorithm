import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1062_가르침 {
	static int N, K, M = 26, answer = 0;
	static String[] words;
	static boolean isTeached[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(K<5) {
			System.out.println(0);
			System.exit(0);
		}
		
		words = new String[N];
		isTeached = new boolean[M];
		
		for (int i=0; i<N; i++) {
			words[i] = br.readLine();
			words[i].replace("anta", "");
			words[i].replace("tica", "");
		}
		
		teachVitalAlphabet();
		teach(0, 0);
		
		System.out.println(answer);
	}
	
	static void teach(int cnt, int start) {
		if(cnt==K-5) {
			answer = Math.max(answer, countReadableWord());
			return;
		}
		for (int i=start; i<M; i++) {
			if(isTeached[i]) continue;
			isTeached[i] = true;
			teach(cnt+1, i+1);
			isTeached[i] = false;
		}
	}
	
	static void teachVitalAlphabet() {
		isTeached['a'-'a'] = true;
		isTeached['c'-'a'] = true;
		isTeached['i'-'a'] = true;
		isTeached['n'-'a'] = true;
		isTeached['t'-'a'] = true;
	}
	
	static int countReadableWord() {
		int result = 0;
		
		for (int i=0; i<N; i++) {
			boolean readable = true;
			for (int j=0; j<words[i].length(); j++) {
				int idx = words[i].charAt(j) - 'a';
				if(!isTeached[idx]) {
					readable = false;
					break;
				}
			}
			if(readable) result++;
		}
		
		return result;
	}
}
