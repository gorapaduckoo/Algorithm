import java.util.*;

class Work implements Comparable<Work> {
    String name;
    int startTime;
    int playTime;
    
    public Work(String name, int startTime, int playTime) {
        this.name = name;
        this.startTime = startTime;
        this.playTime = playTime;
    }
    
    public int compareTo(Work o) {
        return this.startTime - o.startTime;
    }
}

public class Programmers_과제진행하기 {
	int N;
    Queue<Work> todo;
    Stack<Work> playing;
    List<String> completed;
    
    public String[] solution(String[][] plans) {
        init(plans);
        
        int nowTime = todo.peek().startTime;
        playing.add(todo.poll());
        
        while(!todo.isEmpty()) {
            Work nowWork = playing.peek();
            Work nextWork = todo.peek();
            
            if(nowTime + nowWork.playTime <= nextWork.startTime) {
                Work completedWork = playing.pop();
                completed.add(completedWork.name);
                
                nowTime += completedWork.playTime;
            } else {
                nowWork.playTime -= (nextWork.startTime - nowTime);
                nowTime = nextWork.startTime;
                playing.add(todo.poll());
            }
            
            if(playing.isEmpty()) {
                nowTime = todo.peek().startTime;
                playing.add(todo.poll());
            }
        }
        
        while(!playing.isEmpty()) {
            completed.add(playing.pop().name);
        }
        
        String[] answer = completed.toArray(new String[0]);
        return answer;
    }
    
    void init(String[][] plans) {
        N = plans.length;
        todo = new ArrayDeque<>();
        playing = new Stack<>();
        completed = new ArrayList<>();
        
        Work[] works = new Work[N];
        for (int i=0; i<N; i++) {
            String name = plans[i][0];
            int startTime = convertTime(plans[i][1]);
            int playTime = Integer.parseInt(plans[i][2]);
            
            works[i] = new Work(name, startTime, playTime);
        }
        
        Arrays.sort(works);
        for (Work work : works) {
            todo.add(work);
        }
    }
    
    int convertTime(String time) {
        String[] str = time.split(":");
        
        int hour = Integer.parseInt(str[0]);
        int minute = Integer.parseInt(str[1]);
        
        return hour*60 + minute;
    }
}
