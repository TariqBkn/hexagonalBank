import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Majority<T> {
    T findMajorityIn(List<T> inputList) {
        final int size = inputList.size();
        final int halfSize = size/2;
        final Map<T, Integer> countByElement = new HashMap<>();

        for(T object : inputList){
            int count = countByElement.getOrDefault(object,0)+1;
            if(count>halfSize){
                return object;
            }
            countByElement.put(object, count);
        }
        return null;
    }


    public static void main(String[] args) {
        List<Integer> integers = List.of(3, 3, 4, 2, 4, 2, 4, 4, 4);
        Majority<Integer> majority = new Majority<>();
        Integer result = majority.findMajorityIn(integers);
        System.out.println(result);
    }
}
