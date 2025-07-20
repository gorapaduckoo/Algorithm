import java.util.*;

class Server {
    int returnTime;
    
    public Server(int returnTime) {
        this.returnTime = returnTime;
    }
}

public class Programmers_서버증설횟수 {
	int answer = 0;
    Queue<Server> servers = new ArrayDeque<>();
    
    public int solution(int[] players, int m, int k) {
        
        for (int i=0; i<players.length; i++) {
            
            returnServer(i);
            int serverNum = calculateNeedServer(players[i], m);
            addServer(serverNum, i, k);
        }
        
        return answer;
    }
    
    public void returnServer(int nowTime) {
        while(!servers.isEmpty() && servers.peek().returnTime == nowTime) {
            servers.poll();
        }
    }
    
    public int calculateNeedServer(int user, int unit) {
        if (user / unit > servers.size()) {
            return ((user / unit) - servers.size());
        }
        
        return 0;
    }
    public void addServer(int num, int nowTime, int runningTime) {
        answer += num;
        for (int i=0; i<num; i++) {
            servers.add(new Server(nowTime + runningTime));
        }
    }
}
