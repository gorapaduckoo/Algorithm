import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1082_방번호 {
	static int N, M, min = Integer.MAX_VALUE, minIdx = 0;
	static int[] price;
	static int[] cnt;
	static long ans = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		price = new int[N];
		cnt = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
			if(i>0 && min>=price[i]) {
				min = price[i];
				minIdx = i;
			}
		}
		M = Integer.parseInt(br.readLine());
		if(N==1 || price[minIdx] > M) {
			System.out.println(0);
			System.exit(0);
		}
		// 1~9중에 제일 싼 거 강매
		cnt[minIdx]++;
		M -= price[minIdx];
		
		if (min > price[0]) {
			min = price[0];
			minIdx = 0;
		}
		// 0~9중에 제일 싼 숫자 최대한 많이 사기
		while(M>=price[minIdx]) {
			cnt[minIdx]++;
			M -= price[minIdx];
		}
		
			// j: 더 높은 수, i: 현재 수
		for (int j=N-1; j>=0; j--) {
			for (int i=0; i<j; i++) {
				if(cnt[i]==0) continue;
				int diff = price[j]-price[i];
				if(diff<0) continue;
				while(M>=diff && cnt[i]>0) {
					cnt[i]--;
					cnt[j]++;
					M-=diff;
				}
			}
		}
		
		
		for (int i=N-1; i>=0; i--) {
			while(cnt[i]>0) {
				sb.append(i);
				cnt[i]--;
			}
		}
	
		System.out.println(sb.toString());
	}
}
