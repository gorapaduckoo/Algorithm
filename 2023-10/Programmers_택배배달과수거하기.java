import java.util.Stack;

class House {
    int dist;
    int box;
    public House(int dist, int box) {
        this.dist = dist;
        this.box = box;
    }
}

class Programmers_택배배달과수거하기 {
    int truckBox;
    Stack<House> deliverHouse = new Stack<>();
    Stack<House> pickupHouse = new Stack<>();
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        for (int i=0; i<n; i++) {
            if(deliveries[i]>0) deliverHouse.add(new House(i+1, deliveries[i]));
            if(pickups[i]>0) pickupHouse.add(new House(i+1, pickups[i]));
        }
        
        while(deliverHouse.size()>0 || pickupHouse.size()>0) {
            int maxDist = 0;
            
            int deliverCount = 0;
            while(deliverHouse.size()>0 && deliverCount<cap) {
                House house = deliverHouse.pop();
                deliverCount += house.box;
                if(deliverCount > cap) {
                    deliverHouse.add(new House(house.dist, deliverCount-cap));
                    deliverCount = cap;
                }
                maxDist = Math.max(maxDist, house.dist);
            }
            
            int pickupCount = 0;
            while(pickupHouse.size()>0 && pickupCount<cap) {
                House house = pickupHouse.pop();
                pickupCount += house.box;
                if(pickupCount > cap) {
                    pickupHouse.add(new House(house.dist, pickupCount-cap));
                    pickupCount = cap;
                }
                maxDist = Math.max(maxDist, house.dist);
            }
            
            answer += (maxDist*2);
        }
        
        return answer;
    }
}
