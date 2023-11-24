public class Programmers_징검다리건너기 {
	public int solution(int[] stones, int k) {
        int answer = 0;
        
        
        int start = 0, end = 0;
        for (int i=0; i<stones.length; i++) {
            end = Math.max(stones[i], end);
        }
        
        while (start <= end) {
            int mid = (start+end)/2;
            if (canPass(mid, stones, k)) start = mid+1;
            else end = mid-1;
        }

        return start-1;
    }
    
    boolean canPass(int num, int[] stones, int k) {
        int cnt = 0;
        
        for (int i=0; i<stones.length; i++) {
            if(stones[i] < num) cnt++;
            else cnt = 0;
            if(cnt>=k) return false;
        }
        
        return true;
    }
}
