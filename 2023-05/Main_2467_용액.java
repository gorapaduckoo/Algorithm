import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2467_용액 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] solution = new int[N];
		
		int bestSolution = Integer.MAX_VALUE;
		int[] answerIdx = new int[2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			solution[i] = Integer.parseInt(st.nextToken());
		}
		
		int left=0, right=N-1;
		while(left<right) {
			int sum = solution[left]+solution[right];
			if(Math.abs(bestSolution) > Math.abs(sum)) {
				bestSolution = sum;
				answerIdx[0] = left;
				answerIdx[1] = right;
			}
			if(sum<0) left++;
			else if(sum>0) right--;
			else break;
		}
		
		System.out.println(solution[answerIdx[0]] + " " + solution[answerIdx[1]]);
	}
}
