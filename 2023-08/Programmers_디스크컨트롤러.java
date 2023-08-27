import java.util.Arrays;
import java.util.PriorityQueue;

class Work implements Comparable<Work>{
    int startTime;
    int workTime;
    
    public int compareTo(Work o) {
        return this.workTime - o.workTime;
    }
    
    public Work(int startTime, int workTime) {
        this.startTime = startTime;
        this.workTime = workTime;
    }
}


public class Programmers_디스크컨트롤러 {
	PriorityQueue<Work> pq = new PriorityQueue<>();
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (o1, o2) -> {
            if(o1[0]==o2[0]) return o1[1]-o2[1];
            return o1[0]-o2[0];
        });
        
        pq.add(new Work(jobs[0][0], jobs[0][1]));
        int nowTime = jobs[0][0];
        int idx = 1;
        while(!pq.isEmpty()) {
            Work work = pq.poll();
            answer += (nowTime - work.startTime + work.workTime);
            nowTime += work.workTime;
            
            while(idx<jobs.length && jobs[idx][0]<=nowTime) {
                pq.add(new Work(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if(pq.isEmpty() && idx<jobs.length) {
                nowTime = jobs[idx][0];
                pq.add(new Work(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
        }
        
        return answer/jobs.length;
    }
}
