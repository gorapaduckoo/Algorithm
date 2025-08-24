import java.util.*;

class Point {
    int x;
    int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean isSame(Point o) {
        if (this.x == o.x && this.y == o.y) {
            return true;
        }
        
        return false;
    }
}

class Robot {
    boolean isArrived;
    Point now;
    Queue<Point> dests = new ArrayDeque<>();
    
    public Robot(Point now, Queue<Point> dests) {
        this.now = now;
        this.dests = dests;
    }
    
    public void move() {
        Point dest = dests.peek();
        if (now.isSame(dest)) {
            dests.poll();
            dest = dests.peek();
        }
        
        if (now.x == dest.x) {
            if (now.y < dest.y) now.y++;
            else now.y--;
        } else {
            if (now.x < dest.x) now.x++;
            else now.x--;
        }
    }
    
    public boolean checkArrived() {
        if (dests.size() == 1 && now.isSame(dests.peek())) {
            dests.poll();
        }
        
        if (dests.isEmpty()) {
            return true;
        }
        
        return false;
    }
}

public class Programmers_충돌위험찾기 {
	int N, K, arrived = 0;
    Robot[] robots;
    Point[] points;
    int[][] map;
    
    public int solution(int[][] pointArr, int[][] routes) {
        int answer = 0;
        
        init(pointArr, routes);
        
        for (int i=0; i<=100; i++) {
            for (int j=0; j<=100; j++) {
                if (map[i][j] >= 2) {
                    answer++;
                }
            }
        }
        
        while(arrived < N) {
            for (Robot robot : robots) {
                if (robot.checkArrived()) {
                    if (!robot.isArrived) {
                        map[robot.now.x][robot.now.y]--;
                        robot.isArrived = true;
                        arrived++;
                    }
                    continue;
                }
                
                map[robot.now.x][robot.now.y]--;
                robot.move();
                map[robot.now.x][robot.now.y]++;
            }
            
            for (int i=0; i<=100; i++) {
                for (int j=0; j<=100; j++) {
                    if (map[i][j] >= 2) {
                        answer++;
                    }
                }
            }
        }
        
        return answer;
    }
    
    void init(int[][] pointArr, int[][] routes) {
        N = routes.length;
        K = pointArr.length;
        
        robots = new Robot[N];
        points = new Point[K];
        
        map = new int[100+1][100+1];
        
        for (int i=0; i<K; i++) {
            points[i] = new Point(pointArr[i][0], pointArr[i][1]);
        }
        for (int i=0; i<N; i++) {
            Point start = points[routes[i][0]-1];
            Queue<Point> dests = new ArrayDeque();
            for (int j=1; j<routes[0].length; j++) {
                Point dest = points[routes[i][j]-1];
                dests.add(new Point(dest.x, dest.y));
            } 
            robots[i] = new Robot(new Point(start.x, start.y), dests);
            map[start.x][start.y]++;
        }
        
    }
}
