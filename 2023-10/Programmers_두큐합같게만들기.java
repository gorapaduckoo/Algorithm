import java.util.ArrayDeque;
import java.util.Queue;

class Programmers_두큐합같게만들기 {
    public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length;
        long sum1 = 0, sum2 = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        for (int i=0; i<N; i++) {
            sum1 += queue1[i];
            q1.add(queue1[i]);
            sum2 += queue2[i];
            q2.add(queue2[i]);
        }
        
        if((sum1+sum2)%2 != 0) return -1;
        
        int cnt = 0;
        while(sum1!=sum2 && cnt<=4*N) {
            if(sum1 > sum2) {
                int n = q1.poll();
                sum1 -= n;
                q2.add(n);
                sum2 += n;
            } else {
                int n = q2.poll();
                sum2 -= n;
                q1.add(n);
                sum1 += n;
            }
            cnt++;
        }
        
        if(cnt>4*N) return -1;
        return cnt;
    }
}