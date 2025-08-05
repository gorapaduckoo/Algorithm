import java.util.*;

class Point {
    int redX;
    int redY;
    int blueX;
    int blueY;
    int cnt;
    
    public Point(int redX, int redY, int blueX, int blueY, int cnt) {
        this.redX = redX;
        this.redY = redY;
        this.blueX = blueX;
        this.blueY = blueY;
        this.cnt = cnt;
    }
}

public class Programmers_수레움직이기 {
final int EMPTY = 0, RED_START = 1, BLUE_START = 2, RED_END = 3, BLUE_END = 4, WALL = 5;
    
    int N, M, answer = Integer.MAX_VALUE;
    int redStartX, redStartY, blueStartX, blueStartY;
    int redEndX, redEndY, blueEndX, blueEndY;
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    int[][] map;
    boolean[][][] visited;
    
    public int solution(int[][] maze) {        
        init(maze);
        
        visited[redStartX][redStartY][0] = true;
        visited[blueStartX][blueStartY][1] = true;
        move(redStartX, redStartY, blueStartX, blueStartY, 0, visited);
        
        if (answer == Integer.MAX_VALUE) {
            return 0;
        }
        return answer;
    }
    
    void init(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        
        map = new int[N][M];
        visited = new boolean[N][M][2];
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                map[i][j] = maze[i][j];
                
                if (map[i][j] == 1) {
                    redStartX = i;
                    redStartY = j;
                } else if (map[i][j] == 2) {
                    blueStartX = i;
                    blueStartY = j;
                } else if (map[i][j] == 3) {
                    redEndX = i;
                    redEndY = j;
                } else if (map[i][j] == 4) {
                    blueEndX = i;
                    blueEndY = j;
                }
                
            }
        }
    }
    
    void move(int redX, int redY, int blueX, int blueY, int dist, boolean[][][] visited) {
        
        if (isEnd(redX, redY, blueX, blueY)) {
            answer = Math.min(answer, dist);
        }
        for (int i=0; i<4; i++) {
            int redNX = redX;
            int redNY = redY;
            if (!(redX == redEndX && redY == redEndY)) {
                redNX += dx[i];
                redNY += dy[i];
            }
            
            for (int j=0; j<4; j++) {
                int blueNX = blueX;
                int blueNY = blueY;
                if (!(blueX == blueEndX && blueY == blueEndY)) {
                    blueNX += dx[j];
                    blueNY += dy[j];
                }
                
                if (isPossible(redX, redY, blueX, blueY, redNX, redNY, blueNX, blueNY) && dist+1 < answer) {
                    visited[redNX][redNY][0] = true;
                    visited[blueNX][blueNY][1] = true;
                    
                    move(redNX, redNY, blueNX, blueNY, dist+1, visited);
                    
                    visited[redNX][redNY][0] = false;
                    visited[blueNX][blueNY][1] = false;
                }
            }
        }
    }
    
    boolean isEnd(int redX, int redY, int blueX, int blueY) {
        if (map[redX][redY] == RED_END && map[blueX][blueY] == BLUE_END) {
            return true;
        }
        
        return false;
    }
    
    boolean isPossible(int redX, int redY, int blueX, int blueY, int redNX, int redNY, int blueNX, int blueNY) {
        if (redNX < 0 || redNY < 0 || redNX >= N || redNY >= M) return false;
        if (blueNX < 0 || blueNY < 0 || blueNX >= N || blueNY >= M) return false;

        if (redNX == blueNX && redNY == blueNY) return false;
        if (redNX == blueX && redNY == blueY && blueNX == redX && blueNY == redY) return false;
        
        if (map[redNX][redNY] == RED_END && blueNX == redNX && blueNY == redNY) return false;
        if (map[blueNX][blueNY] == BLUE_END && redNX == blueNX && redNY == blueNY) return false;
        if (map[redNX][redNY] == WALL || map[blueNX][blueNY] == WALL) return false;
        
        if (map[redNX][redNY] != RED_END && visited[redNX][redNY][0]) return false;
        if (map[blueNX][blueNY] != BLUE_END && visited[blueNX][blueNY][1]) return false;
        
        return true;
    }
}
