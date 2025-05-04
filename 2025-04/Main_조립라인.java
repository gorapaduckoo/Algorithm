import java.io.*;
import java.util.*;

public class Main_조립라인 {

    static final int A = 0, B = 1;
    static int N;
    static int[][] workTime, moveTo, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        workTime = new int[N+1][2];
        moveTo = new int[N+1][2];
        dp = new int[N+1][2];
        
        for (int i=1; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            workTime[i][A] = Integer.parseInt(st.nextToken());
            workTime[i][B] = Integer.parseInt(st.nextToken());
            moveTo[i+1][B] = Integer.parseInt(st.nextToken());
            moveTo[i+1][A] = Integer.parseInt(st.nextToken());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        workTime[N][A] = Integer.parseInt(st.nextToken());
        workTime[N][B] = Integer.parseInt(st.nextToken());

        for (int i=1; i<=N; i++) {
            dp[i][A] = Math.min(dp[i-1][A], dp[i-1][B]+moveTo[i][A]) + workTime[i][A];
            dp[i][B] = Math.min(dp[i-1][B], dp[i-1][A]+moveTo[i][B]) + workTime[i][B];
        }

        int answer = Math.min(dp[N][A], dp[N][B]);
        System.out.println(answer);
    }
}
