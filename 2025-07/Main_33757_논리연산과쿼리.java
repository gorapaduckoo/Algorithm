import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_33757_논리연산과쿼리 {
	static int N, Q;
	static Map<Integer, List<Integer>> blocks;
	static Set<Integer> activeBlocks;
	static int[] num, blockIdx, activeCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		
		num = new int[N];
		blockIdx = new int[N];
		blocks = new HashMap<>();
		
		int idx = 0;
		blocks.put(idx, new ArrayList<>());
		
		for (int i=0; i<N; i++) {
			num[i] = str.charAt(2 * i) - '0';
			blocks.get(idx).add(i);
			blockIdx[i] = idx;
			
			if (i == N-1) break;
			if (str.charAt(2*i + 1) == '|') {
				idx++;
				blocks.put(idx, new ArrayList<>());
			}
			
		}
		
		activeCnt = new int[blocks.size()];
		activeBlocks = new HashSet<>();
		
		initBlockResult();
		
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<Q; i++) {
			int n = Integer.parseInt(st.nextToken()) - 1;
			
			if(num[n] == 1) {
				activeCnt[blockIdx[n]]--;
			} else {
				activeCnt[blockIdx[n]]++;
			}
			num[n] ^= 1;
			
			calculateBlockResult(blockIdx[n]);
			sb.append(calculateResult());
		}
		
		System.out.println(sb.toString());
	}
	
	public static void initBlockResult() {
		int idx = 0;
		int cnt = 0;
		for (int i=0; i<N; i++) {
			if (blockIdx[i] != idx) {
				activeCnt[idx] = cnt;
				calculateBlockResult(idx);
				idx++;
				cnt = 0;
			}
			
			if (num[i] == 1) {
				cnt++;
			}
		}
		
		activeCnt[idx] = cnt;
		calculateBlockResult(idx);
	}
	
	public static void calculateBlockResult(int i) {
		if (activeCnt[i] != blocks.get(i).size() && activeBlocks.contains(i)) {
			activeBlocks.remove(i);
		} else if (activeCnt[i] == blocks.get(i).size() && !activeBlocks.contains(i)) {
			activeBlocks.add(i);
		}
	}
	
	public static int calculateResult() {
		if (activeBlocks.size() > 0) {
			return 1;
		}
		
		return 0;
	}
}
