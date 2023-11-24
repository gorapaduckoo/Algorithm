import java.util.HashMap;
import java.util.Map;

public class Programmers_호텔방배정 {
	Map<Long, Long> bookedRoom = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        for (int i=0; i<room_number.length; i++) {
            long roomIdx = room_number[i];
            answer[i] = findNearRoom(roomIdx);
        }
        return answer;
    }
    
    long findNearRoom(long roomIdx) {
        if(!bookedRoom.containsKey(roomIdx)) {
            bookedRoom.put(roomIdx, roomIdx+1);
            return roomIdx;
        } else {
            long nearRoom = findNearRoom(bookedRoom.get(roomIdx));
            bookedRoom.put(roomIdx, nearRoom);
            return nearRoom;
        }
    }
}
