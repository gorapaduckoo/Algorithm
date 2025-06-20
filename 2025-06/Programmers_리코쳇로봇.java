import java.util.*;

class Point {
    int x;
    int y;
    int dist;
    
    public Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Programmers_리코쳇로봇 {
	int N, M;
    char WALL = 'D', START = 'R', END = 'G';
    
    int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    char[][] board;
    boolean[][] visited, visitable;
    
    Queue<Point> q;
    
    public int solution(String[] boards) {
        int answer = -1;
        init(boards);
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == START) {
                    q.add(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }
        
        while(!q.isEmpty()) {
            Point now = q.poll();
            
            if (board[now.x][now.y] == END) {
                answer = now.dist;
                break;
            }
            
            List<Point> nextPoints = findNextPoints(now);
            
            for (Point next : nextPoints) {
                q.add(next);
                visited[next.x][next.y] = true;
            }
        }
        
        
        return answer;
    }
    
    void init(String[] boards) {
        N = boards.length;
        M = boards[0].length();
        
        board = new char[N][M];
        visited = new boolean[N][M];
        visitable = new boolean[N][M];
        
        q = new ArrayDeque<>();
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                char c = boards[i].charAt(j);
                board[i][j] = c;
            }
        }
    }
    
    List<Point> findNextPoints(Point now) {
        List<Point> result = new ArrayList<>();
        
        for (int i=0; i<4; i++) {
            int nx = now.x+dx[i];
            int ny = now.y+dy[i];
            
            while (nx>=0 && ny>=0 && nx<N && ny<M && board[nx][ny]!=WALL) {
                nx += dx[i];
                ny += dy[i];
            }
            nx -= dx[i];
            ny -= dy[i];
            
            if (!visited[nx][ny]) {
                result.add(new Point(nx, ny, now.dist+1));
            }
        }
        
        return result;
    }
}
