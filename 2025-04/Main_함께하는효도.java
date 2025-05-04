import java.io.*;
import java.util.*;

public class Main_함께하는효도 {

    static int N, M, answer = 0;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int[][] tree, friends;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[N][N];
        friends = new int[M][2];
        
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<2; j++) {
                friends[i][j] = Integer.parseInt(st.nextToken())-1;
            }
        }

        int x = friends[0][0];
        int y = friends[0][1];
        int temp = tree[x][y];
        tree[x][y] = 0;
        harvest(0, 0, temp, x, y);

        System.out.println(answer);
    }

    static void harvest(int friendsIdx, int time, int sum, int x, int y) {
        if (time == 3) {
            if (friendsIdx == M-1) {
                answer = Math.max(answer, sum);
                return;
            }

            int nx = friends[friendsIdx+1][0];
            int ny = friends[friendsIdx+1][1];
            int temp = tree[nx][ny];
            tree[nx][ny] = 0;
            harvest(friendsIdx+1, 0, sum+temp, nx, ny);
            tree[nx][ny] = temp;
            return;
        }

        for (int i=0; i<4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx<0 || ny<0 || nx>=N || ny>=N) continue;
            
            int temp = tree[nx][ny];
            tree[nx][ny] = 0;
            harvest(friendsIdx, time+1, sum+temp, nx, ny);
            tree[nx][ny] = temp;
        }
    }
}

