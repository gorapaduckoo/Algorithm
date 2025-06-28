import java.util.*;

class Point {
    int x;
    int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Programmers_지게차와크레인 {
	char EMPTY = ' ';
    int N, M;
    int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
    boolean[][] isOutside;
    char[][] container;
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        init(storage);
        updateOutside();
        
        for (String request : requests) {
            if (request.length() == 1) {
                deliverByForklift(request.charAt(0));
            } else {
                deliverByCrane(request.charAt(0));
            }
            
            updateOutside();
        }
        
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (container[i][j] != EMPTY) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    void init(String[] storage) {
        N = storage.length;
        M = storage[0].length();
        
        isOutside = new boolean[N+2][M+2];
        
        container = new char[N+2][M+2];
        for (int i=0; i<N+2; i++) {
            Arrays.fill(container[i], EMPTY);
        }
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                container[i][j] = storage[i-1].charAt(j-1);
            }
        }
    }
    
    void deliverByForklift(char type) {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (container[i][j] == type && isNearOutside(i, j)) {
                    container[i][j] = EMPTY;
                }
            }
        }
    }
    
    void deliverByCrane(char type) {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (container[i][j] == type) {
                    container[i][j] = EMPTY;
                }
            }
        }
    }
    
    boolean isNearOutside(int x, int y) {
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx<0 || ny<0 || nx>=N+2 || ny>=M+2) continue;
            if (isOutside[nx][ny]) {
                return true;
            }
        }
        
        return false;
    }
    
    void updateOutside() {
        Queue<Point> q = new ArrayDeque<>();
        isOutside = new boolean[N+2][M+2];
        
        q.add(new Point(0, 0));
        isOutside[0][0] = true;
        
        while(!q.isEmpty()) {
            Point now = q.poll();
            
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx<0 || ny<0 || nx>=N+2 || ny>=M+2 || isOutside[nx][ny]) continue;
                if (container[nx][ny] != EMPTY) continue;
                
                q.add(new Point(nx, ny));
                isOutside[nx][ny] = true;
            }
        }
    }
}
