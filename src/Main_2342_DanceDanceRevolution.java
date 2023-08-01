import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2342_DanceDanceRevolution {
	static int[][] power = { { 0, 2, 2, 2, 2 },
						{ 0, 1, 3, 4, 3 },
						{ 0, 3, 1, 3, 4 },
						{ 0, 4, 3, 1, 3 },
						{ 0, 3, 4, 3, 1 }};
	static int MAX = 400_001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int[] inputs = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();	
		int[][][] dp = new int[inputs.length][5][5];
		
		for (int i=0; i<inputs.length; i++) {
			for (int j=0; j<5; j++) {
				Arrays.fill(dp[i][j], MAX);
			}
		}
		
		int firstStep = inputs[0];
		dp[0][firstStep][0] = 2;
		dp[0][0][firstStep] = 2;
		
		for (int i=1; i<inputs.length; i++) {
			int step = inputs[i];
			for (int l=0; l<5; l++) {
				for (int r=0; r<5; r++) {
					if(r!=step) dp[i][step][r] = Math.min(dp[i][step][r], dp[i-1][l][r]+power[l][step]);
					if(l!=step) dp[i][l][step] = Math.min(dp[i][l][step], dp[i-1][l][r]+power[r][step]);
				}
			}
		}
		
		int idx = inputs.length-1;
		int answer = Integer.MAX_VALUE;
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				answer = Math.min(answer, dp[idx][i][j]);
			}
		}
		System.out.println(answer);
		
	}
}
