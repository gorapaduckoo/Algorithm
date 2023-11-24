import java.util.HashSet;
import java.util.Set;

public class Programmers_불량사용자 {
	boolean[] isPicked;
    Set<String> answerList = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        
        isPicked = new boolean[user_id.length];
        for (int i=0; i<banned_id.length; i++) {
            banned_id[i] = banned_id[i].replaceAll("[*]", ".");
        }
        
        pick(0, user_id, banned_id);
        
        return answerList.size();
    }
    
    void pick(int cnt, String[] user_id, String[] banned_id) {
        if (cnt == banned_id.length) {
            answerList.add(getIdList(user_id));
            return;
        }
        for (int i=0; i<user_id.length; i++) {
            if (isPicked[i]) continue;
            if (!user_id[i].matches(banned_id[cnt])) continue;

            isPicked[i] = true;
            pick(cnt+1, user_id, banned_id);
            isPicked[i] = false;
        }
    }
    
    String getIdList(String[] user_id) {
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<user_id.length; i++) {
            if(isPicked[i]) sb.append("1");
            else sb.append("0");
        }
        
        return sb.toString();
    }
}
